<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.contactmgmt.model.Contact" %>
<%@ page import="com.contactmgmt.dao.Dao" %>

<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>

<html>
  <head>
    <title>Contact List</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
<%
Dao dao = Dao.INSTANCE;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
List<Contact> contacts = new ArrayList<Contact>();
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    contacts = dao.getContact(user.getUserId());
}
    
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      <div style="float: left;"><img src="images/contact.jpg" /></div>
      <div style="float: left;" class="headline">Contact List</div>
      <div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
    </div>
  </div>

<div style="clear: both;"/>  
You have a total number of <%= contacts.size() %>  Contacts.

<table>
  <tr>
      <th>Name</th>
      <th>Contact</th>
      <th>Remove Contact</th>
    </tr>

<% for (Contact contact : contacts) {%>
<tr> 
<td>
<%=contact.getName()%>
</td>
<td>
<%=contact.getContact() %>
</td>
<td>
<a class="done" href="/removecontact?id=<%=contact.getId()%>" >Remove</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New Contact</div>

<% if (user != null){ %> 

<form action="/contactmanagement" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="name">Name</label></td>
      <td><input type="text" name="Name" id="Name" size="65"/></td>
    </tr>
    <tr>
      <td valign="Contactinfo"><label for="Contactinfo">Contact</label></td>
      <td><input type="text" name="Contactinfo" id="Contactinfo"></textarea></td>
    </tr>
  	<tr>
      <td colspan="2" align="right"><input type="submit" value="Create New"/></td>
    </tr>
  </table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html> 