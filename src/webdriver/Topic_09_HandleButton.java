package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_HandleButton {
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

    public void SleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[a='Đăng nhập']")).click();
        WebElement ButtonLogin = driver.findElement(By.cssSelector(".fhs-btn-login"));
        //verify color login button disable
        Assert.assertEquals(Color.fromString(ButtonLogin.getCssValue("color")).asHex().toUpperCase(), "#636363");
        //verify button login is enable after input value
        driver.findElement(By.cssSelector("#login_username")).sendKeys("Nhung@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("123423424");
        Assert.assertTrue(driver.findElement(By.cssSelector("#login_username")).isEnabled());
        Assert.assertEquals(Color.fromString(ButtonLogin.getCssValue("background")).asHex().toUpperCase(), "#C92127");

        //Demo page2
//        driver.get("https://play.goconsensus.com/u5d5156df");
//        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[text()="Continue"]"))
//                .getCssValue("background-color")).asHex().toLowerCase(), "var(--contained-button-hover-background-color)");
    }

    @Test
    public void TC_02_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        WebElement DualzoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input"));
        WebElement RearsideCheckbox = driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input"));
        ClickCheckbox(DualzoneCheckbox);
        Assert.assertTrue(DualzoneCheckbox.isSelected());
        ClickCheckbox(RearsideCheckbox);
        Assert.assertTrue(RearsideCheckbox.isSelected());

        UncheckCheckbox(DualzoneCheckbox);
        Assert.assertFalse(DualzoneCheckbox.isSelected());
        UncheckCheckbox(RearsideCheckbox);
        Assert.assertFalse(RearsideCheckbox.isSelected());
    }

    @Test
    public void TC_03_DefaultCBRD() {
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement SummerRB = driver.findElement(By.xpath("//input[@value='Summer']"));
        ClickCheckbox(SummerRB);
        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement CheckedCB = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));
        WebElement IndeterminateCB = driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input"));
        ClickCheckbox(CheckedCB);
        Assert.assertTrue(CheckedCB.isSelected());
        ClickCheckbox(IndeterminateCB);
        Assert.assertTrue(IndeterminateCB.isSelected());
        UncheckCheckbox(CheckedCB);
        Assert.assertFalse(CheckedCB.isSelected());
        UncheckCheckbox(IndeterminateCB);
        Assert.assertFalse(IndeterminateCB.isSelected());
    }

    @Test
    public void TC_04_SelectAllor1CB() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> AllCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : AllCheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        //Vi da su dung refesh nen can gan lai gia tri allcheckbox
        AllCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : AllCheckbox)
            if (checkbox.getAttribute("value").equals(" Heart Attack ") && !checkbox.isSelected()) {
                checkbox.click();
                Assert.assertTrue(checkbox.isSelected());
            }
    }

    @Test
    public void TC_05_Custom_Radio() {
        driver.get("https://login.ubuntu.com/");
        By Radiobutton = By.xpath("//span[text()='I don’t have an Ubuntu One account']/preceding::input[@type='radio']");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(Radiobutton));

        Assert.assertTrue(driver.findElement(Radiobutton).isSelected());
    }

    public void ClickCheckbox(WebElement Element) {
        if (!Element.isSelected()) {
            Element.click();
            SleepInSecond(2);
        }
    }

    public void UncheckCheckbox(WebElement Element) {
        if (Element.isSelected()) {
            Element.click();
            SleepInSecond(2);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
