package hust.mysql.dao;

import hust.mysql.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017/5/22.
 */
public interface EmployeeDAO {

    /**
     *  添加 员工
     * @param employee
     * @return int
     * @throws SQLException
     */
    int insertEmpl(User employee) throws SQLException;

    /**
     *  根据 id号 删除员工
     * @param id
     * @return int
     * @throws SQLException
     */
    int deleteEmpl(String id) throws SQLException;

    /**
     *  修改员工信息
     * @param employee
     * @return int
     * @throws SQLException
     */
    int updateEmpl(User employee) throws SQLException;

    /**
     *  根据 id 查找员工
     * @param id
     * @return Employee
     * @throws SQLException
     */
    User searchEmplByid(String id) throws SQLException;

    /**
     *  得到所员工信息
     * @return list
     * @throws SQLException
     */
    List<User> findAllEmpl() throws SQLException;
}
