package br.edu.utfpr.nossafaunaiguacu.features.category.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding binding;
    private CategoryViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        setupRecyclerView();
        setupQrCodeButton();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setAdapter(new CategoryAdapter(viewModel.getCategories()));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void setupQrCodeButton() {
        binding.scanQrCodeBtn.setOnClickListener(view -> {
            // TODO call scan qr code activity
        });
    }
}
