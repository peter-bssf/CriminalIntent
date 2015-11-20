package com.pengl.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pengl on 15-11-20.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mAdapter;
    private List<Integer> mPositionChanged = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecycleView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    /*
    private void iterateAllPosition() {
        for(int i=0, size = mPositionChanged.size(); i<size; i++) {
            int key = mPositionChanged.keyAt(i);
            boolean changed = mPositionChanged.get(key);
            if(changed) {
                mAdapter.notifyItemChanged(key);
            }
        }
    }*/

    private void updateUI() {
        CrimeLab crimeLab =CrimeLab.get(getActivity());
        List<Crime> crimes =crimeLab.getCrimes();

        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecycleView.setAdapter(mAdapter);
        } else {
            //iterateAllPosition();
            for(int i: mPositionChanged) {
                mAdapter.notifyItemChanged(i);
            }
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolveCheckBox;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            mDateTextView  = (TextView) itemView.findViewById(R.id.list_item_data_text_view);
            mSolveCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        @Override
        public void onClick(View v) {
            Intent i = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(i);
        }

        public void bindCrime (Crime crime, int position) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolveCheckBox.setChecked(mCrime.isResolved());
            for(int i: mPositionChanged) {
                if(i == position)
                    return;
            }
            mPositionChanged.add(position);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime, position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
