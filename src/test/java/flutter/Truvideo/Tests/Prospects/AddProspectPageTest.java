package flutter.Truvideo.Tests.Prospects;

import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import flutter.Truvideo.BaseClass.BaseClass;
import flutter.Truvideo.Pages.AddProspectPage;

public class AddProspectPageTest extends BaseClass {
	AddProspectPage addProspectPage;

	@BeforeMethod
	public void setUpTestEnvironment_ClassLevel() throws MalformedURLException, Exception {
		if (driver == null) {
			driver = setUpApplication();
			addProspectPage = loadDealerCodePage().navigateToUserListScreen_Sales()
					.navigateTo_RO_Prospect_ListPage(userForLogin_Sales).NavigateTo_AddProspect_Page();
		}
		if (addProspectPage == null) {
			addProspectPage = new AddProspectPage(driver);
		}
	}

	@AfterMethod
	public void tearDown_OnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			driver.quit();
			driver = null;
		}
	}

	// @Test
	public void verifyCancelButton_AddProspectScreen() throws Exception {
		Assert.assertTrue(addProspectPage.checkCancelButton());
	}

	@Test(priority = 1)
	public void verifyRequiredFields_AddProspectScreen() throws Exception {
		Assert.assertTrue(addProspectPage.checkRequired_Fields());
	}

	@Test(priority = 2)
	public void verifyAddNewProspect() throws Exception {
		Assert.assertTrue(addProspectPage.checkCreateProspect());
	}

	// @Test(dependsOnMethods = "verifyAddNewProspect")
	public void verifyProspectsOnDashboard() throws Exception {
		addProspectPage.verifyCreatedRO_OnDashboard();
	}

}
