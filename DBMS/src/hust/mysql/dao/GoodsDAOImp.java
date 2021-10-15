package hust.mysql.dao;

import hust.mysql.bean.Goods;
import hust.mysql.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017/5/23.
 */
public class GoodsDAOImp implements GoodsDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insertGoods(Goods goods) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO goods VALUES (?,?,?,?)";
        Object[] objects = {goods.getG_id(),goods.getG_name(),goods.getPrice(),goods.getAmount()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int deleteGoods(String g_id) throws SQLException {
        int n = 0;
        String sql = "DELETE FROM goods WHERE gid = ?";
        Object[] objects = {g_id};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }


    @Override
    public int updateGoods(Goods goods) throws SQLException {
        int n = 0;
        String sql = "UPDATE goods SET gname = ?,price = ?,stock = ? WHERE gid = ?";
        Object[] objects = {goods.getG_name(),goods.getPrice(),goods.getAmount(),goods.getG_id()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int updateGoodsCount(String g_id, int count) throws SQLException {
        int n = 0;
        Goods goods = searchGoodsByCode(g_id);
        int countTemp = -1;
        countTemp = goods.getAmount() - count;
        String sql = "UPDATE goods SET stock = ? WHERE gid = ?";
        Object[] objects = {countTemp,g_id};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }


    @Override
    public Goods searchGoods(String g_name) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM goods WHERE gname = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,g_name);
        set = statement.executeQuery();
        Goods goods1 = null;
        while (set.next()){
            goods1 = new Goods();
            goods1.setG_id(set.getString("gid"));
            goods1.setG_name(set.getString("gname"));
            goods1.setAmount(set.getInt("stock"));
            goods1.setPrice(set.getDouble("price"));
//            goods.add(goods1);
        }
        set.close();
        statement.close();
        connection.close();
        return goods1;
    }

    @Override
    public Goods searchGoodsByCode(String g_id) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM goods WHERE gid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,g_id);
        set = statement.executeQuery();
        Goods goods1 = null;
        while (set.next()){
            goods1 = new Goods();
            goods1.setG_id(set.getString("gid"));
            goods1.setG_name(set.getString("gname"));
            goods1.setAmount(set.getInt("stock"));
            goods1.setPrice(set.getDouble("price"));
//            goods.add(goods1);
        }
        set.close();
        statement.close();
        connection.close();
        return goods1;
    }

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        List<Goods> goods = new ArrayList<>();
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM goods";
        statement = connection.prepareStatement(sql);
        set = statement.executeQuery();
        Goods goods1 = null;
        while (set.next()){
            goods1 = new Goods();
            goods1.setG_id(set.getString("gid"));
            goods1.setG_name(set.getString("gname"));
            goods1.setAmount(set.getInt("stock"));
            goods1.setPrice(set.getDouble("price"));
            goods.add(goods1);
        }
        set.close();
        statement.close();
        connection.close();
        return goods;

    }
}

