package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class Testcase3 {
    public static void main(String[] args) {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Click on "Hoa Sinh Nhật"
        WebElement hoa = driver.findElement(By.xpath("//strong[contains(text(),'Hoa Sinh Nhật')]"));
        hoa.click();

        // Step 3: Read the cost of "Luôn Bên Em (460 cành)"
        WebElement ab = driver.findElement(By.xpath("//div[contains(@class,'right')]//span[contains(@class,'price-new')][normalize-space()='460.000VND']"));
        String cd = ab.getText();
        System.out.println("The cost of the Luôn Bên Em is: " + cd);

        // Step 4: Click on "Luôn Bên Em"
        WebElement go = driver.findElement(By.xpath("//div[contains(@class,'right')]//a[contains(text(),'Luôn Bên Em')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", go);

        // Step 5: Read the price of "Luôn Bên Em"
        WebElement sonyXperiaMobileDetail = driver.findElement(By.cssSelector(".price-new"));
        String sonyXperiaMobileDetailText = sonyXperiaMobileDetail.getText();
        System.out.println("The price of Luôn Bên Em in detail page is: " + sonyXperiaMobileDetailText);

        // Step 6: Compare the price in the list and detail pages
        WebElement ef = driver.findElement(By.xpath("//div[@class='price']//span[@id='price-special']"));
        String sonyXperiaDetailPriceText = ef.getText();
        if (cd.equals(sonyXperiaDetailPriceText)) {
            System.out.println("The product price in the list and detail pages is equal.");
        } else {
            System.out.println("The product price in the list and detail pages is not equal.");
        }

        // Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File(".\\img\\test3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Close the browser
        driver.quit();
    }
}
