package hust.mysql.service;

import hust.mysql.bean.User;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    /**
     *  员工 登录验证
     * @param account
     * @param password
     * @return
     */
    Map isLogin(String account,String password,int flag);

    /**
     *  添加员工
     * @param employee
     * @return boolean
     */
    boolean addEmployee(User employee);

    /**
     * 根据 员工账户 删除 员工
     * @param e_account
     * @return boolean
     */
    boolean deleteEmployee(String e_account);

    /**
     *  修改 信息
     * @param employee
     * @return boolean
     */
    boolean updateEmployee(User employee);

    /**
     *  根据 账户 查找指定员工
     * @param e_account
     * @return Employee
     */
    User searchEmployee(String e_account);

    /**
     *  获取 数据库中 所有员工信息
     * @return
     */
    List<User> getEmployeeAll();
}
