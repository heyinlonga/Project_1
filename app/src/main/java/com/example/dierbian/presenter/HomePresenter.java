package com.example.dierbian.presenter;

import com.example.dierbian.bean.ItemBean;
import com.example.dierbian.contract.HomeContract;
import com.example.dierbian.model.HomeModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.utils.INetCallBack;

public class HomePresenter extends BasePresenter<HomeContract.IHomeView,HomeModel> implements HomeContract.IHomePresenter {

    @Override
    public void Home(String url) {
        iModel = new HomeModel(this);
        iModel.getHomeData(url, new INetCallBack<ItemBean>() {

            @Override
            public void onSuccess(ItemBean bannerBeans) {
                iView.HomeRelt(bannerBeans);
            }

            @Override
            public void onFail(String err) {
                iView.tips(err);
            }
        });
    }

    @Override
    public HomeModel getiModel() {
        return new HomeModel(this);
    }
}

