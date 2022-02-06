package com.example.demo.core;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

@Component
public class RGBAnalyzer{

    private static final int MAX_COVER = 1000;

    private static final String FILE_DIR="/images/";

    public String core(String fileName) throws IOException {
        return traverse(FILE_DIR+fileName);
    }

    private String traverse(String filePath)throws IOException{
        int[] rgb = new int[3];
        File file = new  File("test2.png");
        System.out.println(file.getAbsolutePath());
        BufferedImage bi=null;
        bi = ImageIO.read(file);
        int width=bi.getWidth();
        int height=bi.getHeight();
        int minx=bi.getMinX();
        int miny=bi.getMinY();
        System.out.println("width="+width+",height="+height+".");
        System.out.println("minx="+minx+",miniy="+miny+".");
        String rgb_string=null;
        HashMap<String,Integer> rgbMap = new HashMap<>(1000);
        TreeMap<String, Integer>rgbTreeMap = new TreeMap<>();
        int k=0;
        for(int i=minx;i<width;i++) {
            for(int j=miny;j<height && (k<MAX_COVER);j++)
            {
                int pixel=bi.getRGB(i,j);
                rgb[0] = (pixel &  0xff0000) >> 16;
                rgb[1] = (pixel &  0xff00) >> 8;
                rgb[2] = (pixel &  0xff);
                rgb_string=rgb[0]+","+rgb[1]+","+rgb[2];
                rgbMap.putIfAbsent(rgb_string,0);
                rgbMap.put(rgb_string,rgbMap.get(rgb_string)+1);
                rgbTreeMap.put(rgb_string,rgbMap.get(rgb_string));
                System.out.println(rgb_string);
                System.out.println("i="+i+",j="+j);
                System.out.println(k=++k);

            }
        }
        System.out.println("rgbTreeMap"+rgbTreeMap.get(rgbTreeMap.firstKey()));
        return rgbTreeMap.firstKey();
    }
}
