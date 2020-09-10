package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FindLocationTest extends BaseTest {

    @DataProvider(name = "Valid locations to find")
    public Object[][] validLocationsToFind() {
        return new Object[][]{
                {"Montreal"}, {"Amsterdam"}, {"Prague"}
        };
    }

    @DataProvider(name = "Invalid locations to find")
    public Object[][] invalidLocationsToFind() {
        return new Object[][]{
                {"asdasad"}, {"test"}, {"345@kfmv"}
        };
    }

    @Test(dataProvider = "Valid locations to find")
    public void findPlaceWithValidData(String location) {
        loginPage.openPage();
        homePage.searchPlacesByLocations(location)
                .checkIfCorrectPlaceDisplayed(location);
    }

    @Test(dataProvider = "Invalid locations to find")
    public void findPlaceWithInvalidData(String invalidLocation) {
        loginPage.openPage();
        homePage.searchPlacesByLocations(invalidLocation)
                .checkIfLocationsNotDisplayed(invalidLocation);
    }
}
