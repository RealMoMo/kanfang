package momo.com.week7.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import momo.com.week7.bean.CityBean;

/**
 * 解析城市数据
 */
public class CityJsonUtils {

    //hotcities为热门城市
      static final String[] letters ={
              "hotcities","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
      };
    public static List<CityBean> getCityByJson(String value)throws Exception{
        //结果数据集合
        ArrayList<CityBean> data = new ArrayList<>();
        //最外层的json
        JSONObject json = new JSONObject(value);
        //拿到cities数据
        JSONObject cities = json.optJSONObject("cities");
        //取出对应字母的jsonArray
        //循环letters数组，得到每个字母,从cities中取出对应的JsonArray
        for(int i=0;i<letters.length;i++) {

            JSONArray arr = cities.optJSONArray(letters[i]);
            //可能有些首字母没有对应的城市(做非空判断)
            if(arr!=null) {
                //再循环遍历arr中的每个JsonObject
                for (int j = 0; j < arr.length(); j++) {
                    //取出对应的JSONObject
                    JSONObject tmp = arr.optJSONObject(j);
                    //构建CityBean
                    CityBean bean = new CityBean(tmp,i==0?"热门城市":letters[i],i);
                    //设置城市拼音首字母数据
                    setCityPinYinHeadWord(bean);
                    //添加到集合中
                    data.add(bean);
                }
            }
        }


        return data;
    }



    private static void setCityPinYinHeadWord(CityBean bean){
        String citypinyin = bean.getCitypinyin();
        String[] pinyinWordArray = citypinyin.split(" ");
        StringBuilder pinyinHeadArray = new StringBuilder();
        for (int i=0;i<pinyinWordArray.length;i++){
            pinyinHeadArray.append(pinyinWordArray[i].substring(0,1));

        }
        bean.setCitywordheadpinyin(pinyinHeadArray.toString());
        LogPrint.print(pinyinHeadArray.toString());
    }
}
