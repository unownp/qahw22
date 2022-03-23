package np.qa.lesson22.helpers;


import np.qa.lesson22.drivers.BrowserStackMobileDriver;

import static io.restassured.RestAssured.given;

public class BrowserStack {
    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(BrowserStackMobileDriver.browserStack.user(), BrowserStackMobileDriver.browserStack.key())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
