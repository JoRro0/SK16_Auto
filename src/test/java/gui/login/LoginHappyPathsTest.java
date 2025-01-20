package gui.login;

import com.GG.POM.HomePage;
import com.GG.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class LoginHappyPathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    @Test()
    public void verifyTheUserCanLoginWithValidCredentials(ITestContext context) throws InterruptedException {

        context.setAttribute("userName", "JoRro0");
        context.setAttribute("password", "Georgi123*");
        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.Verify the user is on the home page ");
        boolean isNavHomeShown = homePage.isNavHomeShown();
        Assert.assertTrue(isNavHomeShown);

        log.info("STEP 1.1.2. Verify that the login link is presented ");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link");
        homePage.clickOnNavBarLogin();

        log.info("STEP 2.1.: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(super.driver,log);
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide username");
        loginPage.provideUserName(context.getAttribute("userName").toString());

        log.info("STEP 4. Provide password");
        loginPage.providePassword(context.getAttribute("password").toString());

        log.info("STEP 5. Click on loginButton");
        loginPage.clickOnLoginButton();

        log.info("STEP 6. Verify that the success message is displayed");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,LOGIN_SUCCESSFUL_MSG);

        log.info("STEP 6.1 Verify that the LogOut link is displayed");
        boolean isShownNavBarLogOutLink = homePage.isNavLogOutLinkShown();
        Assert.assertTrue(isShownNavBarLogOutLink);

        log.info("STEP 6.1 Verify that HomePage navigation bar profile link displayed");
        boolean isShownNavProfileBar = homePage.isNavProfileShown();
        Assert.assertTrue(isShownNavProfileBar);
    }
}
