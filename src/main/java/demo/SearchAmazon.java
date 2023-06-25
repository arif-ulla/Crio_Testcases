package demo;

//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchAmazon {
    ChromeDriver driver;

    public SearchAmazon() {
        System.out.println("Constructor: SearchAmazon");
        WebDriverManager.chromedriver().timeout(30).setup();        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }    

    public void testCase01() {
        
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");

        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("amazon");
        searchInput.submit();

        // Wait for the search results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if "amazon.in" or "amazon.com" is present in the search results
        String pageSource = driver.getPageSource();
        boolean amazonInPresent = pageSource.contains("amazon.in");
        boolean amazonComPresent = pageSource.contains("amazon.com");

        // Print the result
        if (amazonInPresent || amazonComPresent) {
            System.out.println("Test Passed: 'amazon.in' or 'amazon.com' is present in the search results.");
        } else {
            System.out.println("Test Failed: 'amazon.in' or 'amazon.com' is not present in the search results.");
        }
    }

    public void endTest() {
        System.out.println("End Test case: SearchAmazon");
        driver.close();
        driver.quit();
    }
}
