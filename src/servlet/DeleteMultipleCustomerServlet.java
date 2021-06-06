package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import service.IBaseService;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/22
 */
public class DeleteMultipleCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String customers = req.getParameter("borId");
        IBaseService service = new CustomerService();

        JSONArray jsonArray=JSONArray.parseArray(customers);//JSON.parseArray(jsonStr);
        for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
            JSONObject job=(JSONObject)iterator.next();
            String id=job.get("id").toString();
            boolean result = service.delete(id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
