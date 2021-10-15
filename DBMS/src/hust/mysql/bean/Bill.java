package hust.mysql.bean;

import java.sql.Timestamp;

/**
 * 流水记录表——数据库中的Bill视图
 */
public class Bill {
    private String lid;  //订单编号
    private String cid;    //收银员id
    private String gid;    //商品货号

    private Integer buy_num;  //商品数目
    private Double price;   //单价
    private Timestamp ptime;    //结账时间

    public Bill(String lid,String cid,String gid,Integer buy_num,Double price,Timestamp ptime){
        this.lid = lid;
        this.gid = gid;
        this.cid = cid;
        this.buy_num = buy_num;
        this.price = price;
        this.ptime = ptime;
    }
    public Bill(){

    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setBuy_num(Integer buy_num) {
        this.buy_num = buy_num;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPtime(Timestamp ptime) {
        this.ptime = ptime;
    }

    public String getGid() {
        return gid;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getBuy_num() {
        return buy_num;
    }

    public String getCid() {
        return cid;
    }

    public String getLid() {
        return lid;
    }

    public Timestamp getPtime() {
        return ptime;
    }
}
