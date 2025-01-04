package gui.login;

import com.GG.POM.HomePage;
import com.GG.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginNegativePathsTest extends BaseTest {
    private static final String LOGIN_FORM_TITLE = "Sign in";
    private static final String LOGIN_NOT_SUCCESSFUL_MSG = "User not found";
    public static final String LOGIN_PAGE = "/users/login";

    @Test(DataProvider )
    public void verifyUserCannotLoginWithWrongUserName() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1: Already registered user is landing Iskilo loginpage");
        loginPage.navigateToLoginPage();
        //Verification that the user is on login page
        log.info("STEP 1.1 Verify that the login link is presented!");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);
        log.info("STEP 1.2 Verify that the login form is presented!");
        //Verification login form is presented
        String actualLoginPageFormTitle = loginPage.getLoginPageFormTitle();

        Assert.assertEquals(actualLoginPageFormTitle, LOGIN_FORM_TITLE);

        log.info("Step 2 Input WRONG user name");
        loginPage.provideUserName("WRONG");

        log.info("Step 3 Input password");
        loginPage.providePassword("testing");

        log.info("Step 4 Click on Login Button");
        loginPage.clickOnLoginButton();

        //Assertion

        log.info("Step 4.1 Verify that the error message" + loginPage.getLoginActionMessage() + " is shown");
        Assert.assertEquals(loginPage.getLoginActionMessage(), LOGIN_NOT_SUCCESSFUL_MSG);
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible

        log.info("Step 4.2 Verify that the login button form is visible");
        Assert.assertTrue(isShownNavBarLoginLink);

        // Login button from login Form visible
        log.info("Step 4.3 Verify that the URL stays the same");
        boolean isURLLoaded = homePage.isURLLoaded(LOGIN_PAGE);
        Assert.assertTrue(isURLLoaded);
        // page ULR = some
    }

    @Test
    public void verifyUserCannotLoginWithEmptyUserName(){
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing Iskilo loginpage");
        loginPage.navigateToLoginPage();
        //Verification that the user is on login page
        //Verification login form is presented

        loginPage.provideUserName(" ");

        loginPage.providePassword("testing");


        loginPage.clickOnLoginButton();
        //Assertion
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible
        // Login button from login Form visible
        // page ULR = some
    }

    @Test
    public void verifyUserCannotLoginWithWrongPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword("WrongPassword");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword(" ");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyCredentialsData(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
    }

    @AfterMethod
    public void tearDown(){
        if(driver!= null){
            driver.quit();
        }
    }

}