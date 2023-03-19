package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;

public class Testcase4 {
        public static void main(String[] args) {
            // Create a new instance of the ChromeDriver
            WebDriver driver = new ChromeDriver();

            // Step 1: Go to https://www.flowercorner.vn/
            driver.get("https://www.flowercorner.vn/");

            // Step 2: Click on "Hoa Sinh Nhật"
            WebElement hoa = driver.findElement(By.xpath("//strong[contains(text(),'Hoa Sinh Nhật')]"));
            hoa.click();

            // Step 3: Click on add to cart
            WebElement go = driver.findElement(By.xpath("//div[@id='mfilter-content-container']//div[1]//div[1]//div[2]//div[4]//a[1]"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click()", go);

            // Step 4: Change "QTY" value to 100000 and click "UPDATE" button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
            qtyInput.clear();
            qtyInput.sendKeys("100000");
            WebElement updateBtn = driver.findElement(By.cssSelector("#button-cart"));
            updateBtn.click();

            // Step 5: Verify error message
            WebElement wait = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".warning")));
            String actualErrorMessage = wait.getText();
            String expectedErrorMessage = "×\nSản phẩm đánh dấu *** không có đủ số lượng trong kho!";

            if (actualErrorMessage.equals(expectedErrorMessage)) {
                System.out.println("Test passed - Error message is correct");
            } else {
                System.out.println("Test failed - Error message is incorrect");
            }

            // Screenshot
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile,
                        new File(".\\img\\test4.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Close the browser
            driver.quit();
        }
    }

