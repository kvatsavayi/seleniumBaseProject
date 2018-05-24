package com.selenium.common;

import org.openqa.selenium.WebDriver;

/**
 ********************************************************************
 * Provides boilerplate for a generic web page
 * 
 * @author Kyle Williamson <k.williamson@pilz.ie>
 * @version 1.0.0
 ********************************************************************
 */
public abstract class AGenericPage
{

   protected WebDriver driver;
   
   public AGenericPage(WebDriver driver) {
      this.driver = driver;
   }
}

