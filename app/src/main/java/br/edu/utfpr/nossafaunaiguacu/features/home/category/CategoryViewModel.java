package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;

public class CategoryViewModel extends ViewModel {

    private final MutableLiveData<List<CategoryModel>> _categories = new MutableLiveData<>();
    public final LiveData<List<CategoryModel>> categories = _categories;

    public void getCategories() {

    }
}
