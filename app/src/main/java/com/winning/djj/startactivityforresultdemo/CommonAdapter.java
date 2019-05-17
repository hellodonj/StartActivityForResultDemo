package com.winning.djj.startactivityforresultdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 描述: 排班adapter
 * 作者|时间: djj on 2018/11/5 10:48
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> list;
    private Context mContext;
    private int resId;


    public CommonAdapter(List<T> list, Context mContext, int resId) {
        this.list = list;
        this.mContext = mContext;
        this.resId = resId;
    }

    /**
     * 替换元素并刷新
     *
     * @param mData
     */
    public void refresh(List<T> mData) {
        list = mData;
        this.notifyDataSetChanged();
    }

    /**
     * 删除元素并刷新
     *
     * @param position
     */
    public void deleteList(int position) {
        list.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getHolder(mContext, convertView, resId);
        fillData(position, viewHolder);
        return viewHolder.getContentView();
    }

    public abstract void fillData(int position, CommonViewHolder holder);
}
