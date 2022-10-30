package StepDefinitions;

import Pages.CommonPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PetStoreAPITestSteps extends CommonPage {

    private Logger log = LogManager.getLogger(PetStoreAPITestSteps.class);

    @Given("user is authorized to use Pet Store")
    public void user_is_authorized_to_use_pet_store() {
        System.out.println("user is authorized to use Pet Store");

    }

    @When("^user adds a new pet with \"([^\"]*)\" input to the pet store with payload from file (.+)$")
    public void user_adds_a_new_pet_with_something_input_to_the_pet_store_with_payload_from_file(String scenario, String filename) throws Throwable {
        String body = readJsonContent(filename);
        callPostApi(body, "pet");
    }

    @Then("^the new pet \"([^\"]*)\" with status code (.+)$")
    public void the_new_pet_something_with_status_code(String scenario, int statusCode) throws Throwable {
        validateStatusCode(statusCode);
    }

    @When("^user tries to find a pet with \"([^\"]*)\" id (.+)$")
    public void user_tries_to_find_a_pet_with_id(String string, int petId) {
        callGetApi("pet/" + petId);
        System.out.println("user tries to find a pet with id" + petId);
    }

    @Then("^the expected pet details given in file (.+) is be returned with status code (.+)$")
    public void the_expected_pet_details_given_in_file_is_returned_with_status_code(String filename, int statusCode) throws Throwable {
        String expectedResponse = readJsonContent(filename);
        validateStatusCode(statusCode);
        validateResponse(expectedResponse);
    }


    @Then("^error message given in file (.+) is returned with status code (.+)$")
    public void error_message_given_in_file_is_returned_with_status_code(String filename, int statusCode) throws Throwable {
        String expectedResponse = readJsonContent(filename);
        validateStatusCode(statusCode);
        validateResponse(expectedResponse);
    }

}
