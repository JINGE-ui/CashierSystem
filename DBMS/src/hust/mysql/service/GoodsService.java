package hust.mysql.service;


import hust.mysql.bean.Goods;

import java.util.List;

/**
 * Created by 戴宪锁 on 2017/5/23.
 */
public interface GoodsService {
    /**
     *  商品入库
     * @param goods
     * @return boolean
     */
    boolean addGoods(Goods goods);

    /**
     * 商品下架
     * @param g_code
     * @return boolean
     */
    boolean deleteGoods(String g_code);

    /**
     *  修改商品数量
     * @param g_code
     * @param g_amount  卖出的数目
     * @return boolean
     */
    boolean updateGoods(String g_code,int g_amount);

    /**
     * 修改商品信息
     * @param goods
     * @return boolean
     */
    boolean updateGoods(Goods goods);

    /**
     *  根据 商品ID 判断该商品是否存在
     * @param g_id
     * @return
     */
    boolean isExist(String g_id);

    /**
     *  根据  商品名称 查找
     * @param g_name
     * @return
     */
    Goods searchGoods(String g_name);

    /**
     *  根据商品条码 查询商品
     * @param g_code
     * @return Goods
     */
    Goods searchGoodsByCode(String g_code);

    /**
     *  获取 所有商品
     * @return list
     */
    List<Goods> getAll();

    /**
     *  获取指定路径下 excl 表中的数据
     * @param filePath
     * @return
     */
    //List<Goods> getAllGoodsByExcl(String filePath);
}

