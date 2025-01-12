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


@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String fullName=req.getParameter("fullName");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String about=req.getParameter("about");
		
		User u = new User(fullName,email,password,about);
		System.out.println(u);
		
		
		UserDao dao=new UserDao(HibernateUtil.getSessionFactory());
		boolean f=dao.saveUser(u);
		HttpSession httpsession =req.getSession();
		if(f)
		{
			httpsession.setAttribute("msg", "register successfully");
//			System.out.println("register successfully");
			resp.sendRedirect("register.jsp");
		}
		else
		{ 
			httpsession.setAttribute("msg", "not refisterd   successfully");
//			System.out.println("not refisterd  successfully");
			resp.sendRedirect("register.jsp");
		}
		
	}
	

}
