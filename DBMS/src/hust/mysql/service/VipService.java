package hust.mysql.service;

import hust.mysql.bean.Goods;
import hust.mysql.bean.Vip;

import java.sql.SQLException;
import java.util.List;

public interface VipService {
    /**
     * 办理会员卡
     * @param vip
     * @return
     */
    boolean insertVip(Vip vip);

    /**
     * 查找会员卡
     * @param v_id
     * @return
     */
    Vip searchVipbyId(String v_id);

    /**
     * 获取所有会员信息
     * @return
     */
    List<Vip> getAll();

    /**
     * 更改累计金额
     * @param vip
     * @param cost
     * @return
     */
    boolean updateVipAmount(Vip vip,Double cost);

    /**
     * 更改会员卡信息
     * @param vip
     * @return
     */
    boolean updateVip(Vip vip);
}
