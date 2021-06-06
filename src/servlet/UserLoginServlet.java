package servlet;

import entity.Admin;
import service.IAdminService;
import service.impl.AdminService;

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
 * @date 2021/6/3
 */
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAdminService adminService = new AdminService();
        req.setCharacterEncoding("utf-8");
        String adminName = req.getParameter("adminName");
        String password = req.getParameter("password");

        Admin admin = new Admin(adminName,password);

        boolean result = adminService.verifyAdmin(admin);

        if(result){
            resp.sendRedirect("/CustomerTest/" );
        }else{
            resp.sendRedirect("/CustomerTest/adminLogin.jsp?operatingResult=verifyFail" );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
