package servlet;

import entity.Customer;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/25
 */
public class ModifyCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        req.setCharacterEncoding("utf-8");
        String idStr = req.getParameter("customerId");
        int id =  Integer.parseInt(idStr);
        String name = req.getParameter("customerName");
        String gender = req.getParameter("customerSex");
        String phone = req.getParameter("customerPhone");
        String email = req.getParameter("customerEmail");
        String description = req.getParameter("customerRemark");

        Customer customer = new Customer(
                id,
                name,
                gender,
                phone,
                email,
                description
        );

        boolean result = customerService.update(customer);

        if(result){
            resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=modifySuccess");
        }else{
            resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=modifyFail");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
