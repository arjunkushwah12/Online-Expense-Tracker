<%@page import="com.entity.Expense"%>
<%@page import="com.dao.ExpenseDao"%>
<%@page import="com.db.HibernateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../component/all_css.jsp"%>
<style type="text/css">
        .card-sh {
            box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body class="bg-light">

<c:if test="${empty loginuser}">
<c:redirect url="../login.jsp"></c:redirect>
</c:if>
<%
		int id=Integer.parseInt(request.getParameter("id"));
		ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
		Expense ex = dao.getExpenseById(id);
%>


	<%@ include file="../component/navbar.jsp"%>
	<div class="container">

	<div class="row">

		<div class="col-md-4 offset-md-4">

			<div class="card card-sh">

				<div class="cord-header text-center">

					<p class="fs-3">Update Expense</p>
					<c:if test="${not empty msg}">
						<p class="text-center text-success">${msg}</p>
						<c:remove var="msg"/>
						</c:if>

				</div>

				<div class="card-body">

					<form action="../updateExpense" method="post">

						<div class="mb-3">

							<label>Title</label> <input type="text" name="title"
								class="form-control" value="<%=ex.getTitle()%>">

						</div>
						<div class="mb-3">

							<label>Date</label> <input type="date" name="date"
								class="form-control" value="<%=ex.getDate()%>">

						</div>

						<div class="mb-3">

							<label>Time</label> <input type="time" name="time"
								class="form-control" value="<%=ex.getTime()%>">

						</div>
						
						<div class="mb-3">

							<label>Description</label> <input type="text" name="description"
								class="form-control" value="<%=ex.getDescription()%>">

						</div>
						<div class="mb-3">

							<label>Price</label> <input type="text" name="price"
								class="form-control" value="<%=ex.getPrice()%>">
						</div>
						<input type="hidden" name="id" value="<%=ex.getId()%>">
	
						<button class="btn btn-success col-md-12">Update</button>					

					</form>

				</div>

			</div>

		</div>
		</div>
		</div>
</body>
</html>