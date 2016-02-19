<!DOCTYPE html>
<html>
	<head>
		<title>Overzicht zorgprofessionals</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="mobile-web-app-cable" content="yes">
		<meta charset="utf-8">
		
		<script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"> </script>
		<link rel="import" href="/FamilyWeb/elements.html">
		<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
		<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
		<link rel="import" href="/FamilyWeb/custom_elements/employee-table.html">
		<link rel="stylesheet" href="/FamilyWeb/styles/socialworker_overview.css">
	</head>
	
	<body fullbleed layout horizontal>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel drawer> <core-toolbar> 
	<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
    <div class="menuTitle" flex>Menu</div>
	</core-toolbar> <responsive-menu-administrator current="2"></responsive-menu-administrator>
	</core-header-panel> <core-header-panel main> <core-toolbar>
	<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Overzicht zorgprofessionals</div> <options-menu></options-menu> </core-toolbar>
	
	<div class="content" fit layout vertical>
	
		<!-- INFORMATION MESSAGE -->
		<% if (!(request.getAttribute("message") == null)) { %>
		<message-window-${messageType} message="${message}"></message-window-${messageType}>
		<% } %>
		
		<p>Hieronder ziet u een overzicht van alle gebruikers dit zijn zorgprofessionals en administrators.</p>
		
		<!-- THIS DATA IS SEND TO THE SERVLET -->
		<form id="tableForm" action="/FamilyWeb/EmployeeServlet.do" method="post">	
			<input id="currentID" name="currentID" type="hidden" value=""> 
			<input name="option" type="hidden" value="summary">
		</form>
		
		<!-- DATA TABLE -->
		<Employee-table show="contacts" id="table"></Employee-table>
		
	</div>
	</core-header-panel> </core-drawer-panel>
	<script>
		//SET DATA FOR SERVLET AND TRIGGER FORM SUBMIT
		function submit(ID) {
			document.getElementById("currentID").value = ID;
			document.getElementById("tableForm").submit();
		};
		//LOAD DATA INTO TABLE 
		document.addEventListener('polymer-ready', function() {
			var obj = document.querySelector('#table');
			var data = ${usersJSON};
			obj.loadData(data);
		});
	</script>
</body>
</html>
