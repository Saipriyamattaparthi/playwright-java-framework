package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        
        String browserName = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        int slowMo = Integer.parseInt(ConfigReader.get("slowMo"));
        
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo);
        
        if(browserName.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(options);
        } else if(browserName.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(options);
        } else {
            browser = playwright.webkit().launch(options);
        }
        
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}