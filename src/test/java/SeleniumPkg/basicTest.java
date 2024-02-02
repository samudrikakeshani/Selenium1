package SeleniumPkg;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class basicTest{
WebDriver driver;
SoftAssert sa;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        sa = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

}
    @Test(priority = 2)
    public void valid_login(){
        driver.get("http://65.0.102.64/tms");//load login page
        driver.findElement(By.xpath("//input[@class='k-input-inner']")).sendKeys("demo");//enter valid user-name

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("demo");//enter valid password

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        /***
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@_ngcontent-ng-c1921795930 ='']/p[@class='user-name']")));
        sa.assertEquals(driver.findElement(By.xpath("//div[@_ngcontent-ng-c1921795930 ='']/p[@class='user-name']")).getText(),"demo"); //validate login by capturing an element from home page
        driver.findElement(By.linkText("Maintenance")).click();
        driver.findElement(By.linkText("Vehicle Types")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-navigation-drawer/kendo-drawer-container/kendo-drawer-content/main/app-maintenance/div/button")).click();

       **** navigation path: valid-login-> maintenance-> vehicle types-> new vehicle type
         **/
    }
    @Test(priority = 1)
    public void invalid_login(){
        driver.get("http://65.0.102.64/tms");//load login page
        driver.findElement(By.xpath("//input[@class='k-input-inner']")).sendKeys("demo");//enter invalid user-name

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin");//enter invalid password

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //sa.assertEquals(driver.findElement(By.xpath("//p[@class='login-failed-msg']")).getText(),"User Name & Password Doesn't Match");

    }

   @AfterTest
    public void tearDown(){
        sa.assertAll();
        //driver.quit();
   }
}
