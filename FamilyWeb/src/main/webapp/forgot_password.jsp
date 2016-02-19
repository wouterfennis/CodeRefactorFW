<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="stylesheet" href="/FamilyWeb/styles/forgot_password.css">
	<title>Wachtwoord resetten</title>
</head>
<body fullbleed layout vertical>
    <core-header-panel flex>
        <core-toolbar>
            <div id="title">Wachtwoord resetten</div>
        </core-toolbar>
        <div class="content">
        <% if(!(request.getAttribute("message") == null)){%>
        <message-window-${messageType} message="${message}"></message-window-${messageType}>
        <% } %>
            <form id="forgot_password_form" action="/FamilyWeb/LoginSupportServlet.do" method="POST">
                <fieldset>
                    <legend>Wachtwoord vergeten</legend>
                    <div class="information">
                        <label>Gebruikersnaam :</label>
                        <input id="username" name="username" type="text" placeholder="Gebruikersnaam" />
                        <input name="option" value="forgotPassword" type="hidden" />
                    </div>
					<div id="reset_button_container">
                        <input class="submit_button" type="submit" value="Resetten">
					</div>
                    <p>Als u op de knop "Resetten" drukt wordt er een mail naar u gestuurd met een nieuw wachtwoord</p>
                    <p id="forgot"><a href="forgot_username.jsp">Gebruikersnaam vergeten?</a></p>
                </fieldset>
            </form>
        </div>
    </core-header-panel>
</body>
</html>

