package com.example.myapplication.ui.fragment.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.IPresenterImpl;
import com.example.myapplication.ui.fragment.my.activity.SiteActivity;

import butterknife.BindView;

public class MyFragment extends BaseFragment {
    @BindView(R.id.imgbg)
    ImageView imgbg;
    @BindView(R.id.my_imghead)
    ImageView myImghead;
    @BindView(R.id.my_site)
    TextView mySite;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        mySite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SiteActivity.class));
            }
        });

    }

    @Override
    protected IPresenterImpl initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
