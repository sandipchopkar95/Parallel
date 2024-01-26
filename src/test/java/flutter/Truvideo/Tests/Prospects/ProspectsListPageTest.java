package flutter.Truvideo.Tests.Prospects;

import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.annotations.*;
import flutter.Truvideo.BaseClass.BaseClass;
import flutter.Truvideo.Pages.AddProspectPage;
import flutter.Truvideo.Pages.RO_ListPage;

public class ProspectsListPageTest extends BaseClass {
	RO_ListPage ro_ListPage;

	@BeforeClass
	public void setUp() throws MalformedURLException, Exception {
		if(driver==null) {
		driver = setUpApplication();
		ro_ListPage = loadDealerCodePage().navigateToUserListScreen_Sales()
				.navigateTo_RO_Prospect_ListPage(userForLogin_Sales);
		}
	}

	@BeforeMethod
	public void setDriverObject() {
		if (ro_ListPage == null) {
			ro_ListPage = new RO_ListPage(driver);
		}
	}

	//@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyAllFooterTabs_and_HeadersTabs() throws Exception {
		Assert.assertTrue(ro_ListPage.checkAllFooterTab_and_HederTabs());
	}

	@Test(priority = 2)
	public void verifySearchProspectsFunction() throws InterruptedException {
		Assert.assertTrue(ro_ListPage.checkSearchRO_ProspectFunction("Test"));
	}

	@Test(priority = 3)
	public void verifyProspects_Status_New() throws Exception {
		Assert.assertTrue(ro_ListPage.checkRO_Prospects_Status_New());
	}

	@Test(priority = 4)
	public void verifyProspects_Status_All() throws Exception {
		Assert.assertTrue(ro_ListPage.checkRO_Prospects_Status_All());
	}

	@Test(priority = 6)
	public void verifyProspects_Status_My() throws Exception {
		Assert.assertTrue(ro_ListPage.checkProspects_Status_My());
	}

	@Test(priority = 7)
	public void verifyNavigationTo_Chat() throws InterruptedException {
		Assert.assertTrue(ro_ListPage.checkNavigationTo_Chat());
	}

	@Test(priority = 8)
	public void verifyNavigationTo_Messages() {
		Assert.assertTrue(ro_ListPage.checkNavigationTo_Messages());
	}

	@Test(priority = 9)
	public void verifyNavigationTo_Contacts() {
		Assert.assertTrue(ro_ListPage.checkNavigationTo_Contacts());
	}

	@Test(priority = 10)
	public void verifyNavigation_BackTo_Prospects() {
		Assert.assertTrue(ro_ListPage.checkNavigationBackTo_Prospects());
	}

	@Test(priority = 11)
	public void verifyNavigationToProfileIcon() throws InterruptedException {
		Assert.assertTrue(ro_ListPage.checkNavigationToProfileIcon());
		driver.navigate().back();
	}

	@Test(priority = 12)
	public void verifyNavigationToAddProspectsPage() {
		Assert.assertTrue(ro_ListPage.checkNavigationTo_AddProspects_Page());
		AddProspectPage addProspectPage=new AddProspectPage(driver);
		addProspectPage.getCancelButton().click();
	}
}
