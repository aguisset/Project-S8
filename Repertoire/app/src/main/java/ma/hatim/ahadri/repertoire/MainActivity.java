package ma.hatim.ahadri.repertoire;


import android.support.v4.app.Fragment;


/*
    The Main Activity class of the App !
 */
public class MainActivity extends MainFragmentActivity
{
    @Override
    public Fragment createFragment()
    {
        return ContactsListFragment.newInstance();
    }

}