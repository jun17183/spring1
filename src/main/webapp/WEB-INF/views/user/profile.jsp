<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>my profile</title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.loginUser.profileImg}">
        <c:set var="img" value="/res/img/noprofile.jpg"/>
    </c:when>
    <c:otherwise>
        <c:set var="img" value="/img/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileImg}"/>
    </c:otherwise>
</c:choose>
<div>
    <form action="profile" method="post" enctype="multipart/form-data" id="frm" onsubmit="return imgChk();">
        이미지변경 : <input type="file" name="profileImg" accept="image/*">
        <input type="submit" value="이미지 업로드">
    </form>
</div>
<div>
    <div><img src="${img}"></div>
    <div>PK : ${sessionScope.loginUser.iuser}</div>
    <div>ID : ${sessionScope.loginUser.uid}</div>
    <div>Name : ${sessionScope.loginUser.unm}</div>
</div>

<script>
    var frmElem = document.querySelector('#frm');
    function imgChk() {
        if(frmElem.profileImg.files.length === 0) {
            alert('이미지를 선택해 주세요.');
            return false;
        }
    }
</script>

</body>
</html>

<!--
    var img value 주소에서 /img/~ 이건 디스패처서블릿에서 매핑한 그 D:/springImg 주소다.
-->