package com.hello.aleks.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "com.hello.aleks.criminalintent.crimeid";
    private static final String EXTRA_ADAPTER_POSITION = "com.hello.aleks.criminalintent.adapterposition";


    public static Intent newIntent(Context packageContext, UUID crimeId, int adapterPosition) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_ADAPTER_POSITION, adapterPosition);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        int adapterPosition = getIntent().getIntExtra(EXTRA_ADAPTER_POSITION, 0);
        return CrimeFragment.newInstance(crimeId, adapterPosition);
    }
}
