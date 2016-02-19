<!DOCTYPE html>
<html>
<head>
<title>Gezinsleden</title>
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
<link rel="stylesheet"
	href="/FamilyWeb/styles/family_members_overview.css">
<link rel="import"
	href="/FamilyWeb/custom_elements/family-members-table.html">
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
            <div id="title" flex>Gezinsleden</div>
	<options-menu></options-menu>
	<div class="bottom fit" horizontal layout>
		<paper-tabs id="scrollableTabs" selected="0" flex scrollable noink>

		<paper-tab> <a href="/FamilyWeb/socialworker/family/family_members_overview.jsp"
			horizontal center-center layout>Gezinsleden</a></paper-tab> 
		<paper-tab>
		<a href="/FamilyWeb/socialworker/family/network_compare.jsp" horizontal center-center layout>Netwerken</a></paper-tab>
		<paper-tab> <a href="/FamilyWeb/socialworker/family/new_network_contacts.jsp" horizontal
			center-center layout>Nieuw Netwerk</a></paper-tab> 
			<paper-tab> <a
			href="/FamilyWeb/socialworker/family/share_networks.jsp" horizontal center-center layout>Netwerk(en)
			delen</a></paper-tab> 
			<paper-tab> <a href="/FamilyWeb/socialworker/family/transfer.jsp" horizontal
			center-center layout>Overdragen</a></paper-tab> 
			</paper-tabs>
	</div>
	</core-toolbar>
	<div class="content" fit layout vertical>
        <% if(!(request.getAttribute("message") == null)){%>
        <message-window-${messageType} message="${message}"></message-window-${messageType}>
        <% } %>
<div class="manual"><p>Hieronder ziet u de gezinsleden van de cliënt: <span class="bold">${client.forename} ${client.surname}</span><br>
U kunt de gegevens wijzigen van de gezinlid of een nieuw gezinslid toevoegen.</p></div>
<Family-members-table show="contacts" id="table"></Family-members-table>
		<form id="tableForm" action="/FamilyWeb/FamilyMemberServlet.do" method="post">			
		<div id="login_button_container">
			<input class="submit_button" name = "new" type="submit" value="Nieuw gezinslid toevoegen">
		</div>
			<input id="currentID" name="currentID" type="hidden" value="0"> 
			<input name="option" type="hidden" value="summary">
		</form>
	</div>
	</core-header-panel> </core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

	<script>
	 document.addEventListener('polymer-ready', function () {
         var obj = document.querySelector('#table');
         console.log("Polymer Ready");
         var data = ${familyJSON};
         console.log("JSON OBJECT before table : " + obj);
         obj.loadData(data);
     });
	 function submit(ID) {
         document.getElementById("currentID").value = ID;
         document.getElementById("tableForm").submit();
     };		
	</script>
</body>
</html>