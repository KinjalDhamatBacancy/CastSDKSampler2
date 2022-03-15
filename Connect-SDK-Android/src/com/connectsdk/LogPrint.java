package com.connectsdk;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogPrint {

    public static String logFileId = "";

    public static void appendLog(String text) {
        if (logFileId.isEmpty()) {
            DateFormat df = new DateFormat();
            String date = df.format("hh:mm:ss", new java.util.Date()).toString();
            logFileId = date;
        }

        File log = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + "/Log");

        if (!log.exists()) {
            log.mkdir();
        }

        File logFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + "/Log/Logs_file" + logFileId + ".txt");


        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                Log.e("appendLog", e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            //BufferedWriter for performance, true to set append to file flag
            DateFormat df = new DateFormat();
            String date = df.format("yyyy-MM-dd hh:mm:ss", new java.util.Date()).toString();


            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(date + "===> " + text);
            buf.newLine();
            buf.close();
            Log.e("Tag", " ===> " + text);
        } catch (IOException e) {
            Log.e("appendLog", e.getMessage());
            e.printStackTrace();
        }
    }
}
