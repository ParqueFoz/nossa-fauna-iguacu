package br.edu.utfpr.nossafaunaiguacu.features.home.animal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityAnimalBinding;

public class AnimalActivity extends AppCompatActivity {

    private ActivityAnimalBinding binding;
    private AnimalModel animalModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        animalModel = (AnimalModel) getIntent().getExtras().getSerializable("MODEL");
        setupAnimal();
    }

    private void setupAnimal() {

    }

    public static Intent newInstance(Context activity, AnimalModel animalModel) {
        Intent intent = new Intent(activity, AnimalActivity.class);
        intent.putExtra("MODEL", animalModel);
        return intent;
    }
}
