package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.data.model.CategoryModel;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemCategoryBinding;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemCategoryHeaderBinding;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CategoryModel> categories;
    private final OnSelectCategoryListener listener;

    public CategoryAdapter(List<CategoryModel> categories, OnSelectCategoryListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).bind(categories.get(position - 1));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_category_header) {
            return new HeaderViewHolder(ItemCategoryHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), listener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_category_header;
        } else {
            return R.layout.item_category;
        }
    }

    @Override
    public int getItemCount() {
        if (categories.size() == 0) {
            return 0;
        }
        return categories.size() + 1;
    }
}

class HeaderViewHolder extends RecyclerView.ViewHolder {

    public HeaderViewHolder(ItemCategoryHeaderBinding binding) {
        super(binding.getRoot());
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder {

    private final ItemCategoryBinding binding;
    private final OnSelectCategoryListener listener;

    public CategoryViewHolder(ItemCategoryBinding binding, OnSelectCategoryListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(CategoryModel model) {
        setupViews(model);
        setupListener(model);
    }

    private void setupViews(CategoryModel model) {
        binding.title.setText(model.getName());
        binding.animalBg.setClipToOutline(true);
        binding.backgroundImg.setClipToOutline(true);
        Glide.with(binding.animalBg)
                .load(model.getImageUrl())
                .centerCrop()
                .into(binding.animalBg);
    }

    private void setupListener(CategoryModel model) {
        binding.getRoot().setOnClickListener(view -> {
            listener.onCategorySelected(model.getId());
        });
    }
}