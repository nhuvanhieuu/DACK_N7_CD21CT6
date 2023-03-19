package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Testcase3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create a new instance of the Chrome driver
        WebDriver driver = new FirefoxDriver();



        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Hover over Tài Khoản menu
        WebElement taiKhoan = driver.findElement(By.xpath("//a[contains(text(),'Tài Khoản')]"));
        Actions action = new Actions(driver);
        action.moveToElement(taiKhoan).perform();

        // Step 3: Click Đăng nhập
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dangnhap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Đăng Nhập')]")));
        dangnhap.click();

        // Step 4: Nhập tất cả trường trong đăng nhâp
        WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
        email.sendKeys("hieu23234324@gmail.com");

        WebElement mk = driver.findElement(By.xpath("//input[@id='input-password']"));
        mk.sendKeys("hieu123456");

        WebElement element = driver.findElement(By.xpath("//input[@value='Đăng nhập']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);


        Thread.sleep(5000);

        //step 5: click xem lịch sử đặt hàng
        WebElement lichsu = driver.findElement(By.xpath("//a[contains(text(),'Xem lịch sử Đặt hàng')]"));
        lichsu.click();


        Thread.sleep(5000);

        //step 6: click vào đơn hàng đã đặt
        WebElement xemdonhang = driver.findElement(By.xpath("//tbody/tr[1]/td[7]/a[1]"));
        xemdonhang.click();

        //step 7: xác minh đang ở trong thông tin đơn hàng
        Thread.sleep(5000);

        WebElement title = driver.findElement(By.xpath("//h1[@id='title-page']"));
        String expectedtitle = "Thông tin đơn hàng";
        String actualtitle = title.getText();

        if(actualtitle.equals(expectedtitle)){
            System.out.println("bạn đang xem đơn hàng chi tiết");
        }else{
            System.out.println("sai rồi");
        }

        //step 8 kểm tra tổng đơn giá có đúng không
        Thread.sleep(5000);

        WebElement expectedtt = driver.findElement(By.xpath("//td[normalize-space()='6.360.000VND']"));
        String tongtien = expectedtt.getText();
        System.out.println(tongtien);
        String actualtt = "6.360.000VND";

        if(actualtt.equals(tongtien)){
            System.out.println("đúng giá");
        }else{
            System.out.println("sai giá");
        }

        // Screenshot
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
