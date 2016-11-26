package momo.com.week7.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class HomePageBean {

    /**
     * retcode : 0
     * data : [{"commentid":"1633881584","summary":"　　近日发布的10月份70城房价显示，一线和二线城市新房价格环比分别上涨0.5%和1.3%，比9月回落2.8和1.0个百分点；二手住房价格环比分别上涨0.6%和0.8%，比9月回落2.9和1.1个百分点。同时，10月下半月房价下跌城市由上半月的2个增至7个。　　截至10月份，今年商品房销售面积累计同比达到26.8%，与2015年全年相比提高20.3个","id":"HBJ2016112302398802","title":"评论：改善型住房需求仍较大","commentcount":0,"thumbnail":"http://inews.gtimg.com/newsapp_ls/0/823635274_640330/0","imagecount":0,"type":"0","groupthumbnail":"http://inews.gtimg.com/newsapp_ls/0/823635274_150120/0"}]
     * retmsg : 成功
     */
    private int retcode;
    private List<DataEntity> data;
    private String retmsg;

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
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

    public class DataEntity {
        /**
         * commentid : 1633881584
         * summary : 　　近日发布的10月份70城房价显示，一线和二线城市新房价格环比分别上涨0.5%和1.3%，比9月回落2.8和1.0个百分点；二手住房价格环比分别上涨0.6%和0.8%，比9月回落2.9和1.1个百分点。同时，10月下半月房价下跌城市由上半月的2个增至7个。　　截至10月份，今年商品房销售面积累计同比达到26.8%，与2015年全年相比提高20.3个
         * id : HBJ2016112302398802
         * title : 评论：改善型住房需求仍较大
         * commentcount : 0
         * thumbnail : http://inews.gtimg.com/newsapp_ls/0/823635274_640330/0
         * imagecount : 0
         * type : 0
         * groupthumbnail : http://inews.gtimg.com/newsapp_ls/0/823635274_150120/0
         */
        private String commentid;
        private String summary;
        private String id;
        private String title;
        private int commentcount;
        private String thumbnail;
        private int imagecount;
        private String type;
        private String groupthumbnail;

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setImagecount(int imagecount) {
            this.imagecount = imagecount;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setGroupthumbnail(String groupthumbnail) {
            this.groupthumbnail = groupthumbnail;
        }

        public String getCommentid() {
            return commentid;
        }

        public String getSummary() {
            return summary;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public int getImagecount() {
            return imagecount;
        }

        public String getType() {
            return type;
        }

        public String getGroupthumbnail() {
            return groupthumbnail;
        }


        //----测试
        @Override
        public String toString() {
            return title;
        }
    }
}
