package service;

import entity.Admin;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/6/3
 */
public interface IAdminService extends IBaseService<Admin> {
    /**
     * 管理员登录的验证方法
     * @param admin 管理员对象
     * @return 是否存在
     */
    boolean verifyAdmin(Admin admin);

    /**
     * 判断某信息是否存在
     * @param admin 对象
     * @return 是否存在
     */
    boolean isExist(Admin admin);
}
