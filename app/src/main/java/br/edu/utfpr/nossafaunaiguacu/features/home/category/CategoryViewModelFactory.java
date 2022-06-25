package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.edu.utfpr.nossafaunaiguacu.data.repository.RemoteRepository;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {

    private final RemoteRepository remoteRepository;

    public CategoryViewModelFactory(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new CategoryViewModel(remoteRepository);
    }
}
