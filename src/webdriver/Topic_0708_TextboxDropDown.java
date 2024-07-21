package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_0708_TextboxDropDown {
    WebDriver driver;
    WebDriverWait explicitWait;
    Select select;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String Email, FirstName, LastName, Password, MiddleName, Username, NumPhone, Comments, DateOB, MonthOB, YearOB;
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
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

    //Handle textbox textarea
    @Test
    public void TC_03_AddNewCustomer() {
        Email = "automationnh" + rand.nextInt(9999) + "@gmail.com";
        DateOB = "10";
        MonthOB = "01";
        YearOB = "2000";
        String NameCus = "Auto Testing";
        String Address = "TPHCM";
        //Create account
        driver.get("https://demo.guru99.com/");
        driver.findElement(By.name("emailid")).sendKeys(Email);
        driver.findElement(By.name("btnLogin")).click();
        String UserID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        String Password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
        driver.get("http://demo.guru99.com/v4");
        driver.findElement(By.name("uid")).sendKeys(UserID);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.name("btnLogin")).click();
        //New customer
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td//input")).sendKeys(NameCus);
        driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td//input[@value='m']")).click();
//        WebElement Gender = driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td//input[@value='m']"));
//        Gender.click();
//        String GenderText = Gender.getText();
        WebElement DateOfBirth = driver.findElement(By.xpath("//td[text()='Date of Birth']//following-sibling::td//input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('type','date')", DateOfBirth);
        DateOfBirth.clear();
        DateOfBirth.sendKeys(DateOB + "/" + MonthOB + "/" + YearOB);
        driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td//textarea")).sendKeys(Address);
        driver.findElement(By.xpath("//td[text()='City']//following-sibling::td//input")).sendKeys(Address);
        driver.findElement(By.xpath("//td[text()='State']//following-sibling::td//input")).sendKeys(Address);
        driver.findElement(By.xpath("//td[text()='PIN']//following-sibling::td//input")).sendKeys("123456");
        driver.findElement(By.xpath("//td[text()='Mobile Number']//following-sibling::td//input")).sendKeys("0234332333");
        driver.findElement(By.xpath("//td[text()='E-mail']//following-sibling::td//input")).sendKeys(Email);
        driver.findElement(By.xpath("//td[text()='Password']//following-sibling::td//input")).sendKeys(Password);
        driver.findElement(By.xpath("//td//input[@value='Submit']")).click();
        SleepInSecond(10);
        //verify customer
        System.out.println("Customer ID:" + driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText());
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), NameCus);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(), "male");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), YearOB + "-" + DateOB + "-" + MonthOB);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), Address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), "123456");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), "0234332333");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), Email);
        //Edit customer
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td//input")).sendKeys(UserID);
        driver.findElement(By.name("AccSubmit")).click();
    }

    @Test
    public void TC_04_DropdownDefault() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        Password = "Abc12494#hdh";
        Email = "automation" + rand.nextInt() + "@gmail.com";
        //your personal details
        driver.findElement(By.cssSelector("span.female")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        driver.findElement(By.id("LastName")).sendKeys("Test");
        //PickDate
        Select DateOfBirthDropDown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(DateOfBirthDropDown.getOptions().size(), 32);
        DateOfBirthDropDown.selectByVisibleText("10");
        //PickMonth
        Select DateOfBirthMonthDropDown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(DateOfBirthMonthDropDown.getOptions().size(), 13);
        DateOfBirthMonthDropDown.selectByVisibleText("January");
//        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("January");
        //pickYear
        Select DateOfBirthYearDropDown = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(DateOfBirthYearDropDown.getOptions().size(), 112);
        DateOfBirthYearDropDown.selectByVisibleText("2000");
        driver.findElement(By.id("Email")).sendKeys(Email);
        driver.findElement(By.id("Password")).sendKeys(Password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(Password);
        driver.findElement(By.xpath("//button[text()='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page registration-result-page']//following-sibling::div//div[@class='result']")).getText(), "Your registration completed");
        //verify information account
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        driver.findElement(By.xpath("//a[text()='Log in']")).click();
        driver.findElement(By.id("Email")).sendKeys(Email);
        driver.findElement(By.id("Password")).sendKeys(Password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        driver.findElement(By.xpath("//div[@class='header']//preceding-sibling::li//a[text()='My account']")).click();
        SleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), "Automation");
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), "Test");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"), "10");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"), "1");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), "2000");
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), Email);
    }

    @Test
    public void TC_05_Dropdown2() {
        driver.get("https://www.rode.com/wheretobuy");
        Select CountryDropdown = new Select(driver.findElement(By.id("country")));
        Assert.assertFalse(CountryDropdown.isMultiple());
        CountryDropdown.selectByVisibleText("Vietnam");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        List<WebElement> DealerList = driver.findElements(By.xpath("//h3[text()='Dealers']//parent::div//preceding-sibling::h4"));
        for (WebElement webElement : DealerList) {
            String name = webElement.getText();
            System.out.println(name);
        }
        System.out.println(DealerList.size());
    }
