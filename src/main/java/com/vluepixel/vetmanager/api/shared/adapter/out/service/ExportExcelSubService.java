package com.vluepixel.vetmanager.api.shared.adapter.out.service;

import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vluepixel.vetmanager.api.shared.adapter.out.service.auxiliar.Table;
import com.vluepixel.vetmanager.api.shared.application.mapper.BasicMapper;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.repository.BasicRepository;
import com.vluepixel.vetmanager.api.shared.domain.util.SpanishUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Export excel sub service.
 */
@Slf4j
public class ExportExcelSubService<E, DTO> implements ExportExcelPort<E, DTO> {
    private static final int COLUMN_WIDTH_OFFSET = 1000;
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";
    private static final String DEFAULT_FONT = "Arial";

    private final Map<CellStyle, CellStyle> dateStyleCache = new HashMap<>();
    private final Map<CellStyle, CellStyle> dateTimeStyleCache = new HashMap<>();

    private final BasicRepository<E, ?> basicRepository;
    private final BasicMapper<E, DTO> basicMapper;
    private final Class<DTO> clazz;

    public ExportExcelSubService(
            BasicRepository<E, ?> basicRepository,
            BasicMapper<E, DTO> basicMapper,
            Class<DTO> clazz) {
        this.basicRepository = basicRepository;
        this.basicMapper = basicMapper;
        this.clazz = clazz;
    }

    @Override
    public void export(OutputStream outputStream) {
        List<DTO> data = basicRepository.findAll().stream()
                .map(basicMapper::toDto)
                .toList();

        try (Workbook workbook = new XSSFWorkbook()) {
            Table table = new Table(clazz);
            table.process(data);

            initializeCellStyles(workbook);
            generateSheet(workbook, table, clazz);

            adjustColumnWidths(workbook);
            sortSheetsAlphabetically(workbook);
            moveMainSheetToFirstPosition(workbook, clazz);

            workbook.write(outputStream);
        } catch (Exception e) {
            log.error("Error exporting Excel", e);
            throw new InternalServerErrorException(e);
        }
    }

