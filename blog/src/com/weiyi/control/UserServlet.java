package com.weiyi.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.weiyi.model.User;
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		if(query.contains("login")){
			doLogin(request,response);
		}else if(query.contains("UserRegister")){
			doRegister(request,response);
		}
		
		
	}

	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("UserServlet.doRegister()");
		String data = request.getParameter("data");
		JSONObject json = new JSONObject(data);
		User user = new User();
		user.setTel(json.getString("telphone"));
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		
	}
	
}
