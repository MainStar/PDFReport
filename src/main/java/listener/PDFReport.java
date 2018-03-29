package listener;

import pdf.PDFGeneration;
import com.lowagie.text.DocumentException;
import org.testng.*;
import org.testng.xml.XmlSuite;
import test_model.Model;

import java.io.FileNotFoundException;
import java.util.*;

public class PDFReport implements IReporter {

    private int skipped = 0;
    private int successed = 0;
    private int failed = 0;

    private PDFGeneration pdfGeneration = new PDFGeneration();
    private List<Model> passedList = new ArrayList<>();
    private List<Model> failedList = new ArrayList<>();
    private List<Model> skippedList = new ArrayList<>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String s) {

        for (ISuite suite : suites){
            String suiteName = suite.getName();
            //System.out.println("Suite name: " + suiteName);
            Map<String, ISuiteResult> suiteResult = suite.getResults();
            for (ISuiteResult sr : suiteResult.values()){
                ITestContext context = sr.getTestContext();
                successed += context.getPassedTests().getAllResults().size();
                skipped += context.getSkippedTests().getAllResults().size();
                failed += context.getFailedTests().getAllResults().size();
                //System.out.println("Test name: " + context.getName()); //Получаем имя теста из testng.xml
                String testName = context.getName();

                Collection<ITestNGMethod> listPassed = context.getPassedTests().getAllMethods();
                Collection<ITestNGMethod> listFailed = context.getFailedTests().getAllMethods();
                Collection<ITestNGMethod> listSkipped = context.getSkippedTests().getAllMethods();

                for (ITestNGMethod el : listPassed){
                    Model model = new Model();
                    model.setSuiteName(suiteName);
                    model.setTestName(testName);
                    model.setMethodName(el.getMethodName());
                    System.out.println(suiteName);
                    System.out.println(testName);
                    System.out.println("Method name: " + el.getMethodName());
                    passedList.add(model);
                }

                for (ITestNGMethod el : listFailed){
                    Model model = new Model();
                    model.setSuiteName(suiteName);
                    model.setTestName(testName);
                    model.setMethodName(el.getMethodName());
                    failedList.add(model);
                }

                for (ITestNGMethod el : listSkipped){
                    Model model = new Model();
                    model.setSuiteName(suiteName);
                    model.setTestName(testName);
                    model.setMethodName(el.getMethodName());
                    skippedList.add(model);
                }
            }
        }
        try {

            pdfGeneration.createDocument(passedList, failedList, skippedList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}