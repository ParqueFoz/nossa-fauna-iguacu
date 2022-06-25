package br.edu.utfpr.nossafaunaiguacu.features.home.animals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemAnimalBinding;

public class AnimalsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AnimalModel> animals;
    private OnAnimalClickListener listener;

    public AnimalsAdapter(
            List<AnimalModel> animals,
            OnAnimalClickListener listener
    ) {
        this.animals = animals;
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
        ((AnimalViewHolder) holder).bind(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}

class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final ItemAnimalBinding binding;
    private final OnAnimalClickListener listener;
    private Boolean favorite = true;

    public AnimalViewHolder(ItemAnimalBinding binding, OnAnimalClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(AnimalModel model) {
        binding.getRoot().setOnClickListener(v -> {
            listener.onAnimalSelected(model);
        });
        favorite = LocalRepository.isFavorite(model.getId());
        binding.animalImage.setOnClickListener(v -> {
            favorite = !favorite;
            LocalRepository.saveFavorite(model.getId(), favorite);
            binding.animalImage.setImageDrawable(AppCompatResources.getDrawable(
                    binding.animalImage.getContext(),
                    favorite ? R.drawable.ic_animal_fav_filled : R.drawable.ic_animal_fav_empty
            ));
        });
        binding.animalName.setText(model.getName());
        Glide.with(binding.animalImage)
                .load(model.getBackgroundImage())
                .centerCrop()
                .into(binding.animalImage);
    }
}