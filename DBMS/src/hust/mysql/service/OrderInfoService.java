package hust.mysql.service;

import hust.mysql.bean.OrderInfo;

public interface OrderInfoService {
    /**
     *  添加 订单详情
     * @param orderInfo
     * @return boolean
     */
    boolean addOrderInfo(OrderInfo orderInfo);

    /**
     * 根据 商品货号  查询该商品的销售总量
     * @param g_id
     * @return int
     */
    int getOrderInfoCountByG_code(String g_id);

    /**
     * 先查找，更新数目；  没找到，就插入
     * @param orderInfo
     * @return
     */
    boolean updateOrderInfo(OrderInfo orderInfo);

    /**
     * 根据(gid,lid)查找，返回数据库中现有的数目
     * @param orderInfo
     * @return
     */
    int IsExistOrderInfoBy2id(OrderInfo orderInfo);
}
