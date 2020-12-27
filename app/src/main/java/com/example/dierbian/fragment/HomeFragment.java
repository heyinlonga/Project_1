package com.example.dierbian.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import com.example.dierbian.R;
import com.example.dierbian.adapter.GridtAdapter;
import com.example.dierbian.adapter.LanGeAdapter;
import com.example.dierbian.adapter.LanGetowAdapter;
import com.example.dierbian.adapter.MyAdapter;
import com.example.dierbian.adapter.MyTowAdapter;
import com.example.dierbian.adapter.RecyAdapter;
import com.example.dierbian.bean.ItemBean;
import com.example.dierbian.contract.HomeContract;
import com.example.dierbian.presenter.HomePresenter;
import com.example.mvplibrary.base.BaseFragment;
import com.example.mvplibrary.utils.URLConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView {


    @BindView(R.id.home_Recycler)
    RecyclerView homeRecycler;
    private ArrayList<ItemBean.DataDTO.BannerDTO> banner;
    private ArrayList<ItemBean.DataDTO.ChannelDTO> channel;
    private ArrayList<ItemBean.DataDTO.BrandListDTO> brandList;

    private ArrayList<ItemBean.DataDTO.TopicListDTO> topicList;
    private ArrayList<ItemBean.DataDTO.CategoryListDTO> categoryList;

    private LanGeAdapter lanGeAdapter;
    private LanGetowAdapter lanGetowAdapter;

    private DelegateAdapter adapter;

    private VirtualLayoutManager layoutManager;
    private MyAdapter myAdapter;
    private RecyAdapter recyAdapter;

    @Override
    protected void initView(View view) {
        banner = new ArrayList<>();
        channel = new ArrayList<>();
        brandList = new ArrayList<>();
        topicList = new ArrayList<>();
        categoryList = new ArrayList<>();


        layoutManager = new VirtualLayoutManager(getContext());
        //设置回收复用线程池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        homeRecycler.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 15);
        //通栏
        myAdapter = initTongLan();
        lanGeAdapter = initLanGe();
        lanGetowAdapter = initChannel();

        recyAdapter = initTop();
    }

    @Override
    protected void initData() {
        presenter.Home(URLConstant.NEWLIST);
    }

    @SuppressLint("WrongConstant")


    private void initAddAdapter() {
        homeRecycler.setLayoutManager(layoutManager);
        homeRecycler.setAdapter(adapter);
    }

    private GridtAdapter initGrid(ArrayList<ItemBean.DataDTO.CategoryListDTO.GoodsListDTO> categoryListt, int i) {
        /**
         TODO 设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(brandList.size());// 设置布局里Item个数
        gridLayoutHelper.setPadding(5, 5, 5, 0);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{50, 50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(2);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(2);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(2);// 设置每行多少个网格
        GridtAdapter griddAdapter = new GridtAdapter(gridLayoutHelper, categoryListt,i);
        return griddAdapter;
    }

    private RecyAdapter initTop() {
        /**
         TODO 设置通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        singleLayoutHelper.setPadding(0, 50, 0, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        singleLayoutHelper.setMargin(20, 80, 20, 0);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(1-5);// 设置设置布局内每行布局的宽与高的比
        RecyAdapter recyAdapter = new RecyAdapter(getContext(),singleLayoutHelper, topicList);
        return recyAdapter;
    }



    private MyTowAdapter initGoodslist(String title) {
        /**
         TODO 设置通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
        singleLayoutHelper.setPadding(0, 50, 0, -20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        singleLayoutHelper.setMargin(20, 80, 20, 0);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        MyTowAdapter myTowAdapter = new MyTowAdapter(singleLayoutHelper,title);
        return myTowAdapter;
    }

    private LanGetowAdapter initChannel() {
        /**
         TODO 设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(channel.size());// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 50, 20, 0);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        columnLayoutHelper.setMargin(0, 0, 0, 0);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
//        columnLayoutHelper.setWeights(new float[]{100});// 设置该行每个Item占该行总宽度的比例
        // 同上面Weigths属性讲解
        LanGetowAdapter getowAdapter = new LanGetowAdapter(columnLayoutHelper, channel);
        return getowAdapter;
    }

    private LanGeAdapter initLanGe() {
        /**
          TODO 设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        columnLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(2);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
//        columnLayoutHelper.setWeights(new float[]{100});// 设置该行每个Item占该行总宽度的比例
        // 同上面Weigths属性讲解
        LanGeAdapter geAdapter = new LanGeAdapter(columnLayoutHelper, banner);
        return geAdapter;
    }

    private MyAdapter initTongLan() {
        /**
          TODO 设置通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //singleLayoutHelper.setPadding(0, 0, 0, 0);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        singleLayoutHelper.setMargin(50, 50, 50, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        MyAdapter myAdapter = new MyAdapter(singleLayoutHelper);
        return myAdapter;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public <T> void HomeRelt(T t) {
        ItemBean bean = (ItemBean) t;
        if (bean!=null){
            List<ItemBean.DataDTO.BannerDTO> banner = bean.getData().getBanner();
            List<ItemBean.DataDTO.ChannelDTO> channel = bean.getData().getChannel();

            List<ItemBean.DataDTO.TopicListDTO> topic = bean.getData().getTopicList();
            List<ItemBean.DataDTO.CategoryListDTO> category = bean.getData().getCategoryList();
            this.banner.clear();
            this.banner.addAll(banner);
            lanGeAdapter.notifyDataSetChanged();

            this.channel.clear();
            this.channel.addAll(channel);
            lanGetowAdapter.notifyDataSetChanged();

            topicList.clear();
            topicList.addAll(topic);
            recyAdapter.notifyDataSetChanged();

            adapter = new DelegateAdapter(layoutManager, false);
            adapter.addAdapter(myAdapter);
            adapter.addAdapter(lanGeAdapter);
            adapter.addAdapter(lanGetowAdapter);

            adapter.addAdapter(recyAdapter);
            categoryList.clear();
            categoryList.addAll(category);
        }
        initAddAdapter();
    }

    @Override
    public void tips(String string) {
        Log.e("TAG", "tips: " + string);
    }
}