package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import base.ConfigReader;

public class LoginPage {
    private Page page;
    private Locator usernameInput;
    private Locator passwordInput;
    private Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("#userName");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("#login");
    }

    public void navigateToLogin() {
        String url = ConfigReader.get("baseUrl");
        page.navigate(url);
    }

    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void doLogin(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}