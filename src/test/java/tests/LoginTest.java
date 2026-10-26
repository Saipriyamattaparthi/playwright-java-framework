package tests;

import base.BaseTest;
import base.ExcelReader;
import pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    void dataDrivenLoginTest() {
        String excelPath = "src/test/resources/testdata/LoginData.xlsx";
        Object[][] data = ExcelReader.getTestData(excelPath, "Sheet1");
        
        LoginPage loginPage = new LoginPage(page);
        
        for(Object[] row : data) {
            String user = row[0].toString();
            String pass = row[1].toString();
            
            System.out.println("Testing with: " + user + " / " + pass);
            
            loginPage.navigateToLogin();
            loginPage.doLogin(user, pass);
            
            page.waitForTimeout(2000);
            System.out.println("URL after login: " + page.url());
        }
        
        System.out.println("Day 20 Excel Data Driven DONE!");
    }
}