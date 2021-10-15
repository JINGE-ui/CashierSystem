package hust.mysql.dao;

import hust.mysql.bean.OrderInfo;
import hust.mysql.utils.JDBCUtil;

import javax.swing.*;
import java.sql.*;

/**
 * Created by 戴宪锁 on 2017/5/23.
 */
public class OrderInfoImp implements OrderInfoDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insertOrderInfo(OrderInfo orderInfo) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO sells VALUES (?,?,?)";
        Object[] objects = {orderInfo.getG_id(),orderInfo.getL_id(),orderInfo.getInfo_amount()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int getOrderInfoEmployeeCount(String g_id) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT COUNT(*) FROM sells WHERE gid = ? ";
        statement = connection.prepareStatement(sql);
        statement.setString(1,g_id);
        set = statement.executeQuery();
        int n = 0;
        if (set.next()){
            n = set.getInt(1);
        }
        set.close();
        statement.close();
        connection.close();
        return n;
    }

    @Override
    public int updateOrderInfo(OrderInfo orderInfo) throws SQLException {
        int n = 0;
        int flag = IsExistOrderInfoBy2id(orderInfo);
        if(flag != -1){
            String sql = "UPDATE sells SET buy_num = ? WHERE gid=? AND lid=?";
            Object[] objects = {orderInfo.getInfo_amount()+flag,orderInfo.getG_id(),orderInfo.getL_id()};
            n = jdbcUtil.executeUpdate(sql,objects);
        }else{   //没有找到
            n = insertOrderInfo(orderInfo);
        }
        return n;
    }

    @Override
    public int IsExistOrderInfoBy2id(OrderInfo orderInfo) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM sells WHERE gid = ? AND lid=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,orderInfo.getG_id());
        statement.setString(2,orderInfo.getL_id());
        set = statement.executeQuery();
        int flag = -1;
        if(set.next()){
            flag = set.getInt("buy_num");
        }
        set.close();
        statement.close();
        connection.close();
        return flag;
    }
}
