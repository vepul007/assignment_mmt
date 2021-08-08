package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelSarchResults extends PageObject{
    @FindBy(xpath = "//div[@class='makeFlex spaceBetween hrtlCenter appendTop10']/div/span[2]")
    private WebElement result;

    public HotelSarchResults(WebDriver driver) {
        super(driver);
    }
    public String resultText(){
        return this.result.getText();
    }
}
