package br.edu.utfpr.nossafaunaiguacu.features.onboard.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityOnBoardBinding;

public class OnBoardActivity extends AppCompatActivity {

    private ActivityOnBoardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        setupViewPager();
    }

    private void setupLayout() {
        binding = ActivityOnBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupViewPager() {
        ViewPager2 viewPager = binding.viewPager2;
        viewPager.setAdapter(new OnBoardAdapter(this, getActionListener()));
    }

    private OnBoardActionListener getActionListener() {
        return new OnBoardActionListener() {
            @Override
            public void onNext(boolean isLastPage) {
                if (isLastPage) {
                    finish();
                } else {
                    ViewPager2 viewPager = binding.viewPager2;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }

            @Override
            public void onSkip() {
                finish();
            }
        };
    }
}
