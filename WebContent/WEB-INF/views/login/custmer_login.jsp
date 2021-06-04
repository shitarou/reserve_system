<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/custmer_app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">
                名前か電話番号が間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>お客様ログイン</h2>
        <form method="POST" action="<c:url value='/login' />">
            <label for="name">名前</label><br />
            <input type="text" name="name" value="${code}" />
            <br /><br />

            <label for="tell_num">電話番号</label><br />
            <input type="text" name="tell_num" />
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>
    </c:param>
</c:import>