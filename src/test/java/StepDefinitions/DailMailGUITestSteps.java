package StepDefinitions;

import Pages.HomePage;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DailMailGUITestSteps {

    WebDriver driver = InitializationHooks.driver;
    HomePage objHome;
    public String pageTimeStamp = "";
    private Logger log = LogManager.getLogger(DailMailGUITestSteps.class);

    @Given("^user navigates to daily mail home page$")
    public void navigateToHomePage() throws Throwable {
        driver.navigate().to("https://www.dailymail.co.uk/home/index.html");
        Thread.sleep(3000);
        objHome = new HomePage(driver);
        System.out.println("User successfully navigated to Daily Mail Home page");
        objHome.acceptCookies();
    }

    @When("^user verifies webpage for date and time$")
    public void captureDateTimeStampFromHomePage() throws Throwable {
        pageTimeStamp = objHome.captureHopePageTimeStamp();
    }

    @Then("^it should match current date and time$")
    public void validateTimeStamp() throws Throwable {
        String pattern = "EEEE, MMM dd yyyy, h a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String currentTimeStamp = simpleDateFormat.format(new Date());
        System.out.println("Current: " + currentTimeStamp);
        System.out.println("Page: " + pageTimeStamp);

        LocalDateTime dateTimeFromPage = LocalDateTime.parse(pageTimeStamp, DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, ha"));
        LocalDateTime dateTimeCurrent = LocalDateTime.parse(currentTimeStamp, DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, h a"));

        long hours = ChronoUnit.HOURS.between(dateTimeFromPage, dateTimeCurrent);
        // Note: This could be a defect in the application. It doesn't show exact system time on the Page
        // For that reason I have taken a range limit of 1 hour for current validation
        if(hours <=1){
            System.out.println("Date and Time displayed in page is in sync with current date and time");
        }else{
            System.out.println("Date and Time displayed in page is not in sync with current date and time");
            Assert.fail();
        }
    }

    @When("^user navigates to the Sport menu$")
    public void userNavigatesToSportMenu() throws Throwable {
        objHome.navigateToSportTab();
    }

    @Then("^primary navigation color and secondary navigation color should match$")
    public void validateColorsForPrimaryAndSecondaryNavigationTabs() throws Throwable {
        objHome.validatePrimarySecondaryNavigationColors();
    }

    @When("^user click on the Football sub navigation item$")
    public void user_click_on_the_football_sub_navigation_item() throws Throwable {
        objHome.clickOnFootBallSubNavigationItem();
    }

    @And("^clicks the first article displayed$")
    public void clicks_the_first_article_displayed() throws Throwable {
        objHome.clickOnTheFirstArticleDisplayedUnderFootBall();
    }

    @And("^traverses to the gallery  image and clicks the gallery icon on the right hand side corner with numbers$")
    public void traverses_to_the_gallery_image_and_clicks_the_gallery_icon_on_the_right_hand_side_corner_with_numbers() throws Throwable {
        objHome.clickOnViewGalleryIcon();
    }

    @Then("^gallery loads on full page$")
    public void gallery_loads_on_full_page() throws Throwable {
        objHome.validateGalleryLoadsOnFullScreen();
    }

    @And("^user verifies that it has previous and next buttons$")
    public void user_verifies_that_it_has_previous_and_next_buttons() throws Throwable {
        objHome.validatePreviousAndNextButtonExists();
    }

    @When("^user click on the next and previous buttons, it navigates to appropriate gallery picture$")
    public void user_click_on_the_next_and_previous_buttons_it_navigates_to_appropriate_gallery_picture() throws Throwable {
        objHome.validateNextAndPreviousButtonFunctionalities();
    }

    @When("^user clicks on the Facebook share icon$")
    public void user_clicks_on_the_facebook_share_icon() throws Throwable {
        objHome.clickOnFacebookShareIcon();
    }

    @Then("^user verifies that Facebook modal dialog opens$")
    public void user_verifies_that_facebook_modal_dialog_opens() throws Throwable {
        objHome.validateFacebookModalDialogOpens();
    }
    @When("^user navigates and clicks on the full screen button of a video embedded within the article$")
    public void user_navigates_and_clicks_on_the_full_screen_button_of_a_video_embedded_within_the_article() throws Throwable {
        objHome.clickOnFullScreenButtonVideo();
    }

    @Then("^user should be able to view the video in full screen and minimized mode$")
    public void user_should_be_able_to_view_the_video_in_full_screen_and_minimized_mode() throws Throwable {
        objHome.validateVideoPlayInFullScreenAndMinimisedModes();
    }

    @When("^user navigates to bottom right of the article page to the Premier League table section$")
    public void user_navigates_to_bottom_right_of_the_article_page_to_the_premier_league_table_section() throws Throwable {
        objHome.navigateToPremierLeagueTab();
    }

    @And("^exhibit the points or positions from the Premier League table for the team (.+)$")
    public void exhibit_the_points_or_positions_from_the_premier_league_table_for_the_team(String team) throws Throwable {
        objHome.getPosAndPtsOfTeam(team);
    }

}
