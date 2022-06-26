package br.edu.utfpr.nossafaunaiguacu.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityHomeBinding;
import br.edu.utfpr.nossafaunaiguacu.features.QrCodeActivity;
import br.edu.utfpr.nossafaunaiguacu.features.home.animals.AnimalsFragment;
import br.edu.utfpr.nossafaunaiguacu.features.home.category.CategoryFragment;
import br.edu.utfpr.nossafaunaiguacu.features.home.category.OnSelectCategoryListener;
import br.edu.utfpr.nossafaunaiguacu.features.home.location.LocationFragment;

public class HomeActivity extends FragmentActivity implements OnSelectCategoryListener {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupListeners();
        binding.bottomNav.setSelectedItemId(R.id.home);
    }

    private void setupListeners() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.favs: {
                    switchFragment(AnimalsFragment.newInstance(-1, true));
                    return true;
                }
                case R.id.home: {
                    switchFragment(CategoryFragment.newInstance());
                    return true;
                }
                case R.id.loc: {
                    switchFragment(new LocationFragment());
                    return true;
                }
                default:
                    return true;
            }
        });
        binding.scanQrCodeBtn.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, QrCodeActivity.class));
        });
    }

    @Override
    public void onCategorySelected(Integer id) {
        switchFragment(AnimalsFragment.newInstance(id, false));
        binding.bottomNav.setSelectedItemId(R.id.inv);
    }

    private void switchFragment(Fragment fragment) {
        if (fragment instanceof LocationFragment) {
            binding.scanQrCodeBtn.setVisibility(View.GONE);
        } else {
            binding.scanQrCodeBtn.setVisibility(View.VISIBLE);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "fragment")
                .commit();
    }
}
