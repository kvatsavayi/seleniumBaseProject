package com.selenium.properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 ********************************************************************
 * Implementation using application.properties
 * 
 * @author Kyle Williamson <k.williamson@pilz.ie>
 * @version 1.0.0
 ********************************************************************
 */
public class PropertiesManager implements IPropertiesManager
{
   
   private static final String PROPERTIES_FILE = "application.properties";

   static boolean initialised;
   
   private ClassLoader classLoader;
   
   public PropertiesManager() {
      classLoader = getClass().getClassLoader();
   }
   
   /**
    * This implementation reads from application.properties
    * Locks the interface after the initial read
    */
   public void initialiseProperties() throws IOException
   {
      if(!initialised) {
         Properties properties = new Properties();
         properties.load(classLoader.getResourceAsStream(PROPERTIES_FILE));
         Enumeration<?> propertiesEnum = properties.propertyNames();
         while(propertiesEnum.hasMoreElements()) {
            String key = (String) propertiesEnum.nextElement();  
            System.getProperties().put(key, properties.get(key));
         }
         initialised = true;
      }
   }

}

