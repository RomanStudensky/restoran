package ru.pnzgu.restauran.util.excel;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.store.entity.Category;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.entity.Prodaza;
import ru.pnzgu.restauran.store.entity.SostavProd;
import ru.pnzgu.restauran.util.excel.enums.MenuCol;
import ru.pnzgu.restauran.util.excel.enums.SostavPostavCol;
import ru.pnzgu.restauran.util.excel.enums.SostavProdCol;
import ru.pnzgu.restauran.util.excel.enums.SpistProdCol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ExcelExportUtil {

    public static ByteArrayOutputStream createPostavExcelReport(PostavshikDTO postavshikDTO, List<NakladDTO> nakladList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        createPostavReport(postavshikDTO, nakladList, workbook, cellStyle);

        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    public static ByteArrayOutputStream createSpisCurrentDateExcelReport(List<AktDTO> aktList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        createSpisReport(aktList, workbook, cellStyle);

        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    public static ByteArrayOutputStream createProdazaExcelReport(Prodaza prod, LocalDate date, List<SostavProd> sostavProdList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        createProductsReport(prod, date, sostavProdList, workbook, cellStyle);

        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    public static ByteArrayOutputStream createOrderExelDocument(Prodaza prod, LocalDate date, List<SostavProd> sostavProdList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

//        createOrderReport(prod, date, sostavProdList, workbook, cellStyle);

        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    public static ByteArrayOutputStream createMenuExcelDocument(List<Category> menu) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        menu.forEach((category) -> createMenuDocument(category, workbook));

        workbook.write(outputStream);
        outputStream.close();
        return outputStream;
    }

    private static void createMenuDocument(Category category, XSSFWorkbook workbook) {

        Font fontTitle = workbook.createFont();
        fontTitle.setFontHeightInPoints((short)18);
        fontTitle.setFontName("Courier New");

        Font fontBludo = workbook.createFont();
        fontBludo.setFontHeightInPoints((short)14);
        fontBludo.setFontName("Courier New");

        Font fontSostav = workbook.createFont();
        fontSostav.setFontHeightInPoints((short)10);
        fontSostav.setFontName("Courier New");
        fontSostav.setItalic(true);

        XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setFont(fontTitle);

        XSSFCellStyle cellStyleBludo = workbook.createCellStyle();
        cellStyleBludo.setFont(fontBludo);

        XSSFCellStyle cellStyleSostav = workbook.createCellStyle();
        cellStyleSostav.setFont(fontSostav);

        Sheet sheet = workbook.createSheet(category.getNameCat());
        sheet.autoSizeColumn(1);

        int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        row.setHeightInPoints(30.0f);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, MenuCol.LENGTH - 1));

        createCellWithBorder(cellStyleTitle, row, sheet, 0, category.getNameCat());

        rowNum++;
        row = sheet.createRow(rowNum);
        row.setHeightInPoints(25.f);
        createCellWithoutBorder(cellStyleBludo, row, sheet, MenuCol.COLUMN_WEIGHT.getColNum(), MenuCol.COLUMN_WEIGHT.getColName());

        cellStyleBludo.setAlignment(HorizontalAlignment.LEFT);
        for (Menu menu : category.getMenuList()) {
            rowNum++;
            row = sheet.createRow(rowNum);
            row.setHeightInPoints(18.0f);
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, MenuCol.LENGTH - 3));

            cellStyleBludo.setFont(fontBludo);
            createCellWithoutBorder(cellStyleBludo, row, sheet, MenuCol.COLUMN_NAME.getColNum(), menu.getBludo());
            createCellWithoutBorder(cellStyleBludo, row, sheet, MenuCol.COLUMN_WEIGHT.getColNum(), String.valueOf(menu.getWeight()));
            createCellWithoutBorder(cellStyleBludo, row, sheet, MenuCol.COLUMN_PRICE.getColNum(), String.valueOf(menu.getPrice()) + " ₽");
            rowNum++;
            row = sheet.createRow(rowNum);
            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, MenuCol.LENGTH - 3));


            createCellWithoutBorder(cellStyleSostav, row, sheet, MenuCol.COLUMN_SOSATAV.getColNum(),
                    menu
                            .getSostavList()
                            .stream()
                            .map(sostavBludoDTO -> sostavBludoDTO.getProduct().getNameProd())
                            .collect(Collectors.joining(", "))
            );
        }
    }

    private static void createPostavReport(PostavshikDTO postavshikDTO, List<NakladDTO> nakladList, XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        Sheet sheet = workbook.createSheet(String.format("Отчёт по поставкам продуктов от поставщика: %s", postavshikDTO.getNamePost()));
        sheet.autoSizeColumn(1);

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.setHeightInPoints(30.0f);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, SostavPostavCol.LENGTH - 1));
        setMergedCellBorders(new CellRangeAddress(0, 0, 0, SostavPostavCol.LENGTH - 1), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, String.format("Отчёт по поставкам продуктов от поставщика: %s", postavshikDTO.getNamePost()));

        for (NakladDTO nakladDTO : nakladList) {
            if (nakladDTO.getSostav().isEmpty()) {
                continue;
            }

            rowNum+=2;
            row = sheet.createRow(rowNum);
            row.setHeightInPoints(30.0f);

            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SostavPostavCol.LENGTH - 1));
            setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SostavPostavCol.LENGTH - 1), sheet);

            createCellWithBorder(cellStyle, row, sheet, 0, String.format("Товарная накладная: №%s от %s", nakladDTO.getId(), nakladDTO.getDateNaklad()));

            // Шапка
            rowNum++;
            row = sheet.createRow(rowNum);
            row.setHeightInPoints(25.0f);
            for (int colNum = 0; colNum < SostavPostavCol.LENGTH; colNum++) {
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.values()[colNum].getColNum(), SostavPostavCol.values()[colNum].getColName());
            }
            // Тело
            for (SostavPostavDTO sostavPostavDTO : nakladDTO.getSostav()) {
                rowNum++;
                row = sheet.createRow(rowNum);
                row.setHeightInPoints(18.0f);
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.COLUMN_NUMBER.getColNum(), String.valueOf(sostavPostavDTO.getId()));
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.COLUMN_PROD_NAME.getColNum(), sostavPostavDTO.getProduct().getNameProd());
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.COLUMN_QUANTITY.getColNum(), String.valueOf(sostavPostavDTO.getQuantity()));
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.COLUMN_PRICE.getColNum(), String.valueOf(sostavPostavDTO.getPrice()));
                createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.COLUMN_SUMMA.getColNum(), String.valueOf(sostavPostavDTO.getSumma()));
            }
            Long summ = nakladDTO
                    .getSostav()
                    .stream()
                    .map(SostavPostavDTO::getSumma)
                    .reduce(0L, Long::sum);

            rowNum++;
            row = sheet.createRow(rowNum);
            row.setHeightInPoints(25.0f);

            sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SostavPostavCol.LENGTH - 2));
            setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SostavPostavCol.LENGTH - 2), sheet);

            createCellWithBorder(cellStyle, row, sheet, 0, "Итого:");
            createCellWithBorder(cellStyle, row, sheet, SostavPostavCol.LENGTH - 1, String.valueOf(summ));



            rowNum++;
        }

    }

    private static void createSpisReport(List<AktDTO> sostavAktList, XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        Sheet sheet = workbook.createSheet("Отчёт по списанным продуктам");
        sheet.autoSizeColumn(1);

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.setHeightInPoints(30.0f);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 1));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 1), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, "Отчёт по списанным продуктам");

        rowNum++;
        row = sheet.createRow(rowNum);
        row.setHeightInPoints(30.0f);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 1));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 1), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, String.format("Дата формирования отчёта: %s", LocalDate.now()));

        // Шапка
        rowNum++;
        row = sheet.createRow(rowNum);
        row.setHeightInPoints(25.0f);
        for (int colNum = 0; colNum < SpistProdCol.LENGTH; colNum++) {
            createCellWithBorder(cellStyle, row, sheet, SpistProdCol.values()[colNum].getColNum(), SpistProdCol.values()[colNum].getColName());
        }

        for (AktDTO aktDTO : sostavAktList) {

            if (aktDTO.getSpisProducts().isEmpty()) {
                continue;
            }

            // Тело
            for (SostavAktDTO sostavAktDTO : aktDTO.getSpisProducts()) {
                rowNum++;
                row = sheet.createRow(rowNum);
                row.setHeightInPoints(18.0f);
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_NUMBER.getColNum(), String.valueOf(sostavAktDTO.getId()));
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_PROD_NAME.getColNum(), sostavAktDTO.getProduct().getNameProd());
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_QUANTITY.getColNum(), String.valueOf(sostavAktDTO.getQuantity()));
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_REASON.getColNum(), String.valueOf(sostavAktDTO.getReason()));
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_DATE.getColNum(), String.valueOf(aktDTO.getDateAkt()));
                createCellWithBorder(cellStyle, row, sheet, SpistProdCol.COLUMN_SUMMA.getColNum(), String.valueOf(sostavAktDTO.getSumma()));
            }
        }
        rowNum++;
        Double summ = sostavAktList
                .stream()
                .map(akt -> akt
                        .getSpisProducts()
                        .stream()
                        .map(SostavAktDTO::getSumma)
                        .reduce(0.0D, Double::sum))
                .reduce(0.0D, Double::sum);

        row = sheet.createRow(rowNum);
        row.setHeightInPoints(25.0f);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 2));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SpistProdCol.LENGTH - 2), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, "Итого:");
        createCellWithBorder(cellStyle, row, sheet, SpistProdCol.LENGTH - 1, String.valueOf(summ));
    }

    private static void createProductsReport(Prodaza prodaza, LocalDate date, List<SostavProd> sostavProdList, XSSFWorkbook order, XSSFCellStyle cellStyle) {
        Sheet sheet = order.createSheet("Отчёт о продажах в баре");
        sheet.autoSizeColumn(1);

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 1));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 1), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, String.format("Дата формирования отчёта: %s", date));

        rowNum++;
        row = sheet.createRow(rowNum);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 1));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 1), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, String.format("Официант: %s", prodaza.getUser().getFio()));

        // Шапка
        rowNum++;
        row = sheet.createRow(rowNum);
        row.setHeightInPoints(25.0f);
        for (int colNum = 0; colNum < SostavProdCol.LENGTH; colNum++) {
            createCellWithBorder(cellStyle, row, sheet, SostavProdCol.values()[colNum].getColNum(), SpistProdCol.values()[colNum].getColName());
        }

        for (SostavProd sostavProd : sostavProdList) {
                rowNum++;
                row = sheet.createRow(rowNum);
                row.setHeightInPoints(18.0f);
                createCellWithBorder(cellStyle, row, sheet, SostavProdCol.COLUMN_NUMBER.getColNum(), String.valueOf(sostavProd.getId()));
                createCellWithBorder(cellStyle, row, sheet, SostavProdCol.COLUMN_DRINK_NAME.getColNum(), sostavProd.getBludo().getBludo());
                createCellWithBorder(cellStyle, row, sheet, SostavProdCol.COLUMN_QUANTITY.getColNum(), sostavProd.getQuantity().toString());
                createCellWithBorder(cellStyle, row, sheet, SostavProdCol.COLUMN_PRICE.getColNum(), sostavProd.getBludo().getPrice().toString());
                createCellWithBorder(cellStyle, row, sheet, SostavProdCol.COLUMN_SUMMA.getColNum(), sostavProd.getSumma().toString());

        }
        rowNum++;
        Double summ = sostavProdList
                .stream()
                .map(SostavProd::getSumma)
                .reduce(0.0D, Double::sum);

        row = sheet.createRow(rowNum);
        row.setHeightInPoints(25.0f);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 2));
        setMergedCellBorders(new CellRangeAddress(rowNum, rowNum, 0, SostavProdCol.LENGTH - 2), sheet);

        createCellWithBorder(cellStyle, row, sheet, 0, "Итого:");
        createCellWithBorder(cellStyle, row, sheet, SostavProdCol.LENGTH - 1, String.valueOf(summ));
    }

    private static void createCellWithBorder(XSSFCellStyle cellStyle, Row row, Sheet sheet, int colNumber, String cellValue) {
        Cell cell = row.createCell(colNumber);
        cell.setCellStyle(getCellStyleWithBorder(cellStyle));
        cell.setCellValue(cellValue);
        sheet.autoSizeColumn(colNumber);
    }

    private static void createCellWithoutBorder(XSSFCellStyle cellStyle, Row row, Sheet sheet, int colNumber, String cellValue) {
        Cell cell = row.createCell(colNumber);
        cell.setCellStyle(getCellStyleWithoutBorder(cellStyle));
        cell.setCellValue(cellValue);
        sheet.autoSizeColumn(colNumber);
    }

    private static XSSFCellStyle getCellStyleWithBorder(XSSFCellStyle cellStyle) {

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setWrapText(true);


        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    private static XSSFCellStyle getCellStyleWithoutBorder(XSSFCellStyle cellStyle) {

        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setWrapText(true);

        cellStyle.setBorderBottom(BorderStyle.NONE);
        cellStyle.setBorderLeft(BorderStyle.NONE);
        cellStyle.setBorderRight(BorderStyle.NONE);
        cellStyle.setBorderTop(BorderStyle.NONE);

        return cellStyle;
    }

    private void setMergedCellBorders(CellRangeAddress cellRangeAddress, Sheet sheet) {
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);

        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAddress, sheet);
        RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAddress, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAddress, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), cellRangeAddress, sheet);
    }

}
