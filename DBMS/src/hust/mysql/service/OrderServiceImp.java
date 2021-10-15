package hust.mysql.service;

import hust.mysql.bean.Order;
import hust.mysql.dao.OrderDAO;
import hust.mysql.dao.OrderDAOImp;

import java.sql.SQLException;

/**
 * Created by 戴宪锁 on 2017/5/23.
 */
public class OrderServiceImp implements OrderService {
    private OrderDAO orderDAO = new OrderDAOImp();

    /**
     *  添加订单
     * @param order
     * @return boolean
     */
    @Override
    public boolean addOrder(Order order) {
        boolean flag = false;
        try {
            if (orderDAO.insertOrder(order) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据 员工ID 得到 是 该员工结算的订单数量
     * @param c_id
     * @return int
     */
    @Override
    public int getOrderCountByE_id(String c_id) {
        int n = 0;
        try {
            n = orderDAO.getEmployeeOrderCount(c_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int getAllAmount() {
        int n = -1;
        try {
            n = orderDAO.getAllAmount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}
