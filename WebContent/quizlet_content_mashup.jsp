<%-- 
  - Author(s):
  - Date:
  - Copyright Notice:
  - @(#)
  - Description: 
  --%>

<%@page contentType="text/html" pageEncoding="UTF-8"
	errorPage="error.jsp" 
	import ="beans.grades.Grades"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/bbNG" prefix="bbNG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="grades" scope="session" class="beans.grades.Grades"></jsp:useBean>

<!-- Importing values from the bundles -->
<fmt:setLocale value="en" />
<fmt:setBundle basename="quizlet.bundle.quizlet_en" />
<fmt:message key="quizlet.instructions" var="quizletInstructions"></fmt:message>
<fmt:message key="quizlet.title" var="quizletTitle"></fmt:message>
<fmt:message key="plugin.logo" var="pluginLogo"></fmt:message>
<fmt:message key="quizlet.step.label.idText" var="quizletStepLabelId"></fmt:message>
<fmt:message key="quizlet.step.title" var="quizletStepTitle"></fmt:message>
<fmt:message key="quizlet.step.instructions"
	var="quizletStepInstructions"></fmt:message>
<fmt:message key="quizlet.step.label.titleText"
	var="quizletStepLabelTitle"></fmt:message>

<bbNG:learningSystemPage title="This is the bbNG:genericPage title"
	ctxId="ctx">
	
	<c:set var="courseId"><%=ctx.getCourseId().toString()%></c:set>
	<c:set var="currentUrl"><%=ctx.getRequestUrl().toString()%></c:set>
	<c:set var="contentId"><%=ctx.getContentId().toString()%></c:set>
	<bbNG:pageHeader instructions="${quizletInstructions}">
		<bbNG:pageTitleBar iconUrl="${pluginLogo}" showIcon="true"
			showTitleBar="true" title="${quizletTitle}" />
	</bbNG:pageHeader>
	<div id="userinfo">
		<c:catch var="exception">
			<c:out value="<%= grades.getUserInfo(ctx.getCourseId()) %>"></c:out>
		</c:catch>
		<c:if test = "${exception != null}">
  			<p>The exception is : ${exception} <br />
   			<c:forEach var="stackTraceElem" items="${exception.stackTrace}">
				<c:out value="${stackTraceElem}"/><br/>
			</c:forEach>
		</c:if>
		
	</div>
	<bbNG:form
		action="http://adn-testweb:55333/webapps/na-B2Quizlet2-BBLEARN/CreateContent"
		method="post" name="quizletForm">
		<bbNG:dataCollection markUnsavedChanges="true"
			showSubmitButtons="true">
			<bbNG:step title="${quizletStepTitle}"
				instructions="${quizletStepInstructions}">
				<!-- These hidden fields are for the Content Management System(CMS) so that Blackboard knows where this came from. -->
				<input type="hidden" value="<%=ctx.getCourseId().toString()%>"
					name="courseId" />
				<input type="hidden" value="<%=ctx.getContentId().toString()%>"
					name="contentId" />
				<input type="hidden" value="${pageContext.request.requestURI}"
					name="http_ref" />

				<!-- To view available attributes for this "datElement" tag press Ctrl + Space while in the tag it self -->
				<bbNG:dataElement isRequired="true" label="${quizletStepLabelTitle}">
					<bbNG:textElement name="quizletName" value="" size="50"
						minLength="1" />
				</bbNG:dataElement>

				<bbNG:dataElement isRequired="true" label="${quizletStepLabelId}">
					<bbNG:textElement name="quizletId" label="${quizletStepLabelId}" />
				</bbNG:dataElement>
			</bbNG:step>
			<bbNG:stepSubmit cancelUrl="window.history.back()">
				<bbNG:stepSubmitButton label="Submit" />
			</bbNG:stepSubmit>
		</bbNG:dataCollection>
	</bbNG:form>
</bbNG:learningSystemPage>
