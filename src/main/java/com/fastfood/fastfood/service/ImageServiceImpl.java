package com.fastfood.fastfood.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageServiceImpl {

    /*
    метод отримує картинку та зжимає її до необхідних розмірів
    параметри :
        imagePathToRead - картинка яка приходе;
        imagePathToWrite - картинка на виході;
        resizeWidth - необхіжна ширина картинки;
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
}
