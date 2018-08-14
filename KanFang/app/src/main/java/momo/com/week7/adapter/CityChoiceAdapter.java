package momo.com.week7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import momo.com.week7.R;
import momo.com.week7.bean.CityBean;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 城市选择界面的
 * Stickylistheaders的适配器
 * 继承BaseAdapter,实现StickyListHeadersAdapter
 */
public class CityChoiceAdapter extends BaseAdapter implements StickyListHeadersAdapter{
    //Stickylistheaders要展示城市数据
    List<CityBean> data;
    //全部城市数据
    List<CityBean> allData;
    LayoutInflater inflater;


    public CityChoiceAdapter(Context context,List<CityBean> data) {
        this.data = data;
        inflater=LayoutInflater.from(context);
        //====================
        allData = new ArrayList<>();
        allData.addAll(data);
    }

    //向外提供方法，设置allData的数据
    public void setAllData(List<CityBean> data){
        allData.clear();
        allData.addAll(data);

    }

    //======================Stick....
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeadViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.city_head_item,parent,false);
            viewHolder = new HeadViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (HeadViewHolder) convertView.getTag();
        }
        //绑定数据
        viewHolder.tv_head.setText(data.get(position).getLetter());

        return convertView;
    }

    /*返回头部分类id*/
    @Override
    public long getHeaderId(int position) {
        return data.get(position).getLetterId();
    }


    class HeadViewHolder{
        TextView tv_head;

        public HeadViewHolder(View view) {
            tv_head = (TextView) view.findViewById(R.id.city_head);
        }
    }



    //==================listview的adapter

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BeanViewHolder viewHolder =null;
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.city_item_layout,parent,false);
            viewHolder = new BeanViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (BeanViewHolder) convertView.getTag();
        }
        //绑定数据
        viewHolder.tv_cityname.setText(data.get(position).getCityname());

        return convertView;
    }


    class BeanViewHolder{
        TextView tv_cityname;

        public BeanViewHolder(View view) {
            tv_cityname = (TextView) view.findViewById(R.id.city_name);

        }
    }



    /**
     *
     * @param strSearch 搜索的字符串
     */
    //检索方法
    public void search(String strSearch){
        //先清除展示的数据
        data.clear();
        //如果搜索字符串为null或长度为0，就加载所有数据
        if(strSearch==null||strSearch.length()==0){
            data.addAll(allData);
        }else {
            //搜索逻辑代码
            //if else if顺序不能乱
            for (CityBean city : allData) {
                //(中文搜索)如果城市名中，包含有搜索的字符串，表示检索到了
                if (city.getCityname().contains(strSearch)) {
                    data.add(city);
                    //拼音首字母搜索
                }else if(city.getCitywordheadpinyin().contains(strSearch)){
                    data.add(city);
                    //单个拼音字完全匹配搜索
                }else if(searchOneWord(city,strSearch)){
                    data.add(city);
                    //完全匹配搜索
                }else if(searchAllWord(city,strSearch)){
                    data.add(city);
                }
//                else if(city.getCitypinyin().replace(" ","").contains(strSearch)){
//                    data.add(city);
//                }
                ;

            }


        }
        //把搜索的结果设置给自己，再更新界面
        notifyDataSetChanged();

    }


//    private boolean ispinyingHeadCharMatch(CityBean bean,String strSearch){
//        String citypinyin = bean.getCitypinyin();
//        String[] pinyinWordArray = citypinyin.split(" ");
//        StringBuilder pinyinHeadArray = new StringBuilder();
//        for (int i=0;i<pinyinWordArray.length;i++){
//            pinyinHeadArray.append(pinyinWordArray[i].substring(0,1));
//            if(pinyinHeadArray.toString().contains(strSearch)){
//                return true;
//            }
//        }
//        LogPrint.print(citypinyin);
//        return false;
//    }




    private boolean searchAllWord(CityBean bean,String strSearch){
            String cityName = bean.getCitypinyin().replace(" ","");

       return  isSame(cityName,strSearch);

    }

    private boolean searchOneWord(CityBean bean,String strSearch){
        boolean flag;
        String[] cityName = bean.getCitypinyin().split(" ");
        for (int i=0;i<cityName.length;i++) {


            if(isSame(cityName[i],strSearch)==true){
                return true;
            }
        }
        return false;
    }

    //完全匹配字符串方法
    private boolean isSame(String word,String strSearch){
        String matchWord = word.substring(0,word.length()<strSearch.length()?word.length():strSearch.length());
        return matchWord.equals(strSearch);
    }

}
