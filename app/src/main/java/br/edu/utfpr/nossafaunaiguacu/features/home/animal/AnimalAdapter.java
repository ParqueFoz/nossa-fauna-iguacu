package br.edu.utfpr.nossafaunaiguacu.features.home.animal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.data.model.AnimalModel;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemAnimalHeaderBinding;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemAnimalSectionBinding;

public class AnimalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AnimalModel animalModel = null;

    public AnimalAdapter(AnimalModel animalModel) {
        this.animalModel = animalModel;
    }

    @Override
    public int getItemCount() {
        if (animalModel == null) {
            return 0;
        } else {
            return 7;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((HeaderViewHolder) holder).bind(animalModel);
        } else {
            ((SectionViewHolder) holder).bind(animalModel, position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_animal_header) {
            return new HeaderViewHolder(ItemAnimalHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new SectionViewHolder(ItemAnimalSectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_animal_header;
        } else {
            return R.layout.item_animal_section;
        }
    }
}

class HeaderViewHolder extends RecyclerView.ViewHolder {

    private ItemAnimalHeaderBinding binding;

    public HeaderViewHolder(ItemAnimalHeaderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(AnimalModel animalModel) {
        Glide.with(binding.img)
                .load(animalModel.getBackgroundImage())
                .centerCrop()
                .into(binding.img);
    }
}

class SectionViewHolder extends RecyclerView.ViewHolder {

    private ItemAnimalSectionBinding binding;

    public SectionViewHolder(ItemAnimalSectionBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(AnimalModel model, int position) {
        binding.title.setOnClickListener(v -> {
            if (position == 0) {
                binding.content.setVisibility(binding.content.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            } else {
                binding.img.setVisibility(binding.img.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        if (position == 1) {
            binding.title.setText("Características");
            binding.content.setText(model.getParticulars());
        } else if (position == 2) {
            binding.title.setText("Alimentação");
            binding.content.setText(model.getFeeding());
        } else if (position == 3) {
            binding.title.setText("Reprodução");
            binding.content.setText(model.getRepro());
        } else if (position == 4) {
            binding.title.setText("Habitat");
            binding.content.setText(model.getHabitat());
        } else if (position == 5) {
            binding.title.setText("Importância Ecológica");
            binding.content.setText(model.getImportEco());
        } else {
            binding.title.setText("Pegada");
            Glide.with(binding.img)
                    .load(model.getFootPrint())
                    .centerCrop()
                    .into(binding.img);
        }
    }
}
