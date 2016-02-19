<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="stylesheet" href="/FamilyWeb/styles/forgot_username.css">
	<title>Gebruikersnaam opvragen</title>
</head>
<body fullbleed layout vertical>
    <core-header-panel flex>
        <core-toolbar>
            <div id="title">Gebruikersnaam opvragen</div>
        </core-toolbar>
        <div class="content">
        <% if(!(request.getAttribute("message") == null)){%>
        <message-window-${messageType} message="${message}"></message-window-${messageType}>
        <% } %>
            <form id="forgot_username_form" action="/FamilyWeb/LoginSupportServlet.do" method="POST">
                <fieldset>
                    <legend>Gebruikersnaam opvragen</legend>
                    <div class="information">
                        <label>Email :</label>
                        <input id="email" name="email" type="email" placeholder="Email" />
                        <input name="option" value="forgotUsername" type="hidden" />
                    </div>
					<div id="retrieve_button_container">
                        <input class="submit_button" type="submit" value="Opvragen">
					</div>
                    <p>Als u op de knop "Opvragen" drukt wordt er een mail naar u gestuurd met uw bestaande gebruikersnaam</p>
                </fieldset>
            </form>
        </div>
    </core-header-panel>
</body>
</html>

