package br.edu.utfpr.nossafaunaiguacu.features.home.animals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.utfpr.nossafaunaiguacu.databinding.FragmentAnimalsBinding;

public class AnimalsFragment extends Fragment {

    private static final String IS_FAVORITE = "IS_FAVORITE";

    private AnimalsViewModel viewModel;
    private FragmentAnimalsBinding binding;
    private Boolean isFavorite;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentAnimalsBinding.inflate(inflater, container, false);
        isFavorite = getArguments().getBoolean(IS_FAVORITE, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        getAnimals();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(AnimalsViewModel.class);
        viewModel.animals.observe(getViewLifecycleOwner(), animals -> {
            binding.recyclerView.setAdapter(new AnimalsAdapter(animals, isFavorite, getAnimalClickListener()));
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
        });
    }

    private OnAnimalClickListener getAnimalClickListener() {
        return animalId -> {

        };
    }

    private void getAnimals() {
        viewModel.getAnimals(isFavorite);
    }
}
