package com.selenium.helpers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenShotUtil
{
   private static final String APPLICATION_ROOT_ID = "root";

   private static final String PNG = "png";

   private static final String IMAGE_FORMAT = ".png";

   private static final String FAILED_SCREEN_SHOT_PATH = "resources/FailedScreenShot/";

   private static final String MASTER_SCREEN_SHOTS_PATH = "resources/MasterScreenShots/";

   private static boolean compare = true;

   public static boolean captureAndCompare(WebDriver driver, String imageName, WebElement element) throws IOException
   {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      BufferedImage fullImg = ImageIO.read(screenshot);

      // Get the location of element on the page
      Point point = element.getLocation();

      // Get width and height of the element
      int eleWidth = element.getSize().getWidth();
      int eleHeight = element.getSize().getHeight();
      // Crop the entire page screenshot to get only element screenshot
      BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
      if (compare)
      {

         Image masterImage = ImageIO.read(new File(MASTER_SCREEN_SHOTS_PATH + imageName + IMAGE_FORMAT));
         BufferedImage bufferedMasterImage = toBufferedImage(masterImage);
         return compareAndHighlight(eleScreenshot, bufferedMasterImage, FAILED_SCREEN_SHOT_PATH + imageName + IMAGE_FORMAT, true, Color.pink.getRGB());
      }
      else
      {

         File file = new File(MASTER_SCREEN_SHOTS_PATH + imageName + IMAGE_FORMAT);
         ImageIO.write(eleScreenshot, PNG, file);
         return true;
      }
   }

   public static boolean compareAndHighlight(final BufferedImage img1, final BufferedImage img2, String highlightImagePath, boolean highlight, int colorCode) throws IOException
   {

      final int w = img1.getWidth();
      final int h = img1.getHeight();
      final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
      final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);

      boolean imagesMatch = true;

      int noOfpixels = 0;

      if (!(java.util.Arrays.equals(p1, p2)))
      {

         if (highlight)
         {
            for (int i = 0; i < p1.length; i++)
            {
               if (p1[i] != p2[i])
               {
                  p1[i] = colorCode;

                  noOfpixels++;
               }
            }
            final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            out.setRGB(0, 0, w, h, p1, 0, w);

            if (((noOfpixels * 100 / p1.length)) > 1)
            {
               File outputfile = new File(highlightImagePath);
               ImageIO.write(out, PNG, outputfile);

               imagesMatch = false;

            }
            else
            {
               imagesMatch = true;
            }

         }
      }
      return imagesMatch;
   }

   public static BufferedImage toBufferedImage(Image img)
   {
      if (img instanceof BufferedImage)
      {
         return (BufferedImage) img;
      }

      // Create a buffered image with transparency
      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      // Draw the image on to the buffered image
      Graphics2D bGr = bimage.createGraphics();
      bGr.drawImage(img, 0, 0, null);
      bGr.dispose();

      // Return the buffered image
      return bimage;
   }
}