//
//    @Test
//    public void TC_06_HTMLDropDown() {
//        driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
//    }

    @Test
    public void TC_07_CustomDropdown() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        SelectItemDropDown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
        System.out.println("Done");

    }

    @Test
    public void TC_07_02ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectItemDropDown("div#root", "div.visible.menu.transition div[role='option']", "Christian");
        SleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
        System.out.println("Done");
    }

    @Test
    public void TC_07_03VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        SelectItemDropDown("div.btn-group", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }


    @Test
    public void TC_07_04Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        EnterAndSelectItemDropDown("input.search", "div[role='listbox']", "Belarus");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belarus");
    }

    @Test
    public void TC_08_MultipleSelect() {
        driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        driver.findElement(By.cssSelector("button.ms-choice")).click();
//        SelectItemDropDown("button.ms-choice", "div.ms-parent.multiple-select.ms-parent-open div.ms-drop.bottom span", "January");
//        SelectItemDropDown("button.ms-choice", "div.ms-parent.multiple-select.ms-parent-open div.ms-drop.bottom span", "February");
//        SelectItemDropDown("button.ms-choice", "div.ms-parent.multiple-select.ms-parent-open div.ms-drop.bottom span", "March");
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='January']")).click();
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='February']")).click();
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='March']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".ms-parent-open button:nth-child(1) span")).getText(), "January, February, March");
        System.out.println("Show correctly <= 3item");
        driver.findElement(By.cssSelector("button.ms-choice")).click();
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='April']")).click();
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='May']")).click();
        driver.findElement(By.xpath("//div[@class='ms-parent multiple-select ms-parent-open']//following-sibling::span[text()='June']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ms-parent.multiple-select.ms-parent-open button.ms-choice span")).getText(), "6 of 12 selected");
    }

    public void SelectItemDropDown(String ParentCSS, String AllItem, String ExpectedTextItem) {
        //click any tag to expand dropdown
        driver.findElement(By.cssSelector(ParentCSS)).click();
        //Waiting for all item to load successfully
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(AllItem)));
        List<WebElement> SpeedDropdownItems = driver.findElements(By.cssSelector(AllItem));
        for (WebElement tempItem : SpeedDropdownItems) {
            String itemText = tempItem.getText();
            System.out.println(itemText);

            if (itemText.trim().equals(ExpectedTextItem)) {
                tempItem.click();
                break;
            }

        }
    }

    public void EnterAndSelectItemDropDown(String TextBoxCSS, String AllItem, String ExpectedTextItem) {
        //click any tag to expand dropdown
        driver.findElement(By.cssSelector(TextBoxCSS)).clear();
        driver.findElement(By.cssSelector(TextBoxCSS)).sendKeys(ExpectedTextItem);
        //Waiting for all item to load successfully
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(AllItem)));
        List<WebElement> SpeedDropdownItems = driver.findElements(By.cssSelector(AllItem));
        for (WebElement tempItem : SpeedDropdownItems) {
            String itemText = tempItem.getText();
            System.out.println(itemText);

            if (itemText.trim().equals(ExpectedTextItem)) {
                tempItem.click();
                break;
            }

        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
