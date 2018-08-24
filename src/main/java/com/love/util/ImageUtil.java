package com.love.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @author admin
 */
public class ImageUtil {


    public static void main(String[] args) throws IOException {
        Thumbnails.of("f:/L3.jpg")
                .scale(0.5f)
                .outputQuality(0.5f)
                .toFile("d:/L3.jpg");
    }


}
