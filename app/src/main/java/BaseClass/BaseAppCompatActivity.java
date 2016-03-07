package BaseClass;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.toqu3.eligebien.R;

/**
 * Created by BALAREZO on 06/03/2016.
 */
public class BaseAppCompatActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fm;
    private FragmentTransaction ft;
    private String fragmenold, fragmentnew;
    private int container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        initInstanceFragments();
    }

    public void initContainerFragment(int container) {this.container = container;}

    public void initInstanceFragments() {}

    public void initCastViews(){}

    @Override
    public void onClick(View v) {
    }

    public void transactionFragment(Fragment fragmentnew, Fragment fragmentold, String typetransaction, boolean isaddToBackStack) {
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        if (fragmentold != null) {
            this.fragmenold = fragmentold.getClass().getSimpleName();
        }
        this.fragmentnew = fragmentnew.getClass().getSimpleName();
        switch (typetransaction) {
            case "add":
                ft.add(this.container, fragmentnew, this.fragmentnew);
                break;
            case "replace":
                ft.replace(this.container, fragmentnew, this.fragmentnew);
                break;
        }
        if (isaddToBackStack) {
            ft.addToBackStack(this.fragmentnew);
        }
        ft.commit();
        fm.executePendingTransactions();
    }

}