    private void generateSheet(Workbook workbook, Table table, Class<?> clazz) throws Exception {
        Sheet sheet = workbook.createSheet(generateSheetName(clazz));

        createHeaderRow(workbook, sheet, table.getHeaders());
        populateDataRows(workbook, sheet, table);

        for (Table innerTable : table.getNestedTables()) {
            generateSheet(workbook, innerTable, innerTable.getEntityType());
        }
    }

    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = createHeaderStyle(workbook);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(SpanishUtils.translate(headers[i]));
        }
    }

    private void populateDataRows(Workbook workbook, Sheet sheet, Table table) {
        List<Object[]> rows = table.getRows();
        int rowSize = table.getHeaders().length;

        CellStyle evenStyle = createEvenRowStyle(workbook);
        CellStyle startEvenStyle = createStartEvenRowStyle(workbook);
        CellStyle endEvenStyle = createEndEvenRowStyle(workbook);
        CellStyle oddStyle = createOddRowStyle(workbook);
        CellStyle startOddStyle = createStartOddRowStyle(workbook);
        CellStyle endOddStyle = createEndOddRowStyle(workbook);

        boolean isEven = false;
        Object id = null;

        for (int i = 0; i < rows.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowValues = rows.get(i);

            if (id == null || !id.equals(rowValues[0])) {
                id = rowValues[0];
                isEven = !isEven;
            }

            for (int j = 0; j < rowSize; j++) {
                Object value = rowValues[j];
                CellStyle style = determineCellStyle(j, rowSize, isEven,
                        startEvenStyle, endEvenStyle, evenStyle,
                        startOddStyle, endOddStyle, oddStyle);

                createCell(workbook, row, j, value, style);
            }
        }
    }

    private CellStyle determineCellStyle(
            int columnIndex,
            int rowSize,
            boolean isEven,
            CellStyle startEven,
            CellStyle endEven,
            CellStyle even,
            CellStyle startOdd,
            CellStyle endOdd,
            CellStyle odd) {
        if (columnIndex == 0) {
            return isEven ? startEven : startOdd;
        } else if (columnIndex == rowSize - 1) {
            return isEven ? endEven : endOdd;
        }
        return isEven ? even : odd;
    }

    private void createCell(Workbook workbook, Row row, int cellIndex, Object value, CellStyle style) {
        Cell cell = row.createCell(cellIndex);

        if (value == null) {
            cell.setCellValue("");
            cell.setCellStyle(style);
            return;
        }

        Class<?> valueType = value.getClass();

        // Dates
        if (value instanceof LocalDateTime) {
            handleDateTimeCell(workbook, cell, (LocalDateTime) value, style);
            return;
        } else if (value instanceof LocalDate) {
            handleDateCell(workbook, cell, (LocalDate) value, style);
            return;
        }

        if (valueType.isEnum()) {
            handleEnumCell(cell, value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }

        cell.setCellStyle(style);
    }

    private void handleEnumCell(Cell cell, Object value) {
        String enumName = ((Enum<?>) value).name().toLowerCase();
        cell.setCellValue(SpanishUtils.translate(enumName));
    }

    private void handleDateCell(Workbook workbook, Cell cell, LocalDate date, CellStyle baseStyle) {
        CellStyle dateStyle = dateStyleCache.computeIfAbsent(baseStyle, s -> {
            CellStyle newStyle = workbook.createCellStyle();
            newStyle.cloneStyleFrom(s);
            newStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_FORMAT));
            return newStyle;
        });

        cell.setCellValue(date);
        cell.setCellStyle(dateStyle);
    }

    private void handleDateTimeCell(Workbook workbook, Cell cell, LocalDateTime dateTime, CellStyle baseStyle) {
        CellStyle dateTimeStyle = dateTimeStyleCache.computeIfAbsent(baseStyle, s -> {
            CellStyle newStyle = workbook.createCellStyle();
            newStyle.cloneStyleFrom(s);
            newStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_TIME_FORMAT));
            return newStyle;
        });

        cell.setCellValue(dateTime);
        cell.setCellStyle(dateTimeStyle);
    }

    private void adjustColumnWidths(Workbook workbook) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            Row firstRow = sheet.getRow(0);
            if (firstRow == null)
                continue;

            int numColumns = firstRow.getLastCellNum();
            for (int col = 0; col < numColumns; col++) {
                Cell cell = firstRow.getCell(col, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (cell != null) {
                    sheet.autoSizeColumn(col);
                    int currentWidth = sheet.getColumnWidth(col);
                    sheet.setColumnWidth(col, currentWidth + COLUMN_WIDTH_OFFSET);
                }
            }
        }
    }

    private void sortSheetsAlphabetically(Workbook workbook) {
        List<String> sheetNames = new ArrayList<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetName(i));
        }

        Collections.sort(sheetNames);

        for (int i = 0; i < sheetNames.size(); i++) {
            String sheetName = sheetNames.get(i);
            int currentIndex = workbook.getSheetIndex(sheetName);
            if (currentIndex != i) {
                workbook.setSheetOrder(sheetName, i);
            }
        }
    }

    private void moveMainSheetToFirstPosition(Workbook workbook, Class<?> clazz) {
        String mainSheetName = generateSheetName(clazz);
        int mainSheetIndex = workbook.getSheetIndex(mainSheetName);

        if (mainSheetIndex > 0) {
            workbook.setSheetOrder(mainSheetName, 0);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontName(DEFAULT_FONT);
        style.setFont(font);

        applyFill(style, IndexedColors.SEA_GREEN);

        style.setTopBorderColor(IndexedColors.WHITE.getIndex());
        style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        style.setRightBorderColor(IndexedColors.WHITE.getIndex());

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    private CellStyle createEvenRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.WHITE);

        applyCommonBorders(style);

        return style;
    }

    private CellStyle createStartEvenRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.WHITE);

        applyCommonBorders(style);

        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createEndEvenRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.WHITE);

        applyCommonBorders(style);

        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.LIGHT_GREEN);

        applyCommonBorders(style);

        return style;
    }

    private CellStyle createStartOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.LIGHT_GREEN);

        applyCommonBorders(style);

        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }

    private CellStyle createEndOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        applyFill(style, IndexedColors.LIGHT_GREEN);

        applyCommonBorders(style);

        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private void applyCommonBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
    }

    private void applyFill(CellStyle style, IndexedColors color) {
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    private String generateSheetName(Class<?> clazz) {
        return SpanishUtils.translate(clazz.getSimpleName().replace("Dto", ""));
    }

    private void initializeCellStyles(Workbook workbook) {
        CellStyle baseDateStyle = workbook.createCellStyle();
        baseDateStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_FORMAT));

        CellStyle baseDateTimeStyle = workbook.createCellStyle();
        baseDateTimeStyle.setDataFormat(workbook.createDataFormat().getFormat(DATE_TIME_FORMAT));
    }
}