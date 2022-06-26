package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.RetrofitProvider;
import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;
import br.edu.utfpr.nossafaunaiguacu.data.repository.RemoteRepository;
import br.edu.utfpr.nossafaunaiguacu.data.service.Service;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryViewModel extends ViewModel {

    private final MutableLiveData<List<CategoryModel>> _categories = new MutableLiveData<>();
    public final LiveData<List<CategoryModel>> categories = _categories;
    private final List<Disposable> disposables = new ArrayList<>();
    private final RemoteRepository remoteRepository = new RemoteRepository(RetrofitProvider.getRetrofit().create(Service.class));

    public void getCategories() {
        Disposable disposable = remoteRepository.getCategories()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(_categories::postValue, this::handleError);

        disposables.add(disposable);
    }

    private void handleError(Throwable throwable) {
        _categories.postValue(new ArrayList<>());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (Disposable disposable : disposables) {
            if (!disposable.isDisposed()) disposable.dispose();
        }
    }
}
