package com.example.myapplication.ui.fragment.shopp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.CartBean;
import com.example.myapplication.bean.DeleteCartBean;
import com.example.myapplication.interfaces.ICart;
import com.example.myapplication.presenter.cart.CartListPersenter;
import com.example.myapplication.ui.fragment.shopp.adapter.ShoppingRlvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShoppingFragment extends BaseFragment<ICart.ICartPersenter> implements ICart.ICartView, View.OnClickListener {
    @BindView(R.id.layout_top)
    FrameLayout layoutTop;
    @BindView(R.id.shopp_rlv)
    RecyclerView shoppRlv;
    @BindView(R.id.radio_select)
    CheckBox radioSelect;
    @BindView(R.id.txt_allPrice)
    TextView txtAllPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;
    @BindView(R.id.txt_selectAll)
    TextView txtSelectAll;

    private List<CartBean.DataBean.CartListBean> list;
    ShoppingRlvAdapter shoppingRlvAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shopp;
    }

    private int allNumber;
    private int allPrice;

    @Override
    protected void initView() {
        txtEdit.setOnClickListener(this);
        list = new ArrayList<>();
        shoppingRlvAdapter = new ShoppingRlvAdapter(context, list);
        shoppRlv.setLayoutManager(new LinearLayoutManager(context));
        shoppRlv.setAdapter(shoppingRlvAdapter);

        txtSelectAll.setText("全选(0)");
        radioSelect.setOnClickListener(this::onClick);
        txtEdit.setOnClickListener(this::onClick);
        txtSubmit.setOnClickListener(this::onClick);
        
        shoppingRlvAdapter.setOnItemCheckBoxClickListener(new ShoppingRlvAdapter.CheckBoxClick() {
            @Override
            public void checkChange() {
                boolean bl = CheckSelectAll();
                txtSelectAll.setText("全选（" + allNumber + ")");
                txtAllPrice.setText("￥" + allPrice);
                radioSelect.setChecked(bl);
                shoppingRlvAdapter.notifyDataSetChanged();
            }
        });





    }

    private void selectAll() {

        resetSelect(!radioSelect.isChecked());
        radioSelect.setSelected(!radioSelect.isChecked());
        txtSelectAll.setText("全选(" + allNumber + ")");
        txtAllPrice.setText("￥" + allPrice);
        shoppingRlvAdapter.notifyDataSetChanged();


    }

    private void resetSelect(boolean b) {
        allNumber=0;
        allPrice=0;
        for (CartBean.DataBean.CartListBean item:list){
            item.select=b;
            if (b){
                allNumber+=item.getNumber();
                allPrice+=item.getNumber()*item.getRetail_price();
            }
        }
        if (!b){
            allPrice=0;
            allNumber=0;
        }
    }

    private boolean CheckSelectAll() {
        allNumber = 0;
        allPrice = 0;
        boolean isSelectAll = true;
        for (CartBean.DataBean.CartListBean item : list) {
            if (item.select) {
                allNumber += item.getNumber();
                allPrice += item.getNumber() * item.getRetail_price();
            }
            if (isSelectAll && !item.select) {
                isSelectAll = false;
            }
        }
        return isSelectAll;
    }


    @Override
    protected ICart.ICartPersenter initPersenter() {
        return new CartListPersenter();
    }

    @Override
    protected void initData() {
        persenter.getCartList();
    }

    @Override
    public void getCartListReturn(CartBean result) {
        list.addAll(result.getData().getCartList());
        shoppingRlvAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCartListReturn(DeleteCartBean result) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radio_select:
                selectAll();
                break;
            case R.id.txt_edit:
                clickEdit();
                break;
            case R.id.txt_submit:
                deleteList();
                break;
        }
    }

    private void deleteList() {
        if ("下单".equals(txtSubmit.getText())){

        }else if ("删除所选".equals(txtSubmit.getText())){
            StringBuilder sb = new StringBuilder();
            List<Integer> is=getSelectProducts();
            for (Integer i : is) {
                sb.append(i);
                sb.append(",");
            }
            if (sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
                String s = sb.toString();
                persenter.deleteCartList(s);
            }else {
                Toast.makeText(context, "没有选中要删除的商品", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<Integer> getSelectProducts() {
        List<Integer> it = new ArrayList<>();
        for (CartBean.DataBean.CartListBean item : list) {
            if (item.select){
                it.add(item.getProduct_id());
            }
        }

        return it;
    }


    private void clickEdit() {
        if (txtEdit.getText().equals("编辑")) {
            shoppingRlvAdapter.isEditor = true;
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            txtAllPrice.setVisibility(View.GONE);
        } else if (txtEdit.getText().equals("完成")) {
            shoppingRlvAdapter.isEditor = false;
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            txtAllPrice.setVisibility(View.VISIBLE);
            txtAllPrice.setText("￥0");
        }
        resetSelect(false);
        shoppingRlvAdapter.notifyDataSetChanged();
    }
}
