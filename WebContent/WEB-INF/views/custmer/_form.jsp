<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="name">名前</label><br />
<input type="text" name="name" value="${custmer.name}" />
<br /><br />

<label for="tell_num">電話番号</label><br />
<input type="text" name="tell_num" value="${custmer.tell_num}" />
<br  /><br  />

<label for="reserve_day">予約日</label><br />
<input type="text" name="reserve_day" value="${custmer.reserve_day}" />
<br /><br />

<label for="peoples">人数</label><br />
<input type="text" name="peoples" />
<br /><br />



<br /><br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">更新</button>
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>