<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/custmer_app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>予約　一覧</h2>
        <input    type="submit" value="検索"><br><br>
        <input type="hidden" name="action" value="search">
        <table id="custmer_list">

            <tbody>
                <tr>
                    <th>予約日時</th>
                    <th>名前</th>
                </tr>
                <c:forEach var="custmer" items="${custmer}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><fmt:formatDate value="${custmer.reserve_day}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><c:out value="${custmer.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${employee.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/custmer/show?id=${custmer.id}' />">詳細</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${custmer_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((custmer_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/custmer/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/custmer/new' />"><input type="button"value="新規登録"></a></p>

    </c:param>
</c:import>