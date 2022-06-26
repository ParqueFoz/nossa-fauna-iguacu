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
import br.edu.utfpr.nossafaunaiguacu.features.home.animal.AnimalActivity;

public class AnimalsFragment extends Fragment {

    private static final String IS_FAVORITE = "IS_FAVORITE";
    private static final String CATEGORY_ID = "CATEGORY_ID";

    private AnimalsViewModel viewModel;
    private FragmentAnimalsBinding binding;
    private Boolean isFavorite;
    private Integer categoryId;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentAnimalsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isFavorite = getArguments().getBoolean(IS_FAVORITE, false);
        categoryId = getArguments().getInt(CATEGORY_ID, 1);
        setupViewModel();
        getAnimals();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(AnimalsViewModel.class);
        viewModel.animals.observe(getViewLifecycleOwner(), animals -> {
            binding.progress.setVisibility(View.GONE);
            binding.recyclerView.setAdapter(new AnimalsAdapter(animals, getAnimalClickListener()));
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
        });
    }

    private OnAnimalClickListener getAnimalClickListener() {
        return animalModel -> {
            startActivity(AnimalActivity.newInstance(getContext(), animalModel));
        };
    }

    private void getAnimals() {
        viewModel.getAnimals(categoryId, isFavorite);
    }

    public static AnimalsFragment newInstance(Integer categoryId, Boolean isFavorite) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID, categoryId);
        args.putBoolean(IS_FAVORITE, isFavorite);
        AnimalsFragment fragment = new AnimalsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
