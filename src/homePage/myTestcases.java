package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestcases {

	WebDriver driver = new ChromeDriver();
	String AlMosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanage = "en";
	Random rand = new Random();
	String language = driver.findElement(By.tagName("html")).getAttribute("lang");

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(AlMosaferURL);

		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
	}

	@Test(priority = 1)
	public void CheckTheDefaultLangugeIsEnglish() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);

		if (ActualLanguage.equals(ExpectedDefaultLanage)) {

			System.out.println("Defualt language is English ");

		}
	}

	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));

		String ActualCurrency = Currency.getText();

		System.out.println("The currency is : " + ActualCurrency);

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		System.out.println("The Number is : " + ActualContactNumber);

		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		boolean ExpectedResultsForTheLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		WebElement logo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));

		boolean ActualResultForThelogo = logo.isDisplayed();

		System.out.println("The logo shown status : " + ActualResultForThelogo);

		Assert.assertEquals(ActualResultForThelogo, ExpectedResultsForTheLogo);

	}

	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {
		String expectedValue = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTab.getAttribute("aria-selected");

		System.out.println("The value is : " + ActualValue);

		Assert.assertEquals(ActualValue, expectedValue);

	}

	@Test(priority = 6)

	public void CheckDepatureDate() {

		LocalDate todayDate = LocalDate.now();

		int Today = todayDate.getDayOfMonth();

		int Tomorrow = todayDate.plusDays(1).getDayOfMonth();
		int ThedayAfterTomorrow = todayDate.plusDays(2).getDayOfMonth();

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualreturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
		Assert.assertEquals(ActualreturnDateAsInt, ThedayAfterTomorrow);

	}

	@Test(priority = 7)

	public void RandomlyChangeTheLanguage() {
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomIndex = rand.nextInt(URLs.length);

		System.out.println("The Website is : " + URLs[RandomIndex]);

		driver.get(URLs[RandomIndex]);

	}

	@Test(priority = 8)
	public void hotelSearch() {

		WebElement hotelsTab = driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels"));
		hotelsTab.click();

		String language = driver.findElement(By.tagName("html")).getAttribute("lang");

		WebElement hotelsInput = driver.findElement(By.tagName("input"));

		String[] enCities = { "Riyadh", "Jeddah", "Dubai" };
		String[] arCities = { "الرياض", "جدة", "دبي" };

		int enCitiesSelect = enCities.length;
		int arCitiesSelect = arCities.length;

		int enRandomIndex = rand.nextInt(enCitiesSelect);
		int arRandomIndex = rand.nextInt(arCitiesSelect);

		if (language.equals("en"))

		{

			hotelsInput.sendKeys(enCities[enRandomIndex]);
			hotelsInput.click();
			System.out.println("The city is : " + enCities[enRandomIndex]);

		}

		else if (language.equals("ar")) {

			hotelsInput.sendKeys(arCities[arRandomIndex]);
			hotelsInput.click();
			System.out.println("The city is : " + arCities[arRandomIndex]);

		}

	}

	@Test(priority = 9)
	public void checkRandomSelect() {

		WebElement select = driver.findElement(By.tagName("select"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		int optionsSize = options.size() - 1;
		int randomOptions = rand.nextInt(optionsSize);

		WebElement randomOption = options.get(randomOptions);
		randomOption.click();

		String value = options.get(randomOptions).getAttribute("value");

		System.out.println("Option sizes : " + optionsSize);
		System.out.println("The random option is : " + randomOptions);
		System.out.println("Check random select value : " + value);

	}

	@Test(priority = 10)
	public void searchHotelsButton() {
		
		WebElement searchHotelButtonEn = driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk"));
		searchHotelButtonEn.click();
		Assert.assertEquals(".sc-1vkdpp9-5.btwWVk", ".sc-1vkdpp9-5.btwWVk");
		
		
		

	}

}