package com.example.dierbian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.dierbian.fragment.ClassificationFragment;
import com.example.dierbian.fragment.HomeFragment;
import com.example.dierbian.fragment.MineFragement;
import com.example.dierbian.fragment.ShoppingFragment;
import com.example.dierbian.fragment.SpecialFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLl;
    private BottomNavigationView mBnv;
    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private ClassificationFragment classificationFragment;
    private ShoppingFragment shoppingFragment;
    private MineFragement mineFragement;
    private Fragment[] fragments;
    private int lastFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLl = findViewById(R.id.ll);
        mBnv = findViewById(R.id.bnv);
        initView();
    }

    public void initView() {
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        classificationFragment = new ClassificationFragment();
        shoppingFragment = new ShoppingFragment();
        mineFragement = new MineFragement();
        fragments = new Fragment[]{homeFragment,specialFragment,classificationFragment,shoppingFragment,mineFragement};
        lastFrament = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.ll,homeFragment).show(homeFragment).commit();

        mBnv.setOnNavigationItemSelectedListener(changeFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.menu1:
                {
                    if (lastFrament!=0){
                        switchFragment(lastFrament,0);
                        lastFrament = 0;
                    }
                    return true;
                }
                case R.id.menu2:
                {
                    if (lastFrament!=1){
                        switchFragment(lastFrament,1);
                        lastFrament = 1;
                    }
                    return true;
                }
                case R.id.menu3:
                {
                    if (lastFrament!=2){
                        switchFragment(lastFrament,2);
                        lastFrament=2;
                    }
                    return true;
                }
                case R.id.menu4:
                {
                    if (lastFrament!=3){
                        switchFragment(lastFrament,3);
                        lastFrament=3;
                    }
                    return true;
                }
                case R.id.menu5:
                {
                    if (lastFrament!=4){
                        switchFragment(lastFrament,4);
                        lastFrament=4;
                    }
                }
            }
            return false;
        }
        //切换Fragment
    };
    private void switchFragment(int lastFrament, int i) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.hide(fragments[lastFrament]);//隐藏上一个Fragment
        if (fragments[i].isAdded()==false){
            t.add(R.id.ll,fragments[i]);
        }
        t.show(fragments[i]).commitAllowingStateLoss();
    }
}