package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Testcase2 {
    public static void main(String[] args) throws IOException {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Verify Title of the page
        List<WebElement> cartEmptyMsg = driver.findElements(By.xpath("//img[@title='FlowerCorner']"));
        Assert.assertEquals(cartEmptyMsg.size(), 1, "FlowerCorner");
        System.out.println("Title verification successful!");

        // Step 3: choose button for sort
        WebElement hoa = driver.findElement(By.xpath("//strong[contains(text(),'Hoa Sinh Nhật')]"));
        hoa.click();

        // Step 4: choose value for sort
        WebElement category = driver.findElement(By.xpath("//div[@class='sort']//select[@onchange='location = this.value;']"));
        Select countrySelect = new Select(category);
        countrySelect.selectByVisibleText("Giá (Cao > Thấp)");
        // Step 5: Verify all products are sorted by price
        String[] productPrice = driver.findElements(By.cssSelector("body > div:nth-child(8) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3)"))
                .stream()
                .map(WebElement::getText)
                .toArray(String[]::new);

        boolean isSorted = true;
        for (int i = 0; i < productPrice.length - 1; i++) {
            if (productPrice[i].compareTo(productPrice[i + 1]) > 0) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            System.out.println("Product sorting price successful!");
        } else {
            System.out.println("Product sorting price failed!");
        }        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //screenshot
        try {
            FileUtils.copyFile(scrFile,
                    new File(".\\img\\test2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the browser
        driver.quit();
    }
}
