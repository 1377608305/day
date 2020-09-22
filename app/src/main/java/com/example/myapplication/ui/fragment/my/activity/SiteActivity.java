package com.example.myapplication.ui.fragment.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.interfaces.IPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiteActivity extends BaseActivity {

    @BindView(R.id.site_rlv)
    RecyclerView siteRlv;
    @BindView(R.id.establish)
    TextView establish;

    @Override
    protected IPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        establish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SiteActivity.this,UserSiteActivity.class));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_site;
    }

}