package com.wangxingxing.widget.lsn20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.wangxingxing.widget.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MapView extends View {

    //上下文
    private Context context;
    //画笔
    private Paint paint;
    //所有的省份的集合
    private List<ProvinceItem> itemList;
    //绘制地图的颜色
    private int[] colorArray = new int[]{0xFF239BD7, 0xFF30A9E5, 0xFF80CBF1, 0xFFFFFFFF};
    //适配比例
    private float scale=1.0f;
    //地图的矩形对象
    private RectF totalRect;
    //被选中的省
    private ProvinceItem select;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 定义init方法  用来初始化我们paint对象
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        //开启解析XML文件的线程
        loadThread.start();
    }

    private Thread loadThread = new Thread(){
        @Override
        public void run() {
            //定义输入流加载中国地图XML文件
            InputStream inputStream = context.getResources().openRawResource(R.raw.china);
            //定义一个集合
            List<ProvinceItem> list = new ArrayList<>();
            try {
                //取得DocumentBuilderFactory实例
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(inputStream);
                //获取到xml文件的根目录
                Element rootElement = doc.getDocumentElement();
                //获取根节点下面的某些节点
                NodeList items = rootElement.getElementsByTagName("path");
                //首先 定义四个点
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;
                //遍历所有的path节点
                for (int i = 0; i < items.getLength(); i++) {
                    //获取到每一个path节点
                    Element element = (Element) items.item(i);
                    //获取到path节点中的android:pathData属性值
                    String pathData = element.getAttribute("android:pathData");
                    //将path字符串转为path对象
                    Path path = PathParser.createPathFromPathData(pathData);
                    ProvinceItem provinceItem = new ProvinceItem(path);
                    list.add(provinceItem);

                    //获取控件的宽高
                    RectF rect = new RectF();
                    //获取到每个省份的边界
                    path.computeBounds(rect, true);
                    //遍历取出每个path中的left取所有的最小值
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    //遍历取出每个path中的right取所有的最大值
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    //遍历取出每个path中的top取所有的最小值
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    //遍历取出每个path中的bottom取所有的最大值
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                }
                //创建整个地图的矩形
                totalRect = new RectF(left, top, right, bottom);
                itemList = list;
                handler.sendEmptyMessage(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 设置画省份的颜色
     */
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (itemList == null) {
                return;
            }
            int totalNumber = itemList.size();
            for (int i = 0; i < totalNumber; i++) {
                int color = Color.WHITE;
                int flag = i % 4;
                switch (flag) {
                    case 1:
                        color = colorArray[0];
                        break;
                    case 2:
                        color = colorArray[1];
                        break;
                    case 3:
                        color = colorArray[2];
                        break;
                    default:
                        color = Color.CYAN;
                        break;
                }
                //将颜色设置给每个省份的封装对象
                itemList.get(i).setDrawColor(color);
            }
            postInvalidate();
        }
    };

    /**
     * 绘制的方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //先判断itemList是否Wie空
        if (itemList != null && itemList.size() > 0) {
            canvas.save();
            canvas.scale(scale, scale);
            for (ProvinceItem provinceItem : itemList) {
                if (select != null) {
                    provinceItem.drawItem(canvas, paint, false);
                }
            }
            if (select != null) {
                select.drawItem(canvas, paint, true);
            }
        }
    }

    /**
     * 重新测量 做适配使用
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取到当前控件宽高值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (totalRect != null) {
            //获取到地图的矩形的宽度
            double mapWidth = totalRect.width();
            //获取比例值
            scale = (float) (width / mapWidth);
        }
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将当前手指触摸到位置传过去  判断当前点击的区域
        handlerTouch(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }

    private void handlerTouch(float x, float y) {
        //判空
        if (itemList == null || itemList.size() == 0) {
            return;
        }
        //定义一个空的被选中的省份
        ProvinceItem selectItem = null;
        for (ProvinceItem provinceItem : itemList) {
            //入股点击的是这个省份的范围之内 就把当前省份的封装对象绘制的方法 传一个true
            if (provinceItem.isTouch(x / scale, y / scale)) {
                selectItem = provinceItem;
            }
        }
        if (selectItem != null) {
            select = selectItem;
            postInvalidate();
        }
    }
}
