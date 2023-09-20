package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_ExerciseLogin {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    public void SleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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

    //Login with empty email and password
    @Test
    public void TC_01() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//child::a[text()='My Account']")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("send2")).click();
        //verify message
        Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
    }

    //login invalid email
    @Test
    public void TC_02() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//child::a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        Assert.assertTrue(driver.findElement(By.id("advice-validate-email-email")).isDisplayed());
    }

    //login with password <6 characters
    @Test
    public void TC_03() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//child::a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("456");
        driver.findElement(By.id("send2")).click();
        Assert.assertTrue(driver.findElement(By.id("advice-validate-password-pass")).isDisplayed());
    }

    //Login with incorrect email/password
    @Test
    public void TC_04() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//child::a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123123");
        driver.findElement(By.id("send2")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='error-msg']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
