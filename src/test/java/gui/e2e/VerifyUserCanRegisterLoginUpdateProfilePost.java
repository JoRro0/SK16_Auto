package gui.e2e;

import com.GG.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import java.io.File;

import static com.GG.POM.HomePage.HOME_PAGE_URL;
import static com.GG.POM.LoginPage.LOGIN_FORM_TITLE;

public class VerifyUserCanRegisterLoginUpdateProfilePost extends BaseTest {

    @Test(priority = 0)
    public void verifyUserCanRegisterWithValidData(ITestContext context) throws InterruptedException {

        String REGISTRATION_SUCCESSFUL_MSG = "Successful register!";
        context.setAttribute("birthDate", "12221989");
        context.setAttribute("publicInfo","testGeorgi");
        context.setAttribute("password", RegistrationDataGenerator.createPasswordFor());
        context.setAttribute("email",RegistrationDataGenerator.createEmail());
        context.setAttribute("userName",RegistrationDataGenerator.createUser());

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        log.info("STEP 1 Not registered user is landing on Iskillo register page");
        log.info("STEP 1.1 Verify that the sign up title is presented!");

        registrationPage.navigateToRegPage();
        boolean isSignUpTittleShown = registrationPage.isRegistrationTitleFormTitleShown();
        Assert.assertTrue(isSignUpTittleShown);

        log.info("STEP 1.2 Verify that the login nav link is presented!");
        boolean isLoginNavLinkShown = registrationPage.isNavLoginLinkShown();
        Assert.assertTrue(isLoginNavLinkShown);

        log.info("STEP 2 Input user name");
        registrationPage.inputUserName(context.getAttribute("userName").toString());

        log.info("STEP 2.1 Input email");
        registrationPage.inputEmail(context.getAttribute("email").toString());

        log.info("STEP 2.2 Input birth date");
        registrationPage.inputBirthDate(context.getAttribute("birthDate").toString());

        log.info("STEP 2.3 Input password");
        registrationPage.inputPassword(context.getAttribute("password").toString());

        log.info("STEP 2.4 Input confirm password");
        registrationPage.inputConfirmPassword(context.getAttribute("password").toString());

        log.info("STEP 2.1 Input public info");
        registrationPage.inputPublicInfo(context.getAttribute("publicInfo").toString());

        log.info("STEP 3 Click on sign in button");
        registrationPage.clickOnRegistrationFormSubmitButton();

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
    }
    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";

    @Test(priority = 1)
    public void verifyTheUserCanLoginWithValidCredentials(ITestContext context) throws InterruptedException {

        context.setAttribute("userName", "JoRro0");
        context.setAttribute("password", "Georgi123*");
        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 5: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 5.1.Verify the user is on the home page ");
        boolean isNavHomeShown = homePage.isNavHomeShown();
        Assert.assertTrue(isNavHomeShown);

        log.info("STEP 5.2. Verify that the login link is presented ");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 6: The use is navigating to the login page via click on navigation bar login link");
        homePage.clickOnNavBarLogin();

        log.info("STEP 6.1.: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(super.driver,log);
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 7. Provide username");
        loginPage.provideUserName(context.getAttribute("userName").toString());

        log.info("STEP 8. Provide password");
        loginPage.providePassword(context.getAttribute("password").toString());

        log.info("STEP 9. Click on loginButton");
        loginPage.clickOnLoginButton();

        log.info("STEP 10. Verify that the success message is displayed");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,LOGIN_SUCCESSFUL_MSG);

        log.info("STEP 10.1 Verify that the LogOut link is displayed");
        boolean isShownNavBarLogOutLink = homePage.isNavLogOutLinkShown();
        Assert.assertTrue(isShownNavBarLogOutLink);

        log.info("STEP 10.2 Verify that HomePage navigation bar profile link displayed");
        boolean isShownNavProfileBar = homePage.isNavProfileShown();
        Assert.assertTrue(isShownNavProfileBar);
    }
    public static final String caption = "Pivot!";
    File postPicture = new File("src/test/resources/upload/piiivot.jpg");

    @Test(priority = 2)
    public void verifyUserCanCreatePost(ITestContext context) {
        context.setAttribute("userName", "JoRro0");
        context.setAttribute("password", "Georgi123*");
        log.info("STEP 12: Already registered user is landing on Iskilo Home page");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();
        log.info("STEP 12.1 Verify that the login link is presented!");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);
        homePage.clickOnNavBarLogin();

        log.info("STEP 13 Input valid user name and password");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(),context.getAttribute("password").toString());

        log.info("STEP 13.1 Verify the New post link is presented!");
        boolean isShownNavBarNewPostLink = homePage.isNavNewPostShown();
        Assert.assertTrue(isShownNavBarNewPostLink);
        homePage.clickOnNavBarNewPost();

        PostPage postPage = new PostPage(super.driver, log);

        log.info("STEP 13.2 Upload a picture");
        postPage.uploadPicture(postPicture);

        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();
        log.info("STEP 13.3 Verify that the new post (picture) is presented");
        boolean isImageVisible = postPage.isImageVisible();
        Assert.assertTrue(isImageVisible);

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        log.info("STEP 13.4 Verify that the user name: "+context.getAttribute("userName").toString() +" is presented");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, context.getAttribute("userName").toString());
    }

    @Test (priority = 3)
    public void verifyUserCanChangePostStatusFromPublicToPrivate(ITestContext context) {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 14 The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();
        log.info("STEP 14.1 Verify that the login link is presented!");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 14.2 Verify that the login form is presented!");
        String actualLoginPageFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginPageFormTitle,  LOGIN_FORM_TITLE);

        log.info("STEP 15 The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(), context.getAttribute("password").toString());

        log.info(" STEP 16 The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("STEP 17 The user has clicked on the first post.");

        profilePage.clickOnPrivacyPostButton();
        log.info(" STEP 18 The user has clicked on the lock post button.");
        log.info("STEP 18.1 Verify that the lock post message is presented.");
        boolean isPostPrivacyMessageVisible = profilePage.isPostPrivacyMessageShown();
        Assert.assertTrue(isPostPrivacyMessageVisible);

    }
    @Test(priority = 4)
    public void verifyUserCanDeletePost(ITestContext context) {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 19 The user has navigated to the Login page");
        loginPage.navigateToLoginPage();
        log.info("STEP 19.1 Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 20 The user has logged in with username and password");
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(), context.getAttribute("password").toString());

        log.info("STEP 21 The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickOnPostPrivateButton();
        log.info("STEP 22 The user has clicked on private button");
        profilePage.clickPost(0);
        log.info("STEP 22.1 The user has clicked on the first post");

        profilePage.clickOnDeleteButton();
        log.info("STEP 23 The user has clicked on the Delete post button.");

        profilePage.clickOnYesButton();
        log.info("STEP 24 The user has confirmed the deletion.");

        log.info("STEP 24.1 Verify that the message for deletion: "+profilePage.getConfirmDeletionMessage()+ " is presented!");
        profilePage.isDeletedMessageVisible();
    }
}
