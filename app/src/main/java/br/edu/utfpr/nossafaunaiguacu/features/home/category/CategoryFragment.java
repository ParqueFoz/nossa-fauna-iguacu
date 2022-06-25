package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import br.edu.utfpr.nossafaunaiguacu.databinding.FragmentCategoryBinding;
import dagger.android.support.AndroidSupportInjection;

public class CategoryFragment extends Fragment {

    private static final String LISTENER_KEY = "LISTENER_KEY";

    private FragmentCategoryBinding binding;
    private CategoryViewModel viewModel;
    private OnSelectCategoryListener listener;

    @Inject
    CategoryViewModelFactory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        AndroidSupportInjection.inject(this);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        listener = (OnSelectCategoryListener) getArguments().getSerializable(LISTENER_KEY);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        getCategories();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(CategoryViewModel.class);
        viewModel.categories.observe(getViewLifecycleOwner(), categories -> {
            binding.categories.setAdapter(new CategoryAdapter(categories, listener));
            binding.categories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        });
    }

    private void getCategories() {
        viewModel.getCategories();
    }

    public static CategoryFragment newInstance(OnSelectCategoryListener listener) {
        Bundle args = new Bundle();
        args.putSerializable(LISTENER_KEY, listener);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
