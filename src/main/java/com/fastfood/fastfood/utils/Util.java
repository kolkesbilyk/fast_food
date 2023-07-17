package com.fastfood.fastfood.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Util {

    public static InputStream getResourcesAsStream(String path) throws FileNotFoundException {
        InputStream stream = null;
        try{
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            if (stream == null){
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.replace("\\", "/"));
            }
        }catch (Exception ignored){
            System.err.println("Error " + ignored.getMessage());
        }
        return stream == null ? new FileInputStream(path) : stream;
    }

    /*
    метод отримує картинку та зжимає її до необхідних розмірів
    параметри :
        imagePathToRead - картинка яка приходе;
        imagePathToWrite - картинка на виході;
        resizeWidth - необхідна ширина картинки;
        resizeHeight - висота
     */
    public static void correctSizeImage(String imagePathToRead, String imagePathToWrite, int resizeWidth, int resizeHeight) throws IOException {
        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
            resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
            .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }

    public static void redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getHTTPContext() + url);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getHTTPContext() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest serreq = (HttpServletRequest) ctx.getExternalContext().getRequest();
        String url = ((new HttpServletRequestWrapper(serreq)).getRequestURL()).toString();
        return url.substring(0, url.indexOf("/", 9)) + serreq.getContextPath();
    }
}
