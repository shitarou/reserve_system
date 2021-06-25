<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/custmer_app.jsp">
    <c:param name="content">
        <h2>予約登録</h2>

        <form method="POST" action="<c:url value='/custmer/create' />">
            <c:import url="_form.jsp" />

        </form>

        <p><a href="<c:url value='/calender/show' />">戻る</a></p>
    </c:param>
</c:import>