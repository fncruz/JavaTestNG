import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import io.qameta.allure.Description;



import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.List;


public class SeleniumTest {

    public static ChromeOptions options = new ChromeOptions();
    public static WebDriver driver = new ChromeDriver(options);
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void e2eFlow() {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        // Step 1:
        driver.manage().window().maximize();
        driver.get("https://qubika.com");
        wait.until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete"));
        System.out.println("Qubika site visited");

        try {
            // Step 2a:
            String currentURL = driver.getCurrentUrl();
            assert currentURL != null;
            if (!currentURL.equals("https://qubika.com/")) {
                System.out.println("URL validation failed");
                return;
            } else
                System.out.println("URL validation passed");

            // Step 2b:
            WebElement acceptAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-cky-tag='accept-button")));
            acceptAll.click();
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='logo']")));
            if (logo.isDisplayed()) {
                System.out.println("Qubika logo is displayed correctly");
            } else {
                System.out.println("Qubika logo is not displayed");
                return;
            }

            // Step 3:
            WebElement contactUsButton = driver.findElement(By.cssSelector("a[class='link-label']"));
            contactUsButton.click();
            System.out.println("Contact Us Button clicked");

            // Step 4abc:
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement getInTouchButton = driver.findElement(By.cssSelector("input[value='Submit']"));
            if (nameField.isDisplayed() && emailField.isDisplayed() && getInTouchButton.isDisplayed()) {
                System.out.println("Contact form fields are displayed");
            } else {
                System.out.println("Contact form validation failed");
                return;
            }

            // Step 5:
            getInTouchButton.click();
            System.out.println("Get in Touch Button clicked");

            // Step 6:
            var mandatoryFields = 6;
            List<WebElement> fieldErrors = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hs-error-msg.hs-main-font-element")));
            Helpers.validateMandatoryFields(fieldErrors.size(), mandatoryFields);

            // Step 7:
            WebElement nameFieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='hs_email hs-email hs-fieldtype-text field hs-form-field'] label[class='hs-error-msg hs-main-font-element']")));
            String nameColor = nameFieldError.getCssValue("color");
            Helpers.compareColor(nameColor);

            // Step 8:
            nameField.sendKeys("Test name");
            mandatoryFields--;
            System.out.println("Filled name field");

            // Step 9:
            getInTouchButton.click();
            System.out.println("Get in Touch Button clicked");

            // Step 10:
            fieldErrors = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hs-error-msg.hs-main-font-element")));
            Helpers.validateMandatoryFields(fieldErrors.size(), mandatoryFields);

            // Step 11: Validate that only 'Email' field is marked with red color
            WebElement emailFieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='hs_email hs-email hs-fieldtype-text field hs-form-field'] label[class='hs-error-msg hs-main-font-element']")));
            String emailColor = emailFieldError.getCssValue("color");
            Helpers.compareColor(emailColor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}