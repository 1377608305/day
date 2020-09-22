package com.example.myapplication.ui.fragment.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseListAdapter;
import com.example.myapplication.base.BaseRlvAdapter;
import com.example.myapplication.bean.HomeBean;
import com.example.myapplication.util.UtilGlideImg;

import java.util.List;

public class TopicAdapter extends BaseListAdapter<HomeBean.DataBean.TopicListBean> {
    public TopicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_home_topic;
    }

    private static final String TAG = "TopicAdapter";
    @Override
    protected void initBindData(BaseViewHolder bh, HomeBean.DataBean.TopicListBean topicListBean, int position) {
        ImageView img = (ImageView) bh.getViewById(R.id.img_topic);
        UtilGlideImg.setGlideImage(context,topicListBean.getScene_pic_url(), img);
        ((TextView)bh.getViewById(R.id.txt_topic_name)).setText(topicListBean.getTitle());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


}
