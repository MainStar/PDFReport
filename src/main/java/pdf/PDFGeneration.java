package pdf;

import chart.ChartGeneration;
import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.jfree.chart.JFreeChart;
import table_model.FailedTable;
import table_model.PassedTable;
import table_model.SkippedTable;
import table_model.StatisticTable;
import test_model.Model;
import urls.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGeneration {


   private StatisticTable statisticTable = new StatisticTable();
   private PassedTable passedTable = new PassedTable();
   private FailedTable failedTable = new FailedTable();
   private SkippedTable skippedTable = new SkippedTable();

    public void createDocument(List<Model> passedList, List<Model> failedList, List<Model> skippedList) throws FileNotFoundException, DocumentException {

        int successful = passedList.size();
        int failed = failedList.size();
        int skipped = skippedList.size();

        /** Генерирует изображение диаграмы в ввиде пирога! */
        ChartGeneration chartGeneration = new ChartGeneration();
        JFreeChart chart = chartGeneration.generateChartPie(successful, failed, skipped);
        /**************************************************************/
        PdfWriter writer;
        Document document = new Document(new Rectangle(700, 370));

        writer = PdfWriter.getInstance(document, new FileOutputStream(Constants.REPORT_DIR));
        document.open();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(1000, 900);
        Graphics2D graphics2D = template.createGraphics(1000, 800, new DefaultFontMapper());
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 698, 250); //Растягивает изображение на всю страницу
        chart.draw(graphics2D, rectangle2D);
        graphics2D.dispose();
        contentByte.addTemplate(template, 0, -540);

        //Statistic table
        PdfPTable tableStatisticName = statisticTable.createHeadForTableStatistic();
        PdfPTable tableStatistic = statisticTable.createStatisticTable(successful, failed, skipped);

        //Passed Table
        PdfPTable tableName = passedTable.createNameForPassedTable();
        PdfPTable tablePassed = passedTable.createPassedTable(passedList);

        //Failed table
        PdfPTable tableFailedName = failedTable.createNameForPassedTable();
        PdfPTable tableFailedCreated = failedTable.createFailedTable(failedList);

        //Skipped table
        PdfPTable tableSkippedName = skippedTable.createNameForPassedTable();
        PdfPTable tableSkippedCreated = skippedTable.createSkippedTable(skippedList);

        /** Добавляем таблицу на первую страницу, с статистикой */
        document.add(tableStatisticName);
        document.add(tableStatistic);

        if (passedList.size() > 0) {
            /** Создаем страницу с "Успешными тестами" и добавляем и на нне таблицы */
            document.setPageSize(new Rectangle(700, 350));
            document.setMargins(20, 20, 20, 20);
            document.newPage();
            document.add(tableName);
            document.add(tablePassed);
        }

        if (failedList.size() > 0) {
            /** Создаем страницу с "Не Успешными" тестами и добавляем на нее таблицу */
            document.setPageSize(new Rectangle(700, 350));
            document.setMargins(20, 20, 20, 20);
            document.newPage();
            document.add(tableFailedName);
            document.add(tableFailedCreated);
        }

        //if (skippedList.size() > 0) {
            /** Создаем страницу с "Пропущенными тестами" и добавляем на нее таблицы */
            document.setPageSize(new Rectangle(700, 350));
            document.setMargins(20, 20, 20, 20);
            document.newPage();
            document.add(tableSkippedName);
            document.add(tableSkippedCreated);
        //}

        /** Закрываем документ */
        document.close();
    }
}