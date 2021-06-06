package entity;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:客户实体类
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/15
 */
public class Customer {
    /**
     * 客户编号
     */
    private int id;
    /**
     * 客户姓名
     */
    private String name;
    /**
     * 客户性别
     */
    private String gender;
    /**
     * 客户电话号码
     */
    private String phone;
    /**
     * 客户邮箱地址
     */
    private String email;
    /**
     * 描述
     */
    private String description;

    /**
     * 客户实体类无参构造方法
     */
    public Customer() {
    }

    /**
     * 客户实体类有参构造方法，包含所有属性
     * @param id
     * @param name
     * @param gender
     * @param phone
     * @param email
     * @param description
     */
    public Customer(int id, String name, String gender, String phone, String email, String description) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    /**
     * 客户实体类有参构造方法，不包含客户编号与创建日期
     * @param name
     * @param gender
     * @param phone
     * @param email
     * @param description
     */
    public Customer(String name, String gender, String phone, String email, String description) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    /**
     * 获取客户编号
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 设置客户编号
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取客户姓名
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户姓名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**获取客户性别
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置客户性别
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取客户电话号码
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置客户电话号码
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取客户邮箱
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置客户邮箱
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取描述
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 测试用，输出该实体类的属性
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
