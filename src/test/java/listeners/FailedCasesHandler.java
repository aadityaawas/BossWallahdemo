package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.CommonUtils;
import java.util.Optional;

// Defining the retry mechanism for handing the flaky test cases
public class FailedCasesHandler implements IRetryAnalyzer {
    private final CommonUtils utils = new CommonUtils();
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int MAX_RETRIES = Integer.parseInt(
                Optional.ofNullable(System.getProperty("MAX_RETRY"))
                        .orElseGet(() -> utils.readPropertiesFile().getProperty("MAX_RETRY")));
        // Checking the number of retries for flaky test cases.
        // Currently max tries are 3 but it can be configured
        if (MAX_RETRIES > retryCount) {
            System.out.println("Test case failed, retrying again");
            retryCount++;
            return true;
        }
        // Reached max number of tries, and it is failed that means, there is something wrong with the test case itself not because of flakyness.
        return false;
    }
}
