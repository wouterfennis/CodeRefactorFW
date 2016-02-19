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
<link rel="import"
	href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
<link rel="stylesheet" href="/FamilyWeb/styles/new_network_contacts.css">
<link rel="stylesheet" href="/FamilyWeb/styles/base.css">
<script type="text/javascript" src="/FamilyWeb/javascripts/manage_new_network_contacts.js"></script>
<script type="text/javascript" src="/FamilyWeb/javascripts/validate_new_network_contacts.js"></script>

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
		<%@ page import="domain.FamilyWeb.Familymember"%>
		<message-window-notification
			message="Selecteer een gezinslid en voeg zijn of haar contactpersonen toe aan de contactgroepen."></message-window-notification>
        <% if(!(request.getAttribute("message") == null)){%>
        <message-window-${messageType} message="${message}"></message-window-${messageType}>
        <% } %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div id="form_container">
			<form id="group_form" onsubmit="return validateForm();" action="/FamilyWeb/ContactServlet.do" method="post">

				<div id="select_box_container">
					<div class="information">
						<label class="selectLabel">Selecteer de ondervraagde:</label>
						<!--                                 Nog een foreach loop in maken waarin de familymembers uit de session worden gehaald en in een select worden gestopt -->
						<select id="interviewee" name="interviewee">
							<option class="select_option" value="${client.forename}:${client.client_id}">${client.forename} ${client.surname}</option>
							<c:forEach items="${client.myFamilymembers}" var="familymember">
								<option class="select_option" value="${familymember.forename}:${familymember.member_id}">${familymember.forename} ${familymember.surname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- elke groep kan ook via een jsp functie worden aangemaakt zolang alle groepen al in de sessie staan. Dus die moeten al eerder worden aangemaakt-->
				<div id="household" class="group">
					<input id="counterhousehold"  name="counterhousehold" type="hidden" value="0" />
					<h3>Gezin</h3>
					<core-icon-button class="add" onclick="addInput('household')"
						icon="add"></core-icon-button>
				</div>
				<div id="family" class="group">
					<input id="counterfamily" name="counterfamily" type="hidden" value="0" />
					<h3>Familie</h3>
					<core-icon-button class="add" onclick="addInput('family')"
						icon="add"></core-icon-button>
				</div>
				<div id="friends" class="group">
					<input id="counterfriends" name="counterfriends" type="hidden" value="0" />
					<h3>Vrienden</h3>
					<core-icon-button class="add" onclick="addInput('friends')"
						icon="add"></core-icon-button>
				</div>
				<div id="colleagues" class="group">
					<input id="countercolleagues" name="countercolleagues" type="hidden" value="0" />
					<h3>Collega's</h3>
					<core-icon-button class="add" onclick="addInput('colleagues')"
						icon="add"></core-icon-button>
				</div>
				<div id="neighbours" class="group">
					<input id="counterneighbours" name="counterneighbours" type="hidden" value="0" />
					<h3>Buren</h3>
					<core-icon-button class="add" onclick="addInput('neighbours')"
						icon="add"></core-icon-button>
				</div>
				<div id="acquaintance" class="group">
					<input id="counteracquaintance" name="counteracquaintance" type="hidden"
						value="0" />
					<h3>Kennissen</h3>
					<core-icon-button class="add" onclick="addInput('acquaintance')"
						icon="add"></core-icon-button>
				</div>
				<div id="education" class="group">
					<input id="countereducation" name="countereducation" type="hidden" value="0" />
					<h3>Onderwijs</h3>
					<core-icon-button class="add" onclick="addInput('education')"
						icon="add"></core-icon-button>
				</div>
				<div id="club" class="group">
					<input id="counterclub" name="counterclub" type="hidden" value="0" />
					<h3>Verenigingen</h3>
					<core-icon-button class="add" onclick="addInput('club')" icon="add"></core-icon-button>
				</div>
				<div id="religion" class="group">
					<input id="counterreligion" name="counterreligion" type="hidden" value="0" />
					<h3>Religie</h3>
					<core-icon-button class="add" onclick="addInput('religion')"
						icon="add"></core-icon-button>
				</div>
				<div id="careinstitution" class="group">
					<input id="countercareinstitution" name="countercareinstitution" type="hidden"
						value="0" />
					<h3>Zorginstellingen</h3>
					<core-icon-button class="add" onclick="addInput('careinstitution')"
						icon="add"></core-icon-button>
				</div>
				<div id="youthcare" class="group">
					<input id="counteryouthcare" name="counteryouthcare" type="hidden" value="0" />
					<h3>Jeugdzorg</h3>
					<core-icon-button class="add" onclick="addInput('youthcare')"
						icon="add"></core-icon-button>
				</div>
				<div id="bureauhalt" class="group">
					<input id="counterbureauhalt" name="counterbureauhalt" type="hidden" value="0" />
					<h3>Bureau HALT</h3>
					<core-icon-button class="add" onclick="addInput('bureauhalt')"
						icon="add"></core-icon-button>
				</div>
				<div id="justice" class="group">
					<input id="counterjustice" name="counterjustice" type="hidden" value="0" />
					<h3>Justitie</h3>
					<core-icon-button class="add" onclick="addInput('justice')"
						icon="add"></core-icon-button>
				</div>
				<div id="select_box_container">
					<div class="information">
						<label class="selectLabel">Selecteer de vragenlijst die u wilt afnemen:</label> <select
							id="survey_selection" name="survey">
							<c:forEach items="${surveys}" var="survey">
								<option class="select_option" value="${survey}">${survey}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div id="submit_button_container">
				<input type="submit" class="submit_button"
					value="Vragenlijst afnemen">
				</div>
			</form>
		</div>
	</div>
	</core-header-panel> </core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>
</body>

</html>
