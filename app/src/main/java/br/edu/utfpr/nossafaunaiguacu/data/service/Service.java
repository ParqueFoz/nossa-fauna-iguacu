package br.edu.utfpr.nossafaunaiguacu.data.service;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("categorias")
    Single<List<CategoryModel>> getCategories();

    @GET("animalsByCategoria/{id}")
    Single<List<AnimalModel>> getAnimals(@Path("id") Integer id);

    @GET("animal/{id}")
    Single<AnimalModel> getAnimal(@Path("id") Integer id);

    @GET("animais")
    Single<List<AnimalModel>> getAnimals();
}
