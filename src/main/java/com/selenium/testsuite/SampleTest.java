package com.selenium.testsuite;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.common.AGenericTest;
import com.selenium.helpers.RetryAnalyzer;
import com.selenium.helpers.ScreenShotUtil;
import com.selenium.helpers.SeleniumHelpers;

public class SampleTest extends AGenericTest
{

   @Test(retryAnalyzer = RetryAnalyzer.class)
   public void sampleTest() throws IOException
   {
      driver.findElement(By.id("lst-ib")).sendKeys("hello world");
      WebElement googleLogo = driver.findElement(By.id("hplogo"));
      ScreenShotUtil.captureAndCompare(driver, "googleLogo",googleLogo);
      
      
      SeleniumHelpers.waitForSpecificTimeInSeconds(10);
   }

   @Override
   public void init() throws IOException
   {
      super.init();

   }

   @Override
   public void initialisePages()
   {
      // TODO:K.Vatsavayi Auto-generated method stub

   }

}
