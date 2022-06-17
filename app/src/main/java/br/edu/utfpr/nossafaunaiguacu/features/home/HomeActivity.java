package br.edu.utfpr.nossafaunaiguacu.features.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityHomeBinding;
import br.edu.utfpr.nossafaunaiguacu.features.home.animals.AnimalsFragment;
import br.edu.utfpr.nossafaunaiguacu.features.home.category.CategoryFragment;
import br.edu.utfpr.nossafaunaiguacu.features.home.category.OnSelectCategoryListener;
import br.edu.utfpr.nossafaunaiguacu.features.home.location.LocationFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupListeners();
    }

    private void setupListeners() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.favs: {
                    switchFragment(new AnimalsFragment());
                    return true;
                }
                case R.id.home: {
                    switchFragment(CategoryFragment.newInstance(getSelectedCategoryListener()));
                    return true;
                }
                case R.id.loc: {
                    switchFragment(new LocationFragment());
                    return true;
                }
                default:
                    return false;
            }
        });
        binding.scanQrCodeBtn.setOnClickListener(view -> {
            // TODO call scan qr code activity
        });
    }

    private OnSelectCategoryListener getSelectedCategoryListener() {
        return id -> {
            switchFragment(new AnimalsFragment());
            binding.bottomNav.setSelected(false);
        };
    }

    private void switchFragment(Fragment fragment) {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(fragment, "fragment")
                .commit();
    }
}
