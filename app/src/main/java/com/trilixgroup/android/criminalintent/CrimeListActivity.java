package com.trilixgroup.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by tylerfunk on 6/21/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
