package dao.impl;

import dao.IAdminDao;
import dao.util.DataBaseConnect;
import entity.Admin;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/6/3
 */
public class AdminDao implements IAdminDao {
    /**
     * 添加记录
     *
     * @param admin 记录对象
     * @return 是否成功
     */
    @Override
    public boolean add(Admin admin) {
        return false;
    }

    /**
     * 修改记录
     *
     * @param admin 对象
     * @return
     */
    @Override
    public boolean update(Admin admin) {
        return false;
    }

    /**
     * 删除记录
     *
     * @param s 对象的某个属性
     * @return 是否成功
     */
    @Override
    public boolean delete(String s) {
        return false;
    }

    /**
     * 查询全部记录的信息
     *
     * @param pages
     * @param limit
     * @return 返回全部记录信息的List
     */
    @Override
    public List<Admin> queryAll(int pages, int limit) {
        return null;
    }

    /**
     * 按照name查询信息
     *
     * @param name
     * @return 返回符合条件的记录对象
     */
    @Override
    public Admin queryByName(String name) {
        return null;
    }

    /**
     * 查询记录的总数量
     *
     * @return
     */
    @Override
    public int queryCount() {
        return 0;
    }

    /**
     * 管理员登录的验证方法
     *
     * @param admin 管理员对象
     * @return 是否找到
     */
    @Override
    public boolean selectAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;

        boolean result = false;

        conn = DataBaseConnect.connectDataBase();
        String deleteSql = "select * FROM admin WHERE name=? and password=?";
        try {
            sta = conn.prepareStatement(deleteSql);
            sta.setString(1,admin.getName());
            sta.setString(2,admin.getPassword());
            res = sta.executeQuery();

            if(res.next()){
                result = true;
            }

        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 判断某信息是否存在
     *
     * @param admin 对象
     * @return 是否存在
     */
    @Override
    public boolean isExist(Admin admin) {

        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet res = null;
        boolean result = false;

        conn = DataBaseConnect.connectDataBase();
        String querySql = "SELECT name FROM admin where  name= ?";

        try {
            sta = conn.prepareStatement(querySql);
            sta.setString(1,admin.getName());
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
