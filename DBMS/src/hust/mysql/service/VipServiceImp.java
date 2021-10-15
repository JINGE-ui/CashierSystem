package hust.mysql.service;

import hust.mysql.bean.Goods;
import hust.mysql.bean.Vip;
import hust.mysql.dao.VipDAO;
import hust.mysql.dao.VipDAOImp;

import java.sql.SQLException;
import java.util.List;

public class VipServiceImp implements VipService{
    private VipDAO  vipDAO = new VipDAOImp();

    @Override
    public boolean insertVip(Vip vip) {
        boolean flag = false;
        try {
            if (vipDAO.insertVip(vip) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Vip searchVipbyId(String v_id) {
        Vip vip = null;
        try {
            vip = vipDAO.searchVipbyId(v_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vip;
    }

    @Override
    public List<Vip> getAll() {
        List<Vip> vips = null;
        try {
            vips = vipDAO.getAllvip();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vips;
    }

    @Override
    public boolean updateVipAmount(Vip vip, Double cost) {
        boolean flag = false;
        try {
            if (vipDAO.updateVipAmount(vip,cost) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateVip(Vip vip) {
        boolean flag = false;
        try {
            if (vipDAO.updateVip(vip) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
