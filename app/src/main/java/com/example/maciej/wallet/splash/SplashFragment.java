package com.example.maciej.wallet.splash;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maciej.wallet.R;
import com.example.maciej.wallet.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by maciej on 07/10/16.
 */
public class SplashFragment extends BaseFragment<SplashFragmentPresenterInterface> implements SplashFragmentView {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private boolean permissionGranted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        presenter = new SplashFragmentPresenter(this);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (permissionGranted) {
            permissionGranted = false;
            //TODO: display menu
        }
    }

    @OnClick(R.id.permission_button)
    public void askForPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.onPermissionGranted();
                }
                break;
        }
    }

    @Override
    public void displayMenu() {
        if (isResumed()) {
            //TODO: display menu
        } else {
            permissionGranted = true;
        }
    }
}