package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightSearchResults extends PageObject {

    @FindBy(xpath = "//div[@class=\"listingLhs \"]/div/div[3]/div/label[1]/div/span[1]")
    private WebElement non_stop_flight;

    @FindBy(xpath = "//div[@class=\"listingLhs \"]/div/div[5]/div/div/div[2]")
    private WebElement flight_timings;

    @FindBy(xpath = "//*[starts-with(@id,'flight_list')]/div/div[2]/div[2]/div")
    private List<WebElement> lowest_prices;

    @FindBy(xpath = "//div[@class='collapse show']/div/div[@class='viewFareRowWrap']/div/div[3]")
    private List<WebElement> fare_row;

    @FindBy(xpath="//button[@class='button buttonSecondry buttonBig fontSize12 relative']")
    private WebElement pay_flight_popUp;

    public FlightSearchResults(WebDriver driver) {
        super(driver);
    }

    public void nonStopFlightClick(){
        try{
            this.pay_flight_popUp.click();
        }catch(Exception ignored){

        }
        this.non_stop_flight.click();
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    public void flightTimingsClick(){
        this.flight_timings.click();
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    public List<WebElement> getLowest_prices(){
        return this.lowest_prices;
    }

    public List<WebElement> getFare_row(){
        return this.fare_row;
    }
}
