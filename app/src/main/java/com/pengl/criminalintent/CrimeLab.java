package com.pengl.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pengl on 15-11-19.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

        // for test to create a bunch crime items
        for(int i=0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setResolved(i % 2 == 0);
            mCrimes.add(c);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime crime: mCrimes)
            //Notice: should use getId().equals method to compare with id;
            //or it'll fail to return the crime you want.
            if(crime.getId().equals(id))
                return crime;
        return null;
    }

    public Crime addCrime() {
        int i = mCrimes.size() + 1;

        Crime crime = new Crime();
        crime.setTitle("Crime #" + i);
        crime.setResolved(false);
        mCrimes.add(crime);

        return crime;
    }

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }
}
