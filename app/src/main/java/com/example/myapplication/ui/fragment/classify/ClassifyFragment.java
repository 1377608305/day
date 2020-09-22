package com.example.myapplication.ui.fragment.classify;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.interfaces.IPresenterImpl;
import com.example.myapplication.ui.classify.adapter.MyPagerAdapter;
import com.example.myapplication.ui.classify.fragment.UserclassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class ClassifyFragment extends BaseFragment implements VerticalTabLayout.OnTabSelectedListener{
    @BindView(R.id.class_tab)
    VerticalTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    List<String> mTitlesList;
    List<Fragment> mFragmentsList;
    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        mTitlesList = new ArrayList<>();//初始化Tab栏数据
        mTitlesList.add("服装");
        mTitlesList.add("餐厨");
        mTitlesList.add("配件");
        mTitlesList.add("居家");
        mTitlesList.add("洗护");
        mTitlesList.add("婴童");
        mTitlesList.add("杂货");
        mTitlesList.add("饮食");

        mFragmentsList = new ArrayList<>();//添加碎片
        for (int i = 0; i <8 ; i++) {
            mFragmentsList.add(new UserclassifyFragment());
        }

        //创建适配器、适配器继承自FragmentPagerAdapter
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager(), mFragmentsList,mTitlesList);
        vp.setAdapter(myPagerAdapter);//绑定适配器
        //Tab和ViewPager相互联动
        tab.setupWithViewPager(vp);

        tab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return mTitlesList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public QTabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public QTabView.TabTitle getTitle(int position) {
                QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                        .setContent(mTitlesList.get(position))//设置数据   也有设置字体颜色的方法
                        .build();
                return title;
            }

            @Override
            public int getBackground(int position) {
                return R.color.bai;//选中的背景颜色
            }
        });


    }
    @Override
    public void onTabSelected(TabView tab, int position) {
        vp.setCurrentItem(position);
    }

    @Override
    protected IPresenterImpl initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }
}
