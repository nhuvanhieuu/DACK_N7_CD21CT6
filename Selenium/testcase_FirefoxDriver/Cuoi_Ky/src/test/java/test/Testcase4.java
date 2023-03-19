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

public class Testcase4 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Create a new instance of the Chrome driver
        WebDriver driver = new FirefoxDriver();

        // Step 1: Go to https://www.flowercorner.vn/
        driver.get("https://www.flowercorner.vn/");

        // Step 2: Hover over Tài Khoản menu
        WebElement taiKhoan = driver.findElement(By.xpath("//a[contains(text(),'Tài Khoản')]"));
        Actions action = new Actions(driver);
        action.moveToElement(taiKhoan).perform();

        Thread.sleep(3000);

        // Step 3: Click Đăng nhập
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dangnhap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Đăng Nhập')]")));
        dangnhap.click();

        // Step 4: Nhập tất cả trường trong đăng nhâp
        WebElement email = driver.findElement(By.xpath("//input[@id='input-email']"));
        email.sendKeys("hieu1234@gmail.com");

        WebElement mk = driver.findElement(By.xpath("//input[@id='input-password']"));
        mk.sendKeys("hieudeptrai1");

        //đăng nhập
        WebElement element = driver.findElement(By.xpath("//input[@value='Đăng nhập']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);



        // step 5: thêm sản phẩm vào giỏ hàng
        WebElement homepage = driver.findElement(By.xpath("//img[@title='FlowerCorner']"));
        homepage.click();

        Thread.sleep(2000);

        //step 6: thêm sản phẩm vào giả hàng
        WebElement addproduct = driver.findElement(By.xpath("//img[@alt='Red Wine']"));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", addproduct);



        Thread.sleep(2000);
        WebElement Dathang = driver.findElement(By.xpath("//input[@id='button-cart']"));
        JavascriptExecutor executor2 = (JavascriptExecutor) driver;
        executor2.executeScript("arguments[0].click();", Dathang);
        Thread.sleep(2000);


        Thread.sleep(2000);

        //step 7: click vào giỏ hàng
        WebElement giohang = driver.findElement(By.xpath("//img[@class='cart-icon-hover']"));
        JavascriptExecutor executor3 = (JavascriptExecutor) driver;
        executor3.executeScript("arguments[0].click();", giohang);


        Thread.sleep(2000);

        //step 8: cập nhật số lượng sản phẩm
        WebElement qty = driver.findElement(By.xpath("//input[@name='quantity[YToxOntzOjEwOiJwcm9kdWN0X2lkIjtpOjY1NjM7fQ==]']"));
        qty.sendKeys("5");

        Thread.sleep(2000);

        WebElement update = driver.findElement(By.xpath("//input[@title='Cập nhật']"));
        update.click();

        Thread.sleep(5000);

        //step 9: kiễm tra xem sô tổng cộng tiền sau khi cập nhất đơn hàng có đúng không
        WebElement tongcong = driver.findElement(By.xpath("//tbody//tr//td[6]"));
        String expectedtongcong = "7.650.000VND";
        String actualtongcong = tongcong.getText();

        if (actualtongcong.equals(expectedtongcong)) {
            System.out.println("giá đã cập nhat đúng");
        } else {
            System.out.println("giá cập nhật sai");

            Thread.sleep(2000);


        }
        //step 10: tiến hành đặt hàng
        WebElement thdathang = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        JavascriptExecutor executor4 = (JavascriptExecutor) driver;
        executor4.executeScript("arguments[0].click();", thdathang);
        Thread.sleep(5000);

        WebElement nguoinhan = driver.findElement(By.cssSelector("#shipping_address_firstname"));
        nguoinhan.sendKeys("hieu");

        WebElement dienthoai = driver.findElement(By.cssSelector("#shipping_address_company"));
        dienthoai.clear();
        dienthoai.sendKeys("123456786");


        Thread.sleep(2000);
        WebElement districtField = driver.findElement(By.cssSelector("#shipping_address_district_id"));
        Select districtSelect = new Select(districtField);
        districtSelect.selectByVisibleText("Quận 7");

        Thread.sleep(2000);

        WebElement thanhtoan = driver.findElement(By.cssSelector("#cod"));
        JavascriptExecutor executor6 = (JavascriptExecutor) driver;
        executor6.executeScript("arguments[0].click();", thanhtoan);

        Thread.sleep(2000);
        WebElement xacnhandonghang = driver.findElement(By.xpath("//button[@id='qc_confirm_order']"));
        JavascriptExecutor executor5 = (JavascriptExecutor) driver;
        executor5.executeScript("arguments[0].click();", xacnhandonghang);

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
                new File("D:\\hocki5\\congnghekiemthuungdung\\selenium-webdriver-java-main\\baitap\\img\\test10.png"));
        //        } catch (IOException e) {
        //            throw new RuntimeException(e);
        //        }
        // Close the browser
        driver.quit();


    }

}
