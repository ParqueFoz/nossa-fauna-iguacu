package br.edu.utfpr.nossafaunaiguacu.features;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.google.zxing.Result;

import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;
import br.edu.utfpr.nossafaunaiguacu.databinding.ActivityQrCodeBinding;
import br.edu.utfpr.nossafaunaiguacu.features.home.animal.AnimalActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeActivity extends Activity implements ZXingScannerView.ResultHandler {
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    private ZXingScannerView mScannerView;
    private ActivityQrCodeBinding binding;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        binding = ActivityQrCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        binding.contentFrame.addView(mScannerView);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
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
            finish();
        });
    }
}
