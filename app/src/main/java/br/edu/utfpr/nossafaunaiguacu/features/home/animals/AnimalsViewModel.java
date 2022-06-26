package br.edu.utfpr.nossafaunaiguacu.features.home.animals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.RetrofitProvider;
import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;
import br.edu.utfpr.nossafaunaiguacu.data.repository.RemoteRepository;
import br.edu.utfpr.nossafaunaiguacu.data.service.Service;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AnimalsViewModel extends ViewModel {

    private final MutableLiveData<List<AnimalModel>> _animals = new MutableLiveData<>();
    public final LiveData<List<AnimalModel>> animals = _animals;
    private RemoteRepository remoteRepository = new RemoteRepository(RetrofitProvider.getRetrofit().create(Service.class));
    private final List<Disposable> disposables = new ArrayList<>();

    public void getAnimals(Integer cId, Boolean isFavorite) {
        disposables.add(remoteRepository.getAnimals(cId)
                .map((model) -> {
                    if (isFavorite) {
                        List<AnimalModel> animals = new ArrayList<>();
                        for (AnimalModel animal : model) {
                            if (LocalRepository.isFavorite(animal.getId())) {
                                animals.add(animal);
                            }
                        }
                        return animals;
                    } else {
                        return model;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(_animals::postValue, error -> {
                    _animals.postValue(new ArrayList<>());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (Disposable disposable : disposables) {
            if (!disposable.isDisposed()) disposable.dispose();
        }
    }
}
