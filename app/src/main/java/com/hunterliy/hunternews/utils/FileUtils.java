package com.hunterliy.hunternews.utils;

import com.hunterliy.hunternews.activity.HunterNewsApp;;import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static String getCacheDir(){
        return HunterNewsApp.getInstance().getRoot()+"cache/";
    }

    public static String readTextFromFile(String fileName){

        File file = new File(fileName);
        if (!file.exists()){
            return null;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            int available = inputStream.available();
            byte[] buffer = new byte[available];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean saveText2SDcard(String fileName,String text){
        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
            return false;
        }

        return true;
    }
}
