package com.appsoft.job;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import oracle.sql.*;
import java.io.OutputStream;
import java.sql.SQLException;

public class ImageConverter {
    /**
      * Take in a BLOB file (specified as an array parameter but we only ever use [0])
      * Read in the binary stream of the BLOB
      * Change the binary stream to jpg
      * Write the binary stream jpg to the BLOB
      * The BLOB parameter is passed in via out - so there is no need to return the BLOB, only edit it
      */
    public static void convertImage(BLOB[] blob) {
       BufferedImage bufferedImage = null;
       OutputStream outputStream = null;
        try {
            bufferedImage = ImageIO.read(blob[0].getBinaryStream());

            outputStream = blob[0].setBinaryStream(0);

            RenderedImage renderedImage = (RenderedImage)bufferedImage;

            ImageIO.write(renderedImage, "JPG", outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}