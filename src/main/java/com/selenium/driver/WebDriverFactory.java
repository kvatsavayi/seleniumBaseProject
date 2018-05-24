package com.selenium.driver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 ********************************************************************
 * WebDriverFactory implementation providing driver building functionality.
 * Builds without the use of third party library
 * 
 * @author Kyle Williamson <k.williamson@pilz.ie>
 * @version 1.0.0
 ********************************************************************
 */
public class WebDriverFactory implements IWebDriverFactory
{
   private static final String DOWNLOAD_PATH_PROP = "download.path";
   
   private final String DOWNLOAD_PATH;
   
   public WebDriverFactory() {
      DOWNLOAD_PATH = System.getProperty(DOWNLOAD_PATH_PROP);
   }
   
   /**
    * Creates a generic chrome driver using the provided options
    * 
    * Allows resuable generic chrome driver for Chrome browser & electron
    * 
    * @param   chromeOptions options to customize the generic chrome driver
    * @return  generic ChomeDriver
    */
   private ChromeDriver createGenericChromeDriver(ChromeOptions chromeOptions) {
      // Define constants used for driver creation
      final String DEFAULT_DOWNLOAD_PATH = "download.default_directory";
      final String DEFAULT_CONTENT_SETTINGS_POPUPS = "profile.default_content_settings.popups";
      final int DEFAULT_CONTENT_SETTINGS_POPUPS_VAL = 0;
      final String PREFS_OPTION = "prefs";
      final String TEST_TYPE_ARG = "--test-type";
      final boolean ACCEPT_SSL_CERTS = true;
      
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      
      // Setup browser preferences
      Map<String, Object> chromePrefs = new HashMap<>();
      chromePrefs.put(DEFAULT_CONTENT_SETTINGS_POPUPS, DEFAULT_CONTENT_SETTINGS_POPUPS_VAL);
      chromePrefs.put(DEFAULT_DOWNLOAD_PATH, DOWNLOAD_PATH);
      
      // Setup browser options
      chromeOptions.setExperimentalOption(PREFS_OPTION, chromePrefs);
      chromeOptions.addArguments(TEST_TYPE_ARG);
      
      // Setup browser capabilities
      capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
      capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS , ACCEPT_SSL_CERTS);
      
      return new ChromeDriver(capabilities);
   }

   public WebDriver createElectronDriver() throws IOException
   {
      // Define constants used for driver creation
      final String ELECTRON_PATH_PROPERTY = "electron.app.path";
      final String ELECTRON_PATH = System.getProperty(ELECTRON_PATH_PROPERTY);
      File electronPath = new File(ELECTRON_PATH);
      
      // Setup browser options
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setBinary(electronPath.getCanonicalFile());
      
      return createGenericChromeDriver(chromeOptions);
   }

   public WebDriver createChromeDriver()
   {
      
      return createGenericChromeDriver(new ChromeOptions());
   }

   public WebDriver createFireFoxDriver()
   {
      // Define constants used for driver creation
      final String DEFAULT_DOWNLOAD_PATH = "browser.download.dir";
      final String DEFAULT_DOWNLOAD_FOLDERLIST = "browser.download.folderList";
      final String NEVER_ASK_SAVE_TO_DISK = "browser.helperApps.neverAsk.saveToDisk";
      final String DL_MNG_SHOW_WHEN_STARTING = "browser.download.manager.showWhenStarting";
      final String PDFJS_DISABLED = "pdfjs.disabled";
      
      final int FOLDER_LIST = 2;
      final String NEVER_ASK = "application/pdf";
      final boolean SHOW_WHEN_START = false;
      final boolean PDFJS_VAL = true;
      
      // Setup browser profile
      FirefoxProfile ffProfile = new FirefoxProfile();
      ffProfile.setPreference(DEFAULT_DOWNLOAD_PATH, DOWNLOAD_PATH);
      ffProfile.setPreference(DEFAULT_DOWNLOAD_FOLDERLIST, FOLDER_LIST);
      ffProfile.setPreference(NEVER_ASK_SAVE_TO_DISK, NEVER_ASK);
      ffProfile.setPreference(DL_MNG_SHOW_WHEN_STARTING, SHOW_WHEN_START);
      ffProfile.setPreference(PDFJS_DISABLED, PDFJS_VAL);
      
      return new FirefoxDriver(ffProfile);
   }

   public WebDriver createEdgeDriver()
   {
      return new EdgeDriver();
   }

   public WebDriver createIEDriver()
   {
      return new InternetExplorerDriver();
   }

}

