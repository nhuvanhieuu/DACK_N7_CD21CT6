package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Testcase1 {
    public static void main(String[] args) throws IOException {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Verify Title of the page
        List<WebElement> cartEmptyMsg = driver.findElements(By.xpath("//img[@title='FlowerCorner']"));
        Assert.assertEquals(cartEmptyMsg.size(), 1, "FlowerCorner");
        System.out.println("Title verification successful!");

        // Step 3: Click on -> search
        WebElement search = driver.findElement(By.xpath("//div[@class='button-search']"));
        search.click();

        // Step 4: In the list of all products, select SORT BY -> dropdown as name
        WebElement searchvalue = driver.findElement(By.xpath(" //input[@id='input-search']"));
        searchvalue.sendKeys("hoaaaaaaaaaaaaaaaaaaaaaaaaaaf");
        WebElement category = driver.findElement(By.xpath(" //select[@name='category_id']"));
        Select countrySelect = new Select(category);
        countrySelect.selectByVisibleText("Bó Hoa Cúc Mẫu Đơn");
        WebElement searchkey = driver.findElement(By.xpath(" //input[@id='button-search']"));
        searchkey.click();

        // Step 5: Verify all products are sorted by popularity
        List<WebElement> EmptyProduct = driver.findElements(By.xpath("//p[contains(text(),'Không có kết quả nào!')]"));
        if (EmptyProduct.size() == 1) {
            System.out.println("không có sản phẩm nào");
        } else {
            System.out.println("đã hiển thị sản phẩm");
        }

        TakesScreenshot screenshot =((TakesScreenshot)driver);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File(".\\img\\test1.png"));

        // Step 6: Quit browser session
        driver.quit();
    }
}
