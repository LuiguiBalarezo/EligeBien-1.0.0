package UtilsClass;

import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by BALAREZO on 14/03/2016.
 */
public class PrintHashKey {
    private PackageManager contextWrapper = null;
    public PrintHashKey(PackageManager packageManager) {
        this.contextWrapper = packageManager;
    }

    public void printHashKey() {
        try {
            PackageInfo info = contextWrapper.getPackageInfo(
                    "com.toqu3.eligebien",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }
}
