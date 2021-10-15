package hust.mysql.dao;

import hust.mysql.bean.Goods;
import hust.mysql.bean.Vip;
import hust.mysql.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VipDAOImp implements VipDAO{
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insertVip(Vip vip) throws SQLException {
        int n=0;
        String sql = "INSERT INTO vip VALUES (?,?,?,?,?)";
        Object[] objects = {vip.getV_id(),vip.getV_name(),vip.getTelephone(),vip.getE_time(),vip.getCost()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }


    @Override
    public Vip searchVipbyId(String v_id) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM vip WHERE vid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,v_id);
        set = statement.executeQuery();
        Vip vips1 = null;
        while(set.next()){
            vips1 = new Vip();
            vips1.setV_id(set.getString("vid"));
            vips1.setV_name(set.getString("vname"));
            vips1.setTelephone(set.getString("telephone"));
            vips1.setE_time(set.getTimestamp("etime"));
            vips1.setCost(set.getDouble("amount"));
        }
        set.close();
        statement.close();
        connection.close();
        return vips1;
    }

    @Override
    public List<Vip> getAllvip() throws SQLException {
        List<Vip> vips = new ArrayList<>();
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM vip";
        statement = connection.prepareStatement(sql);
        set = statement.executeQuery();
        Vip vip1 = null;
        while (set.next()){
            vip1 = new Vip();
            vip1.setV_id(set.getString("vid"));
            vip1.setV_name(set.getString("vname"));
            vip1.setTelephone(set.getString("telephone"));
            vip1.setE_time(set.getTimestamp("etime"));
            vip1.setCost(set.getDouble("amount"));
            vips.add(vip1);
        }
        set.close();
        statement.close();
        connection.close();
        return vips;
    }

    @Override
    public int updateVipAmount(Vip vip,Double cost) throws SQLException {
        int n = 0;
        String sql = "UPDATE vip SET amount=? WHERE vid = ?";
        Object[] objects = {vip.getCost()+cost,vip.getV_id()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int updateVip(Vip vip) throws SQLException {
        int n = 0;
        String sql = "UPDATE vip SET etime=?,amount=? WHERE vid = ?";
        Object[] objects = {vip.getE_time(),vip.getCost(),vip.getV_id()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }
}
