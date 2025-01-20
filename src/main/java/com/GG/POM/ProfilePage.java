package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy (xpath = "//div[contains(@class,'edit-profile-pic ')]")
    private WebElement uploadImage;

    @FindBy (id = "upload-img" )
    private WebElement hiddenUploadImage;

    @FindBy (xpath = "//i[contains(@class,'like far fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy (xpath = "//i[contains(@class,'ml-4 far fa-thumbs-down fa-2x')]")
    private WebElement dislikeButton;

    @FindBy (xpath = "//label[contains(@class,'delete-ask')]")
    private WebElement deletePostButton;

    @FindBy (xpath = "//button[contains(@class,'btn btn-primary btn-sm')]")
    private WebElement areYouSureYesButton;

    @FindBy (xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;

    @FindBy (xpath = "//div[contains(@aria-label,'Post liked')]")
    private WebElement postLikeMessage;

    @FindBy (xpath = "//div[contains(@aria-label,'Post disliked')]")
    private WebElement postDislikeMessage;

    @FindBy (xpath = "//i[@class='fas fa-unlock ng-star-inserted']")
    private WebElement postPrivacyButton;

    @FindBy (xpath = "//div[text()=' Post is now private ']")
    private WebElement postPrivacyMessage;

    @FindBy (xpath = "//label[text()=' Private ']")
    private WebElement postPrivateButton;

    public void clickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void clickOnDeleteButton() {
        waitAndClickOnWebElement(deletePostButton);
    }

    Actions action = new Actions(driver);
    public void HoverOverProfilePicture () {
        action.moveToElement(uploadImage).perform();
    }

    public ProfilePage (WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver,this);
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
        }

    public boolean isDeletedMessageVisible() {
        boolean isDeletedMessageVisible = false;
        try {
            isDeletedMessageVisible = wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post Deleted! message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post Deleted! message is not displayed!");
            isDeletedMessageVisible = false;
        }
        return isDeletedMessageVisible;
    }

    public void clickOnPrivacyPostButton() {
        waitAndClickOnWebElement(postPrivacyButton);
    }
    public boolean isPostPrivacyMessageShown() {
        return isPresented(postPrivacyMessage);
    }
    public String getConfirmDeletionMessage() {
        WebElement confirmDeletionMessage = driver.findElement(By.xpath("//div[contains(@aria-label,'Post Deleted!')]"));
        return confirmDeletionMessage.getText();
    }

    public void clickOnPostPrivateButton() {
        waitAndClickOnWebElement(postPrivateButton);
    }
}
