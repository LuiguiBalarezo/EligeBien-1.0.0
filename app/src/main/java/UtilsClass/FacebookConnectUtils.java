package UtilsClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BALAREZO on 13/03/2016.
 */
public class FacebookConnectUtils {

    private static FacebookConnectUtils instance;
    private static Context mcontext;

    private CallbackManager callbackManager = null;
    private Profile profile = null;
    private ProfileTracker profileTracker = null;
    private GraphRequest graphRequestInfo = null, graphRequestFriends = null;
    private Bundle bundleinfo = null;
    private LoginResult mloginResult = null;
    private boolean cancelLogin = false;
    private String errorLogin = null;
    private static List<String> miListaDeEnteros = null;
    private String[] miArrayDePalabras = {};
    private static Activity mActivity = null;
    private static Fragment mFragment = null;

    /*AccessTokenTracker*/
    private AccessTokenTracker accessTokenTracker = null;

    private FacebookConnectUtils() {

    }

    public static FacebookConnectUtils getInstance(Context context) {
        if (instance == null) {
            instance = new FacebookConnectUtils();
            mcontext = context;
            miListaDeEnteros = new ArrayList<>();
        }
        return instance;
    }

    /*Call for set Activity references*/
    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    /*Call in onCreate()*/
    public void getFaebookSdkInitialize() {
        FacebookSdk.sdkInitialize(mcontext);
    }

    /*Call in oncreate() before create setContentView() */
    public void getcallbackManagerFactory() {
        callbackManager = CallbackManager.Factory.create();
    }

    /*Call for Status*/
    public void getLoginManager(FacebookCallback facebookCallback) {
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);
    }

    /*COnfigure Permission*/
    public void LogInloginWithReadPermissions(String... permissions) {
        if (mActivity != null) {
            LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList(permissions));
        } else if (mFragment != null) {
            LoginManager.getInstance().logInWithReadPermissions(mFragment, Arrays.asList(permissions));
        }
    }

    public void LogOutFacebook() {
        LoginManager.getInstance().logOut();
    }

    public void getOnActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /*AccessTokenTracker*/
    public void getAccessTokenTracker(AccessTokenTracker accessTokenTracker) {
        if (this.accessTokenTracker == null) {
            this.accessTokenTracker = accessTokenTracker;
        }
    }

    public void getAccessTokenTrackerStop() {
        if (accessTokenTracker != null) {
            accessTokenTracker.stopTracking();
        }
    }

    /*Profie*/
    public Profile getProfile() {
        return profile = Profile.getCurrentProfile();
    }

    public void getProfileTracker(ProfileTracker profileTracker) {
        this.profileTracker = profileTracker;
    }

    public void getgraphRequestInfo(GraphRequest graphRequest, String fields) {
        this.graphRequestInfo = graphRequest;
        bundleinfo = new Bundle();
        bundleinfo.putString("fields", fields);
        graphRequestInfo.setParameters(bundleinfo);
        graphRequestInfo.executeAsync();

    }

    public void getProfileTrackerStop() {
        if (profileTracker != null) {
            profileTracker.stopTracking();
        }
    }

}
