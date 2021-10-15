package hust.mysql.service;

import hust.mysql.bean.User;
import hust.mysql.dao.EmployeeDAO;
import hust.mysql.dao.EmployeeDAOImp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**p
 * Created by 戴宪锁 on 2017/5/23.
 */
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAOImp();

    /**
     *  用户登录 验证
     * @param account   用户id
     * @param password
     * @param flag  用户权限
     * @return
     */
    @Override
    public Map isLogin(String account,String password,int flag) {
        Map map = new HashMap();
        User employee = null;
        try {
            employee = employeeDAO.searchEmplByid(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employee != null){
            if (employee.getUserpwd().equals(password) && employee.getFlag()==flag){
                map.put("employee",employee);
                map.put("info","登录成功!");
            }else {
                map.put("info","密码错误!");
            }
        }else {
            map.put("info","账号不存在!");
        }
        return map;
    }

    /**
     *  添加 员工
     * @param employee
     * @return boolean
     */
    @Override
    public boolean addEmployee(User employee) {
        boolean flag = false;
        try {
            if (employeeDAO.insertEmpl(employee) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *  根据 账户 删除 员工
     * @param e_account
     * @return boolean
     */
    @Override
    public boolean deleteEmployee(String e_account) {
        boolean flag = false;
        try {
            if (employeeDAO.deleteEmpl(e_account) == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改信息
     * @param employee
     * @return boolean
     */
    @Override
    public boolean updateEmployee(User employee) {
        boolean flag = false;
        try {
            if (employeeDAO.updateEmpl(employee) == 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据 id 查找指定 员工
     * @param e_account
     * @return Employee
     */
    @Override
    public User searchEmployee(String e_account) {
        User employee = null;

        try {
            employee = employeeDAO.searchEmplByid(e_account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<User> getEmployeeAll() {
        List<User> employees = null;
        try {
            employees = employeeDAO.findAllEmpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
