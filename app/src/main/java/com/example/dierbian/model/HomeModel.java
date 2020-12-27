package com.example.dierbian.model;

import com.example.dierbian.bean.ItemBean;
import com.example.dierbian.contract.HomeContract;
import com.example.mvplibrary.utils.INetCallBack;
import com.example.mvplibrary.utils.RetrofitUtils;

public class HomeModel implements HomeContract.IHomeModel {
    private HomeContract.IHomePresenter presenter;

    public HomeModel(HomeContract.IHomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getHomeData(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url, new INetCallBack<ItemBean>() {
            @Override
            public void onSuccess(ItemBean itemBean) {
                callBack.onSuccess((T) itemBean);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }
}
