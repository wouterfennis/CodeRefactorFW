<!DOCTYPE html>
<html>
<head>
<title>Nieuw netwerk</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-cable" content="yes">
<meta charset="utf-8">
<script
	src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="/FamilyWeb/elements.html">
<link rel="import"
	href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-media-query/core-media-query.html">
<link rel="import"
	href="/FamilyWeb/bower_components/paper-tabs/paper-tabs.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-icon-button/core-icon-button.html">
<link rel="stylesheet" href="/FamilyWeb/styles/new_network_questions.css">
<script type="text/javascript" src="/FamilyWeb/javascripts/validate_new_network_questions.js"></script>

</head>
<body fullbleed layout vertical>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel
		drawer> <core-toolbar> 
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
	</core-toolbar> <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
	</core-header-panel> <core-header-panel main> <core-toolbar
		class="medium-tall"> 
<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Nieuw netwerk</div>
	<options-menu></options-menu>
	<div class="bottom fit" horizontal layout>
		<paper-tabs id="scrollableTabs" selected="2" flex scrollable noink>

		<paper-tab>
		<a href="/FamilyWeb/socialworker/family/family_members_overview.jsp" horizontal center-center layout>Gezinsleden</a></paper-tab>
		<paper-tab>
		<a href="/FamilyWeb/socialworker/family/network_compare.jsp" horizontal center-center layout>Netwerken</a></paper-tab>
		<paper-tab>
		<a href="/FamilyWeb/socialworker/family/new_network_contacts.jsp" horizontal center-center layout>Nieuw
			Netwerk</a></paper-tab> <paper-tab>
		<a href="/FamilyWeb/socialworker/family/share_networks.jsp" horizontal center-center layout>Netwerk(en)
			delen</a></paper-tab> <paper-tab>
		<a href="/FamilyWeb/socialworker/family/transfer.jsp" horizontal center-center layout>Overdragen</a></paper-tab>

		</paper-tabs>
	</div>
	</core-toolbar>
	<div class="content" layout vertical>
		<message-window-notification
			message="Beantwoord per contact de vragen."></message-window-notification>
		<%@ page import="java.util.ArrayList" %>
		<%@ page import="domain.FamilyWeb.Contact" %>
		<%@ page import="domain.FamilyWeb.Question" %>
		<%@ page import="domain.FamilyWeb.Answer" %>
        <% if(!(request.getAttribute("message") == null)){%>
        <message-window-${messageType} message="${message}"></message-window-${messageType}>
        <% } %>
		<div id="form_container">
			<form id="questions_form" onSubmit="return validateForm();" action="/FamilyWeb/SurveyServlet.do" method="post">
				<!-- elke contact kan ook via een jsp functie worden aangemaakt zolang alle contact al in de sessie staan. Dus die moeten al eerder worden aangemaakt-->
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
				<input id="totalContacts" type="hidden" value="${fn:length(contacts)}">
				<input id="totalQuestions" type="hidden" value="${fn:length(survey.questions)}">
<c:forEach items="${contacts}" var="contact">
					<div class="contact">
						<h3>${contact.fullname} - ${contact.role}</h3>
						<core-icon-button class="add" onclick="viewQuestions(${contact.contact_id})" icon="arrow-drop-down"></core-icon-button>
						<div id="contact${contact.contact_id}" class="questions">
						<c:forEach items="${survey.questions}" var="question">
						<div id="${question.question_id}" class="question">
						<span id="${question.question_id}:${contact.contact_id}:warning" class="true">Deze vraag is niet correct beantwoord!</span>
						<p class="questionTitle">${question.question}</p>
						<c:choose>
  						<c:when test="${fn:length(question.theAnswers) < 6}">
   							<c:forEach items="${question.theAnswers}" var="answer">
								<div class="radio">
								<input type="radio" id="${answer.answer_id}" name="${question.question_id}:${contact.contact_id}" value="${answer.answer_id}" />${answer.answer}
								</div>
							</c:forEach>
  						</c:when>
  						<c:when test="${fn:length(question.theAnswers) > 5}">
						<select name="${question.question_id}:${contact.contact_id}">
							<c:forEach items="${question.theAnswers}" var="answer">
								<option class="select_option" value="${answer.answer_id}">${answer.answer}</option>
							</c:forEach>
						</select>
  						</c:when>
  						</c:choose>
						</div>						
						</c:forEach> 
						<c:if test="${fn:length(contact.commentary) > 1}">
							<p>Opmerking bij contact: ${contact.commentary}</p>           
						</c:if>       		
						<div><input class="displayButton" type="button" onclick="closeQuestions(${contact.contact_id})" value="Verbergen" /></div>						
						</div>
					</div>
				</c:forEach>
				<div>
                    <h4><label class="commentLabel">Algemene opmerking:</label></h4>
                    <textarea name="general_comment" placeholder="Algemene opmerking"></textarea>
                </div>
				<div id="submit_button_container">
					<input class="submit_button" type="submit" value="Netwerk genereren">
				</div>
			</form>
		</div>
	</div>
	</core-header-panel> 
	</core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

	<script>
		function viewQuestions(questionsID) {
            document.getElementById("contact"+questionsID).style.display = 'block';
        }
		
		function closeQuestions(questionsID){
			document.getElementById("contact"+questionsID).style.display = 'none';
		}

        document.querySelector('#mediaQuery').addEventListener('core-media-change',
          function (e) {
              document.body.classList.toggle('core-narrow', e.detail.matches);
              document.querySelector('#scrollableTabs').updateBar();
          });

    </script>
</body>

</html>
