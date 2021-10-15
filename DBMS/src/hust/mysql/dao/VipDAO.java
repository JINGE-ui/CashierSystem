package hust.mysql.dao;

import hust.mysql.bean.Goods;
import hust.mysql.bean.User;
import hust.mysql.bean.Vip;

import java.sql.SQLException;
import java.util.List;

public interface VipDAO {
    /**
     * 办理会员卡
     * @param vip
     * @return
     * @throws SQLException
     */
    int insertVip(Vip vip)  throws SQLException;

    /**
     * 查找会员卡
     * @param v_id
     * @return
     * @throws SQLException
     */
    Vip searchVipbyId(String v_id)  throws SQLException;

    /**
     * 获取所有会员信息
     * @return
     * @throws SQLException
     */
    List<Vip> getAllvip() throws SQLException;

    /**
     * 修改会员卡累计金额
     * @param vip
     * @return
     * @throws SQLException
     */
    int updateVipAmount(Vip vip,Double cost) throws SQLException;

    /**
     * 更改会员卡信息
     * @param vip
     * @return
     * @throws SQLException
     */
    int updateVip(Vip vip) throws SQLException;
}

