package gui.post;

import com.GG.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static com.GG.POM.LoginPage.LOGIN_FORM_TITLE;

public class PostTests extends BaseTest {
    public static final String caption = "Pivot!";
    File postPicture = new File("src/test/resources/upload/piiivot.jpg");

    @Test(priority = 0)
    public void verifyUserCanCreatePost(ITestContext context) {
        context.setAttribute("userName", "JoRro0");
        context.setAttribute("password", "Georgi123*");
        log.info("STEP 1: Already registered user is landing on Iskilo Home page");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();
        log.info("STEP 1.1: 1.1 Verify that the login link is presented!");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);
        homePage.clickOnNavBarLogin();

        log.info("STEP 2 Input valid user name and password");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(),context.getAttribute("password").toString());

        log.info("STEP 2.1 Verify the New post link is presented!");
        boolean isShownNavBarNewPostLink = homePage.isNavNewPostShown();
        Assert.assertTrue(isShownNavBarNewPostLink);
        homePage.clickOnNavBarNewPost();

        PostPage postPage = new PostPage(super.driver, log);

        log.info("STEP 2.2 Upload a picture");
        postPage.uploadPicture(postPicture);

        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();
        log.info("STEP 2.2 Verify that the new post (picture) is presented");
        boolean isImageVisible = postPage.isImageVisible();
        Assert.assertTrue(isImageVisible);

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        log.info("STEP 2.3 Verify that the user name: "+context.getAttribute("userName").toString() +" is presented");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, context.getAttribute("userName").toString());
    }

    @Test (priority = 1)
    public void verifyUserCanChangePostStatusFromPublicToPrivate(ITestContext context) {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1 The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();
        log.info("STEP 1.1 Verify that the login link is presented!");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 1.2 Verify that the login form is presented!");
        String actualLoginPageFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginPageFormTitle,  LOGIN_FORM_TITLE);

        log.info("STEP 2 The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(), context.getAttribute("password").toString());

        log.info(" STEP 3 The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("STEP 4 The user has clicked on the first post.");

        profilePage.clickOnPrivacyPostButton();
        log.info(" STEP 5 The user has clicked on the lock post button.");
        log.info("STEP 5.1 Verify that the lock post message is presented.");
        boolean isPostPrivacyMessageVisible = profilePage.isPostPrivacyMessageShown();
        Assert.assertTrue(isPostPrivacyMessageVisible);

    }
    @Test(priority = 2)
    public void verifyUserCanDeletePost(ITestContext context) {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1 The user has navigated to the Login page");
        loginPage.navigateToLoginPage();
        log.info("STEP 1.1 Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2 The user has logged in with username and password");
        loginPage.loginWithUSerAndPassword(context.getAttribute("userName").toString(), context.getAttribute("password").toString());

        log.info("STEP 3 The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickOnPostPrivateButton();
        log.info("STEP 4 The user has clicked on private button");
        profilePage.clickPost(0);
        log.info("STEP 4.1 The user has clicked on the first post");

        profilePage.clickOnDeleteButton();
        log.info("STEP 5 The user has clicked on the Delete post button.");

        profilePage.clickOnYesButton();
        log.info("STEP 6 The user has confirmed the deletion.");

        log.info("STEP 6.1 Verify that the message for deletion: "+profilePage.getConfirmDeletionMessage()+ " is presented!");
        profilePage.isDeletedMessageVisible();
    }
}
