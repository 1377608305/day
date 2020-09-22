package com.example.myapplication.ui.fragment.special;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.IPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class SpecialFragment extends BaseFragment {

    private List<String> list;
    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
      /*  specialRlvAdapter=new SpecialRlvAdapter(context,list);
        specialRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        specialRlv.setAdapter(specialRlvAdapter);*/
    }

    @Override
    protected IPresenterImpl initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
