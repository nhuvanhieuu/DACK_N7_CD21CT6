package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class Testcase1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create a new instance of the Chrome driver
        WebDriver driver = new FirefoxDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Hover over Tài Khoản menu
        WebElement taiKhoan = driver.findElement(By.xpath("//a[contains(text(),'Tài Khoản')]"));
        Actions action = new Actions(driver);
        action.moveToElement(taiKhoan).perform();

        // Step 3: Click Đăng Ký
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement Dangky = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Đăng Ký')]")));
        Dangky.click();


//        WebElement Dangky = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > header:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)"));
//        Dangky.click();

        // Step 4: Nhập tất cả trường trong đăng ký
        WebElement ten = driver.findElement(By.cssSelector("#input-firstname"));
        ten.sendKeys("hieu12");

        WebElement email = driver.findElement(By.cssSelector("#input-email"));
        email.sendKeys("hieu23234324@gmail.com");


        WebElement dienthoai = driver.findElement(By.cssSelector("#input-telephone"));
        dienthoai.sendKeys("3456786543");

        WebElement diachi = driver.findElement(By.cssSelector("#input-address-1"));
        diachi.sendKeys("sai gon");

        WebElement matkhau = driver.findElement(By.cssSelector("#input-password"));
        matkhau.sendKeys("hieu123456");

        WebElement nhaplaimk = driver.findElement(By.cssSelector("#input-confirm"));
        nhaplaimk.sendKeys("hieu123456");

        Thread.sleep(5000);
        WebElement checkbox = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", checkbox);


        //step 5: click dang ký
        WebElement dangky = driver.findElement(By.cssSelector("input[value='Tiếp tục']"));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", dangky);


        Thread.sleep(5000);

        //step 6: kiễm tra tài khoản đăng ký thành công
        WebElement title = driver.findElement(By.xpath("//h1[@id='title-page']"));
        String expectedtitle = title.getText();
        System.out.println(expectedtitle);
        String actualtitle = "Tài khoản của bạn đã được tạo!";

        if(actualtitle.equals(expectedtitle)){
            System.out.println("tài khoản đã được tạo");
        }else{
            System.out.println("tài khoản chưa được tạo");
        }

        TakesScreenshot screenshot =((TakesScreenshot)driver);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //        try {
        FileUtils.copyFile(scrFile,
                new File("D:\\hocki5\\congnghekiemthuungdung\\selenium-webdriver-java-main\\baitap\\img\\test10.png"));
        //        } catch (IOException e) {
        //            throw new RuntimeException(e);
        //        }
        // Close the browser
        driver.quit();



    }
}
