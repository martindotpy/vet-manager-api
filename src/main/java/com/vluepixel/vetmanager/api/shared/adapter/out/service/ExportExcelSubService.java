package com.vluepixel.vetmanager.api.shared.adapter.out.service;

import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
 * Export excel service.
 *
 * @param <E>   Entity type.
 * @param <DTO> DTO type.
 */
@Slf4j
public class ExportExcelSubService<E, DTO> implements ExportExcelPort<E, DTO> {
    private static final int COLUMN_WIDTH_OFFSET = 1000;
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";
    private CellStyle dateCellStyle;
    private CellStyle dateTimeCellStyle;

    private final BasicRepository<E, ?> basicRepository;
    private final BasicMapper<E, DTO> basicMapper;
    private final Class<DTO> clazz;

    @SuppressWarnings("unchecked")
    public ExportExcelSubService(BasicRepository<E, ?> basicRepository, BasicMapper<E, DTO> basicMapper) {
        this.basicRepository = basicRepository;
        this.basicMapper = basicMapper;
        this.clazz = (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
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

            // Adjust column width
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Row firstRow = sheet.getRow(0);
                Cell cell = firstRow.getCell(0);

                while (cell != null) {
                    sheet.autoSizeColumn(cell.getColumnIndex());
                    sheet.setColumnWidth(cell.getColumnIndex(),
                            sheet.getColumnWidth(cell.getColumnIndex()) + COLUMN_WIDTH_OFFSET);
                    cell = firstRow.getCell(cell.getColumnIndex() + 1);
                }
            }

            // Sort sheets alphabetically
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets - 1; i++) {
                for (int j = 0; j < numberOfSheets - i - 1; j++) {
                    Sheet sheet1 = workbook.getSheetAt(j);
                    Sheet sheet2 = workbook.getSheetAt(j + 1);
                    if (sheet1.getSheetName().compareTo(sheet2.getSheetName()) > 0) {
                        workbook.setSheetOrder(sheet2.getSheetName(), j);
                        workbook.setSheetOrder(sheet1.getSheetName(), j + 1);
                    }
                }
            }

            workbook.setSheetOrder(generateSheetName(clazz), 0);

            workbook.write(outputStream);
        } catch (Exception e) {
            throw new InternalServerErrorException(e);
        }
    }

    private void generateSheet(Workbook workbook, Table table, Class<?> clazz)
            throws IllegalArgumentException, IllegalAccessException {
        Sheet sheet = workbook.createSheet(generateSheetName(clazz));

        populateDataRows(workbook, sheet, table);
        createHeaderRow(workbook, sheet, table.getHeaders());

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

        Object id = null;
        boolean isEven = true;
        CellStyle evenStyle = createEvenRowStyle(workbook);
        CellStyle startEvenStyle = createStartEvenRowStyle(workbook);
        CellStyle endEvenStyle = createEndEvenRowStyle(workbook);
        CellStyle oddStyle = createOddRowStyle(workbook);
        CellStyle startOddStyle = createStartOddRowStyle(workbook);
        CellStyle endOddStyle = createEndOddRowStyle(workbook);

        for (int i = 0; i < rows.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object[] rowValues = rows.get(i);

            if (id == null || !id.equals(rowValues[0])) {
                id = rowValues[0];
                isEven = !isEven;
            }

            for (int j = 0; j < rowSize; j++) {
                Object value = rowValues[j];

                createCell(workbook, row, j, value);

                if (j == 0)
                    row.getCell(j).setCellStyle(isEven ? startEvenStyle : startOddStyle);
                else if (j == rowSize - 1)
                    row.getCell(j).setCellStyle(isEven ? endEvenStyle : endOddStyle);
                else
                    row.getCell(j).setCellStyle(isEven ? evenStyle : oddStyle);
            }
        }
    }

    private void createCell(Workbook workbook, Row row, int cellIndex, Object value) {
        Cell cell = row.createCell(cellIndex);

        if (value == null) {
            cell.setCellValue("");
            return;
        }

        Class<?> valueType = value.getClass();

        if (valueType.isEnum())
            handleEnumCell(cell, value);
        else if (value instanceof LocalDateTime localDateTimeValue)
            handleDateTimeCell(workbook, cell, localDateTimeValue);
        else if (value instanceof LocalDate localDateValue)
            handleDateCell(workbook, cell, localDateValue);
        else if (value instanceof Number numberValue)
            cell.setCellValue(numberValue.doubleValue());
        else if (value instanceof Boolean booleanValue)
            cell.setCellValue(booleanValue);
        else
            cell.setCellValue(value.toString());

    }

    private void handleEnumCell(Cell cell, Object value) {
        String enumName = ((Enum<?>) value).name().toLowerCase();
        cell.setCellValue(SpanishUtils.translate(enumName));
    }

    private void handleDateCell(Workbook workbook, Cell cell, LocalDate date) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(dateCellStyle);
        cell.setCellStyle(style);
        cell.setCellValue(date);
    }

    private void handleDateTimeCell(Workbook workbook, Cell cell, LocalDateTime dateTime) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(dateTimeCellStyle);
        cell.setCellStyle(style);
        cell.setCellValue(dateTime);
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontName("Aptos Narrow");
        style.setFont(font);

        style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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

        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createStartEvenRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createEndEvenRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createStartOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private CellStyle createEndOddRowStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private String generateSheetName(Class<?> clazz) {
        return SpanishUtils.translate(clazz.getSimpleName().replaceAll("(?i)dto", ""));
    }

    private void initializeCellStyles(Workbook workbook) {
        dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(DATE_FORMAT));

        dateTimeCellStyle = workbook.createCellStyle();
        dateTimeCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(DATE_TIME_FORMAT));
    }
}