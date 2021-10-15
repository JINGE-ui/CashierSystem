package hust.mysql.dao;

import hust.mysql.bean.Bill;
import hust.mysql.bean.User;
import hust.mysql.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImp implements BillDAO{
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public List<Bill> findAllBill() throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        //从视图Bill选取
        String sql = "SELECT * FROM Bill";
        statement = connection.prepareStatement(sql);
        set = statement.executeQuery();
        List<Bill> bills = new ArrayList<>();
        Bill bill = null;

        while (set.next()){
            bill = new Bill(set.getString("lid"),
                    set.getString("cid"),
                    set.getString("gid"),
                    set.getInt("buy_num"),
                    set.getDouble("price"),
                    set.getTimestamp("ptime")
                    );
            bills.add(bill);
        }
        set.close();
        statement.close();
        connection.close();
        return bills;
    }

    @Override
    public List<Bill> searchBillbyCID(String cid) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM bill WHERE cid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,cid);
        set = statement.executeQuery();
        List<Bill> bills = new ArrayList<>();
        Bill bill = null;
        while (set.next()){
            bill = new Bill(set.getString("lid"),
                    set.getString("cid"),
                    set.getString("gid"),
                    set.getInt("buy_num"),
                    set.getDouble("price"),
                    set.getTimestamp("ptime")
            );
            bills.add(bill);
        }
        set.close();
        statement.close();
        connection.close();
        return bills;
    }
}
