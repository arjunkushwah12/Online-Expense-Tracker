package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		UserDao dao = new UserDao(HibernateUtil.getSessionFactory());
		User u =dao.login(email, password);
		System.out.println(u);
		
		HttpSession httpsession =req.getSession();
		if(u==null)
		{
			httpsession.setAttribute("msg","Invalid Email and Password");
			//System.out.println("register successfully");
			resp.sendRedirect("login.jsp");
		}
		else
		{ 
//			u.setFullName("John Doe");
			httpsession.setAttribute("loginuser", u);
			
//			System.out.println("not refisterd  successfully");
			resp.sendRedirect("user/home.jsp");
		}

	}
	

}
