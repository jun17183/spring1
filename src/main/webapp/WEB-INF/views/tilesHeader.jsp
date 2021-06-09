<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
    <ul>
        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                <li><a href="/user/login">Login</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/user/logout">Logout</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="/">Home</a></li>
        <li><a href="/board/list">List</a></li>
        <c:if test="not empty sessionScope.loginUser">
          <li><a href="/board/write">Write</a></li><li><a href="/user/profile">Profile</a></li>
        </c:if>
    </ul>
</header>