package steps;

import pages.HomePage;
import io.qameta.allure.Step;

public class FindLocationSteps {

    private HomePage homePage = new HomePage();

    @Step("Find city to needed date")
    public FindLocationSteps findLocationForNeededDate(String location, String checkIn, String checkOut) {
        homePage.searchPlacesByLocations(location)
                .chooseCheckInAndCheckOutDate(checkIn, checkOut)
                .checkIfCorrectDateIsChosen(checkIn, checkOut);
        return this;
    }
}
