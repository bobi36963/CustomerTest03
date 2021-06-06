package servlet;

import entity.Customer;
import service.ICustomerService;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/17
 */
public class AddCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("customerName");
        String sex = req.getParameter("customerSex");
        String phone = req.getParameter("customerPhone");
        String email = req.getParameter("customerEmail");
        String remark = req.getParameter("customerRemark");

        Customer customer = new Customer(name,sex,phone,email,remark);

        ICustomerService customerService = new CustomerService();
        boolean result = customerService.add(customer);

        resp.setCharacterEncoding("utf-8");

        if(result){
            resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=addSuccess");
        }else{
            resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=addFail");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
