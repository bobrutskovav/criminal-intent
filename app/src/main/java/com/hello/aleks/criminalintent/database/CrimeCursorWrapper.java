package com.hello.aleks.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.hello.aleks.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Aleks on 18.06.2017.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String date = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int solved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SUSPECT));
        String suspectPhone = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SUSPECT_PHONE));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(solved != 0);
        crime.setSuspect(suspect);
        crime.setSuspectPhone(suspectPhone);

        return crime;
    }

}
