package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class FindLocationTest extends BaseTest {

    private String location = "Amsterdam";
    private String checkInDate = "2020-10-24";
    private String checkOutDate = "2020-11-24";

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

    @Test
    public void findLocationForNeededDate() {
        loginPage.openPage();
        locationSteps.findLocationForNeededDate(location, checkInDate, checkOutDate);
    }
}