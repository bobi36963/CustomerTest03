package dao.impl;

import dao.ICustomerDao;
import dao.util.DataBaseConnect;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/15
 */
public class CustomerDao implements ICustomerDao {
    /**
     * 添加记录
     * @param customer  记录对象
     * @return  是否成功
     */
    @Override
    public boolean add(Customer customer) {
        //数据库连接对象
        Connection conn = null;
        //预处理对象
        PreparedStatement sta = null;
        //用于储存执行sql语句后，影响的数据条数，一般执行一次，执行成功设为1
        int result = 0;
        //调用数据库连接方法，获取数据库连接对象
        conn = DataBaseConnect.connectDataBase();
        //添加客户记录的sql语句
        String addSql = "insert into c_customer(name,gender,phone,email,description) values(?,?,?,?,?)";

        try {
            //预处理sql语句
            sta = conn.prepareStatement(addSql);
            sta.setString(1,customer.getName());
            sta.setString(2,customer.getGender());
            sta.setString(3,customer.getPhone());
            sta.setString(4,customer.getEmail());
            sta.setString(5,customer.getDescription());

            //执行sql语句，将返回值赋值给result
            result = sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //结果为1，添加成功，否则失败
        if(result>=1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 修改记录
     * @param customer 对象
     * @return
     */
    @Override
    public boolean update(Customer customer) {
        //数据库连接对象
        Connection conn = null;
        //预处理对象
        PreparedStatement sta = null;
        //用于储存执行sql语句后，影响的数据条数，一般执行一次，执行成功设为1
        int result = 0;
        //调用数据库连接方法，获取数据库连接对象
        conn = DataBaseConnect.connectDataBase();
        //添加客户记录的sql语句
        String updateSql = "update c_customer set name=?,gender=?,phone=?,email=?,description=? where id = ?";

        try {
            //预处理sql语句
            sta = conn.prepareStatement(updateSql);
            sta.setString(1,customer.getName());
            sta.setString(2,customer.getGender());
            sta.setString(3,customer.getPhone());
            sta.setString(4,customer.getEmail());
            sta.setString(5,customer.getDescription());
            sta.setInt(6,customer.getId());

            //执行sql语句，将返回值赋值给result
            result = sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //结果为1，添加成功，否则失败
        if(result>=1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 删除记录
     * @param s 对象的某个属性
     * @return 是否成功
     */
    @Override
    public boolean delete(String s) {
        int id = Integer.parseInt(s);
        Connection conn = null;
        PreparedStatement sta = null;
        int result = 0;
        conn = DataBaseConnect.connectDataBase();
        String deleteSql = "DELETE FROM c_customer WHERE id=?";
        try {
            sta = conn.prepareStatement(deleteSql);
            sta.setInt(1,id);
            result = sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result>=1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询全部记录的信息
     * @param
     * @return 返回全部记录信息的List
     */
    @Override
    public List<Customer> queryAll(int pages,int limit) {
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;
        List<Customer> customers = new ArrayList<Customer>();

        conn = DataBaseConnect.connectDataBase();
        String querySql = "SELECT id,name,gender,phone,email,description FROM c_customer limit "+pages+","+limit;

        try {
            sta = conn.prepareStatement(querySql);
            res = sta.executeQuery();
            while(res.next()){
                Customer customer = new Customer(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getString("gender"),
                        res.getString("phone"),
                        res.getString("email"),
                        res.getString("description")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * 按照name查询信息
     *
     * @param name
     * @return 返回符合条件的记录对象
     */
    @Override
    public Customer queryByName(String name) {
        Customer customer = null;
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;
        conn = DataBaseConnect.connectDataBase();
        String deleteSql = "select * FROM c_customer WHERE name=?";
        try {
            sta = conn.prepareStatement(deleteSql);
            sta.setString(1,name);
            res = sta.executeQuery();

            if(res.next()){
                    customer = new Customer(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getString("gender"),
                        res.getString("phone"),
                        res.getString("email"),
                        res.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  customer;
    }


    /**
     * 查询记录的总数量
     * @return
     */
    @Override
    public int queryCount() {
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;
        int result = 0;

        conn = DataBaseConnect.connectDataBase();
        String querySql = "SELECT count(*) count FROM c_customer";

        try {
            sta = conn.prepareStatement(querySql);
            res = sta.executeQuery();
            if(res.next()){
                result=res.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isExist(String s) {
        int id = Integer.parseInt(s);
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;
        boolean result = false;


        conn = DataBaseConnect.connectDataBase();
        String querySql = "SELECT id FROM c_customer where id = ?";

        try {
            sta = conn.prepareStatement(querySql);
            sta.setInt(1,id);
            res = sta.executeQuery();
           if(res.next()){
               result = true;
           }
        } catch (SQLException e) {
            result=false;
            e.printStackTrace();
        }

        return result;
    }
}
