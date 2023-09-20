package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_2 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    By EmailTextBox = By.id("Email");
    By AgeUnder18 = By.id("under_18");
    By AgeDisable = By.id("radio-disabled");
    By EducationTextBox = By.id("edu");
    By JobRol1DropDown = By.id("job1");
    By JobRol2MDropDown = By.id("job2");
    By JobRol3RopDown = By.id("job3");

    By HoverUser5 = By.xpath("//h5[text()='Name: User5']");
    By DevelopmentCheckbox = By.id("development");
    By Slider1 = By.id("slider-1");
    By Slider2 = By.id("slider-2");
    By PasswordTextBox = By.id("disable_password");
    By RadioDisable = By.id("radio-disabled");
    By Bio = By.id("bio");
    By InterestsDisable = By.id("check-disbaled");
    By javacheckbox = By.id("java");

    public void SleepinSecond(long time) {
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

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Check email display
        if (driver.findElement(EmailTextBox).isDisplayed()) {
            driver.findElement(EmailTextBox).sendKeys("Automation Testing");
            System.out.println("Email is display");
        } else {
            System.out.println("Email is not display");
        }
        //check age display
        if (driver.findElement(AgeUnder18).isDisplayed()) {
            driver.findElement(AgeUnder18).click();
            System.out.println("Age is display");
        } else {
            System.out.println("Age is not display");
        }
        //check education display
        if (driver.findElement(EducationTextBox).isDisplayed()) {
            System.out.println("Education is display");
        } else {
            System.out.println("Education is not display");
        }
        //check hover to user 5
        if (driver.findElement(HoverUser5).isDisplayed()) {
            System.out.println("User5 is display");
        } else {
            System.out.println("User5 is not display");
        }
    }

    @Test
    public void TC_02_isEnable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //check email
        if (driver.findElement(EmailTextBox).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check age
        if (driver.findElement(AgeUnder18).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check education
        if (driver.findElement(EducationTextBox).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check job role1
        if (driver.findElement(JobRol1DropDown).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check job role2
        if (driver.findElement(JobRol2MDropDown).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //development checkbox
        if (driver.findElement(DevelopmentCheckbox).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check slider-1
        if (driver.findElement(Slider1).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check Password
        if (driver.findElement(PasswordTextBox).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check Age Disable
        if (driver.findElement(AgeDisable).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //check Age Disable
        if (driver.findElement(AgeDisable).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //Check Biography
        if (driver.findElement(Bio).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //Check Job role 3
        if (driver.findElement(JobRol3RopDown).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //Check Interests Disable
        if (driver.findElement(InterestsDisable).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
        //Check Slider2
        if (driver.findElement(Slider2).isEnabled()) {
            System.out.println("Element is enable");
        } else {
            System.out.println("Element is not enable");
        }
    }

    @Test
    public void TC_03_IsSlected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(AgeUnder18).click();
        driver.findElement(AgeUnder18).isSelected();

        driver.findElement(javacheckbox).click();
        Assert.assertTrue(driver.findElement(javacheckbox).isSelected());
        driver.findElement(javacheckbox).click();
        Assert.assertFalse(driver.findElement(javacheckbox).isSelected());


    }

    //Register function at MailChimp
    @Test
    public void TC_04_Register() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("Nhung@gmail.com");
        SleepinSecond(2);
        //one lowercase character
        driver.findElement(By.id("new_password")).sendKeys("a");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        driver.findElement(By.id("new_password")).clear();
        SleepinSecond(2);
        //one uppercase character
        driver.findElement(By.id("new_password")).sendKeys("aA");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        driver.findElement(By.id("new_password")).clear();
        SleepinSecond(2);
        //One number
        driver.findElement(By.id("new_password")).sendKeys("1");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        driver.findElement(By.id("new_password")).clear();
        SleepinSecond(2);
        //One special character
        driver.findElement(By.id("new_password")).sendKeys("*");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        SleepinSecond(2);
        //8 characters minimum
        driver.findElement(By.id("new_password")).sendKeys("hihuhihn");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        driver.findElement(By.id("new_password")).clear();
        SleepinSecond(2);
        //completed new password
        driver.findElement(By.id("new_password")).sendKeys("Hongnhung2@");
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
