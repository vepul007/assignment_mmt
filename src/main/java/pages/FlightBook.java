package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBook extends PageObject {

    @FindBy(xpath = "//button[@class='addTravellerBtn']")
    private WebElement add_adult;

    @FindBy(xpath="//input[@placeholder='First & Middle Name']")
    private WebElement first_name;

    @FindBy(xpath="//input[@placeholder='Last Name']")
    private WebElement last_name;

    @FindBy(xpath = "//div[@class='selectTab ']/div")
    private WebElement gender_list;

    @FindBy(xpath = "//input[@placeholder='Mobile No']")
    private WebElement mobile_no;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement email;

    @FindBy(xpath="//div[@class='emailId']")
    private WebElement email_click;

    @FindBy(xpath = "//div[@class='insBottomSection']/div[1]/label/span[@class='customRadioBtn']/span")
    private WebElement secure_trip;

    @FindBy(xpath = "//button[@class='lato-black button buttonPrimary extraPadBtn fontSize16 ']")
    private WebElement continue_button;

    @FindBy(xpath ="//p[@class=\"fareRow\"]/span[2]")
    private WebElement total_price_itenary;

    @FindBy(xpath="//div[@class='detailsPopupFooter']/button")
    private WebElement confirm_button;

    @FindBy(xpath="//p[@class='seatBookingOverlayCta']/button")
    private WebElement free_seat;

    @FindBy(xpath="//span[@class='fontSize16 boldFont appendRight20 linkText ']")
    private WebElement web_check_in_dailog;

    public FlightBook(WebDriver driver) {
        super(driver);
    }

    public void addAdultClick(){
        this.add_adult.click();
    }

    public void setFirst_name(String name){
        this.first_name.clear();
        this.first_name.sendKeys(name);
    }

    public void setLast_name(String name){
        this.last_name.clear();
        this.last_name.sendKeys(name);
    }

    public void setGender(String gender){
         if(gender.equalsIgnoreCase("MALE")){
             this.gender_list.findElement(By.xpath("label[1]")).click();
         }
         else if(gender.equalsIgnoreCase("FEMALE")){
             this.gender_list.findElement(By.xpath("label[2]")).click();
         }
    }

    public void setMobile_no(String no){
        this.mobile_no.clear();
        this.mobile_no.sendKeys(no);
    }

    public void setEmail(String email){
        this.email.clear();
        this.email.sendKeys(email);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.email_click.click();
    }

    public void secureTripClick(){
        this.secure_trip.click();
    }

    public String getTotalpriceItenary(){
        return Utility.getOnlyNumberString(total_price_itenary.getText());
    }

    public void continueButtonClick(){
        this.continue_button.click();
    }

    public void confirmButtonClcick() {
        this.confirm_button.click();
    }

    public void freeSeatClick(){
        try {
            this.free_seat.click();
        }catch (Exception ignored) {
        }
    }

    public void clickWebCheckIn(){
        try{
            this.web_check_in_dailog.click();
        }catch (Exception ignored){

        }
    }

}
