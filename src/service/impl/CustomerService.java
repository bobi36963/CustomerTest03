package service.impl;

import dao.ICustomerDao;
import dao.impl.CustomerDao;
import dao.util.MapToJSON;
import entity.Customer;
import service.ICustomerService;

import java.beans.Customizer;
import java.util.List;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/17
 */
public class CustomerService implements ICustomerService {

    ICustomerDao customerDao = new CustomerDao();

    /**
     * 添加记录
     *
     * @param customer 记录对象
     * @return 是否成功
     */
    @Override
    public boolean add(Customer customer) {
        return customerDao.add(customer);
    }


    /**
     * 修改记录
     *
     * @param customer 记录对象
     * @return 是否成功
     */
    @Override
    public boolean update(Customer customer) {
        boolean result = customerDao.isExist(customer.getId() + "");
        if (result) {
            return customerDao.update(customer);
        } else {
            return false;
        }
    }

    /**
     * 删除记录
     * @param s
     * @return 是否成功
     */
    @Override
    public boolean delete(String s) {
        return customerDao.delete(s);
    }

    /**
     * 查询全部记录的信息
     *
     * @return 返回全部记录信息的List
     */
    @Override
    public List<Customer> queryAll() {
        return null;
    }

    /**
     * 按照name查询信息
     *
     * @param name
     * @return 返回对象
     */
    @Override
    public Customer queryByName(String name) {
        return customerDao.queryByName(name);
    }

}
