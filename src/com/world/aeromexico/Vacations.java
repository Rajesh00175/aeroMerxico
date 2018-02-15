/**
 * 
 */
package com.world.aeromexico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Rajesh.Kumar4
 *
 */
public class Vacations {
	WebDriver Driver;
	String testURL = "https://world.aeromexico.com/en/uk";

	@BeforeTest
	public void BrowserSetup() {
		String aeroDriverPathFirefox = "D:\\Testing\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", aeroDriverPathFirefox);
		Driver = new FirefoxDriver();
		Driver.get(testURL);
		Driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Driver.quit();
	}

	@Test(enabled = true)
	public void Vacation() throws ParseException, InterruptedException {
		String ch = "Vacations";
		List<WebElement> menu = Driver.findElements(By.xpath("//ul[@class='menu']/li/a/span"));
		for (WebElement mn : menu) {
			String choices = mn.getText();
			if (choices.equalsIgnoreCase(ch)) {
				mn.click();
				break;
			}
		}

		// Select FROM and TO destinations element
		WebElement htlTravelingFrom = Driver.findElement(By.id("origin-hotel"));
		htlTravelingFrom.click();
		htlTravelingFrom.sendKeys("MAD");
		Thread.sleep(6000);

		/*
		 * WebDriverWait wait1 = new WebDriverWait(Driver,20);
		 * wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
		 * "//ul[@id='ui-id-5']/li")));
		 */
		Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> htltravelingFROM = Driver.findElements(By.id("//*[ui-id-5']"));	
		for (WebElement htltravlFrom : htltravelingFROM) {
			String htlFromDestination = htltravlFrom.getText();
			if (htlFromDestination.equalsIgnoreCase("Madrid (MAD) Aeropuerto de Barajas, Espana")) {
				htltravlFrom.click();
				break;
			}
		}

		WebElement htlTravelingTo = Driver.findElement(By.id("dest-hotel"));
		htlTravelingTo.click();
		Thread.sleep(6000);

		// Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// htlTravelingTo.sendKeys("Ci");

		List<WebElement> htltravelingTO = Driver.findElements(By.xpath("//ul[@id='ui-id-6']/li"));
		for (WebElement htltravlTo : htltravelingTO) {
			String htlToDestination = htltravlTo.getText();
			if (htlToDestination.equalsIgnoreCase("Ciudad de México (MEX)")) {
				htltravlTo.click();
				break;
			}
		}
		// Select Dates
		String testDepartureDate = "30/03/2018";
		SimpleDateFormat source = new SimpleDateFormat("dd/MM/yyyy");
		Date parseDateDeparture = source.parse(testDepartureDate);
		SimpleDateFormat sdfDepartureDate = new SimpleDateFormat("EEE MMMM dd yyyy");
		String departureDate = sdfDepartureDate.format(parseDateDeparture);
		System.out.println("Departure Date :" + departureDate);
		String[] splitDepartureDate = departureDate.split(" ");
		System.out.println(splitDepartureDate[0] + "----" + splitDepartureDate[1] + "---" + splitDepartureDate[2]
				+ "---" + splitDepartureDate[3]);
		String testDepartureDay = splitDepartureDate[2];
		String testDepartureMonth = splitDepartureDate[1];
		System.out.println("Test Departure Day----" + testDepartureDay);
		System.out.println("Test Departure Month ----" + testDepartureMonth);

		String testReturnDate = "31/03/2018";
		SimpleDateFormat returnSource = new SimpleDateFormat("dd/MM/yyyy");
		Date parseReturnDate = returnSource.parse(testReturnDate);
		SimpleDateFormat sdfReturnDate = new SimpleDateFormat("EEE MMMM dd yyyy");
		String returnDate = sdfReturnDate.format(parseReturnDate);
		System.out.println("Return Date :" + returnDate);
		String[] splitReturnDate = returnDate.split(" ");
		System.out.println(splitReturnDate[0] + "----" + splitReturnDate[1] + "---" + splitReturnDate[2] + "---"
				+ splitReturnDate[3]);
		String testReturnDay = splitReturnDate[2];
		String testReturnMonth = splitReturnDate[1];
		System.out.println("Test Return Day ----" + testReturnDay);
		System.out.println("Test Return Month ----" + testReturnMonth);

		// pick month name for departure date Element
		WebElement htlDepartureDateELE;
		htlDepartureDateELE = Driver.findElement(By.id("date-hotel-init"));
		htlDepartureDateELE.click();
		while (!Driver.findElement(By.className("ui-datepicker-title")).getText().contains(testDepartureMonth)) // Handle
																												// the
																												// month
		{
			Driver.findElement(By.linkText("Next")).click();
		}
		int depDateCount = Driver.findElements(By.className("ui-state-default")).size();

		for (int i = 0; i < depDateCount; i++)

		{

			String depDateValue = Driver.findElements(By.className("ui-state-default")).get(i).getText();
			if (depDateValue.equalsIgnoreCase(testDepartureDay)) {
				Driver.findElements(By.className("ui-state-default")).get(i).click();
				break;
			}

		}

		// pick date for Return date Element

		WebElement htlReturnDateELE;
		htlReturnDateELE = Driver.findElement(By.id("date-hotel-end"));
		htlReturnDateELE.click();
		while (!Driver.findElement(By.className("ui-datepicker-title")).getText().contains(testReturnMonth)) // Handle
																												// the
																												// month
		{
			Driver.findElement(By.linkText("Next")).click();
		}

		List<WebElement> return_m = Driver.findElements(By.className("ui-state-default"));

		for (WebElement return_dayPick : return_m) {
			String returnDay = return_dayPick.getText();
			if (returnDay.equalsIgnoreCase(testReturnDay)) {
				return_dayPick.click();
				break;
			}

		}
	}
}
