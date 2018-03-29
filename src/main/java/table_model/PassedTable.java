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

public class PassedTable {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public PdfPTable createNameForPassedTable(){
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell_1 = new PdfPCell(new Phrase("Passed tests"));
        cell_1.setBackgroundColor(new Color(25, 159, 65));
        cell_1.setBorderWidth(0);
        cell_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell_1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell_1.setMinimumHeight(25f);
        table.addCell(cell_1);

        table.setWidthPercentage(104);
        return table;
    }

    /** Создание таьлицы "Passed Test" */
    public PdfPTable createPassedTable(List<Model> passedList){
        PdfPTable table = new PdfPTable(4);
        String date = sdf.format(new Date());

        /** Делается отступ между таблицами, что бы не сливались! */
        table.setSpacingBefore(1f);

        PdfPCell cell_date = new PdfPCell(new Phrase("Date"));
        PdfPCell cell_test_suite = new PdfPCell(new Phrase("Suite"));
        PdfPCell cell_test_name = new PdfPCell(new Phrase("Test name"));
        PdfPCell cell_method_name = new PdfPCell(new Phrase("Method name"));

        /** Убирание границ в таблице */
        cell_date.setBorder(0);
        cell_test_name.setBorder(0);
        cell_test_suite.setBorder(0);
        cell_method_name.setBorder(0);

        /** Установка цвета для значений в таблице "Passed Test" */
        cell_date.setBackgroundColor(new Color(47, 218, 104));
        cell_test_name.setBackgroundColor(new Color(47, 218, 104));
        cell_test_suite.setBackgroundColor(new Color(47, 218, 104));
        cell_method_name.setBackgroundColor(new Color(47, 218, 104));

        /** Добавление элементов в таблицу "Passed Test" */
        table.addCell(cell_date);
        table.addCell(cell_test_suite);
        table.addCell(cell_test_name);
        table.addCell(cell_method_name);

        for (Model el : passedList){
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
