package table_model;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticTable {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public PdfPTable createHeadForTableStatistic(){
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell(new Phrase("Statistic"));

        cell.setBorder(0);
        cell.setMinimumHeight(25f);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(25, 125, 223));

        table.setSpacingAfter(0.5f);

        table.addCell(cell);

        table.setWidthPercentage(110);
        return table;
    }

    /** Метод отвечает за генерацию таблицы статистики, которая находится на страницы "Chart" */
    public PdfPTable createStatisticTable(int passed, int failed, int skipped){
        String date = sdf.format(new Date());
        PdfPTable table = new PdfPTable(4);
        System.out.println(date);

        PdfPCell cell_date_head = new PdfPCell(new Phrase("Date"));
        PdfPCell cell_passed_head = new PdfPCell(new Phrase("Passed"));
        PdfPCell cell_failed_head = new PdfPCell(new Phrase("Failed"));
        PdfPCell cell_skipped_head = new PdfPCell(new Phrase("Skipped"));

        /** Заливка цветом ХЕДЕР таблицы */
        cell_date_head.setBackgroundColor(new Color(51, 153, 255));
        cell_passed_head.setBackgroundColor(new Color(51, 153, 255));
        cell_failed_head.setBackgroundColor(new Color(51, 153, 255));
        cell_skipped_head.setBackgroundColor(new Color(51, 153, 255));

        /** Установка высоты для ХЕДЕРА таблицы */
        cell_date_head.setMinimumHeight(25f);
        cell_passed_head.setMinimumHeight(25f);
        cell_failed_head.setMinimumHeight(25f);
        cell_skipped_head.setMinimumHeight(25f);

        /** Выравнивание текста в ХЕДЕРЕ таблицы ПО ЦЕНТРУ */
        cell_date_head.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell_passed_head.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell_failed_head.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell_skipped_head.setHorizontalAlignment(Element.ALIGN_CENTER);

        /** Установка значений в таблице */
        PdfPCell cell_date = new PdfPCell(new Phrase(String.valueOf(date)));
        PdfPCell cell_passed = new PdfPCell(new Phrase(String.valueOf(passed)));
        PdfPCell cell_failed = new PdfPCell(new Phrase(String.valueOf(failed)));
        PdfPCell cell_skipped = new PdfPCell(new Phrase(String.valueOf(skipped)));

        /** Убирание рамок ПО ВСЕЙ ТАБЛИЦЕ */
        cell_date_head.setBorder(0);
        cell_passed_head.setBorder(0);
        cell_failed_head.setBorder(0);
        cell_skipped_head.setBorder(0);

        /** Добавление ХЕДЕРА в таблицу */
        table.addCell(cell_date_head);
        table.addCell(cell_passed_head);
        table.addCell(cell_failed_head);
        table.addCell(cell_skipped_head);

        /** Добавление значений в таблицу */
        table.addCell(cell_date);
        table.addCell(cell_passed);
        table.addCell(cell_failed);
        table.addCell(cell_skipped);

        table.setWidthPercentage(110);
        return table;
    }
}
