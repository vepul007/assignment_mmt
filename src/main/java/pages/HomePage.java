package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Utility;

import java.util.concurrent.TimeUnit;

public class HomePage extends PageObject {
    @FindBy(xpath="//li[@data-cy=\"account\"]")
    private WebElement accountPopUp;

    @FindBy(xpath = "//div[@class=\"autopop__wrap makeFlex column defaultCursor\"]")
    private WebElement miniPopUp;

    @FindBy(xpath="//li[@class=\"menu_Flights\"]")
    private WebElement menu_flight;

    @FindBy(xpath = "//div[@data-cy=\"outside\"]")
    private WebElement outside_click;

    @FindBy(xpath = "//li[@data-cy=\"oneWayTrip\"]")
    private WebElement trip_type;

    @FindBy(xpath = "//label[@for=\"fromCity\"]")
    private WebElement from_city;

    @FindBy(xpath = "//input[@placeholder=\"From\"]")
    private WebElement from_city_text;

    @FindBy(xpath = "//ul[@role=\"listbox\"]/li[1]")
    private WebElement city_dropdown_from;

    @FindBy(xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]")
    private WebElement city_dropdown_to;

    @FindBy(xpath = "//label[@for=\"toCity\"]")
    private WebElement to_city;

    @FindBy(xpath = "//input[@placeholder=\"To\"]")
    private WebElement to_city_text;

   @FindBy(xpath = "//a[contains(text(),'Search')]")
   private WebElement search_flight;

   @FindBy(xpath="//span[@class='chNavIcon appendBottom2 chSprite chHotels']")
   private WebElement hotel_button;

   @FindBy(xpath="//input[@data-cy='city']")
   private WebElement hotel_search_bar;

   @FindBy(xpath="//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
   private WebElement hotel_search_text;

   @FindBy(xpath="//p[@class='locusLabel appendBottom5']")
   private WebElement hotel_list;

   @FindBy(xpath = "//button[@data-cy='submit']")
   private WebElement hotel_search;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void flightMenuClick(){
        this.menu_flight.click();
    }

    public void accountPopUpClick() {

        try {
            if (this.miniPopUp.isDisplayed()) {
                this.accountPopUp.click();
            }
        } catch (Exception ignored) {

        }
    }


    public void tripTypeClick(){
        this.trip_type.click();
    }

    public  void setFromCity(String fromCity) throws InterruptedException {
        this.from_city.click();
        this.from_city_text.clear();
        Utility.sendKeysOptimized(from_city_text,fromCity);
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        this.city_dropdown_from.click();
        Thread.sleep(1000);
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    public void setToCity(String toCity) throws InterruptedException {
        this.to_city_text.clear();
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        Utility.sendKeysOptimized(to_city_text,toCity);
        Thread.sleep(1000);
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        this.city_dropdown_to.click();
    }

    public void searchFlightClick(){
        this.search_flight.click();
    }
    public void hotelButtonClick(){this.hotel_button.click();}
    public void setHotel_search_bar(String city) throws InterruptedException {
        this.hotel_search_bar.click();
        Utility.sendKeysOptimized(hotel_search_text,city);
        this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        this.hotel_list.click();
    }
    public void hotelSearchClick(){this.hotel_search.click();}
}
