package br.edu.utfpr.nossafaunaiguacu.data.service;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;
import io.reactivex.rxjava3.core.Single;

public interface Service {

    Single<List<CategoryModel>> getCategories();
}
