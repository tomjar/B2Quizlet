<%-- 
  - Author(s):
  - Date:
  - Copyright Notice:
  - @(#)
  - Description: 
  --%>

<%@page contentType="text/html" pageEncoding="UTF-8"
	import ="beans.users.Users" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="user" scope="session" class="beans.users.Users"></jsp:useBean>
<%@taglib uri="/bbNG" prefix="bbNG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<!-- Importing values from the bundles -->
	<fmt:setLocale value="en" />
	<fmt:setBundle basename="quizlet.bundle.quizlet_en"/>
	<fmt:message key="quizlet.instructions" var="quizletInstructions"></fmt:message>
	<fmt:message key="quizlet.title" var="quizletTitle"></fmt:message>
	<fmt:message key="plugin.logo" var="pluginLogo"></fmt:message>
	<fmt:message key="quizlet.step.label.idText" var="quizletStepLabelId"></fmt:message>
	<fmt:message key="quizlet.step.title" var="quizletStepTitle"></fmt:message>
	<fmt:message key="quizlet.step.instructions" var="quizletStepInstructions"></fmt:message>
	<fmt:message key="quizlet.step.label.titleText" var="quizletStepLabelTitle"></fmt:message>
	
<bbNG:learningSystemPage title="This is the bbNG:genericPage title"
	ctxId="ctx">
	<div id="userinfo">
		<c:out value="<%= user.getUserInfo(ctx.getCourseId()) %>"></c:out>
	</div>
	
	
</bbNG:learningSystemPage>
