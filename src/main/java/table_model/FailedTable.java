package table_model;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import test_model.Model;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FailedTable {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public PdfPTable createNameForPassedTable(){
        PdfPTable table = new PdfPTable(1);
        table.setSpacingBefore(1f);
        PdfPCell cell = new PdfPCell(new Phrase("Failed tests"));
        cell.setBackgroundColor(new Color(204, 0, 0));
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setMinimumHeight(25f);
        table.addCell(cell);

        table.setWidthPercentage(104);
        return table;
    }

    public PdfPTable createFailedTable(List<Model> failedList){
        PdfPTable table = new PdfPTable(4);
        String date = sdf.format(new Date());

        /** Создание полей для таблицы со статическими значениями */
        PdfPCell cellDate = new PdfPCell(new Phrase(String.valueOf(date)));
        PdfPCell cellSuite = new PdfPCell(new Phrase(String.valueOf("Suite")));
        PdfPCell cellTestName = new PdfPCell(new Phrase("Test name"));
        PdfPCell cellMethodName = new PdfPCell(new Phrase("Method name"));

        /** Убирание границ в таблице! */
        cellDate.setBorder(0);
        cellSuite.setBorder(0);
        cellTestName.setBorder(0);
        cellMethodName.setBorder(0);

        /** Установка цвета для значений в таблице "Failed" */
        cellDate.setBackgroundColor(new Color(208,93,93));
        cellSuite.setBackgroundColor(new Color(208,93,93));
        cellTestName.setBackgroundColor(new Color(208,93,93));
        cellMethodName.setBackgroundColor(new Color(208,93,93));

        /** Добавление элементов в таблицу "Failed" */
        table.addCell(cellDate);
        table.addCell(cellSuite);
        table.addCell(cellTestName);
        table.addCell(cellMethodName);

        /**  */
        for (Model el : failedList){
            PdfPCell cell_date_data = new PdfPCell(new Phrase(String.valueOf(date)));
            PdfPCell cell_suite_data = new PdfPCell(new Phrase(String.valueOf(el.getSuiteName())));
            PdfPCell cell_name_data = new PdfPCell(new Phrase(String.valueOf(el.getTestName())));
            PdfPCell cell_method_name_data = new PdfPCell(new Phrase(String.valueOf(el.getMethodName())));
            table.addCell(cell_date_data);
            table.addCell(cell_suite_data);
            table.addCell(cell_name_data);
            table.addCell(cell_method_name_data);
        }
        table.setWidthPercentage(104);
        return table;
    }

}
