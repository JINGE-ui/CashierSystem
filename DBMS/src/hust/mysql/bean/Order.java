package hust.mysql.bean;

import java.sql.Timestamp;

/**
 * 实体类 对应数据库 t_order 表
 * 订单类
 *  除了会员卡号vid，其他都非空
 */
public class Order {
    private String l_id;             // 订单ID
    private String c_id;             // 员工ID
    private Double s_pay;             // 应收
    private Double r_pay;           // 实收 = 应收*0.9 （如果使用会员卡
    private String v_id;             //  使用会员卡号
    private Timestamp e_time;       //完成时间

    public Order() {
    }

    public Order(String l_id, String c_id,Double s_pay,Double r_pay, Timestamp e_time) {
        this.l_id = l_id;
        this.c_id = c_id;
        this.s_pay = s_pay;
        this.r_pay = r_pay;
        this.e_time = e_time;
    }

    public Order(String l_id, String c_id,Double s_pay,Double r_pay,String v_id, Timestamp e_time) {
        this.l_id = l_id;
        this.c_id = c_id;
        this.s_pay = s_pay;
        this.r_pay = r_pay;
        this.v_id = v_id;
        this.e_time = e_time;
    }

    public String getL_id() {
        return l_id;
    }

    public void setL_id(String l_id) {
        this.l_id = l_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public Double getS_pay() {
        return s_pay;
    }

    public void setS_pay(Double s_pay) {
        this.s_pay = s_pay;
    }

    public Double getR_pay(){
        return r_pay;
    }

    public void setR_pay(Double r_pay){
        this.r_pay = r_pay;
    }

    public String getV_id(){
        return this.v_id;
    }

    public void setV_id(String v_id){
        this.v_id = v_id;
    }

    public Timestamp getE_time() {
        return e_time;
    }

    public void setE_time(Timestamp e_time) {
        this.e_time = e_time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "l_id=" + l_id +
                ", c_id=" + c_id +
                ", v_id=" + v_id +
                ", s_pay=" + s_pay +
                ", r_pay=" + r_pay +
                ", e_time=" + e_time +
                '}';
    }
}
