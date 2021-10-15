package hust.mysql.dao;

import hust.mysql.bean.Order;

import java.sql.SQLException;

public interface OrderDAO {
    /**
     * 增加 订单
     * @param order
     * @return int
     * @throws SQLException
     */
    int insertOrder(Order order) throws SQLException;

    /**
     *  根据 员工ID 得到 是 该员工结算的订单数量
     * @param c_Id
     * @return int
     * @throws SQLException
     */
    int getEmployeeOrderCount(String c_Id) throws SQLException;

    /**
     * 获取 数据库订单 数量
     * @return
     * @throws SQLException
     */
    int getAllAmount() throws SQLException;
}
