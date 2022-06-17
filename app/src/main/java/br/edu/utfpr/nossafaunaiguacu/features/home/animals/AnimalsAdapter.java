package br.edu.utfpr.nossafaunaiguacu.features.home.animals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemAnimalBinding;

public class AnimalsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AnimalModel> animals;
    private final Boolean isFavorite;
    private OnAnimalClickListener listener;

    public AnimalsAdapter(
            List<AnimalModel> animals,
            Boolean isFavorite,
            OnAnimalClickListener listener
    ) {
        this.animals = animals;
        this.isFavorite = isFavorite;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAnimalBinding binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AnimalViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AnimalViewHolder) holder).bind(animals.get(position), isFavorite);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}

class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final ItemAnimalBinding binding;
    private final OnAnimalClickListener listener;

    public AnimalViewHolder(ItemAnimalBinding binding, OnAnimalClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(AnimalModel model, Boolean isFavorite) {
        binding.getRoot().setOnClickListener(v -> {
            listener.onAnimalSelected(model.getId());
        });
    }
}