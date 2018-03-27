import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
 
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
 
public class WikipediaSteps {
 
    private WebDriver driver;
 
    @Before
    public void before() {
System.setProperty("webdriver.gecko.driver", "C:\\Users\\jegatheseje43\\Downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		
		
		/*ProfilesIni profile = new ProfilesIni();
		
		FirefoxProfile myprofile = profile.getProfile("default");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, myprofile);
		driver = new FirefoxDriver(dc);
		*/
        
        driver = new FirefoxDriver();
        driver.navigate().to("http://en.wikipedia.org");
    }
 
    @After
    public void after() {
        driver.quit();
    }
 
    @Given("^Enter search term '(.*?)'$")
    public void searchFor(String searchTerm) {
        WebElement searchField = driver.findElement(By.id("searchInput"));
        searchField.sendKeys(searchTerm);
    }
 
    @When("^Do search$")
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();
    }
 
    @SuppressWarnings("deprecation")
	@Then("^Multiple results are shown for '(.*?)'$")
    public void assertSingleResult(String searchResult) {
        WebElement results = driver
            .findElement(By.cssSelector("div#mw-content-text.mw-content-ltr p"));
        assertFalse(results.getText().contains(" may refer to:"));
        assertTrue(results.getText().startsWith(searchResult));
    }
}