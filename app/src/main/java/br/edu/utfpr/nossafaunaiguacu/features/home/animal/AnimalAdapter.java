package br.edu.utfpr.nossafaunaiguacu.features.home.animal;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemAnimalHeaderBinding;

public class AnimalAdapter {

    private AnimalModel animalModel;

    public AnimalAdapter(AnimalModel animalModel) {
        this.animalModel = animalModel;
    }


}

class HeaderViewHolder extends RecyclerView.ViewHolder {

    private ItemAnimalHeaderBinding binding;

    public HeaderViewHolder(ItemAnimalHeaderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(List<String> images) {

    }
}
