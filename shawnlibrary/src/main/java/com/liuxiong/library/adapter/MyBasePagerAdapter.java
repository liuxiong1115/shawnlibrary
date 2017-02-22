package com.liuxiong.library.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 作者：刘雄  2015/10/21 0021.12 38
 * 邮箱：liuxiong1115@gmail.com
 * Viewpager 适配器
 */
public abstract class MyBasePagerAdapter<E> extends PagerAdapter {
    public LayoutInflater mInflater;
    public Context mContext;
    public OnPagerItemClickLitener listener;
    public ViewPager viewPager;
    public ArrayList<E> datas;//数据源
    //View列表
    public ArrayList<View> viewList = new ArrayList<>();


    public MyBasePagerAdapter(Context mContext , ViewPager viewPager) {
        this.mContext = mContext;
        this.viewPager = viewPager;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(ArrayList<E> datas) {
        this.datas = datas;
        viewList = getViews(datas);
    }

    /**
     * 添加一条数据
     * @param arg0
     */
    public void addData(E arg0) {
        datas.add(arg0);
    }

    /**
     * 移除指定的一条数据
     * @param arg0
     */
    public void removeData(int arg0) {
        if(datas != null && arg0 < datas.size())
          datas.remove(arg0);
    }

    /**
     * 返回指定位置的数据源
     * @param index
     * @return
     */
    public E getData(int index){
        if(datas != null && index < datas.size())
            return datas.get(index);
        return null;
    }

    /**
     * 获取指定位置的View视图
     * @param arg0
     * @return
     */
    public View getViewData(int arg0) {
        if (arg0 >= viewList.size()) {
            return null;
        }
        return viewList.get(arg0);
    }

    /**
     * 返回当前选中的Item
     * @return
     */
    public int getCurrentIten(){
       return viewPager.getCurrentItem();
    }

    /**
     * 返回数据总条数
     * @return
     */
    public int getDataCount(){
        if(datas != null)
            return datas.size();
        return -1;
    }

    /**
     * 添加pagerItem点击事件
     * @param listener
     */
    public void setOnPagerItemClickListener(OnPagerItemClickLitener listener){
        this.listener = listener;
    }

    public interface OnPagerItemClickLitener{
        public void onClick(View view, int position);
    }


    public abstract ArrayList<View> getViews(ArrayList<E> datas);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);// 删除页卡
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) { // 这个方法用来实例化页卡
        View view = viewList.get(position);
        try {
            container.addView(view , 0);// 添加页卡
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onClick(v , position);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return datas.size();// 返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;// 官方提示这样写
    }
}
