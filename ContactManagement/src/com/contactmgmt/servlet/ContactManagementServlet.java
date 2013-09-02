package com.contactmgmt.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.contactmgmt.dao.Dao;

@SuppressWarnings("serial")
public class ContactManagementServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    System.out.println("Creating new todo ");
    User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }
    String name = checkNull(req.getParameter("Name"));
    String contactinfo = checkNull(req.getParameter("Contactinfo"));
    Dao.INSTANCE.add(user.getUserId(), name, contactinfo);
    resp.sendRedirect("/ContactMgmt.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
} 