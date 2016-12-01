package com.fosun.fc.projects.creeps.web.rest;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIoTest {

    public static BufferedImage readImage(String fileName) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return bi;
    }

    public static boolean writeImage(RenderedImage im, String formatName, String fileName) {
        boolean result = false;
        try {
            result = ImageIO.write(im, formatName, new File(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static boolean writeJPEGImage(RenderedImage im, String fileName) {
        return writeImage(im, "JPEG", fileName);
    }

    public static boolean writeGIFImage(RenderedImage im, String fileName) {
        return writeImage(im, "GIF", fileName);
    }

    public static boolean writePNGImage(RenderedImage im, String fileName) {
        return writeImage(im, "PNG", fileName);
    }

    public static boolean writeBMPImage(RenderedImage im, String fileName) {
        return writeImage(im, "BMP", fileName);
    }

    public static void main(String[] args) {
         BufferedImage bi = ImageIoTest.readImage("e:/image/image.jpg");
         System.out.println(ImageIoTest.writeJPEGImage(bi,
         "e:/image/testimage.jpg"));
        
    }

}
