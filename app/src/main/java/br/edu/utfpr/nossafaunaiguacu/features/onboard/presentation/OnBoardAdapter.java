package br.edu.utfpr.nossafaunaiguacu.features.onboard.presentation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnBoardAdapter extends FragmentStateAdapter {

    private final int VIEW_PAGER_SIZE = 3;
    private final OnBoardActionListener actionListener;

    public OnBoardAdapter(FragmentActivity activity, OnBoardActionListener actionListener) {
        super(activity);
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return OnBoardFragment.newInstance(position, actionListener);
    }

    @Override
    public int getItemCount() {
        return VIEW_PAGER_SIZE;
    }
}
