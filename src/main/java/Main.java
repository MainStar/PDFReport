

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import org.jfree.chart.JFreeChart;
import urls.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        /** Генерирует изображение диаграмы в ввиде пирога! */
/*        ChartGeneration chartGeneration = new ChartGeneration();
        JFreeChart chart = chartGeneration.generateChartPie();
        /**************************************************************/
/*        PdfWriter writer;
        Document document = new Document(/*new Rectangle(850, 500));*/

/*        writer = PdfWriter.getInstance(document, new FileOutputStream(Constants.REPORT_DIR));
        document.open();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(1000, 800);
        Graphics2D graphics2D = template.createGraphics(1000, 800, new DefaultFontMapper());
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 595, 300); //Растягивает изображение на всю страницу
        chart.draw(graphics2D, rectangle2D);
        graphics2D.dispose();
        contentByte.addTemplate(template, 0, 0);
        document.close();
*/
    }
}
