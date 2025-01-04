package gui.registration;

import com.GG.POM.HomePage;
import com.GG.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import static com.GG.POM.HomePage.HOME_PAGE_URL;

public class RegistrationHappyPath extends BaseTest {

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {

        String USERNAME = RegistrationDataGenerator.createUser();
        String EMAIL = RegistrationDataGenerator.createEmail();
        String PASSWORD = RegistrationDataGenerator.createPasswordFor();
        String REGISTRATION_SUCCESSFUL_MSG = "Successful register!";
        String BIRTHDATE = "12221989";
        String PUBLIC_INFO = "testGeorgi";

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        log.info("STEP 1 Not registered user is landing on Iskillo register page");
        log.info("STEP 1.1 Verify that the sign up title is presented!");
        registrationPage.navigateToRegPage();
        boolean isSignUpTittleShown = registrationPage.isSignUpTitleShown();
        Assert.assertTrue(isSignUpTittleShown);
        log.info("STEP 1.2 Verify that the login nav link is presented!");
        boolean isLoginNavLinkShown = registrationPage.isNavLoginLinkShown();
        Assert.assertTrue(isLoginNavLinkShown);
        log.info("STEP 2 Input user name");
        registrationPage.inputUserName(USERNAME);
        log.info("STEP 2.1 Input email");
        registrationPage.inputEmail(EMAIL);
        log.info("STEP 2.2 Input birth date");
        registrationPage.inputBirthDate(BIRTHDATE);
        log.info("STEP 2.3 Input password");
        registrationPage.inputPassword(PASSWORD);
        log.info("STEP 2.4 Input confirm password");
        registrationPage.inputConfirmPassword(PASSWORD);
        log.info("STEP 2.1 Input public info");
        registrationPage.inputPublicInfo(PUBLIC_INFO);
        log.info("STEP 3 Click on login button");
        registrationPage.clickOnSIgnInButton();
        log.info("Step 3.1 Verify that the message" + registrationPage.getRegisterActionMessage() + " is shown");
        Assert.assertEquals(registrationPage.getRegisterActionMessage(), REGISTRATION_SUCCESSFUL_MSG);
        log.info("STEP 4 The registration is successful and the user is redirected to Home Page");
        log.info("STEP 4.1 Verify the Profile link is presented!");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isShownNavBarProfileLink = homePage.isNavProfileShown();
        Assert.assertTrue(isShownNavBarProfileLink);
        log.info("STEP 4.2 Verify the New post link is presented!");
        boolean isShownNavBarNewPostLink = homePage.isNavNewPostShown();
        Assert.assertTrue(isShownNavBarNewPostLink);
        log.info("STEP 4.2 Verify the LogIn link is NOT presented!");
        homePage.isNavLogInLinkVisible();
        boolean isURLLoaded = homePage.isURLLoaded(HOME_PAGE_URL);
        Assert.assertTrue(isURLLoaded);

        Thread.sleep(4444);
    }
}
