package servlet;

import dao.impl.CustomerDao;
import net.sf.json.JSONArray;
import service.ICustomerService;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/18
 */
public class QueryAllCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDao customerDao=new CustomerDao();
        resp.setContentType("text/html;charset=UTF-8");

        int page = Integer.parseInt( req.getParameter("page"));
        int limit = Integer.parseInt( req.getParameter("limit"));

        int pages = (page-1)*limit;


                PrintWriter out=resp.getWriter();
        org.json.JSONObject jsonObject=new org.json.JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        int count = customerDao.queryCount();
        jsonObject.put("count",count);
        JSONArray result=null;
        result = JSONArray.fromObject(customerDao.queryAll(pages,limit));
        jsonObject.put("data",result);
        out.println(jsonObject.toString());
        out.flush();
        out.close();
    }
}
