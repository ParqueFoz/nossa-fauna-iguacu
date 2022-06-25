package br.edu.utfpr.nossafaunaiguacu.features.onboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityOnBoardBinding;
import br.edu.utfpr.nossafaunaiguacu.features.home.HomeActivity;

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
                    skipOnBoard();
                } else {
                    ViewPager2 viewPager = binding.viewPager2;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }

            @Override
            public void onSkip() {
                skipOnBoard();
            }
        };
    }

    private void skipOnBoard() {
        LocalRepository.saveNotFirstAccess();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
