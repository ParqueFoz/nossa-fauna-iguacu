package br.edu.utfpr.nossafaunaiguacu.features.category.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemCategoryBinding;
import br.edu.utfpr.nossafaunaiguacu.databinding.ItemCategoryHeaderBinding;
import br.edu.utfpr.nossafaunaiguacu.features.category.data.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CategoryModel> categories;

    public CategoryAdapter(List<CategoryModel> categories) {
        this.categories = categories;
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
            return new CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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

    public CategoryViewHolder(ItemCategoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CategoryModel model) {
        setupViews(model);
        setupListener(model);
    }

    private void setupViews(CategoryModel model) {
        // TODO set image | Should it be URL or Bitmap?
        binding.title.setText(model.getName());
        binding.animalBg.setClipToOutline(true);
    }

    private void setupListener(CategoryModel model) {
        binding.getRoot().setOnClickListener(view -> {
            // TODO open next activity
        });
    }
}