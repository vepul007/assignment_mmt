package testCases;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.HotelSarchResults;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class stepDefsHotel {
    private stepDefsFlight stepdefsflight;

    public stepDefsHotel(stepDefsFlight stepdefsflight){
        this.stepdefsflight=stepdefsflight;
    }

    HomePage page;
    HotelSarchResults hotel;

    @Then("I search for hotels in Mumbai")
    public void iSearchForHotelsInMumbai() {
        WebDriver driver = stepdefsflight.getDriver();
        page = new HomePage(driver);
        page.accountPopUpClick();
        page.hotelButtonClick();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("I provide location as {string}")
    public void iProvideLocationAs(String city) throws InterruptedException {
        WebDriver driver = stepdefsflight.getDriver();
        page = new HomePage(driver);
        page.setHotel_search_bar(city);
        Date check_in_date = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(check_in_date);
        c1.add(Calendar.DATE, 1);
        int dayOfWeek_number = c1.get(Calendar.DAY_OF_MONTH);
        String day = Integer.toString(dayOfWeek_number);
        Thread.sleep(4000);
        WebElement dateWidget = driver.findElement(By.xpath("//div[@class='DayPicker-Months']"));
        List<WebElement> checkIn = dateWidget.findElements(By.xpath("//div[@role='gridcell']"));
        for (int i = 0; i < checkIn.size(); i++) {
            try {
                if (day.equalsIgnoreCase(checkIn.get(i).getText()) && !checkIn.get(i).getAttribute("class").equalsIgnoreCase("DayPicker-Day DayPicker-Day--disabled")) {
                    checkIn.get(i).click();
                    break;
                }
            } catch (Exception ignored) {
            }
        }
        Thread.sleep(1000);
        Date check_out_date = new Date();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(check_out_date);
        c2.add(Calendar.DATE, 3);
        dayOfWeek_number = c2.get(Calendar.DAY_OF_MONTH);
        day=Integer.toString(dayOfWeek_number);
        for (int i = 0; i < checkIn.size(); i++) {
            try {
                if (day.equalsIgnoreCase(checkIn.get(i).getText()) && !checkIn.get(i).getAttribute("class").equalsIgnoreCase("DayPicker-Day DayPicker-Day--disabled")) {
                    checkIn.get(i).click();
                    break;
                }
            } catch (Exception ignored) {
            }
        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        page.hotelSearchClick();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Then("I verify if results are getting displayed for Mumbai location or not")
    public void iVerifyIfResultsAreGettingDisplayedForMumbaiLocationOrNot() {
        WebDriver driver = stepdefsflight.getDriver();
        hotel = new HotelSarchResults(driver);
        String actual = "| Showing 970 properties in Mumbai";
        String expected = hotel.resultText();
        Assert.assertEquals(actual,expected);
    }
}
