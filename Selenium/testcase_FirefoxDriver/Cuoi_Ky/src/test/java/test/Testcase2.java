package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Testcase2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Create a new instance of the Chrome driver
        WebDriver driver = new FirefoxDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Hover over Tài Khoản menu
        WebElement taiKhoan = driver.findElement(By.xpath("//a[contains(text(),'Tài Khoản')]"));
        Actions action = new Actions(driver);
        action.moveToElement(taiKhoan).perform();


//        Thread.sleep(1000);

        // Step 3: Click Đăng nhập
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dangnhap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Đăng Nhập')]")));
        dangnhap.click();




        // Step 4: Nhập tất cả trường trong đăng nhập
        WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
        email.sendKeys("hieu23234324@gmail.com");

        WebElement mk = driver.findElement(By.xpath("//input[@id='input-password']"));
        mk.sendKeys("hieu123456");


        //step 5: click đăng nhập

        WebElement dangNhap = driver.findElement(By.xpath("//input[@value='Đăng nhập']"));

        if (dangNhap.isDisplayed() && dangNhap.isEnabled()) {
            System.out.println("Phần tử đăng nhập khả dụng.");
        } else {
            System.out.println("Phần tử đăng nhập không khả dụng.");
        }

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", dangNhap);

        // Step 2: trỏ vào hoa sinh nhât
        Thread.sleep(5000);
        WebElement hoasinhnhat = driver.findElement(By.xpath("//strong[contains(text(),'Hoa Sinh Nhật')]"));
        Actions action1 = new Actions(driver);
        action1.moveToElement(hoasinhnhat).perform();


        //step 3: click vào hoa sinh nhat sang trong


        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement hsnst = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Hoa Sinh Nhật Sang Trọng')]")));
        hsnst.click();

        //step 4: click đặt hàng hoa mấu đơn hồng
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'left')]//img[contains(@alt,'Mẫu Đơn Hồng')]")));
        element.click();

        //step 5: xác nhận đã vào hoa mẫu đơn hồng bằng tiêu đề
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#title-page")));
        String expectedtitle = "Mẫu Đơn Hồng";
        String actualtitle = title.getText();


        System.out.println(actualtitle);

        if(actualtitle.equals(expectedtitle)){
            System.out.println("đúng");
        } else {
            System.out.println("sai");
        }

        //step 6: tiến hành mua hàng click dặt hang


        Thread.sleep(2000);

        WebElement dathang = driver.findElement(By.cssSelector("#button-cart"));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", dathang);

        Thread.sleep(5000);

        WebElement nguoinhan = driver.findElement(By.cssSelector("#shipping_address_firstname"));
        nguoinhan.sendKeys("hieu nhà giàu");

        WebElement dienthoai = driver.findElement(By.cssSelector("#shipping_address_company"));
        dienthoai.sendKeys("123456786");


        Thread.sleep(2000);
        WebElement districtField = driver.findElement(By.cssSelector("#shipping_address_district_id"));
        Select districtSelect = new Select(districtField);
        districtSelect.selectByVisibleText("Quận 7");

        Thread.sleep(2000);

        WebElement thanhtoan = driver.findElement(By.cssSelector("#cod"));
        JavascriptExecutor executor2 = (JavascriptExecutor) driver;
        executor2.executeScript("arguments[0].click();", thanhtoan);

        Thread.sleep(2000);
        WebElement xacnhandonghang = driver.findElement(By.xpath("//button[@id='qc_confirm_order']"));
        JavascriptExecutor executor3 = (JavascriptExecutor) driver;
        executor3.executeScript("arguments[0].click();", xacnhandonghang);

        Thread.sleep(4000);

        WebElement title1 = driver.findElement(By.cssSelector("#title-page"));
        String expectedtitle1 = "Đơn Hàng Của Bạn Đã Được Xử Lý!";
        String actualtitle1 = title1.getText();


        System.out.println(actualtitle1);

        if(actualtitle1.equals(expectedtitle1)){
            System.out.println("đơn hàng đã được xác nhận");
        } else {
            System.out.println("đơn hàng chưa đươc xác nhận");
        }

        // Screenshot
        TakesScreenshot screenshot =((TakesScreenshot)driver);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //        try {
        FileUtils.copyFile(scrFile,
                new File("D:\\hocki5\\congnghekiemthuungdung\\selenium-webdriver-java-main\\baitap\\img\\test11.png"));
        //        } catch (IOException e) {
        //            throw new RuntimeException(e);
        //        }
        // Close the browser
        driver.quit();


    }

}
