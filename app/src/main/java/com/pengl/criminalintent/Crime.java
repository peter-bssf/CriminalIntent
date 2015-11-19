package com.pengl.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pengl on 2015/11/18.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mResolved;

    public Crime() {
        mId = UUID.randomUUID();
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isResolved() {
        return mResolved;
    }

    public void setResolved(boolean resolved) {
        mResolved = resolved;
    }

}
