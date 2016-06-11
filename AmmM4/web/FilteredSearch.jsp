<%-- 
    Document   : FilteredSearch
    Created on : Jun 8, 2016, 1:21:46 PM
    Author     : sym410
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<json:array>
    <c:forEach var="obj" items="${queryResultList}">
        <json:object>
            <json:property name="name" value="${obj.getName()}"/>
            <json:property name="id" value="${obj.getId()}"/>
            <json:property name="url" value="${obj.getUrl()}"/>
            <json:property name="quantity" value="${obj.getQuantity()}"/>
            <json:property name="description" value="${obj.getDescription()}"/>
            <json:property name="price" value="${obj.getPrice()}"/>
            <json:property name="category" value="${obj.getCategory()}"/>
        </json:object>
    </c:forEach>


</json:array>

