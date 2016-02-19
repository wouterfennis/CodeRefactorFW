<!DOCTYPE html>
<html>
<head>
	<title>Startscherm administrator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
	
	<script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
    <link rel="import" href="/FamilyWeb/custom_elements/verzoekenTable.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/startscreen_administrator.css">

</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
            </core-toolbar>
            <responsive-menu-administrator current="0"></responsive-menu-administrator>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Startscherm administrator</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div class="content" fit layout vertical>
            <% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<p>Hier moet een tabel komen met het overzicht van de meldingen</p>
			<Verzoeken-table show="contacts" id="table"></Verzoeken-table>
            </div>
        </core-header-panel>
    </core-drawer-panel>
        <script>
    // id van de familie wordt in zorgproffesionalID neergezet en het formulier wordt verstuurd
        function submit(ID) {
            document.getElementById("zorgproffesionalID").value = ID;
            document.getElementById("zorgproffesionalform").submit()
        };
        
        document.addEventListener('polymer-ready', function () {
            var obj = document.querySelector('#table');
            console.log("Polymer Ready");
            var data = [
                         {
                             "requestNumber": "1",
                             "createdCreated": "10-01-2015",
                             "typeRequest": "Transfer",
                             "fromSocialworker": "Jan De Man",
                             "toSocialworker" : "Jans De Mans",
                             "approved": "Yes",
                         },
                                                  {
                             "requestNumber": "2",
                             "createdCreated": "20-06-2015",
                             "typeRequest": "Share",
                             "fromSocialworker": "Wouter Staal",
                             "toSocialworker" : "Jan De Man",
                             "approved": "No",
                                                  },
                                                                           {
                                                      "requestNumber": "3",
                                                      "createdCreated": "22-06-2015",
                                                      "typeRequest": "Share",
                                                      "fromSocialworker": "Jaap Van Noord",
                                                      "toSocialworker" : "Joery Huiden",
                                                      "approved": "No",
                                                                           }
            ]
            console.log("JSON OBJECT before table : " + obj);
            obj.loadData(data);
        });
    </script>
</body>
</html>