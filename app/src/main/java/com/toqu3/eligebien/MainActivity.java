package com.toqu3.eligebien;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

import org.json.JSONException;
import org.json.JSONObject;

import BaseClass.BaseAppCompatActivity;
import UtilsClass.FacebookConnectUtils;
import callbacks.LoginListener;
import fragments.LoginFragment;
import fragments.SignUpFragment;

//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends BaseAppCompatActivity implements LoginListener {

    /*Views*/
    KenBurnsView kbv;

    /*Fragments*/
    Fragment fragment_login, fragment_sign_up, fragment_sign_in;

    /*Fracebook*/
    FacebookConnectUtils facebookConnectUtils = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Facebook */
        /* Clases, Metodos,  Asociados*/
        /* Inicializar el callManager*/
        /* El contenView es llamado en este orden*/
        facebookConnectUtils = FacebookConnectUtils.getInstance(getApplicationContext());
        facebookConnectUtils.getFaebookSdkInitialize();
        facebookConnectUtils.setActivity(this);
        facebookConnectUtils.getFaebookSdkInitialize();
        facebookConnectUtils.getcallbackManagerFactory();

        setContentView(R.layout.activity_main);

        facebookConnectUtils.getLoginManager(new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("SUCCESS", "-----");
                Log.d("SUCCESS", "ID:" + loginResult.getAccessToken().getUserId());
                Log.d("SUCCESS", "TOKEN:" + loginResult.getAccessToken().getToken());
                Log.d("SUCCESS", "APPLICATIONID:" + loginResult.getAccessToken().getApplicationId());
                Log.d("SUCCESS", "GETDECLINEDPERMISSIONS:" + loginResult.getAccessToken().getDeclinedPermissions());
                Log.d("SUCCESS", "GETEXPIRES:" + loginResult.getAccessToken().getExpires());
                Log.d("SUCCESS", "GETLASTREFRESH:" + loginResult.getAccessToken().getLastRefresh());
                Log.d("SUCCESS", "GETSOURCE:" + loginResult.getAccessToken().getSource());
                Log.d("SUCCESS", "HASHCODE:" + loginResult.getAccessToken().hashCode());
                Log.d("SUCCESS", "ISEXPIRED:" + loginResult.getAccessToken().isExpired());
                Log.d("SUCCESS", "GETPERMISSIONS:" + loginResult.getAccessToken().getPermissions());

                facebookConnectUtils.getAccessTokenTracker(new AccessTokenTracker() {
                    @Override
                    public void startTracking() {
                        super.startTracking();
                        Log.d("SUCCESS", "STARTTRACKING");
                    }

                    @Override
                    public void stopTracking() {
                        super.stopTracking();
                        Log.d("SUCCESS", "STOPTRACKING");
                    }

                    @Override
                    public boolean isTracking() {
                        Log.d("SUCCESS", "ISTRACKING: " + isTracking());
                        return super.isTracking();
                    }

                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                        Log.d("SUCCESS", "OLDACCESSTOKEN: " + oldAccessToken + " CURRENTACCESSTOKEN:" + currentAccessToken);
                    }
                });

                facebookConnectUtils.getProfile();

                facebookConnectUtils.getProfileTracker(new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        Log.d("SUCCESS", "OLDPROFILE: " + oldProfile);
                        Log.d("SUCCESS", "CURRENTPROFILE: " + currentProfile);
                    }
                });

                facebookConnectUtils.getgraphRequestInfo(new GraphRequest().newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("SUCCESS", " INFO " + response.toString());
                        try {

                            Log.d("SUCCESS", "GRAPH ID:" + object.getString("id"));
                            Log.d("SUCCESS", "GRAPH NAME:" + object.getString("name"));
                            Log.d("SUCCESS", "GRAPH FIRSTNAME:" + object.getString("first_name"));
                            Log.d("SUCCESS", "GRAPH LASTNAME:" + object.getString("last_name"));
                            Log.d("SUCCESS", "GRAPH AGERANGE:" + object.getString("age_range"));
                            Log.d("SUCCESS", "GRAPH LINK:" + object.getString("link"));
                            Log.d("SUCCESS", "GRAPH GENDER:" + object.getString("gender"));
                            Log.d("SUCCESS", "GRAPH LOCALE:" + object.getString("locale"));
                            Log.d("SUCCESS", "GRAPH PICTURE:" + object.getString("picture"));
                            Log.d("SUCCESS", "GRAPH TIMEZONE:" + object.getString("timezone"));
                            Log.d("SUCCESS", "GRAPH UPDATEDTIME:" + object.getString("updated_time"));
                            Log.d("SUCCESS", "GRAPH BIRTHDAY:" + object.getString("birthday"));
                            Log.d("SUCCESS", "GRAPH EMAIL:" + object.getString("email"));

                            String imageurl = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=large";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }), "id, name, first_name, last_name, age_range, link, gender, locale, picture, timezone, updated_time, birthday, email, friends");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//
//                        Log.d("SUCCESS", "-----");
//                        Log.d("SUCCESS", "ID:" + loginResult.getAccessToken().getUserId());
//                        Log.d("SUCCESS", "TOKEN:" + loginResult.getAccessToken().getToken());
//                        Log.d("SUCCESS", "APPLICATIONID:" + loginResult.getAccessToken().getApplicationId());
//                        Log.d("SUCCESS", "GETDECLINEDPERMISSIONS:" + loginResult.getAccessToken().getDeclinedPermissions());
//                        Log.d("SUCCESS", "GETEXPIRES:" + loginResult.getAccessToken().getExpires());
//                        Log.d("SUCCESS", "GETLASTREFRESH:" + loginResult.getAccessToken().getLastRefresh());
//                        Log.d("SUCCESS", "GETSOURCE:" + loginResult.getAccessToken().getSource());
//                        Log.d("SUCCESS", "HASHCODE:" + loginResult.getAccessToken().hashCode());
//                        Log.d("SUCCESS", "ISEXPIRED:" + loginResult.getAccessToken().isExpired());
//                        Log.d("SUCCESS", "GETPERMISSIONS:" + loginResult.getAccessToken().getPermissions());
//
//                        accessTokenTracker = new AccessTokenTracker() {
//                            @Override
//                            public void startTracking() {
//                                super.startTracking();
//                                Log.d("SUCCESS", "STARTTRACKING");
//                            }
//
//                            @Override
//                            public void stopTracking() {
//                                super.stopTracking();
//                                Log.d("SUCCESS", "STOPTRACKING");
//                            }
//
//                            @Override
//                            public boolean isTracking() {
//                                Log.d("SUCCESS", "ISTRACKING: " + isTracking());
//                                return super.isTracking();
//                            }
//
//                            @Override
//                            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                                Log.d("SUCCESS", "OLDACCESSTOKEN: " + oldAccessToken + " CURRENTACCESSTOKEN:" + currentAccessToken);
//                            }
//                        };
//
//                        profile = Profile.getCurrentProfile();
//                        Log.d("SUCCESS", "NAME: " + profile.getName());
//                        Log.d("SUCCESS", "FIRSTNAME: " + profile.getFirstName());
//                        Log.d("SUCCESS", "LASTNAME: " + profile.getLastName());
//                        Log.d("SUCCESS", "ID: " + profile.getId());
//                        Log.d("SUCCESS", "MIDDLENAME: " + profile.getMiddleName());
//                        Log.d("SUCCESS", "GETLINKURI: " + profile.getLinkUri());
//                        Log.d("SUCCESS", "GETPROFILEPICTUREURI: " + profile.getProfilePictureUri(100, 100));
//
//                        profileTracker = new ProfileTracker() {
//                            @Override
//                            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                                Log.d("SUCCESS", "OLDPROFILE: " + oldProfile);
//                                Log.d("SUCCESS", "CURRENTPROFILE: " + currentProfile);
//                            }
//                        };
//
//                        graphRequestInfo = GraphRequest.newMeRequest(loginResult.getAccessToken(),
//                                new GraphRequest.GraphJSONObjectCallback() {
//                                    @Override
//                                    public void onCompleted(JSONObject object, GraphResponse response) {
//                                        Log.d("SUCCESS", " INFO " + response.toString());
//                                        try {
//
//                                            Log.d("SUCCESS", "GRAPH ID:" + object.getString("id"));
//                                            Log.d("SUCCESS", "GRAPH NAME:" + object.getString("name"));
//                                            Log.d("SUCCESS", "GRAPH FIRSTNAME:" + object.getString("first_name"));
//                                            Log.d("SUCCESS", "GRAPH LASTNAME:" + object.getString("last_name"));
//                                            Log.d("SUCCESS", "GRAPH AGERANGE:" + object.getString("age_range"));
//                                            Log.d("SUCCESS", "GRAPH LINK:" + object.getString("link"));
//                                            Log.d("SUCCESS", "GRAPH GENDER:" + object.getString("gender"));
//                                            Log.d("SUCCESS", "GRAPH LOCALE:" + object.getString("locale"));
//                                            Log.d("SUCCESS", "GRAPH PICTURE:" + object.getString("picture"));
//                                            Log.d("SUCCESS", "GRAPH TIMEZONE:" + object.getString("timezone"));
//                                            Log.d("SUCCESS", "GRAPH UPDATEDTIME:" + object.getString("updated_time"));
//                                            Log.d("SUCCESS", "GRAPH BIRTHDAY:" + object.getString("birthday"));
//                                            Log.d("SUCCESS", "GRAPH EMAIL:" + object.getString("email"));
//
//                                            String imageurl = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=large";
//
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
//
//                        bundleinfo = new Bundle();
//                        bundleinfo.putString("fields", "id, name, first_name, last_name, age_range, link, gender, locale, picture, timezone, updated_time, birthday, email, friends");
//                        graphRequestInfo.setParameters(bundleinfo);
//                        graphRequestInfo.executeAsync();
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });

        kbv = (KenBurnsView) findViewById(R.id.image);
        kbv.setTransitionListener(transitionListener);
        kbv.setTransitionGenerator(randomTransitionGenerator);

        /*Inicialoza los views de toda la actividad*/
        initCastViews();

        /*Inicializa el Framelayout Contenedor*/
        initContainerFragment(R.id.container_main);

        /*Clases, Metodos Asociados a Goggle*/
        if (savedInstanceState == null) {
            transactionFragment(fragment_login, null, "add", false);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        facebookConnectUtils.getAccessTokenTrackerStop();
        facebookConnectUtils.getProfileTrackerStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*Facebook*/
        facebookConnectUtils.getOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initInstanceFragments() {
        super.initInstanceFragments();
        fragment_login = new LoginFragment();
        fragment_sign_up = new SignUpFragment();

    }

    @Override
    public void initCastViews() {
        super.initCastViews();
    }


    /*CallBacks*/
    /*Login*/
    @Override
    public void onClickSignUp() {
        transactionFragment(fragment_sign_up, fragment_login, "replace", true);
    }

    @Override
    public void onClickSignFace() {
        facebookConnectUtils.LogInloginWithReadPermissions("public_profile", "user_friends");

    }

    @Override
    public void onClickSignGoogle() {
        facebookConnectUtils.LogOutFacebook();
    }

    @Override
    public void onClickSignInEmail() {

    }

    @Override
    public void onClickSignInAnonimous() {

    }

    /*cUSTOM vIEWS*/
    KenBurnsView.TransitionListener transitionListener = new KenBurnsView.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {
        }

        @Override
        public void onTransitionEnd(Transition transition) {
        }
    };
    RandomTransitionGenerator randomTransitionGenerator = new RandomTransitionGenerator(20000, new LinearInterpolator());


}
