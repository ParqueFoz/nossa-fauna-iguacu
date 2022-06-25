package br.edu.utfpr.nossafaunaiguacu.data.repository;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;
import br.edu.utfpr.nossafaunaiguacu.data.service.Service;
import io.reactivex.rxjava3.core.Single;

public class RemoteRepository {

    private Service service;

    public RemoteRepository(Service service) {
        this.service = service;
    }

    public Single<List<CategoryModel>> getCategories() {
        return service.getCategories();
    }

    public Single<List<AnimalModel>> getAnimals(Integer categoryId) {
        return service.getAnimals(categoryId);
    }

    public Single<AnimalModel> getAnimal(Integer id) {
        return service.getAnimal(id);
    }
}
