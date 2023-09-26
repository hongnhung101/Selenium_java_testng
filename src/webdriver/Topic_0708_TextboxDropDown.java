package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_0708_TextboxDropDown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String Email, FirstName, LastName, Password, MiddleName, Username, NumPhone, Comments;
    Random rand;

    public void SleepInSecond(long Time) {
        try {
            Thread.sleep(Time * 1000);
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
        rand = new Random();
//        EmployeeID = String.valueOf(rand.nextInt(999999));


    }

    //Handle textbox/textarea
    @Test
    public void TC_01() {
        Email = "Automationnhnh" + rand.nextInt(9999) + "@gmail.com";
        FirstName = "Auto";
        LastName = "Test";
        Password = "Test1230#$";
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //fill information

        driver.findElement(By.id("firstname")).sendKeys(FirstName);
        driver.findElement(By.id("lastname")).sendKeys(LastName);
        driver.findElement(By.id("email_address")).sendKeys(Email);
        driver.findElement(By.id("password")).sendKeys(Password);
        driver.findElement(By.id("confirmation")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
//        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='box-content']//p")).getText(),
//                FirstName + " " + LastName + "\n" + Email + "\n" + "Change Password");
        String ContactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div//p")).getText();
        Assert.assertTrue(ContactInformation.contains(FirstName));
        //logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        SleepInSecond(5);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/logoutSuccess/");

    }

    //Handle textbox/textarea
    @Test
    public void TC_02() {
//        EmployeeID = String.valueOf(rand.nextInt(999999));
        FirstName = "Auto";
        MiddleName = "Test";
        Username = "AutoTest" + rand.nextInt(999);
        LastName = "FC";
        Password = "Test1230#$";
        NumPhone = "0123123129R" + rand.nextLong(9);
        Comments = "Automation Testing Java";


        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        SleepInSecond(2);
        /*
        note: Add new EmployeeID:
        Actions action;
        action = new Actions(driver);
        driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input").sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input").sendKeys(EmployeeID);
        */
        String EmployeeID = driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input"))
                .getAttribute("value");
        System.out.println("EmployeeID:" + EmployeeID);
        SleepInSecond(5);
        //create login detail
        driver.findElement(By.xpath("//p[text()='Create Login Details']//parent::div//span")).click();
        //fill information username/passsword/confirmpassword
        driver.findElement(By.name("firstName")).sendKeys(FirstName);
        driver.findElement(By.name("middleName")).sendKeys(MiddleName);
        driver.findElement(By.name("lastName")).sendKeys(LastName);
        driver.findElement(By.xpath("//label[text()='Username']//parent::div//following-sibling::div//input")).sendKeys(Username);
        driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div//input")).sendKeys(Password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div//input")).sendKeys(Password);
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();
        SleepInSecond(10);
        //verify information detail
        Assert.assertTrue(driver.findElement(By.name("firstName")).getAttribute("value").contains(FirstName));
        Assert.assertTrue(driver.findElement(By.name("middleName")).getAttribute("value").contains(MiddleName));
        Assert.assertTrue(driver.findElement(By.name("lastName")).getAttribute("value").contains(LastName));
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div//input")).
                getAttribute("value").contains(EmployeeID));
        SleepInSecond(5);
        //verify information immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button[text()=' Add ']")).click();
        driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div//input")).sendKeys(NumPhone);
        driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div//textarea")).sendKeys(Comments);
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();
        SleepInSecond(5);
        driver.findElement(By.xpath("//button//i[@class='oxd-icon bi-pencil-fill']")).click();
        SleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div//input"))
                .getAttribute("value"), NumPhone);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div//textarea"))
                .getAttribute("value"), Comments);

        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        SleepInSecond(5);
        driver.findElement(By.name("username")).sendKeys(Username);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        SleepInSecond(2);
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        SleepInSecond(3);
        driver.findElement(By.xpath("//button//i[@class='oxd-icon bi-pencil-fill']")).click();
        SleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']//parent::div//following-sibling::div//input"))
                .getAttribute("value"), NumPhone);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']//parent::div//following-sibling::div//textarea"))
                .getAttribute("value"), Comments);
    }

    @Test
    public void TC_03() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
