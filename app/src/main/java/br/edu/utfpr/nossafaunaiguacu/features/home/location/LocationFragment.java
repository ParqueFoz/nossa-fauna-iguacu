package br.edu.utfpr.nossafaunaiguacu.features.home.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.edu.utfpr.nossafaunaiguacu.databinding.FragmentLocationBinding;

public class LocationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return FragmentLocationBinding.inflate(inflater).getRoot();
    }
}
