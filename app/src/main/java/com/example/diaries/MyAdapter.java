package com.example.diaries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class MyAdapter extends BaseAdapter{

    private List<Data> mData;
    private Context context;
    private int layoutId;

    public MyAdapter(List<Data> valuesList, Context context, int layoutId) {
        this.mData = valuesList;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        if (mData != null && mData.size() > 0)
            return mData.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mData != null && mData.size() > 0)
            return mData.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(
                    context).inflate(R.layout.list_item, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = mData.get(position).gettitle();
        viewHolder.title.setText(title);
        return convertView;
    }
    public void removeItem(int position){
        this.mData.remove(position);
    }

}
class ViewHolder{
    TextView title;
}
