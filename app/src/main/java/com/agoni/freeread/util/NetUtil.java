package com.agoni.freeread.util;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Agoni on 2017/2/22.
 */

public class NetUtil {

    public static void getString(String path, Handler handler,int what) {
        try {
            InputStream is = new URL(path).openStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();
            String line=null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            String s = sb.toString().trim();
            Message msg = Message.obtain();
            msg.what=what;
            msg.obj=s;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
