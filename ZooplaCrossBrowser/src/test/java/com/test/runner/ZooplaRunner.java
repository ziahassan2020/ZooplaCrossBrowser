package com.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//cucumber goes by order so find finder runs first then new tours you have to make sure.
//features = {"./src/main/resources/NewToursDemoLogin.feature",
//"./src/main/resources/flightFinder.feature"},
@CucumberOptions(
//		features = {"src/main/resources/NewToursDemoLogin.feature"},
		features = {"features/zooplacrossbrowser.feature"},
		glue = {"com.step.def"},
		plugin = {"pretty", "html:target" , "json:target/cucumber.json" , 
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
		monochrome = true		
		
		)

public class ZooplaRunner extends AbstractTestNGCucumberTests{

}
