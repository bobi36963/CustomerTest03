package service;

import java.util.List;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/16
 */
public interface IBaseService<T> {
    /**
     * 添加记录
     *
     * @param t 记录对象
     * @return 是否成功
     */
    boolean add(T t);

    /**
     * 修改记录
     *
     * @param t 记录对象
     * @return 是否成功
     */
    boolean update(T t);

    /**
     * 删除记录
     *
     * @param s
     * @return 是否成功
     */
    boolean delete(String s);

    /**
     * 查询全部记录的信息
     *
     * @param
     * @return 返回全部记录信息的List
     */
    List<T> queryAll();

    /**
     * 按照name查询信息
     * @param name
     * @return 返回对象
     */
    T queryByName(String name);


}
