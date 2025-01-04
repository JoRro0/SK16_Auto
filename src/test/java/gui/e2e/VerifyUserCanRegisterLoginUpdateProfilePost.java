package gui.e2e;

import com.GG.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class VerifyUserCanRegisterLoginUpdateProfilePost extends BaseTest {

    @Test
    public void basicEndToEnd() throws InterruptedException {
        String testUser = "testingDemos";
        String testPassword = "testing";
        File postPicture = new File("src/test/resources/upload/n3Test.jpg");
        String caption = "Testing the create post caption";

        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();
        homePage.clickOnNavBarLogin();

        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.provideUserName(testUser);
        loginPage.providePassword(testPassword);
        loginPage.clickOnLoginButton();

        homePage.clickOnNavBarNewPost();
        PostPage postPage = new PostPage(super.driver, log);

        postPage.uploadPicture(postPicture);

        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser);

    }


}
