package com.trilixgroup.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.trilixgroup.android.criminalintent.Crime;
import com.trilixgroup.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tylerfunk on 6/22/16.
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
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));

        Crime c = new Crime(UUID.fromString(uuidString));
        c.setTitle(title);
        c.setDate(new Date(date));
        c.setSolved(isSolved != 0);

        return c;
    }
}
