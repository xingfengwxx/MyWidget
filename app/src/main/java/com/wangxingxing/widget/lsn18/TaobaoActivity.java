package com.wangxingxing.widget.lsn18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wangxingxing.widget.R;
import com.wangxingxing.widget.lsn14.utils.StatusBarUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TaobaoActivity extends AppCompatActivity {

    //不同item必须不同的viewtype
    int BANNER_VIEW_TYPE = 1;
    int MENU_VIEW_TYPE = 2;
    int NEWS_VIEW_TYPE = 3;
    int TITLE_VIEW_TYPE = 4;
    int GRID_VIEW_TYPE = 5;
    //广告位
    int[] ITEM_URL = {R.mipmap.taobao_item1, R.mipmap.taobao_item2, R.mipmap.taobao_item3, R.mipmap.taobao_item4, R.mipmap.taobao_item5};
    //应用位
    String[] ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    int[] IMG_URLS = {R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai,
            R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction,
            R.mipmap.ic_classify};
    //高颜值商品位
    int[] GRID_URL = {R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4};
    private RecyclerView mRecyclerView;
    //存放各个模块的适配器
    private List<DelegateAdapter.Adapter> mAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);

        initView();
    }

    private void initView() {
        StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.orange), 0);

        //初始化适配器集合
        mAdapters = new LinkedList<>();
        mRecyclerView = findViewById(R.id.recyclerView);

        //初始化VirtualLayoutManager
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        //设置mRecyclerView的setLayoutManager
        mRecyclerView.setLayoutManager(layoutManager);
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        //创建一个VLayout中的DelegateAdapter适配器 因为这个适配器可以添加适配器集合
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        mRecyclerView.setAdapter(delegateAdapter);


        //设置banner的适配器
        BaseDelegateAdapter baseDelegateAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(),
                R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE) {
            //重写onBindViewHolder

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
                super.onBindViewHolder(baseViewHolder, i);
                List<String> arrayList = new ArrayList<>();
                arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574862113663&di=90075f42a06b38800e0c3ca7f3625921&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F07%2F29%2F69%2F5666757345e09.jpg");
                arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574862113663&di=2c5c317db08296e8c5b3dbae30092614&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010d4f582ec1bca84a0d304f14e1b9.jpg");
                arrayList.add("https://image.tubangzhu.com/updata/201808/08/3668de16a2ac736abc21.png");
                arrayList.add("https://image.tubangzhu.com/updata/201808/08/f1296aead26610bf1be5.png");
                arrayList.add("http://218.16.125.44:81/img/20180209/131626435404507144.jpg");
                arrayList.add("https://photo.16pic.com/00/89/13/16pic_8913590_b.jpg");
                arrayList.add("https://image.tubangzhu.com/updata/201810/13/ba126e5741ca06803a8c.png");

                // 绑定数据
                Banner mBanner = baseViewHolder.getView(R.id.banner);
                //设置Banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                mBanner.setImages(arrayList);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                //mBanner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true);
                //设置轮播时间
                mBanner.setDelayTime(5000);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        ToastUtils.showShort("banner点击了" + position);
                    }
                });
            }
        };
        mAdapters.add(baseDelegateAdapter);


        //menu
        // 在构造函数设置每行的网格个数
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);

        BaseDelegateAdapter menuAdapter = new BaseDelegateAdapter(this, gridLayoutHelper, R.layout.vlayout_menu,
                10, MENU_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, final int position) {
                super.onBindViewHolder(baseViewHolder, position);
                baseViewHolder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position] + "");
                baseViewHolder.setImageResource(R.id.iv_menu_home, IMG_URLS[position]);
                baseViewHolder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(ITEM_NAMES[position]);
                    }
                });
            }
        };
        mAdapters.add(menuAdapter);


        //news----->
        BaseDelegateAdapter newsAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(),
                R.layout.vlayout_news, 1, NEWS_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                MarqueeView marqueeView1 = holder.getView(R.id.marqueeView1);
                MarqueeView marqueeView2 = holder.getView(R.id.marqueeView2);

                List<String> info1 = new ArrayList<>();
                info1.add("天猫超市最近发大活动啦，快来抢");
                info1.add("没有最便宜，只有更便宜！");

                List<String> info2 = new ArrayList<>();
                info2.add("这个是用来搞笑的，不要在意这写小细节！");
                info2.add("啦啦啦啦，我就是来搞笑的！");

                marqueeView1.startWithList(info1);
                marqueeView2.startWithList(info2);
                // 在代码里设置自己的动画
                marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out);
                marqueeView2.startWithList(info2, R.anim.anim_bottom_in, R.anim.anim_top_out);

                marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapters.add(newsAdapter);


        //这里我就循环item 实际项目中不同的ITEM 继续往下走就行
        for (int i = 0; i < ITEM_URL.length; i++) {
            //item1 title
            final int finalI = i;
            BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(this, new LinearLayoutHelper(), R.layout.vlayout_title, 1, TITLE_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setImageResource(R.id.iv, ITEM_URL[finalI]);
                }
            };
            mAdapters.add(titleAdapter);
            //item1 gird
            GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(2);
            gridLayoutHelper.setMargin(0, 0, 0, 0);
            gridLayoutHelper.setPadding(0, 0, 0, 0);
            gridLayoutHelper.setVGap(0);// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(0);// 控制子元素之间的水平间距
            gridLayoutHelper.setBgColor(Color.WHITE);
            gridLayoutHelper.setAutoExpand(true);//是否自动填充空白区域
            BaseDelegateAdapter girdAdapter = new BaseDelegateAdapter(this, gridLayoutHelper1, R.layout.vlayout_grid
                    , 4, GRID_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    int item = GRID_URL[position];
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            mAdapters.add(girdAdapter);
        }

        delegateAdapter.addAdapters(mAdapters);
    }
}
