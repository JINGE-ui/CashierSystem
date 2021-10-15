package hust.mysql.dao;

import hust.mysql.bean.Order;
import hust.mysql.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImp implements OrderDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insertOrder(Order order) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO income VALUES (?,?,?,?,?,?)";
        System.out.println(sql);
        Object[] objects = {order.getL_id(),order.getC_id(),order.getS_pay(),order.getR_pay(),
                    order.getV_id(),order.getE_time()};
        n = jdbcUtil.executeUpdate(sql,objects);
        System.out.println("excute insert order:n="+n);
        return n;
    }

    @Override
    public int getEmployeeOrderCount(String c_id) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) FROM income WHERE cid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,c_id);
        resultSet = statement.executeQuery();
        int n = 0;
        if (resultSet.next())
            n = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        connection.close();
        return n;
    }

    @Override
    public int getAllAmount() throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) FROM income";
        statement = connection.prepareStatement(sql);
        //statement.setInt(1,-1);
        resultSet = statement.executeQuery();
        int n = 0;
        if (resultSet.next())
            n = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        connection.close();
        return n;
    }
}
