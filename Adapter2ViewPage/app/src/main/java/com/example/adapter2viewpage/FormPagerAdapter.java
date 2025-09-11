package com.example.adapter2viewpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FormPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public FormPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new FormPageFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}