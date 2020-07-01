package com.step.def;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cucumber.code.AgentInfo;
import com.cucumber.code.BaseLogin;
import com.cucumber.code.PropertySelection;
import com.cucumber.code.SetupBrowser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class StepDefinitionsCrossBrowser {
	
	WebDriver chromedriver, geckodriver;
	private BaseLogin chromelogin, geckologin;
	private PropertySelection chromeps, geckops;
	private AgentInfo chromeagentinfo, geckoagentinfo;	
		
	@Given("^User is on Zoopla Webpage$")
	public void user_is_on_Zoopla_Webpage(DataTable bname) throws Throwable {	
		List<String> list = bname.asList();		
		for(int i=0; i<list.size();i++) {
			if (list.get(i).equalsIgnoreCase("chrome")) {
				chromedriver = SetupBrowser.setupCrossBrowser(chromedriver,list.get(i));
				chromedriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else if(list.get(i).equalsIgnoreCase("firefox")) {
				geckodriver = SetupBrowser.setupCrossBrowser(geckodriver,list.get(i));
				geckodriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}			
		}
		
		chromelogin=new  BaseLogin(chromedriver);
		geckologin=new  BaseLogin(geckodriver);
		chromelogin.openUrl();
		geckologin.openUrl();
		assertEquals(chromedriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
		assertEquals(geckodriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
	}

	@When("User click on Signin Buton")
	public void user_click_on_Signin_Buton() throws Throwable {
		chromelogin.clickSignIn();
		geckologin.clickSignIn();
		assertEquals(chromedriver.getTitle().substring(0, 29),"Register or sign in to Zoopla");	
		assertEquals(geckodriver.getTitle().substring(0, 29),"Register or sign in to Zoopla");
	}

	@When("User Enters Credentials and Submit Login")
	public void user_Enters_username_and_Password_and_Submit_Login() throws Throwable {
		chromelogin.getLogin(); 
		geckologin.getLogin();
	}	

	@Then("User logged in")
	public void user_logged_in() {
		assertEquals(chromedriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
		assertEquals(geckodriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
	}

	@Given("User is on Search Property page")		
	public void user_is_on_Search_Property_page() {
		chromeps = new PropertySelection(chromedriver);
		geckops = new PropertySelection(geckodriver);
		assertEquals(chromedriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
		assertEquals(geckodriver.getTitle(),"Zoopla > Search Property to Buy, Rent, House Prices, Estate Agents");
	}

	@When("User Enters City Name and Search Properties")
	public void user_Enters_City_Name_and_Search_Properties() throws Throwable {		
	    chromeps.searchProperty();
	    geckops.searchProperty();
	}	

	@Then("User gets the Properties page")
	public void user_gets_the_Properties_page() {
	    assertEquals(chromedriver.getTitle(),"Property for Sale in New York - Buy Properties in New York - Zoopla");
	    assertEquals(geckodriver.getTitle(),"Property for Sale in New York - Buy Properties in New York - Zoopla");
	}

	@When("User Display all Properties prices in desc order")
	public void user_Display_all_Properties_prices_in_desc_order() {
	   chromeps.displayPropertyPrices();
	   geckops.displayPropertyPrices();
	}

	@When("User Selects Fifth Property")
	public void user_Selects_Fifth_Property() {
	    chromeps.selectProperty();
	    geckops.selectProperty();
	}
	
	@When("User Verify Logo")
	public void user_Verify_Logo() {
		chromeagentinfo = new AgentInfo(chromedriver);
	    chromeagentinfo.checkAgentLogo();
	    geckoagentinfo = new AgentInfo(geckodriver);
	    geckoagentinfo.checkAgentLogo();
	}

	@When("User Display Agent name and Phone Num")
	public void user_Display_Agent_name_and_Phone_Num() throws Throwable {
	    chromeagentinfo.displayAgentInfo();
	    geckoagentinfo.displayAgentInfo();
	}

	@When("User clicks on Sign out button")
	public void user_clicks_on_Sign_out_button() throws Throwable {
	    chromeagentinfo.logOut();
	    geckoagentinfo.logOut();
	}

	@Then("User Signs Out")
	public void user_Signs_Out() {
	    chromedriver.quit();
	    geckodriver.quit();
	}

}
