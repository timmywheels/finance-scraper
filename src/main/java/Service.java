import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;


public class Service extends Auth {

    public static void run(WebDriver driver, Wait wait){

        try {
    //          7. SCRAPE TABLE
            var Data = new Data();
            System.out.println("Starting scraper...");
            WebElement tableRow = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[1]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div/section/div/section[1]/table/tbody/tr[1]")));

            System.out.println("Table Row: " + tableRow);

            List<WebElement> stockData = driver.findElements(By.cssSelector("#data-util-col > section:nth-child(1) > table > tbody > tr > td"));

            List<List<String>> stockList = new ArrayList<>();

            List<String> singleStock = new ArrayList<>();

            int totalDataPointsPerStock = 4;

            for (WebElement stockDataCell : stockData) {
                singleStock.add(stockDataCell.getAttribute("innerText").trim());

                if (singleStock.size() == totalDataPointsPerStock) {

                    stockList.add(singleStock);
                    String[] companyNameAndSymbol = singleStock.get(0).split("[\\r\\n]+", -1);

                    String symbol = companyNameAndSymbol[0];
                    System.out.println("symbol: " + symbol);

                    String companyName = companyNameAndSymbol[1];
                    System.out.println("companyName: " + companyName);

                    String lastPrice = singleStock.get(1);
                    System.out.println("lastPrice: " + lastPrice);

                    String change = singleStock.get(2);
                    System.out.println("change: " + change);

                    String percentChange = singleStock.get(3);
                    System.out.println("percentChange: " + percentChange);

                    Store.data(symbol, companyName, lastPrice, change, percentChange);

                    singleStock = new ArrayList<>();
                }
            }

            driver.quit();

        } catch (Exception e) {
            System.out.println("Could not scrape table!");
            e.printStackTrace();
            driver.quit();
        }
    }

}