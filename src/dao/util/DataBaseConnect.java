package dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/15
 */
public class DataBaseConnect {
    static final String dataBaseDriver = "com.mysql.cj.jdbc.Driver";
    static final String dataBaseUrl = "jdbc:mysql://localhost/customer?useSSL=FALSE&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    static final String dataBaseUser = "root";
    static final String dataBasePassword = "yuanyubo0514";

    static Connection conn = null;
    public static Connection connectDataBase (){

        try {
            Class.forName(dataBaseDriver);
            conn = DriverManager.getConnection(dataBaseUrl,dataBaseUser,dataBasePassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
