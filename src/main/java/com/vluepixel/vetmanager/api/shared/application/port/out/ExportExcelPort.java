package com.vluepixel.vetmanager.api.shared.application.port.out;

import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

/**
 * Export excel port.
 *
 * @param <E> the type parameter.
 */
public interface ExportExcelPort<E, DTO> {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");

    /**
     * Export.
     *
     * @param outputStream the output stream to write the excel file.
     */
    void export(OutputStream outputStream);
}
