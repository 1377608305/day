package com.example.myapplication.ui.fragment.shopp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseListAdapter;
import com.example.myapplication.bean.CartBean;
import com.example.myapplication.commit.CartView;
import com.example.myapplication.util.UtilGlideImg;

import java.util.List;

public class ShoppingRlvAdapter extends BaseListAdapter {
    public boolean isEditor;
    public ShoppingRlvAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_cartlist_item;
    }

    @Override
    protected void initBindData(BaseViewHolder bh, Object o, int position) {
        CartBean.DataBean.CartListBean bean = (CartBean.DataBean.CartListBean) o;

        ImageView imghead=(ImageView)bh.getViewById(R.id.img_icon) ;
        CheckBox checkBox = (CheckBox) bh.getViewById(R.id.checkbox_select);
        TextView txtName = (TextView) bh.getViewById(R.id.txt_name);
        TextView txtNumber = (TextView) bh.getViewById(R.id.txt_number);
        TextView txtPrice = (TextView) bh.getViewById(R.id.txct_price);
        TextView txtSelect = (TextView) bh.getViewById(R.id.txt_select);
        CartView cartView = (CartView) bh.getViewById(R.id.view_number);

        if (isEditor){
            txtName.setVisibility(View.GONE);
            txtNumber.setVisibility(View.GONE);
            txtSelect.setVisibility(View.VISIBLE);
            cartView.setVisibility(View.VISIBLE);
        }else {
            txtName.setVisibility(View.VISIBLE);
            txtNumber.setVisibility(View.VISIBLE);
            txtSelect.setVisibility(View.GONE);
            cartView.setVisibility(View.GONE);
        }

        UtilGlideImg.setGlideImage(context,bean.getList_pic_url(),imghead);
        txtName.setText(bean.getGoods_name());
        txtNumber.setText("X"+bean.getNumber());
        txtPrice.setText("￥"+bean.getMarket_price());
        cartView.initView();
        cartView.setValue(bean.getNumber());
        cartView.setOnClickListener(new CartView.IClick() {
            @Override
            public void clickCB(int value) {
                bean.setNumber(value);
            }
        });

        checkBox.setSelected(bean.select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bean.select = !bean.select;
                if(click != null){
                    click.checkChange();
                }
            }
        });
    }

    public void setOnItemCheckBoxClickListener(CheckBoxClick click){
        this.click = click;
    }

    CheckBoxClick click;


    public interface CheckBoxClick{
        void checkChange();
    }
}
