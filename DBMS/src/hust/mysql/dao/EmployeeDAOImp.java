package hust.mysql.dao;

import hust.mysql.bean.User;
import hust.mysql.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戴宪锁 on 2017/5/22.
 */
public class EmployeeDAOImp implements EmployeeDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insertEmpl(User employee) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO user VALUES (?,?,?,?,?,?)";
        Object[] objects = {employee.getCid(),employee.getUserpwd(),employee.getCname(),
                employee.getGender(),employee.getAge(),employee.getFlag()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int deleteEmpl(String id) throws SQLException {
        int n = 0;
        String sql = "DELETE FROM user WHERE cid = ?";
        Object[] objects = {id};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }

    @Override
    public int updateEmpl(User employee) throws SQLException {
        int n = 0;
        String sql = "UPDATE user SET cid=?,pwd = ?,cname = ?,gender=?,age=?,flag=? WHERE cid = ?";
        Object[] objects = {employee.getCid(),employee.getUserpwd(),employee.getCname(),
                employee.getGender(),employee.getAge(),employee.getFlag(),employee.getCid()};
        n = jdbcUtil.executeUpdate(sql,objects);
        return n;
    }
    @Override
    public User searchEmplByid(String id) throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        User employee = null;
        String sql = "SELECT * FROM user WHERE cid = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        set = statement.executeQuery();
        if (set.next()){
            employee = new User();
            employee.setCid(set.getString("cid"));
            employee.setCname(set.getString("cname"));
            employee.setUserpwd(set.getString("pwd"));
            employee.setGender(set.getString("gender"));
            employee.setAge(set.getInt("age"));
            employee.setFlag(set.getInt("flag"));
        }
        set.close();
        statement.close();
        connection.close();
        return employee;
    }

    @Override
    public List<User> findAllEmpl() throws SQLException {
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "SELECT * FROM user";
        statement = connection.prepareStatement(sql);
        set = statement.executeQuery();
        List<User> employees = new ArrayList<>();
        User employee = null;

        while (set.next()){
            employee = new User();
            employee.setCid(set.getString("cid"));
            employee.setCname(set.getString("cname"));
            employee.setUserpwd(set.getString("pwd"));
            employee.setGender(set.getString("gender"));
            employee.setAge(set.getInt("age"));
            employee.setFlag(set.getInt("flag"));
            employees.add(employee);
        }
        set.close();
        statement.close();
        connection.close();
        return employees;
    }
}
