package com.selenium.helpers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * TODO:
 * 
 * @author kvatsavayi
 *
 */
public class SeleniumHelpers
{
   

   private static final int DEFAULT_WAIT_IN__MILI_SECONDS = 500;

   public static void waitForSpecificTimeInSeconds(final int d)
   {
      try
      {
         TimeUnit.SECONDS.sleep(d);
      }
      catch (final InterruptedException interruptedException)
      {

      }
   }
   public static void waitForSpecificTimeInMseconds(final int d)
   {
      try
      {
         TimeUnit.MILLISECONDS.sleep(d);
      }
      catch (final InterruptedException interruptedException)
      {

      }
   }

   public static void waitForDefaultTimeElapsed()
   {
      try
      {
         TimeUnit.MILLISECONDS.sleep(DEFAULT_WAIT_IN__MILI_SECONDS);
      }
      catch (final InterruptedException interruptedException)
      {

      }
   }

   
   public static boolean isTheWebElementPresent(WebElement element)
   {
      boolean isPresent = true;
      try
      {
         element.isDisplayed();
      }
      catch (Exception e)
      {

         isPresent = false;

      }
      return isPresent;

   }

   public static boolean isTheWebElementPresent(String id, WebDriver driver)
   {
      boolean isPresent = true;
      try
      {
         driver.findElement(By.id(id)).isDisplayed();
      }
      catch (Exception e)
      {

         isPresent = false;

      }
      return isPresent;

   }
   
   public static void scrollToview(String id,WebDriver driver) throws InterruptedException
   {
      WebElement element = driver.findElement(By.id(id));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
      Thread.sleep(500); 

      
   }
   
  
}
