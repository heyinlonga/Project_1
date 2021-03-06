package com.example.mvplibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P presenter;
    protected Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(container.getContext()).inflate(getLayoutID(),null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        if(presenter == null){
            presenter = getPresenter();
            presenter.attachView(this);
        }
        initView(view);
        initData();
    }

    protected abstract void initView(View view);

    protected abstract void initData();



    protected abstract int getLayoutID();

    public abstract P getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //MVP解绑
        if (presenter != null){
            presenter.detachView();
        }
    }

}
