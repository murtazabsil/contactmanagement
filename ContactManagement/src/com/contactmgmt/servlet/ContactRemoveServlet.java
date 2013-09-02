package com.contactmgmt.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactmgmt.dao.Dao;

public class ContactRemoveServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String id = req.getParameter("id");
    Dao.INSTANCE.remove(Long.parseLong(id));
    resp.sendRedirect("/ContactMgmt.jsp");
  }
} 