package com.example.dierbian.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dierbian.contract.HomeContract;
import com.example.dierbian.presenter.HomePresenter;
import com.example.mvplibrary.base.BaseFragment;
import com.example.mvplibrary.base.BasePresenter;

public class HomeFragment extends BaseFragment<HomePresenter>implements HomeContract.IHomeView {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    public HomePresenter getPresenter() {
        return null;
    }

    @Override
    public void tips(String string) {

    }

    @Override
    public <T> void HomeRelt(T t) {

    }
}
