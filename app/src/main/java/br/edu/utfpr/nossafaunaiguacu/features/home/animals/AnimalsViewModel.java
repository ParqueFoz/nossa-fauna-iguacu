package br.edu.utfpr.nossafaunaiguacu.features.home.animals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;

public class AnimalsViewModel extends ViewModel {

    private final MutableLiveData<List<AnimalModel>> _animals = new MutableLiveData<>();
    public final LiveData<List<AnimalModel>> animals = _animals;

    public void getAnimals(Boolean isFavorite) {
        // TODO filter animals by favorites
    }
}
