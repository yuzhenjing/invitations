package com.love.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @author admin
 */
public class ImageUtil {


    public static void main(String[] args) throws IOException {

        for (int i = 2; i <10 ; i++) {
            String path = "c:/image/L"+i+".jpg";
            Thumbnails.of(path)
                    .scale(0.35f)
                    .outputQuality(0.35f)
                    .toFile(path);
        }


    }


}
