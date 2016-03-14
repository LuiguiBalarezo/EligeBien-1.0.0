package fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toqu3.eligebien.R;

import BaseClass.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import callbacks.LoginListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    LoginListener loginListener;

    @Bind(R.id.btn_sign_up_with_email)
    Button btn_sign_up_email;
    @Bind(R.id.btn_face)
    Button btn_face;
    @Bind(R.id.btn_google)
    Button btn_google;
    @Bind(R.id.btn_sign_in_with_email)
    Button btn_sign_in;
    @Bind(R.id.btn_sign_in_anonimous)
    Button btn_sign_anonimous;

    public LoginFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            loginListener = (LoginListener) mContext;
            mContext = getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener ");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        initSdkFacebook();

        setOnClickListeners(onClickListener,
                btn_sign_up_email,
                btn_face, btn_google,
                btn_sign_in,
                btn_sign_anonimous);

        return view;
    }

    private void initSdkFacebook(){
        ButterKnife.bind(this, view);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sign_up_with_email:
                    loginListener.onClickSignUp();
                    break;
                case R.id.btn_face:
                    loginListener.onClickSignFace();
                    break;
                case R.id.btn_google:
                    loginListener.onClickSignGoogle();
                    break;
                case R.id.btn_sign_in_with_email:
                    loginListener.onClickSignInEmail();
                    break;
                case R.id.btn_sign_in_anonimous:
                    loginListener.onClickSignInAnonimous();
                    break;
            }
        }
    };


}
