<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/startscreen_socialworker.css">

</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
            </core-toolbar>
            <responsive-menu-socialworker current="0"></responsive-menu-socialworker>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Startscherm zorgprofessional</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div class="content" fit layout vertical>
            <% if(!(request.getAttribute("message") == null)){%>
       		<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<p>Hier moet een tabel komen met het overzicht van de meldingen</p>
            </div>
        </core-header-panel>
    </core-drawer-panel>
</body>
</html>

