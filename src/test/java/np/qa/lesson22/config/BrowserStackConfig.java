package np.qa.lesson22.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/browserstack.properties"})
public interface BrowserStackConfig extends Config {

    @Key("user")
    String user();

    @Key("key")
    String key();

    @Key("app")
    String app();
}