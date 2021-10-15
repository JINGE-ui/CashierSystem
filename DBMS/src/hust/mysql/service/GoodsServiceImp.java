package hust.mysql.service;

import hust.mysql.bean.Goods;
import hust.mysql.dao.GoodsDAO;
import hust.mysql.dao.GoodsDAOImp;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;


public class GoodsServiceImp implements GoodsService {
    private GoodsDAO goodsDAO = new GoodsDAOImp();
    /**
     *  商品入库
     * @param goods
     * @return
     */
    @Override
    public boolean addGoods(Goods goods) {
        boolean flag = false;
        try {
            if (goodsDAO.insertGoods(goods) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /***
     * 商品下架
     * @param g_code
     * @return
     */
    @Override
    public boolean deleteGoods(String g_code) {
        boolean flag = false;
        try {
            if (goodsDAO.deleteGoods(g_code) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改 商品数量
     * @param g_id
     * @param g_amount  商品卖出数目
     * @return
     */
    @Override
    public boolean updateGoods(String g_id, int g_amount) {
        boolean flag = false;
        try {
            if (goodsDAO.updateGoodsCount(g_id,g_amount) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改商品 信息
     * @param goods
     * @return
     */
    @Override
    public boolean updateGoods(Goods goods) {
        boolean flag = false;
        try {
            if (goodsDAO.updateGoods(goods) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean isExist(String g_id) {
        boolean flag = false;
        try {
            if (goodsDAO.searchGoodsByCode(g_id) != null){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /***
     * 查找 指定商品
     * @param g_name
     * @return
     */
    @Override
    public Goods searchGoods(String g_name) {
        Goods goodss = null;
        try {
            goodss = goodsDAO.searchGoods(g_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodss;
    }

    @Override
    public Goods searchGoodsByCode(String g_id) {
        Goods goods = null;
        try {
            goods = goodsDAO.searchGoodsByCode(g_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public List<Goods> getAll() {
        List<Goods> goodss = null;
        try {
            goodss = goodsDAO.getAllGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodss;
    }
    /**
     *  获取 指定路径下 excl 表中的数据
     */
    /*
    @Override
    public List<Goods> getAllGoodsByExcl(String filePath) {
        List<Goods> goodss = new ArrayList<>();
        Workbook workbook;
        Sheet sheet;
        try {
            workbook = Workbook.getWorkbook(new File(filePath));
            sheet = workbook.getSheet(0);     // 获取指定 excl 文件中的 第一张表
            int clos = sheet.getColumns();       //得到所有的列
            int rows = sheet.getRows();          //得到所有的行

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    String g_id = sheet.getCell(j++, i).getContents();
                    String type_id = sheet.getCell(j++, i).getContents();
                    String g_code = sheet.getCell(j++, i).getContents();
                    String g_name = sheet.getCell(j++, i).getContents();
                    String g_price = sheet.getCell(j++, i).getContents();
                    String g_amount = sheet.getCell(j++, i).getContents();

                    goodss.add(new Goods(Integer.parseInt(g_id),Integer.parseInt(type_id),g_code,g_name,Double.parseDouble(g_price),Integer.parseInt(g_amount)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return goodss;
    }

     */
}

