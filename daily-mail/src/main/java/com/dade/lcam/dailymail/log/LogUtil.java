package com.dade.lcam.dailymail.log;

import org.springframework.util.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class LogUtil {

    public static void plainFile(Collection collection){
        Assert.notEmpty(collection, "collection is empty");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd-HHmmss");
        String format = sdf.format(new Date());
        String url = "D:\\tmp\\" + format + ".txt";
        File file = new File(url);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()){
                String next = (String) iterator.next();
                pw.print(next);
                pw.print("\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            pw.close();
        }
        pw.close();
    }

    public static void plainFile(String text){
        Assert.notNull(text, "text is null");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd-HHmmss");
        String format = sdf.format(new Date());
        String url = "D:\\tmp\\" + format + ".txt";
        File file = new File(url);
        PrintWriter pw = null;
        try {
             pw = new PrintWriter(file);
             pw.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            pw.close();
        }
        pw.close();
    }

}
