package br.edu.utfpr.nossafaunaiguacu.features;

import android.app.Activity;
import android.os.Bundle;

import com.google.zxing.Result;

import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;
import br.edu.utfpr.nossafaunaiguacu.features.home.animal.AnimalActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        runOnUiThread(() -> {
            int result;
            try {
                result = Integer.parseInt(rawResult.getText());
            } catch (Throwable e) {
                result = 0;
            }
            LocalRepository.discoveredAnimal(result);
            startActivity(AnimalActivity.newInstance(QrCodeActivity.this, result));
        });
    }
}
