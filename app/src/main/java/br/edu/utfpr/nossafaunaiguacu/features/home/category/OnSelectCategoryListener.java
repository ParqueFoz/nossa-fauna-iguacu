package br.edu.utfpr.nossafaunaiguacu.features.home.category;

import java.io.Serializable;

public interface OnSelectCategoryListener extends Serializable {
    void onCategorySelected(Integer id);
}
