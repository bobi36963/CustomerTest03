package service.impl;

import dao.IAdminDao;
import dao.impl.AdminDao;
import entity.Admin;
import service.IAdminService;

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
public class AdminService implements IAdminService {

    IAdminDao adminDao = new AdminDao();

    /**
     * 管理员登录的验证方法
     *
     * @param admin 管理员对象
     * @return 是否存在
     */
    @Override
    public boolean verifyAdmin(Admin admin) {
        return adminDao.selectAdmin(admin);
    }

    /**
     * 判断某信息是否存在
     *
     * @param admin 对象
     * @return 是否存在
     */
    @Override
    public boolean isExist(Admin admin) {
        return adminDao.isExist(admin);
    }

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
     * @param admin 记录对象
     * @return 是否成功
     */
    @Override
    public boolean update(Admin admin) {
        return false;
    }

    /**
     * 删除记录
     *
     * @param s
     * @return 是否成功
     */
    @Override
    public boolean delete(String s) {
        return false;
    }

    /**
     * 查询全部记录的信息
     *
     * @return 返回全部记录信息的List
     */
    @Override
    public List<Admin> queryAll() {
        return null;
    }

    /**
     * 按照name查询信息
     *
     * @param name
     * @return 返回对象
     */
    @Override
    public Admin queryByName(String name) {
        return null;
    }
}
