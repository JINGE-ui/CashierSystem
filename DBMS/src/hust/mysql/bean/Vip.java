package hust.mysql.bean;

import java.sql.Timestamp;
/**
 * 会员卡记录 对应 表 vip
 */
public class Vip {
    private String v_id;
    private String v_name;
    private String telephone;
    private Timestamp e_time;
    private Double cost;

    public Vip(String v_id,String v_name,String telephone,Timestamp e_time,Double cost){
        this.v_id = v_id;
        this.v_name = v_name;
        this.telephone = telephone;
        this.e_time = e_time;
        this.cost = cost;
    }

    public Vip(String v_id,String telephone,Timestamp e_time,Double cost){
        this.v_id = v_id;
        this.telephone = telephone;
        this.e_time = e_time;
        this.cost = cost;
    }
    public Vip(){}

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public Timestamp getE_time() {
        return e_time;
    }

    public void setE_time(Timestamp e_time) {
        this.e_time = e_time;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "v_id=" + v_id +
                ", v_name=" + v_name +
                ", telephone=" + telephone +
                ", cost=" + cost +
                ", e_time=" + e_time +
                '}';
    }
}
