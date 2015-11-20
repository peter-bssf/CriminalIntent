package com.pengl.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by pengl on 15-11-20.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
