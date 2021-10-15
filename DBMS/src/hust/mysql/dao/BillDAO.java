package hust.mysql.dao;

import hust.mysql.bean.Bill;
import hust.mysql.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface BillDAO {
    /**
     * 查询所有流水
     * @return
     * @throws SQLException
     */
    List<Bill> findAllBill() throws SQLException;

    /**
     * 查看收银员经手的流水
     * @return
     * @throws SQLException
     */
    List<Bill> searchBillbyCID(String cid) throws SQLException;
}
