package stepDefs;

import cucumber.api.java.en.Given;
import framework.pages.HomePage;

public class HomePageSteps {

    private HomePage landingPage;

    @Given("^I am on the On-boarding page$")
    public void ValidateHomePage() throws Throwable {
        landingPage = new HomePage().isLandingPage();
    }
}
