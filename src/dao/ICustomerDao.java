package dao;

import entity.Customer;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/15
 */
public interface ICustomerDao extends IBaseDao<Customer>{
    /**
     * 判断某信息是否存在
     * @param s 对象的某个属性
     * @return 是否存在
     */
    boolean isExist(String s);
}
