package com.toqu3.eligebien;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import UtilsClass.FacebookConnectUtils;
import UtilsClass.PrintHashKey;


/**
 * Created by BALAREZO on 12/03/2016.
 */
public class MyApplication extends Application {

    PrintHashKey printHashKey = null;

    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey = new PrintHashKey(getPackageManager());
        printHashKey.printHashKey();
    }

}
