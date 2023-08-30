package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    //setup driver
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Mac OS")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_EmptyData() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        //check message
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_InvalidEmail() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //fill information
        driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@12333.");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@12333.");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0376878990");
        //click button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        //check message
//        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
//        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "");
//        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "");
//        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "");
    }

    @Test
    public void TC_03_IncorrectConfirmEmail() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //fill information
        driver.findElement(By.id("txtFirstname")).sendKeys("Hong Nhung");
        driver.findElement(By.id("txtEmail")).sendKeys("1234@12333");
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@1233w");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0376878990");
        //click button
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }
    

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
