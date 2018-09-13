package framework.pages;

import framework.core.Page;
import framework.exceptionManager.LocatorNotSetException;
import framework.logger.LogUtils;

import java.util.HashMap;

public class HomePage extends Page {

    HashMap<String, HashMap> locatorMap = new HashMap<>();

    public HomePage() {
        System.out.println("Navigating to "+this.getClass().getSimpleName());
    }

    private HashMap<String, HashMap> eleLoginButton() {
        locatorMap.put("android", new HashMap(){{put("id", "login_button");}});
        locatorMap.put("ios", new HashMap(){{put("id", "Log In Button");}});
        locatorMap.put("web", new HashMap(){{put("id", "navbar-login-link");}});
        return locatorMap;
    }

    private HashMap<String, HashMap> eleSignupButton() {
        locatorMap.put("android", new HashMap(){{put("id", "create_site_button");}});
        locatorMap.put("ios", new HashMap(){{put("id", "Sign up for WordPress.com Button");}});
        locatorMap.put("web", new HashMap(){{put("id", "navbar-getstarted-link");}});
        return locatorMap;
    }

    public LoginPage chooseSignInOption() throws LocatorNotSetException {
        click(eleLoginButton());
        return new LoginPage();
    }

    public SignupPage chooseSignUpOption() throws LocatorNotSetException {
        click(eleSignupButton());
        return new SignupPage();
    }

    public HomePage isLandingPage(){
        if(getAllValues("Looking For:")) {
            LogUtils.INFO("**** On-boarding Page Loaded Successfully  ****");
        }
        return this;
    }
}
