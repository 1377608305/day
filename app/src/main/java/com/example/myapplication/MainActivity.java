package com.example.myapplication;

import android.Manifest;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.IHome;
import com.example.myapplication.ui.fragment.classify.ClassifyFragment;
import com.example.myapplication.ui.fragment.home.HomeFragment;
import com.example.myapplication.ui.fragment.my.MyFragment;
import com.example.myapplication.ui.fragment.shopp.ShoppingFragment;
import com.example.myapplication.ui.fragment.special.SpecialFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tab)
    TabLayout tab;

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private FragmentManager fm;
    private int mPosition;
    private ImageView shopping;


    @Override
    protected IHome.IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initTitle();
        initListener();
        initPars();

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fl, fragments.get(0)).commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    private void initPars() {
        String[] mPermissionList = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        ActivityCompat.requestPermissions(this, mPermissionList, 100);
    }

    private void initListener() {



    }

    private void switchFragment(int position) {
/*        FragmentTransaction ft = fm.beginTransaction();
        Fragment showFragment = fragments.get(position);
        Fragment hideFragment = fragments.get(mPosition);
        if (!showFragment.isAdded()) {
            ft.add(R.id.fl, showFragment);
        }
        ft.show(showFragment);
        ft.hide(hideFragment);
        ft.commit();
        mPosition = position;*/
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (!fragments.get(0).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragments.get(0));
                }
                fragmentTransaction.show(fragments.get(0)).hide(fragments.get(1)).hide(fragments.get(2)).hide(fragments.get(3)).hide(fragments.get(4));
                break;
            case 1:
                if (!fragments.get(1).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragments.get(1));
                }
                fragmentTransaction.show(fragments.get(1)).hide(fragments.get(0)).hide(fragments.get(2)).hide(fragments.get(3)).hide(fragments.get(4));
                break;
            case 2:
                //  ToastUtils.onShortToast("跳转");
                if (!fragments.get(2).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragments.get(2));
                }
                fragmentTransaction.show(fragments.get(2)).hide(fragments.get(1)).hide(fragments.get(3)).hide(fragments.get(0)).hide(fragments.get(4));
                break;
            case 3:
                if (!fragments.get(3).isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragments.get(3));
                }
                fragmentTransaction.show(fragments.get(3)).hide(fragments.get(1)).hide(fragments.get(2)).hide(fragments.get(0)).hide(fragments.get(4));
                break;
            case 4:
                if (!fragments.get(4).isAdded()){
                    fragmentTransaction.add(R.id.fl,fragments.get(4));
                }
                fragmentTransaction.show(fragments.get(4)).hide(fragments.get(1)).hide(fragments.get(2)).hide(fragments.get(3)).hide(fragments.get(0));
                break;
        }
        fragmentTransaction.commit();
    }

    private void initTitle() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new MyFragment());

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("专题");
        titles.add("分类");
        titles.add("购物车");
        titles.add("我的");
        for (int i = 0; i < titles.size(); i++) {
            tab.addTab(tab.newTab().setText(titles.get(i)));
        }
        tab.getTabAt(0).setIcon(R.drawable.home_selector);
        tab.getTabAt(1).setIcon(R.drawable.special_selector);
        tab.getTabAt(2).setIcon(R.drawable.classify_selector);
        tab.getTabAt(3).setIcon(R.drawable.shopping_selector);
        tab.getTabAt(4).setIcon(R.drawable.my_selector);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
