package com.kurisuli.xlive;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void writeBytes(byte[] array) {
        FileOutputStream writer = null;

        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/codec.h264");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            writer = new FileOutputStream(file, true);
            writer.write(array);
            writer.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String writeContent(byte[] array) {
        char[] HEX_CHAR_TABLE = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(HEX_CHAR_TABLE[(b & 0xf0) >> 4]);
            sb.append(HEX_CHAR_TABLE[b & 0x0f]);
        }
        Log.i(TAG, "writeContent: " + sb.toString());
        FileWriter writer = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/codecH264.txt");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            writer = new FileWriter(file, true);
            writer.write(sb.toString());
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
