package com.trilixgroup.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trilixgroup.android.criminalintent.database.CrimeBaseHelper;
import com.trilixgroup.android.criminalintent.database.CrimeCursorWrapper;
import com.trilixgroup.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tylerfunk on 6/21/16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context;
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public long addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        return mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public int updateCrime(Crime c) {
        ContentValues cv = getContentValues(c);
        return mDatabase.update(CrimeTable.NAME, cv,
                CrimeTable.Cols.UUID + " = ?",
                new String[] { c.getId().toString() });
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[]{ id.toString()});
        Crime c = null;

        try {
            if (cursor.getCount() == 0) return null;

            cursor.moveToFirst();
            c = cursor.getCrime();
        } finally {
            cursor.close();
        }

        return c;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();

        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved()?1:0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,    // table name
                null,               // columns -- null selects all columns
                whereClause,
                whereArgs,
                null,               // groupBy
                null,               // having
                "_id"               // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }
}
