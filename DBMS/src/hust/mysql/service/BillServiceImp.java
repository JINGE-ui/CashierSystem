package hust.mysql.service;

import hust.mysql.bean.Bill;
import hust.mysql.dao.BillDAO;
import hust.mysql.dao.BillDAOImp;

import java.sql.SQLException;
import java.util.List;

public class BillServiceImp implements BillService{
    private BillDAO billDAO = new BillDAOImp();

    @Override
    public List<Bill> getBillALL() {
        List<Bill> bills = null;
        try {
            bills = billDAO.findAllBill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public List<Bill> searchBillbyCID(String cid) {
        List<Bill> bills = null;

        try {
            bills = billDAO.searchBillbyCID(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
