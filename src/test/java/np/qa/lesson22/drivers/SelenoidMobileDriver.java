package np.qa.lesson22.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import np.qa.lesson22.config.SelenoidConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidMobileDriver implements WebDriverProvider {
    public static SelenoidConfig selenoid = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

    public static URL getSelenoidUrl() {
        try {
            return new URL("https://" + selenoid.user() + ":" + selenoid.password() + "@" + selenoid.hubUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private URL getApkUrl() {
        try {
            return new URL("https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("android");
        options.setPlatformName("8.1");
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        options.setApp(getApkUrl());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");


       return new AndroidDriver(getSelenoidUrl(), options);

    }
}