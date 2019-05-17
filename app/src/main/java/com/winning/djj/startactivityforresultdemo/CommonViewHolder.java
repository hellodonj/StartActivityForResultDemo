package com.winning.djj.startactivityforresultdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 描述: 自定义ViewHolder
 * 作者|时间: djj on 2018/11/5 13:41
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class CommonViewHolder {

    private final View mContentView;

    //Android开发中官方推荐：当使用HashMap(K, V),如果K为整数类型时,使用SparseArray的效率更高
    private SparseArray<View> mSparseArray = new SparseArray<>();

    private Context mContext;

    public CommonViewHolder(Context mContext, int resId) {
        this.mContext = mContext;
        mContentView = LayoutInflater.from(mContext).inflate(resId, null);
        mContentView.setTag(this);
    }

    public View getContentView() {
        return mContentView;
    }

    public static CommonViewHolder getHolder(Context context, View convertView, int resID) {
        //        CommomViewHolder holder = null;
        //        if (holder == null) {
        //            holder = new CommomViewHolder(context, resID);
        //        } else {
        //            holder = (CommomViewHolder) convertView.getTag();
        //        }
        //        return holder;

        if (convertView == null) {
            synchronized (CommonViewHolder.class) {
                if (convertView == null) {
                    return new CommonViewHolder(context, resID);
                }
            }
        }
        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入mSparseArray
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        View view = mSparseArray.get(id);
        if (view == null) {
            view = mContentView.findViewById(id);
            mSparseArray.append(id, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param id
     * @param text
     * @return
     */
    public CommonViewHolder setText(int id, String text) {
        TextView view = getView(id);
        view.setText(text);
        return this;
    }

    /**
     * 设置TextView颜色
     *
     * @param id
     * @return
     */
    public CommonViewHolder setTextColor(int id, String color) {
        TextView view = getView(id);
        view.setTextColor(Color.parseColor(color));
        return this;
    }

    //    public CommomViewHolder setImageByUrl(int id, String url) {
    //        ImageView view = getView(id);
    //        Glide.with(mContext).load(url)
    //                .diskCacheStrategy(DiskCacheStrategy.ALL)
    //                .error(R.mipmap.ic_launcher)
    //                .placeholder(R.mipmap.ic_launcher)
    //                .into(view);
    //        return this;
    //    }

    /**
     * 设置TextView图片资源
     *
     * @param id
     * @param resId
     * @return
     */
    public CommonViewHolder setImageResource(int id, int resId) {
        ImageView view = getView(id);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 设置TextView图片资源
     *
     * @param id
     * @param bitmap
     * @return
     */
    public CommonViewHolder setImageBitmap(int id, Bitmap bitmap) {
        ImageView view = getView(id);
        view.setImageBitmap(bitmap);
        return this;
    }
}
