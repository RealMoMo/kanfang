package momo.com.week7.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class BannerBean {

    /**
     * retcode : 0
     * data : [{"title":"50-70㎡智慧商务空间","houseid":"177446","picurl":"http://p.qpic.cn/estate/0/512c9096291a2baf1b160cf72f08be34.jpg/0","type":"3"},{"title":"泰禾中央广场荟萃世界的精彩","houseid":"166048","picurl":"http://p.qpic.cn/estate/0/8fca7ee91b769935ed42e64a95cbb192.jpg/0","type":"3"},{"title":"海淀院落别墅即将发售！","houseid":"172904","picurl":"http://p.qpic.cn/estate/0/8b47976a6efbc885581a2c495ed72751.jpg/0","type":"3"}]
     * retmsg : 成功
     * ehouse_timestamp : 1478612493
     */
    private int retcode;
    private List<DataEntity> data;
    private String retmsg;
    private int ehouse_timestamp;

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public void setEhouse_timestamp(int ehouse_timestamp) {
        this.ehouse_timestamp = ehouse_timestamp;
    }

    public int getRetcode() {
        return retcode;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public int getEhouse_timestamp() {
        return ehouse_timestamp;
    }

    public class DataEntity {
        /**
         * title : 50-70㎡智慧商务空间
         * houseid : 177446
         * picurl : http://p.qpic.cn/estate/0/512c9096291a2baf1b160cf72f08be34.jpg/0
         * type : 3
         */
        private String title;
        private String houseid;
        private String picurl;
        private String type;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setHouseid(String houseid) {
            this.houseid = houseid;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public String getHouseid() {
            return houseid;
        }

        public String getPicurl() {
            return picurl;
        }

        public String getType() {
            return type;
        }
    }
}
