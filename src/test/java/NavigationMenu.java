import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class NavigationMenu {
    private WebDriver driver;

    @BeforeSuite
    public void initDriver() throws Exception {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @BeforeTest
    public void openUrl(){
        this.driver.manage().window().maximize();
        String url = "https://www.harmonize.health/";
        this.driver.get(url);
    }

    @Test
    public void checkNumberOfMenu(){
        int numberOfElement = this.driver.findElements(By.xpath("/html/body/div[2]/div[1]/nav/*")).size();
        Assert.assertEquals(numberOfElement, 6);
    }

    @Test
    public void checkActiveMenu(){
        WebElement menu = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[1]"));
        Assert.assertTrue(menu.getAttribute("class").contains("w--current"));
    }

    @Test
    public void checkMenus(){
        WebElement home = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[1]"));
        WebElement providers = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[2]"));
        WebElement patients = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[3]"));
        WebElement platform = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[4]"));
        WebElement contactUs = this.driver.findElement(By.xpath("/html/body/div[2]/div[1]/nav/a[5]"));
        Assert.assertEquals(home.getText(), "Home");
        Assert.assertEquals(providers.getText(), "Providers");
        Assert.assertEquals(patients.getText(), "Patients");
        Assert.assertEquals(platform.getText(), "Platform");
        Assert.assertEquals(contactUs.getText(), "Contact us");
    }

    @Test
    public void checkDropdownMenu() {
        WebElement dropdownMenu = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-toggle -0\"]"));
        dropdownMenu.click();
        WebElement resources = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-toggle-0\"]/div[2]"));
        WebElement blog = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-0\"]/a[1]"));
        WebElement about = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-0\"]/a[2]"));
        Assert.assertTrue(dropdownMenu.getAttribute("class").contains("w--open"));
        Assert.assertEquals(resources.getText(), "Resources");
        Assert.assertEquals(blog.getText(), "Blog");
        Assert.assertEquals(about.getText(), "About Harmonize");

    }
    @AfterSuite
    public void quitDriver() throws Exception {
        this.driver.quit();
    }
}
