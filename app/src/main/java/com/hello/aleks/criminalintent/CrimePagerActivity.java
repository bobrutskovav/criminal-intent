package com.hello.aleks.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Aleks on 08.06.2017.
 */

public class CrimePagerActivity extends AppCompatActivity {
    public static final String SUBTITLE_STATE_KEY = "com.hello.aleks.criminalintent.subtitle_state";
    private static final String EXTRA_CRIME_ID = "com.hello.aleks.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private boolean subtitle_flag;

    public static Intent newIntent(Context packageContext, UUID crimeId, boolean subtitle_flag) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(SUBTITLE_STATE_KEY, subtitle_flag);
        return intent;
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = super.getParentActivityIntent();
        intent.putExtra(SUBTITLE_STATE_KEY, subtitle_flag);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        subtitle_flag = getIntent().getBooleanExtra(SUBTITLE_STATE_KEY, false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeid)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
