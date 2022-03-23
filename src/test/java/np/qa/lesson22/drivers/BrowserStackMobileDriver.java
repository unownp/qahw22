package np.qa.lesson22.drivers;
import com.codeborne.selenide.WebDriverProvider;
import np.qa.lesson22.config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;


public class BrowserStackMobileDriver implements WebDriverProvider {
    public static BrowserStackConfig browserStack = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
        mutableCapabilities.setCapability("browserstack.user", browserStack.user());
        mutableCapabilities.setCapability("browserstack.key", browserStack.key());
        mutableCapabilities.setCapability("app", browserStack.app());
        mutableCapabilities.setCapability("device", "Google Pixel 3");
        mutableCapabilities.setCapability("os_version", "9.0");
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}