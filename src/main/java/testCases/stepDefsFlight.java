package testCases;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.FlightBook;
import pages.FlightSearchResults;
import pages.HomePage;
import utility.Utility;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class stepDefsFlight {
    private static boolean initialized = false;

    private WebDriver driver;

    @Before
    public void setUp() throws Exception{
        if(!initialized){
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");    // Replace path with local
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            initialized=true;
        }
    }
    public WebDriver getDriver(){
        return driver;
    }

    HomePage page;
    FlightSearchResults flight;
    FlightBook booking;

    @When("I launch MakeMyTrip Website")
    public void i_launch_make_my_trip_website() {
        driver.get("http://www.makemytrip.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("I search for flight from {string} to {string}")
    public void i_search_for_flight_from_to(String dep_city, String arr_city) throws InterruptedException {
        page = new HomePage(driver);
        page.accountPopUpClick();
        page.flightMenuClick();
        page.tripTypeClick();
        page.setFromCity(dep_city);
        page.setToCity(arr_city);
    }

    @Then("I select departure date of tomorrow")
    public void iSelectDepartureDateOfTomorrow() throws InterruptedException {
        page = new HomePage(driver);
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        int dayOfWeek_number = c.get(Calendar.DAY_OF_MONTH);
        String day = Integer.toString(dayOfWeek_number);
        Thread.sleep(4000);
        WebElement dateWidget = driver.findElement(By.xpath("//div[@class='DayPicker-Months']"));
        List<WebElement> cellsOfDepartureDate = dateWidget.findElements(By.xpath("//div[@role='gridcell']"));
        for (int i = 0; i < cellsOfDepartureDate.size(); i++) {
            try {
                if (day.equalsIgnoreCase(cellsOfDepartureDate.get(i).getText()) && !cellsOfDepartureDate.get(i).getAttribute("class").equalsIgnoreCase("DayPicker-Day DayPicker-Day--disabled")) {
                    cellsOfDepartureDate.get(i).click();
                    break;
                }
            } catch (Exception ignored) {
            }

        }
        Thread.sleep(2000);
        page.searchFlightClick();
    }

    @Then("I select non stop flight option")
    public void iSelectNonStopFlightOption() {
        flight = new FlightSearchResults(driver);
        flight.nonStopFlightClick();
    }

    @Then("I select {int}AM to {int}PM in depature")
    public void iSelectAMToPMInDepature(int arg0, int arg1) {
        flight = new FlightSearchResults(driver);
        flight.flightTimingsClick();
    }

    @Then("I click on view prices for airline having lowest price value")
    public void iClickOnViewPricesForAirlineHavingLowestPriceValue() {
        flight = new FlightSearchResults(driver);
        ArrayList<WebElement> lowest_prices = new ArrayList<>();
        for(int i=0;i<flight.getLowest_prices().size();i++){
            lowest_prices.add(flight.getLowest_prices().get(i).findElement(By.xpath("div/p")));
        }
        ArrayList<Integer> price = new ArrayList<>();
        for(WebElement ele : lowest_prices){
            String str = Utility.getOnlyNumberString(ele.getText().substring(2));
            price.add(Integer.parseInt(str));
        }
        int lowest_price= price.get(0);
        for(int i=0;i<price.size();i++){
            if(lowest_price>price.get(i)){
                lowest_price = price.get(i);
            }
        }

        int index = price.indexOf(lowest_price);
        System.out.println("Lowest Price is: "+lowest_price+" and index: "+index);
        flight.getLowest_prices().get(index).findElement(By.xpath("button")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }


    @Then("I select book now for lowest price")
    public void iSelectBookNowForLowestPrice() {
        flight = new FlightSearchResults(driver);
        ArrayList<WebElement> fare_row = new ArrayList<>();
        for(int i=0;i<flight.getFare_row().size();i++){
            fare_row.add(flight.getFare_row().get(i).findElement(By.xpath("p")));
        }
        ArrayList<Integer> price = new ArrayList<>();
        for(WebElement ele : fare_row){
            String str = Utility.getOnlyNumberString(ele.getText().substring(2));
            price.add(Integer.parseInt(str));
        }
        int lowest_price= price.get(0);
        for(int i=0;i<price.size();i++){
            if(lowest_price>price.get(i)){
                lowest_price = price.get(i);
            }
        }
        int index = price.indexOf(lowest_price);
        System.out.println("Lowest Fare Price is: "+lowest_price+" and index: "+index);
        flight.getFare_row().get(index).findElement(By.xpath("button")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Then("I select enter passenger details and secure the trip")
    public void iSelectEnterPassengerDetailsAndSecureTheTrip() throws InterruptedException {
        booking = new FlightBook(driver);
        Set<String> all_handles = driver.getWindowHandles();
        for (String nextWindow : all_handles) {
            driver.switchTo().window(nextWindow);
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.secureTripClick();
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.addAdultClick();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.setFirst_name("Shashikant");
        booking.setLast_name("Awasthi");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.setGender("MALE");
        booking.setMobile_no("9794155598");
        booking.setEmail("shashikant29.awasthi@gmail.com");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Then("I clear cookies and quit driver")
    public void iClearCookiesAndQuitDriver() throws InterruptedException {
        Thread.sleep(60000);
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Then("I confirm the passenger and capture the total value on fare summary and assert values on itenary and review page")
    public void iConfirmThePassengerAndCaptureTheTotalValueOnFareSummaryAndAssertValuesOnItenaryAndReviewPage() throws InterruptedException {
        booking = new FlightBook(driver);
        String iternary_total_price = booking.getTotalpriceItenary();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.continueButtonClick();
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.confirmButtonClcick();
        Thread.sleep(1000);
        booking.clickWebCheckIn();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.freeSeatClick();
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        booking.continueButtonClick();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        String review_total_price = booking.getTotalpriceItenary();
        Assert.assertEquals(iternary_total_price,review_total_price);
    }
}
