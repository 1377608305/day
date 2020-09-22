package com.example.myapplication.ui.fragment.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.HomeBean;
import com.example.myapplication.interfaces.IHome;
import com.example.myapplication.presenter.home.HomePresenter;
import com.example.myapplication.ui.fragment.home.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<IHome.IPresenter> implements IHome.View {


    @BindView(R.id.home_rlv)
    RecyclerView homeRlv;

    private List<HomeBean.HomeListBean> list;
    private HomeListAdapter homeListAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list=new ArrayList<>();
        homeListAdapter = new HomeListAdapter(list, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        homeListAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).currentType;
                switch (type){
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                    case HomeBean.ITEM_TYPE_TAB:
                    case HomeBean.ITEM_TYPE_HOT:
                        return 2;
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        return 1;

                }
                return 0;
            }
        });
        homeRlv.setLayoutManager(gridLayoutManager);
        homeListAdapter.bindToRecyclerView(homeRlv);

        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).currentType;
                Intent intent = new Intent();
                switch (type){
                    case HomeBean.ITEM_TYPE_BANNER:
                        break;
                    case HomeBean.ITEM_TYPE_BRAND:
                        break;
                    case HomeBean.ITEM_TYPE_HOT:
                        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) list.get(position).data;
                        intent.putExtra("id",bean.getId());
                        intent.setClass(context, DetailGoodActivity.class);
                        startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_TITLE:
                        break;
                    case HomeBean.ITEM_TYPE_TITLETOP:
                        break;
                    case HomeBean.ITEM_TYPE_TOPIC:
                        break;
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        break;
                }
            }
        });
    }

    @Override
    protected IHome.IPresenter initPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        persenter.getData();
    }


    @Override
    public void getData(List<HomeBean.HomeListBean> homeBean) {
        if (homeBean!=null){
            list.addAll(homeBean);
            homeListAdapter.notifyDataSetChanged();
        }
    }
}
