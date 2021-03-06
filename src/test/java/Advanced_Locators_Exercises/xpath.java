package Advanced_Locators_Exercises;

import PO.BasePage;
import PO.GoogleBasePage;
import Utilities.BrowserUtils;
import Utilities.Driver;
import Utilities.RemoteDriver;
import Utilities.StringNameGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class xpath extends BasePage {
    WebDriver driver;
    Map<String, String> bookPrice = new HashMap<>();

    @Test(dataProvider = "getData")
    public void parallelTest(String browser) throws MalformedURLException, InterruptedException {
        WebDriver driver = RemoteDriver.getRemoteDriver(browser);

        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        String bookName = StringNameGenerator.bookName();
        System.out.println("bookName = " + bookName);
        Thread.sleep(5000);
        System.out.println("driver.getTitle() = " + driver.getTitle());
        try {
            searchBar.sendKeys(bookName);
            System.out.println("yolladim");
            searchBar.sendKeys(Keys.TAB, Keys.ENTER);
        } catch (Exception e) {
            System.out.println("I could not find the search bar");
            e.printStackTrace();
        }
        bookPrice.put(bookName, price(1));
        System.out.println("bookPrice = " + bookPrice);
        driver.quit();
    }

    @DataProvider(parallel = true)
    public Object[][] getData() {
        return new Object[][]{{"chrome"}, {"firefox"}};
    }


    @Test
    public void test2() {
        Driver.get().manage().window().maximize();
        Driver.get().get("https://www.amazon.com/");
        List<WebElement> tags = Driver.get().findElements(By.tagName("a"));
        Boolean flag = IntStream.range(1, tags.size())
                .anyMatch(x -> tags.get(x).getText().equals("Semih"));
        System.out.println(flag == true ? "link var" : "link yok");

    }

    @Test
    public void test23() {
        Driver.get().manage().window().maximize();
        Driver.get().get("https://www.google.com/");
        Driver.get().switchTo().frame(0);
        BrowserUtils.waitFor(2);
        try {
            Driver.get().findElement(By.xpath("(//span[@class='RveJvd snByac'])[3]")).click();
        } catch (Exception e) {
            System.out.println("No LOCATOR");
        }
        GoogleBasePage basePage = new GoogleBasePage();
        basePage.searchBar.sendKeys("Asli Pirdal", Keys.ENTER);
        BrowserUtils.waitFor(2);
        try {
            System.out.println("basePage.firstItem.getText() = " + basePage.firstItem.getText());
        } catch (Exception e) {
            System.out.println("Unutamadim");
            ;
        }
    }

    public static int test2(int num1, int num2) {
        return num1 > num2 ? num1 + num2 : num1 * num2;

    }

    public static int test3(int num1, int num2) {
        int toplam = 0;
        for (int i = num1; i < num2; i++) {
            toplam = i % 2 != 0 ? toplam + i : toplam;
        }
        return toplam;
    }

    public static void main(String[] args) {
        System.out.println(test2(9, 10));
        System.out.println(test3(0, 10));
    }
}
