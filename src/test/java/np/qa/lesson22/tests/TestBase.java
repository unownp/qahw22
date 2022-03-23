package np.qa.lesson22.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import np.qa.lesson22.drivers.BrowserStackMobileDriver;
import np.qa.lesson22.drivers.LocalMobileDriver;
import np.qa.lesson22.drivers.RealDeviceDriver;
import np.qa.lesson22.drivers.SelenoidMobileDriver;
import np.qa.lesson22.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static np.qa.lesson22.helpers.Attach.getSessionId;

public class TestBase {
    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        String deviceHost = System.getProperty("deviceHost");

        if(deviceHost.equals("browserstack")){
            Configuration.browser = BrowserStackMobileDriver.class.getName();
        }
        else if(deviceHost.equals("selenoid")){
            Configuration.browser = SelenoidMobileDriver.class.getName();
            Configuration.remote="https://user1:1234@selenoid.autotests.cloud/wd/hub";
        }
        else if(deviceHost.equals("realDevice")){
            Configuration.browser = RealDeviceDriver.class.getName();
        }
        if(deviceHost.equals("emulation")){
            Configuration.browser = LocalMobileDriver.class.getName();
        }

//        Configuration.startMaximized = false;
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String deviceHost = System.getProperty("deviceHost");
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();


        if(deviceHost.equals("browserstack")){
            Attach.videoBrowserStack(sessionId);
        }
        else if(deviceHost.equals("selenoid")){
            Attach.videoSelenoid(sessionId);
        }

    }
}
