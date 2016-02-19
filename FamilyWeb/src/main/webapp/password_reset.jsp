<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="stylesheet" href="/FamilyWeb/styles/password_reset.css">
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
			
            <form id="password_reset_form" action="/FamilyWeb/PasswordresetServlet.do" method="POST">
                <fieldset>
                    <legend>Wachtwoord resetten</legend>
                    <p>U logt voor de eerste keer in of u wachtwoord is gereset.</p>
                    <p>Vul hieronder het oude wachtwoord in en uw eigen nieuwe wachtwoord</p>
                    <div class="information">
                        <label>Oud wachtwoord :</label>
                        <input id="old_password" name="old_password" type="password" placeholder="Oud wachtwoord"/>
                    </div>
                    <div class="information">
                        <label>Nieuw wachtwoord :</label>
                        <input id="new_password" name="new_password" type="password" placeholder="Nieuw wachtwoord"/>
                    </div>
                    <div class="information">
                        <label>Bevestig nieuw wachtwoord :</label>
                        <input id="new_password_confirmation" name="new_password_confirmation" type="password" placeholder="Nieuw wachtwoord"/>
                    </div>
					<div id="reset_button_container">
					 <input class="submit_button" type="submit" value="Resetten">
					</div>
                </fieldset>
            </form>
        </div>
    </core-header-panel>
</body>
</html>

