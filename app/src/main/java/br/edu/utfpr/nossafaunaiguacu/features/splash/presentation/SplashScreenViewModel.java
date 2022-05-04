package br.edu.utfpr.nossafaunaiguacu.features.splash.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashScreenViewModel extends ViewModel {

    private final MutableLiveData<Boolean> _shouldFinishLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> shouldFinishLiveData = _shouldFinishLiveData;

    private final MutableLiveData<Boolean> _showFetchErrorLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> showFetchErrorLiveData = _showFetchErrorLiveData;

    public void fetchApplicationData() {
        keepViewAlive();
    }

    private void keepViewAlive() {
        _shouldFinishLiveData.setValue(false);
    }

    private void finishView() {
        _shouldFinishLiveData.setValue(true);
    }

    private void showFetchError() {
        _showFetchErrorLiveData.setValue(true);
    }

    private void hideFetchError() {
        _showFetchErrorLiveData.setValue(false);
    }

    public boolean isKeepSplashScreenVisible() {
        Boolean isFetchErrorVisible = _showFetchErrorLiveData.getValue();
        return isFetchErrorVisible == null || !isFetchErrorVisible;
    }
}
