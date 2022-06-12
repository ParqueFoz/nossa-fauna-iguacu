package br.edu.utfpr.nossafaunaiguacu.features.category.presentation;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.features.category.data.CategoryModel;

public class CategoryViewModel extends ViewModel {

    public List<CategoryModel> getCategories() {
        // TODO fetch from local repository (This should be fetched when app starts or when it doesn't have content)
        return new ArrayList<>();
    }
}
