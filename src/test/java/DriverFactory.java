import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.assertj.core.util.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class DriverFactory {

    private static WebDriver driver;

    @BeforeSuite
    public void initializeDriver() {
        try {
            String seleniumRemoteUri = System.getenv("selenium_remote_uri");

            if (Strings.isNullOrEmpty(seleniumRemoteUri)) {
                System.out.println("Using local FirefoxDriver ...");

                driver = new FirefoxDriver();
            } else {
                System.out.println("Using RemoteWebDriver - " + seleniumRemoteUri + "...");

                driver = new RemoteWebDriver(
                    new URL(seleniumRemoteUri),
                    DesiredCapabilities.firefox());
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterSuite
    public void closeDriver(){
        driver.close();
    }

}
