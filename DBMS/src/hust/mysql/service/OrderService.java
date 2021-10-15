package hust.mysql.service;

import hust.mysql.bean.Order;

public interface OrderService {
    /**
     *  添加 订单
     * @param order
     * @return boolean
     */
    boolean addOrder(Order order);

    /**
     * 根据 员工ID 得到 是 该员工结算的订单数量
     * @param c_id
     * @return int
     */
    int getOrderCountByE_id(String c_id);

    /**
     *  获取 数据库 所有订单数量
     * @return
     */
    int getAllAmount();
}

