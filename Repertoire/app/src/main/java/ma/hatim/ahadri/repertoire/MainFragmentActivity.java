package ma.hatim.ahadri.repertoire;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


/**
 * Created by hatim on 01/03/16.
 */

public abstract  class MainFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment createFragment();

    @Override

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null)
        {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container , fragment)
                    .commit();
        }


    }



}
