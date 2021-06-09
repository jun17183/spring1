<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="writeMod" method="post">
    <input type="hidden" name="iboard" value="">
    <div>
        <input type="text" name="title" placeholder="title">
    </div>
    <div>
        <textarea name="ctnt" placeholder="ctnt"></textarea>
    </div>
    <div>
        <input type="submit" value="submit">
    </div>
</form>