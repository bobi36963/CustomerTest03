package servlet;

import entity.Customer;
import service.impl.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * copyright(c)2021 YYB.ALL rights Reserved
 * <p>
 * 描述:
 *
 * @author 原玉波
 * @version 1.0
 * @date 2021/5/31
 */
public class SearchCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String searchCustomerName = req.getParameter("searchCustomerName");
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.queryByName(searchCustomerName);

        boolean flag = customer==null?true:false;
        System.out.println(flag);

        resp.setContentType("text/html;charset=UTF-8");

        if(customer!=null){

            String customerId = URLEncoder.encode(customer.getId()+"","utf-8");
            String customerName = URLEncoder.encode(searchCustomerName,"utf-8");
            String customerSex = URLEncoder.encode(customer.getGender(),"utf-8");
            String customerPhone = URLEncoder.encode(customer.getPhone(),"utf-8");
            String customerEmail = URLEncoder.encode(customer.getEmail(),"utf-8");
            String customerRemark = URLEncoder.encode(customer.getDescription(),"utf-8");

            resp.sendRedirect("/CustomerTest/#/page/customerSearch.jsp" +
                    "?operatingResult=searchSuccess" +
                    "&customerId="+customerId+
                    "&customerName="+customerName+
                    "&customerSex="+customerSex+
                    "&customerPhone="+customerPhone+
                    "&customerEmail="+customerEmail+
                    "&customerRemark="+customerRemark
            );
            //resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=searchSuccess&customer="+customer);
        }else{
            resp.sendRedirect("/CustomerTest/#/page/customerSearch.jsp?operatingResult=searchFail" );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
