package br.edu.utfpr.nossafaunaiguacu.features.onboard;

import java.io.Serializable;

public interface OnBoardActionListener extends Serializable {
    void onNext(boolean isLastPage);
    void onSkip();
}
