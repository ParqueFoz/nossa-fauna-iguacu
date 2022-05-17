package br.edu.utfpr.nossafaunaiguacu.features.onboard.presentation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import br.edu.utfpr.nossafaunaiguacu.R;
import br.edu.utfpr.nossafaunaiguacu.databinding.FragmentOnBoardBinding;

public class OnBoardFragment extends Fragment {

    private static final String PAGE_POSITION_KEY = "page_position";
    private static final String ACTION_LISTENER_KEY = "action_listener";
    private FragmentOnBoardBinding binding;
    private OnBoardActionListener actionListener;
    private Integer onBoardPositionKey = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actionListener = (OnBoardActionListener) getArguments().getSerializable(ACTION_LISTENER_KEY);
            onBoardPositionKey = getArguments().getInt(PAGE_POSITION_KEY, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupOnBoardViews();
        setupListeners();
    }

    private void setupOnBoardViews() {
        switch (onBoardPositionKey) {
            case 0: {
                setupFirstOnBoardView();
                break;
            }
            case 1: {
                setupSecondOnBoardView();
                break;
            }
            default:
                setupThirdOnBoardView();
        }
    }

    private void setupFirstOnBoardView() {
        setupBackground(R.drawable.on_board_bg_1);
        binding.subtitleTv.setText(R.string.on_board_subtitle_1);

    }

    private void setupSecondOnBoardView() {
        setupBackground(R.drawable.on_board_bg_2);
        binding.subtitleTv.setText(R.string.on_board_subtitle_2);
    }

    private void setupThirdOnBoardView() {
        setupBackground(R.drawable.on_board_bg_3);
        binding.skipButton.setVisibility(View.GONE);
        binding.nextButton.setText(R.string.on_board_explore);
        binding.subtitleTv.setText(R.string.on_board_subtitle_3);
    }

    private void setupBackground(@DrawableRes int drawableRes) {
        binding.getRoot().setBackground(getDrawable(drawableRes));
    }

    private Drawable getDrawable(@DrawableRes int drawableRes) {
        if (getContext() == null) return null;
        return AppCompatResources.getDrawable(getContext(), drawableRes);
    }

    private void setupListeners() {
        binding.nextButton.setOnClickListener(v -> actionListener.onNext(onBoardPositionKey == 2));
        binding.skipButton.setOnClickListener(v -> actionListener.onSkip());
    }

    public static Fragment newInstance(int onBoardPagePosition, OnBoardActionListener actionListener) {
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_POSITION_KEY, onBoardPagePosition);
        arguments.putSerializable(ACTION_LISTENER_KEY, actionListener);

        Fragment onBoardFragment = new OnBoardFragment();
        onBoardFragment.setArguments(arguments);

        return onBoardFragment;
    }
}
