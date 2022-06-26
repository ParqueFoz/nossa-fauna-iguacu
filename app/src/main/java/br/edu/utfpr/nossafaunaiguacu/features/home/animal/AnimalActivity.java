package br.edu.utfpr.nossafaunaiguacu.features.home.animal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.RetrofitProvider;
import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.data.repository.RemoteRepository;
import br.edu.utfpr.nossafaunaiguacu.data.service.Service;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityAnimalBinding;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AnimalActivity extends AppCompatActivity {

    private ActivityAnimalBinding binding;
    private AnimalModel animalModel;
    private List<Disposable> disposables = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getExtras().getSerializable("MODEL") != null) {
            animalModel = (AnimalModel) getIntent().getExtras().getSerializable("MODEL");
            setupAnimal();
        } else {
            disposables.add(new RemoteRepository(RetrofitProvider.getRetrofit().create(Service.class))
                    .getAnimal(getIntent().getExtras().getInt("ID"))
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(a -> {
                        animalModel = a;
                        setupAnimal();
                    }, throwable -> runOnUiThread(this::finish)));
        }
    }

    private void setupAnimal() {
        binding.progress.setVisibility(View.GONE);
        binding.recyclerView.setAdapter(new AnimalAdapter(animalModel));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Disposable d : disposables) {
            if (!d.isDisposed()) d.dispose();
        }
    }

    public static Intent newInstance(Context activity, AnimalModel animalModel) {
        Intent intent = new Intent(activity, AnimalActivity.class);
        intent.putExtra("MODEL", animalModel);
        return intent;
    }

    public static Intent newInstance(Context activity, Integer id) {
        Intent intent = new Intent(activity, AnimalActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }
}
