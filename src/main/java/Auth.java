import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Auth {


    public static void webClient(String loginUrl, String username, String password) {
        System.setProperty("webdriver.gecko.driver", "/Library/Java/Extensions/geckodriver");
        WebDriver driver = new FirefoxDriver();
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.ERROR);

        try {

//          1. GOTO YAHOO FINANCE LOGIN URL
            driver.navigate().to(loginUrl);
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("Page URL: " + driver.getCurrentUrl());

        } catch (Exception e) {

            System.out.println("Could not find URL!");
            e.printStackTrace();
            driver.quit();
        }


        try {
//          2. ENTER USERNAME
            WebElement usernameInput = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-username")));
            System.out.println("usernameInput:" + usernameInput);
            usernameInput.sendKeys(username);
            System.out.println("Username entered...");

        } catch (Exception e) {
            System.out.println("Error entering username!");
            e.printStackTrace();
            driver.quit();
        }


        try {
//          3. CLICK 'NEXT' BUTTON
            WebElement nextButton = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-signin")));
            System.out.println("nextButton: " + nextButton);
            nextButton.click();
            System.out.println("'Next' button clicked...");

        } catch (Exception e) {
            System.out.println("Error hitting 'next' button!");
            e.printStackTrace();
            driver.quit();
        }

        try {
//          4. ENTER PASSWORD
            WebElement passwordInput = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-passwd")));
            System.out.println("passwordInput: " + passwordInput);
            passwordInput.sendKeys(password);
            System.out.println("Password entered...");


        } catch (Exception e) {
            System.out.println("Error entering password!");
            e.printStackTrace();
            driver.quit();
        }

        try {
//          5. CLICK 'SIGN-IN' BUTTON
            WebElement signInButton = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-signin")));
            System.out.println("signInButton: " + signInButton);
            signInButton.click();
            System.out.println("'Sign-in' button clicked...");

        } catch (Exception e) {
            System.out.println("Error hitting 'sign-in' button!");
            e.printStackTrace();
            driver.quit();
        }

        try {
//          7. SCRAPE TABLE
            Service.run(driver, wait);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
