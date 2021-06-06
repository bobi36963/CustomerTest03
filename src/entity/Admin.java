package entity;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/6/3
 */
public class Admin {
    /**
     * 管理员编号
     */
    private int id;
    /**
     *管理员用户名
     */
    private String name;
    /**
     *管理员密码
     */
    private String password;

    /**
     *无参构造方法
     */
    public Admin() {
    }
    /**
     *含所有属性的有参构造方法
     */
    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     *不含id属性的有参构造方法
     */
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     *获取id
     */
    public int getId() {
        return id;
    }
    /**
     *设置id，不过一般用不上，数据库的id自增的
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *获取用户名
     */
    public String getName() {
        return name;
    }
    /**
     *设置用户名
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *获取密码
     */
    public String getPassword() {
        return password;
    }
    /**
     *设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *测试用的方法
     */
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name=" + name +
                ", password=" + password +
                '}';
    }
}
