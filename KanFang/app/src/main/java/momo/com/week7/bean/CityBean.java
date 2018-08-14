package momo.com.week7.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class CityBean {

    /**
     * citypinyin : zhou shan
     * cityalias : zhoushan
     * comparename : 舟山市
     * esfalias : zhoushan
     * lng : 0.596032
     * xiaomazhuangxiu : 0
     * cityid : 279
     * mobiletype : 0
     * center_y : 122.264106
     * center_x : 30.351147
     * cityname : 舟山
     * xiaomaurl :
     * xiaomarent : 0
     * lat : 0.834871
     */

    /*城市首字母(热门城市为ho~~~)*/
    private String letter;
    /*分组id*/
    private int letterId;

    /*城市首字母拼音*/
    private String citywordheadpinyin;



    private String citypinyin;
    private String cityalias;
    private String comparename;
    private String esfalias;
    private String lng;
    private int xiaomazhuangxiu;
    private String cityid;
    private String mobiletype;
    private String center_y;
    private String center_x;
    private String cityname;
    private String xiaomaurl;
    private int xiaomarent;
    private String lat;

    public CityBean() {
    }

    public CityBean(JSONObject obj,String letter,int letterId){
        this.letter = letter;
        this.letterId =letterId;
        citypinyin = obj.optString("citypinyin");
        cityalias = obj.optString("cityalias");
        comparename = obj.optString("comparename");
        esfalias = obj.optString("esfalias");
        lng = obj.optString("lng");
        xiaomazhuangxiu = obj.optInt("xiaomazhuangxiu");
        cityid = obj.optString("cityid");
        mobiletype = obj.optString("mobiletype");
        center_y = obj.optString("center_y");
        center_x = obj.optString("center_x");
        cityname = obj.optString("cityname");
        xiaomaurl = obj.optString("xiaomaurl");
        xiaomarent = obj.optInt("xiaomarent");
        lat = obj.optString("lat");

    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public String getCitywordheadpinyin() {
        return citywordheadpinyin;
    }

    public void setCitywordheadpinyin(String citywordheadpinyin) {
        this.citywordheadpinyin = citywordheadpinyin;
    }

    public void setCitypinyin(String citypinyin) {
        this.citypinyin = citypinyin;
    }

    public void setCityalias(String cityalias) {
        this.cityalias = cityalias;
    }

    public void setComparename(String comparename) {
        this.comparename = comparename;
    }

    public void setEsfalias(String esfalias) {
        this.esfalias = esfalias;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setXiaomazhuangxiu(int xiaomazhuangxiu) {
        this.xiaomazhuangxiu = xiaomazhuangxiu;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public void setCenter_y(String center_y) {
        this.center_y = center_y;
    }

    public void setCenter_x(String center_x) {
        this.center_x = center_x;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setXiaomaurl(String xiaomaurl) {
        this.xiaomaurl = xiaomaurl;
    }

    public void setXiaomarent(int xiaomarent) {
        this.xiaomarent = xiaomarent;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCitypinyin() {
        return citypinyin;
    }

    public String getCityalias() {
        return cityalias;
    }

    public String getComparename() {
        return comparename;
    }

    public String getEsfalias() {
        return esfalias;
    }

    public String getLng() {
        return lng;
    }

    public int getXiaomazhuangxiu() {
        return xiaomazhuangxiu;
    }

    public String getCityid() {
        return cityid;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public String getCenter_y() {
        return center_y;
    }

    public String getCenter_x() {
        return center_x;
    }

    public String getCityname() {
        return cityname;
    }

    public String getXiaomaurl() {
        return xiaomaurl;
    }

    public int getXiaomarent() {
        return xiaomarent;
    }

    public String getLat() {
        return lat;
    }
}
