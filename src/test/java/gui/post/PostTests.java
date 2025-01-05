package gui.post;

import com.GG.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostTests extends BaseTest {
    public static final String testUser = "JoRro0";
    public static final String testPassword = "Georgi123*";
    public static final String caption = "Pivot!";
    File postPicture = new File("C:\\Users\\35988\\Desktop\\SKILLO_AT_16_TAF-master\\src\\test\\resources\\piiivot.jpg");

    @Test(priority = 0)
    public void verifyUserCanCreatePost() {
        log.info("STEP 1: Already registered user is landing on Iskilo Home page");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();
        log.info("STEP 1.1: 1.1 Verify that the login link is presented!");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);
        homePage.clickOnNavBarLogin();

        log.info("STEP 2 Input valid user name and password");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);

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

        log.info("STEP 2.3 Verify that the user name is presented");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser);
    }

    @Test (priority = 1)
    public void verifyUserCanLikePost() {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info("The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("The user has clicked on the first post.");

        profilePage.ClickOnLikeButton();
        log.info("The user has clicked on the like button.");
        profilePage.isLikeMessageVisible();

    }

    @Test
    public void verifyUserCanDislikePost() {
       ProfilePage profilePage = new ProfilePage(super.driver, log);
       profilePage.navigateTo("posts/all");
    }

    @Test(priority = 4)
    public void verifyUserCanDeletePost() {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info("The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("The user has clicked on the first post.");

        profilePage.ClickOnDeleteButton();
        log.info("The user has clicked on the Delete post button.");

        profilePage.ClickOnYesButton();
        log.info("The user has confirmed the deletion.");

        profilePage.isDeletedMessageVisible();
    }
}
