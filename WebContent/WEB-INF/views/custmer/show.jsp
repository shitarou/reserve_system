<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/custmer_app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${custmer != null}">
                <h2>id : ${custmer.id} お客様の詳細・更新ページ</h2>


                <form method="POST" action="<c:url value='/custmer/update' />">
                    <c:import url="_form.jsp" />
                </form>
                <p><a href="#" onclick="confirmDestroy();"><button type="submit">キャンセル</button></a></p>
                <form method="POST" action="<c:url value='/custmer/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当にキャンセルしてよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p>
            <a href="<c:url value='/custmer/calender' />">カレンダーに戻る</a>
        </p>
    </c:param>
</c:import>