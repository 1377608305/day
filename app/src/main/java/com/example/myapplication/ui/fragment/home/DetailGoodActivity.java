package com.example.myapplication.ui.fragment.home;

import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.AddCartInfoBean;
import com.example.myapplication.bean.GoodDetailBean;
import com.example.myapplication.bean.HomeBean;
import com.example.myapplication.commit.CartView;
import com.example.myapplication.interfaces.ICart;
import com.example.myapplication.presenter.cart.CartPersenter;
import com.example.myapplication.util.GlideImageLoader;
import com.example.myapplication.util.SpUtils;
import com.example.myapplication.util.SystemUtils;
import com.example.myapplication.util.UtilGlideImg;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailGoodActivity extends BaseActivity<ICart.IPersenter> implements ICart.IView, View.OnClickListener {
    /* @BindView(R.id.layout_back)
     RelativeLayout layoutBack;*/
  /*  @BindView(R.id.txt_title)
    TextView txtTitle;*/
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_product)
    TextView txtProduct;
    @BindView(R.id.layout_norms)
    FrameLayout layoutNorms;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;
    @BindView(R.id.layout_imgs)
    LinearLayout layoutImgs;
    @BindView(R.id.layout_parameter)
    LinearLayout layoutParameter;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.layout_cart)
    RelativeLayout layoutCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.good_lin)
    LinearLayout lin;
    @BindView(R.id.good_ly)
    LinearLayout ly;
    @BindView(R.id.nest)
    NestedScrollView nest;
    private PopupWindow mPopWindow;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.txt_addCart)
    TextView txtAddCart;
    @BindView(R.id.txt_count)
    TextView txtCount;
    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";

    private GoodDetailBean goodDetailBean;
    private int currentNum = 1;
    private int id;
    private ImageView head_img;

    @Override
    protected ICart.IPersenter initPresenter() {
        return new CartPersenter();
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        basePresenter.getGoodDetail(id);
    }


    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        txtName.setText(result.getData().getInfo().getName());
        txtDes.setText(result.getData().getInfo().getGoods_brief());
        txtPrice.setText(result.getData().getInfo().getRetail_price() + "");
        txtProduct.setText(result.getData().getBrand().getName());

        UtilGlideImg.setGlideImage(this,result.getData().getBrand().getApp_list_pic_url(), head_img);
        updateBanner(result.getData().getGallery());
        if (result.getData().getComment().getCount() > 0) {
            layoutComment.setVisibility(View.VISIBLE);
            updateComment(result.getData().getComment());
        } else {
            layoutComment.setVisibility(View.GONE);
        }
        updateParameter(result.getData().getAttribute());

        updateDetailInfo(result.getData().getInfo());
    }




    private void updateBanner(List<GoodDetailBean.DataBeanX.GalleryBean> gallery) {
        if (banner.getTag() == null || (int) banner.getTag() == 0) {
            List<String> imgs = new ArrayList<>();
            for (GoodDetailBean.DataBeanX.GalleryBean item : gallery) {
                imgs.add(item.getImg_url());
            }
            banner.setTag(1);
            banner.setImageLoader(new GlideImageLoader()).setImages(imgs).start();
        }
    }


    private void updateComment(GoodDetailBean.DataBeanX.CommentBean commentBean) {

    }


    private void updateParameter(List<GoodDetailBean.DataBeanX.AttributeBean> attributeBean) {
        layoutParameter.removeAllViews(); //清空
        for (GoodDetailBean.DataBeanX.AttributeBean item : attributeBean) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_parameter, null);
            layoutParameter.addView(view);
        }
    }

    private void updateDetailInfo(GoodDetailBean.DataBeanX.InfoBean infoBean) {
        if (!TextUtils.isEmpty(infoBean.getGoods_desc())) {
            String h5 = infoBean.getGoods_desc();
            html = html.replace("$", h5);

            webView.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8", null);

        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void initView() {
        layoutCollect.setOnClickListener(this);
        txtAddCart.setOnClickListener(this);
        layoutCart.setOnClickListener(this);
        layoutNorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    private void pop() {
        if (mPopWindow != null && mPopWindow.isShowing()) {

        }else {
            View popView = LayoutInflater.from(this).inflate(R.layout.popup_test, null);
            int height = SystemUtils.dp2px(this, 250);
            mPopWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,height);
            mPopWindow.setOutsideTouchable(true);
            mPopWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
            mPopWindow.setContentView(popView);
            popView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            CartView cartCustomView = popView.findViewById(R.id.pop_cart);
            ImageView exit = popView.findViewById(R.id.pop_exit);

            head_img = popView.findViewById(R.id.pop_head);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopWindow.dismiss();
                    mPopWindow=null;
                }
            });
            bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }
            });
            bg.setVisibility(View.VISIBLE);
            mPopWindow.showAsDropDown(lin, 0, 0);
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    bg.setVisibility(View.GONE);
                }
            });
            cartCustomView.initView();
            cartCustomView.setOnClickListener(new CartView.IClick() {
                @Override
                public void clickCB(int value) {
                    currentNum=value;
                }
            });

        }


    }
    @Override
    public void addCartInfoReturn(AddCartInfoBean result) {
        int count = result.getData().getCartTotal().getGoodsCount();
        txtCount.setText(String.valueOf(count));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_addCart:
                addCartList();
                break;
            case R.id.layout_cart:
                finish();
                break;
        }
    }

    private void addCartList() {
        boolean login = SpUtils.getInstance().getBoolean("token");
        if(login){
            if(mPopWindow != null && mPopWindow.isShowing()){
                if(goodDetailBean.getData().getProductList().size() > 0){
                    int goodsId = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                    int productId = goodDetailBean.getData().getProductList().get(0).getId();
                    basePresenter.addCart(goodsId,currentNum,productId);
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }else{
                    Toast.makeText(this,"没有产品数据",Toast.LENGTH_SHORT).show();
                }
            }else{
                pop();
            }
        }else{
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
        }
    }




}