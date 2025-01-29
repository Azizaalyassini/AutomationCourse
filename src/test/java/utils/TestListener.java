package utils;

import Tests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class TestListener extends BaseTest implements ITestListener {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    private static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport_" + timestamp + ".html");


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String passedTest = iTestResult.getName();
        // Sets Dashboard as the primary view of the report
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
        extent.attachReporter(reporter);
        extent.createTest(passedTest)
                // Uses the test's group info to set the test category
                .assignCategory(iTestResult.getMethod().getGroups())
                // Prints the stacktrace
                .log(Status.PASS, iTestResult.getThrowable())
                // Prints the test's description
                .info(iTestResult.getMethod().getDescription())
                // Prints the test's group
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        //  Write the test information to the reporter
        extent.flush();
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        String failedTest = iTestResult.getName();
        // Sets Dashboard as the primary view of the report
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
        extent.attachReporter(reporter);
        extent.createTest(failedTest)
                // Uses the test's group info to set the test category
                .assignCategory(iTestResult.getMethod().getGroups())
                // Prints the stacktrace
                .log(Status.FAIL, iTestResult.getThrowable())
                // Prints the test's description
                .info(iTestResult.getMethod().getDescription())
                // Prints the test's group
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        //  Write the test information to the reporter
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}