package hust.mysql.service;

import hust.mysql.bean.Bill;

import java.util.List;

public interface BillService {
    /**
     * 获取所有流水
     * @return
     */
    List<Bill> getBillALL();

    List<Bill> searchBillbyCID(String cid);
}
