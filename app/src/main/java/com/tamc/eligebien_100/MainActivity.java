package com.tamc.eligebien_100;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

import BaseClass.BaseAppCompatActivity;
import callbacks.LoginListener;
import fragments.LoginFragment;
import fragments.SignUpFragment;


public class MainActivity extends BaseAppCompatActivity implements LoginListener{

    /*Views*/
    KenBurnsView kbv;

    /*Fragments*/
    Fragment fragment_login, fragment_sign_up, fragment_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kbv = (KenBurnsView) findViewById(R.id.image);
        kbv.setTransitionListener(transitionListener);
        kbv.setTransitionGenerator(randomTransitionGenerator);

        /*Inicialoza los views de toda la actividad*/
        initCastViews();

        /*Inicializa el Framelayout Contenedor*/
        initContainerFragment(R.id.container_main);

        if (savedInstanceState == null) {
            transactionFragment(fragment_login, null, "add", false);
        }
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

    }

    @Override
    public void onClickSignGoogle() {

    }

    @Override
    public void onClickSignInEmail() {

    }

    @Override
    public void onClickSignInAnonimous() {

    }

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
