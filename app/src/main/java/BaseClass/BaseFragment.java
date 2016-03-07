package BaseClass;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.toqu3.eligebien.MainActivity;

/**
 * Created by BALAREZO on 06/03/2016.
 */
public class BaseFragment extends Fragment{

    public Context mContext;
    public View view;
    private FragmentActivity mActivity;
    private String TAG;
    private Typeface tf;
    private String oldfragment = null;

    public Toolbar mToolbar;
    public TextView txt_toolbar_title;

    public FragmentActivity getfragmentActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {

        if (!(context instanceof FragmentActivity)) {
            throw new IllegalStateException(getClass().getSimpleName());
        }
        mContext = getActivity();
        mActivity = (FragmentActivity) context;
//        ((MainActivity) mContext).fragmentCommunicator = this;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public void setOnClickListeners(View.OnClickListener clickListener,View... view){
        for (View aView : view) {
            aView.setOnClickListener(clickListener);
        }
    }

}
