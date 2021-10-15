package hust.mysql.dao;

import hust.mysql.bean.Goods;

import java.sql.SQLException;
import java.util.List;

public interface GoodsDAO {
    /**
     * 商品入库
     * @param goods
     * @return int
     * @throws SQLException
     */
    int insertGoods(Goods goods) throws SQLException;

    /***
     * 商品下架
     * @param g_code
     * @return int
     * @throws SQLException
     */
    int deleteGoods(String g_id) throws SQLException;

    /**
     * 修改商品信息
     * @param goods
     * @return int
     * @throws SQLException
     */
    int updateGoods(Goods goods) throws SQLException;


    /**
     * 修改指定 商品 库存数量
     * @param g_code
     * @param count
     * @return
     * @throws SQLException
     */
    int updateGoodsCount(String g_code,int count) throws SQLException;

    /**
     *  根据 商品ID 商品
     * @param g_id
     * @return
     * @throws SQLException
     */
    //Goods searchById(Integer g_id) throws SQLException;

    /**
     * 根据 商品名称 查询商品
     * @param g_name
     * @return list
     * @throws SQLException
     */

    Goods searchGoods(String g_name) throws SQLException;

    /**
     *  根据商品条码查找商品
     * @param g_code
     * @return Goods
     * @throws SQLException
     */
    Goods searchGoodsByCode(String g_code) throws SQLException;

    /**
     * 获取 所有 商品
     * @return list
     * @throws SQLException
     */
    List<Goods> getAllGoods() throws SQLException;


}

