package com.hello.aleks.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Aleks on 29.05.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
