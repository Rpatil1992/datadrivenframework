package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utility.ExcelReader;
import base.BaseClass;

public class LoginTest extends BaseClass {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        ExcelReader reader = new ExcelReader();
        return reader.getData();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) throws Exception {

        System.out.println("Trying login -> Username: " + username + " Password: " + password);

       
      
        Thread.sleep(2000);

        // Enter username
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        System.out.println("✔ Entered username");

        // Enter password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        System.out.println("✔ Entered password");

        // Click login
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("✔ Clicked login");

        Thread.sleep(3000);

     // Soft assertion → verify login success
        String currentURL = driver.getCurrentUrl();
        boolean loginSuccess = currentURL.contains("inventory.html");

        soft.assertTrue(loginSuccess, "❌ Login FAILED for: " + username + " / " + password);

        // Soft assert example on page
        soft.assertTrue(driver.getPageSource().contains("Dashboard") ||
                        driver.getPageSource().contains("Invalid credentials"),
                        "❌ Neither Dashboard nor Invalid credentials found");

        // Print all assertion results at end
        soft.assertAll();


        driver.quit();
    }
}