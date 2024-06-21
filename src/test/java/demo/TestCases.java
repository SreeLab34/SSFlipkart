package demo;
import java.util.logging.Level;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void testCase01() throws InterruptedException {
        Wrappers w=new Wrappers(driver);
        System.out.println("Start Test case: Testcase01");
        w.openURL("https://www.flipkart.com/");
        System.out.println("Opened Flipkart site");

        try {
            WebElement notificationFrame = driver.findElement(By.xpath("//div[@class='_1E5Hij']"));
            driver.switchTo().frame(notificationFrame);
            System.out.println("Entered the notification frame");
            WebElement closeNotificationButton = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            closeNotificationButton.click();
            driver.switchTo().defaultContent(); // Switch back to the main content
            System.out.println("Exited the notification frame");
        } catch (Exception e) {
            System.out.println("No notification frame found or couldn't close it.");
        }
       
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        w.waitForElementToBeClickable(searchBox);//click on searchbox
        searchBox.click();
        w.sendKeys(searchBox, "Washing Machine"); //send text in search box
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Sort by popularity 
        WebElement popularitySort = driver.findElement(By.xpath("//div[@class='zg-M3Z'][text()='Popularity']"));
        popularitySort.click();
        Thread.sleep(5000);
       // Find all product ratings and count items with less than 4 stars
        List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='XQDdHH']"));
        int count = 0;
        for (WebElement r : ratings) {
            String ratingText = r.getText();
            double stars = Double.parseDouble(ratingText);
            if (stars < 4.0) {
                count++;
            }
        }
        System.out.println("Number of items with less than 4 stars are " + count);
        System.out.println("End Test case: Testcase01");

    }

    @Test
    public void testCase02()  throws InterruptedException {
        Wrappers w=new Wrappers(driver);
        System.out.println("Start Test case: Testcase02");
        w.openURL("https://www.flipkart.com/");
        System.out.println("Opened Flipkart site");
        
        try {
            WebElement notificationFrame = driver.findElement(By.xpath("//div[@class='_1E5Hij']"));
            driver.switchTo().frame(notificationFrame);
            System.out.println("Entered the notification frame");
            WebElement closeNotificationButton = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            closeNotificationButton.click();
            driver.switchTo().defaultContent(); // Switch back to the main content
            System.out.println("Exited the notification frame");
        } catch (Exception e) {
            System.out.println("No notification frame found or couldn't close it.");
        }
        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        w.waitForElementToBeClickable(searchBox);//click on searchbox
       // searchBox.click();
        w.sendKeys(searchBox, "iPhone"); //send text in search box
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Find all product titles and discount percentages
        List<WebElement> productTitles = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
        List<WebElement> productDiscounts = driver.findElements(By.xpath("//div[@class='UkUFwK']"));
        // Print titles and discount percentages for items with more than 7% discount
        for (int i = 0; i < productTitles.size(); i++) {
            String title = productTitles.get(i).getText();
            String discountText = productDiscounts.get(i).getText().replace("% off", "");
            if (!discountText.isEmpty()) {
                double discount = Double.parseDouble(discountText);
                if (discount > 17.0) {
                    System.out.println("Title: " + title + ", Discount: " + discount + "%");
                }
            }
        }
        System.out.println("End Test case: Testcase02");

    }

    @Test
    public void testCase03()  throws InterruptedException {
        Wrappers w=new Wrappers(driver);
        System.out.println("Start Test case: Testcase03");
        w.openURL("https://www.flipkart.com/");
        System.out.println("Opened Flipkart site");
        
        try {
            WebElement notificationFrame = driver.findElement(By.xpath("//div[@class='_1E5Hij']"));
            driver.switchTo().frame(notificationFrame);
            System.out.println("Entered the notification frame");
            WebElement closeNotificationButton = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            closeNotificationButton.click();
            driver.switchTo().defaultContent(); // Switch back to the main content
            System.out.println("Exited the notification frame");
        } catch (Exception e) {
            System.out.println("No notification frame found or couldn't close it.");
        }
       Thread.sleep(5000);

        WebElement searchBox = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        w.waitForElementToBeClickable(searchBox);//click on searchbox
       // searchBox.click();
        w.sendKeys(searchBox, "Coffee Mug"); //send text in search box
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Filter by 4 stars and above
        WebElement fourStarFilter = driver.findElement(By.xpath("//div[@class='_6i1qKy'][contains(text(),'4')]"));
        fourStarFilter.click();

        // Wait for results to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Find all product titles, image URLs, and review counts
        List<WebElement> productTitles = driver.findElements(By.xpath("//a[@class='wjcEIp']"));
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        List<WebElement> productImageUrls = driver.findElements(By.xpath("//img[@class='DByuf4']"));
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        var productReviews = driver.findElements(By.xpath("//span[@class='Wphh3N']"));
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Create a list to store Product objects (title, imageUrl, reviewCount)
        List<Product> productList = new ArrayList<>();
        // Iterate through each product and store the information
        for (int i = 0; i < productTitles.size(); i++) {
            String title = productTitles.get(i).getText();
            String imageUrl = productImageUrls.get(i).getAttribute("src");
            String reviewCountText = productReviews.get(i).getText().replaceAll("[^0-9]", "");
            int reviewCount = Integer.parseInt(reviewCountText); 
            // Create Product object and add to list
            Product product = new Product(title, imageUrl, reviewCount);
            productList.add(product);
        }
        // Sort productList
        Collections.sort(productList, Comparator.comparingInt(Product::getReviewCount).reversed());
        int count = 0;
        for (Product product : productList) {
            if (count < 5) {
                System.out.println("Title: " + product.getTitle());
                System.out.println("Image URL: " + product.getImageUrl());
                System.out.println(); 
                count++;
            } else {
                break;
            }
        }
        System.out.println("End Test case: Testcase03");
    }
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }

 // Product class to store title, imageUrl, and reviewCount
    private static class Product {
        private String title;
        private String imageUrl;
        private int reviewCount;

        public Product(String title, String imageUrl, int reviewCount) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.reviewCount = reviewCount;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getReviewCount() {
            return reviewCount;
        }
    }
}