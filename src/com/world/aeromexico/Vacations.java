/**
 * 
 */
package com.world.aeromexico;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Rajesh.Kumar4
 *
 */
public class Vacations {
	@BeforeTest
	public void BrowserSetup() {
		System.out.println("Starting Browser..");
		
	}
	@AfterTest
	public void closeBrowser() {
		System.out.println("Closing Browser");
	}
	@Test(enabled=true)
	public void Vacation()
	{
		System.out.println("Running Test");
	}
}
