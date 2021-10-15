package hust.mysql.service;

import hust.mysql.bean.Order;
import hust.mysql.bean.OrderInfo;
import hust.mysql.dao.OrderInfoDAO;
import hust.mysql.dao.OrderInfoImp;

import java.sql.SQLException;


public class OrderInfoServiceImp implements OrderInfoService {
    private OrderInfoDAO orderInfoDAO = new OrderInfoImp();

    @Override
    public boolean addOrderInfo(OrderInfo orderInfo) {
        boolean flag = false;
        try {
            if (orderInfoDAO.insertOrderInfo(orderInfo) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int getOrderInfoCountByG_code(String g_id) {
        int n = 0;
        try {
            n = orderInfoDAO.getOrderInfoEmployeeCount(g_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int IsExistOrderInfoBy2id(OrderInfo orderInfo) {
        int flag=-1;
        try{
            flag = orderInfoDAO.IsExistOrderInfoBy2id(orderInfo);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateOrderInfo(OrderInfo orderInfo) {
        boolean flag = false;
        try {
            if (orderInfoDAO.updateOrderInfo(orderInfo) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
