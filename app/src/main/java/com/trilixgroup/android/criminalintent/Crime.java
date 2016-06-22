package com.trilixgroup.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tylerfunk on 6/21/16.
 */
public class Crime {

    private static final String HUMAN_DATE_FORMAT = "EEEE, MMM d, yyyy";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public String getHumanDate() {
        return android.text.format.DateFormat.format(HUMAN_DATE_FORMAT, mDate).toString();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
