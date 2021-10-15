package hust.mysql.dao;

import hust.mysql.bean.OrderInfo;

import java.sql.SQLException;


public interface OrderInfoDAO {

    /**
     * 增加 订单详情
     * @param orderInfo
     * @return int
     * @throws SQLException
     */
    int insertOrderInfo(OrderInfo orderInfo) throws SQLException;

    /**
     * 根据 商品货号   查询该商品的销售总量
     * @param g_id
     * @return int
     * @throws SQLException
     */
    int getOrderInfoEmployeeCount(String g_id) throws SQLException;

    /**
     * 在数据库中寻找(l_id,g_id)相同的记录，更新其amount值
     * 若没有找到 则 调用insertOrderInfo
     * @param orderInfo
     * @return
     * @throws SQLException
     */
    int updateOrderInfo(OrderInfo orderInfo) throws SQLException;

    /**
     * 在数据库中寻找(l_id,g_id)相同的记录
     * @param orderInfo
     * @return 记录的商品数量  没有找到： -1
     * @throws SQLException
     */
    int IsExistOrderInfoBy2id(OrderInfo orderInfo) throws SQLException;

}
