package momo.com.week7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 练习万能BaseAdapter
 */
public abstract class ExerAdapter<T> extends BaseAdapter{


    List<T> data;
    LayoutInflater inflater;
    int[] layoutId;

    public ExerAdapter(Context context, List<T> data, int ...layoutId) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public abstract void bindView(int postion,ViewHolder holder);

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        int layoutType = getItemViewType(i);
        if(view==null){
            view =inflater.inflate(layoutId[layoutType],viewGroup,false);
            viewHolder =new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        bindView(i,viewHolder);
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return layoutId.length;
    }

    public static  class ViewHolder{

        View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public View findViewById(int id){
            return view.findViewById(id);
        }
    }
}
