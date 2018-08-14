package momo.com.week7.utils;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class ApiManager {


    //主机地址，域名
    public static final String BASE_URL="http://ikft.house.qq.com/";

    //城市选择
    public static final String CITY_CHOICE="index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

    /**
     * 首页 ListView内容
     * <p/>
     * 1)进入时：reqnum=10,pageflag=0,buttonmore=0;
     * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
     * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1;
     * pageflag=0;buttonmore=0;cityid=1;
     */
    public static final String HOME_PAGE="index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=20&act=newslist&channel=71";

    /**
     * 获取首页的Banner数据
     * <p/>
     * cityid是可变
     */
    public static final String HOME_BANNER="index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=homepage&channel=71";


    /**
     * 获取首页Listview_item的资讯详情baseUrl
     * <p/>
     *
     */
    public static final String HOME_ITEM_URL="http://m.house.qq.com/a/";


    /**
     * 获取首页Listview_item的资讯详情
     * <p/>
     *
     */
    public static final String HOUSE_DETAIL="index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71";

    /**
     * 发现Fragment加载的URL地址
     * <p/>
     *
     */
    public static final String DISCOVER_URL="http://m.db.house.qq.com/index.php?mod=appkft&act=discover&cityid=4&rf=kanfang";


//    public static final String TEST="index.php?act=newslist&mod=appkft&devua=appkft_1080_1920_XiaomiMI4LTE_2.3_Android23&appname=QQHouse&guid=867080024411583&devid=867080024411583&huid=H78973766&majorversion=v2&reqnum=20&channel=65";
}
