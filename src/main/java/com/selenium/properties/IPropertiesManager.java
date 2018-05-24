package com.selenium.properties;

import java.io.IOException;

/**
 ********************************************************************
 * Interface for managing application properties
 * 
 * @author Kyle Williamson <k.williamson@pilz.ie>
 * @version 1.0.0
 ********************************************************************
 */
public interface IPropertiesManager
{
   /**
    * Initialize properties provided in resource files
    * 
    * @throws IOException
    */
   void initialiseProperties() throws IOException;
}

