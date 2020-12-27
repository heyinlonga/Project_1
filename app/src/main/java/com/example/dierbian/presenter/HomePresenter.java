package com.example.dierbian.presenter;

import com.example.dierbian.bean.BannerBeans;
import com.example.dierbian.contract.HomeContract;
import com.example.dierbian.model.HomeModel;
import com.example.mvplibrary.base.BasePresenter;
import com.example.mvplibrary.utils.INetCallBack;

public class HomePresenter extends BasePresenter<HomeContract, HomeContract.IHomeView, HomeModel> implements HomeContract.IHomePresenter {

    private HomeModel homeModel;

    @Override
    public void Home(String url) {
        homeModel = new HomeModel(this);
        iModel.getHomeData(url, new INetCallBack<BannerBeans>() {
            @Override
            public void onSuccess(BannerBeans bannerBeans) {
                iView.HomeRelt(bannerBeans);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    public HomeModel getiModel() {
        return new HomeModel(this);
    }
}
