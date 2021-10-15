package hust.mysql.bean;

import java.sql.Timestamp;

/**
 * Created by 戴宪锁 on 2017/5/22.
 * 实体类 对应数据库 sells 表
 * 订单详情类(订单号，商品货号，数目）
 */
public class OrderInfo {
    private String g_id;
    private String l_id;
    private Integer info_amount;

    public OrderInfo() {
    }

    public OrderInfo(String g_id, String l_id, Integer info_amount) {
        this.g_id = g_id;
        this.l_id = l_id;
        this.info_amount = info_amount;
    }

    public String getL_id() {
        return l_id;
    }

    public void setL_id(String l_id) {
        this.l_id = l_id;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public Integer getInfo_amount() {
        return info_amount;
    }

    public void setInfo_amount(Integer info_amount) {
        this.info_amount = info_amount;
    }


    @Override
    public String toString() {
        return "OrderInfoService{" +
                ", g_id=" + g_id +
                ", l_id='" + l_id + '\'' +
                ", info_amount=" + info_amount +
                '}';
    }
}
