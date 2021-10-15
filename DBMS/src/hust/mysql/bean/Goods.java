package hust.mysql.bean;

/**
 * 实体类 对应数据库 goods 表
 * 商品类
 */
public class Goods {
    private String g_id;  //商品货号，即商品条码
    private String g_name;
    private double price;
    private Integer amount;

    public Goods() {
    }

    public Goods(String g_id, String g_name, double price,Integer amount) {
        this.g_id = g_id;
        this.g_name = g_name;
        this.price = price;
        this.amount = amount;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
