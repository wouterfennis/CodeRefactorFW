<!DOCTYPE html>
<html>
<head>
	<title>Client toevoegen/bijwerken</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
	<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/add_edit_client.css">
	<script type="text/javascript" src="/FamilyWeb/javascripts/validate_add_edit_client.js"></script>

</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
            </core-toolbar>
         <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Client toevoegen/bijwerken</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div id="content" >
        	<% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<paper-shadow z="3" animated="true">
                <form id="add_edit_client_form" action="/FamilyWeb/ClientServlet.do" method="post">
                   <% if(request.getAttribute("client") == null){ %>
                   <p>
                        Hieronder kunt u een nieuwe client toevoegen.<br>
                        Let op dat sommige velden goed ingevuld worden.<br>
                        Na de gegevens te hebben ingevoerd kunt u de client toevoegen,<br>
                        door op de knop "Aanmaken" te drukken.<br>
                    </p>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                   <p>
                        Hieronder kunt u een client wijzigen.<br>
                        Let op dat sommige velden goed ingevuld worden.<br> 
                        Na de gegevens te wijzigen kunt u de gegevens opslaan,<br>
                        door op de knop "Opslaan" te drukken.<br>
                    </p>
                    <%} %>
                   <% if(request.getAttribute("client") == null){ %>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                    <input type="hidden" name="option" value="update">
                    <input type="hidden" name="clientID" value="${client.client_id}">
                   <% }%>
					<span id="fileidWarning" class="true">Geen dossiernr ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Dossiernummer :</label>
                          <input id="fileid" name="filenumber" type="text" placeholder="Dossiernr" required value="${client.fileNumber}"/>
                    </div>
					<span id="forenameWarning" class="true">Geen voornaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Voornaam :</label>
                        <input id="forename" name="forename" type="text" placeholder="Voornaam" required value="${client.forename}" />
                    </div>
					<span id="surnameWarning" class="true">Geen achternaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Achternaam :</label>
                        <input id="surname" name="surname" type="text" placeholder="Achternaam" required value="${client.surname}"/>
                    </div>
					<span id="dateofbirthWarning" class="true">Geen geboortedatum ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Geboortedatum :</label>
                        <input id="dateofbirth" name="dateofbirth" type="date" placeholder="DD-MM-JJJJ" required value="${client.dateOfBirth}" />
                    </div>
					<span id="nationalityWarning" class="true">Geen nationaliteit ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Nationaliteit :</label>
                        <input id="nationality" name="nationality" type="text" placeholder="Nationaliteit" required value="${client.nationality}"/>
                    </div>
					<span id="streetWarning" class="true">Geen straatnaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Straat :</label>
                        <input id="street" name="street" type="text" placeholder="Straat" required value="${client.street}"/>
                    </div>
					<span id="streetnumberWarning" class="true">Geen huisnummer ingevuld</span>
                    <div class="information_short">
                        <label><span class="required">* </span>Huisnummer :</label>
                        <input id="streetnumber" name="streetnumber" type="text" placeholder="Huisnr" required value="${client.houseNumber}" />
                    </div>
					<span id="postcodeWarning" class="true">Geen postcode ingevuld</span>
                    <div class="information_short">
                        <label><span class="required">* </span>Postcode :</label>
                        <input id="postcode" name="postcode" type="text" placeholder="Postcode" required value="${client.postcode}"/>
                    </div>
					<span id="cityWarning" class="true">Geen woonplaats ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Woonplaats :</label>
                        <input id="city" name="city" type="text" placeholder="Woonplaats" required value="${client.city}"/>
                    </div>
					<span id="phonenumberWarning" class="true">Geen telefoonnummer ingevuld ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Telefoonnummer vast :</label>
                        <input id="phonenumber" name="phonenumber" type="tel" placeholder="Telefoonnummer" required value="${client.telephoneNumber}"/>
                    </div>
					<span id="mobileWarning" class="true">Geen mobiel nummer ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Mobiel nummer :</label>
                        <input id="mobile" name="mobile" type="tel" placeholder="Mobiel nummer" required value="${client.mobilePhoneNumber}"/>
                    </div>
					<span id="emailWarning" class="true">Geen email ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>E-mail :</label>
                        <input id="email" name="email" type="email" placeholder="E-mail" required value="${client.email}"/>
                    </div>
					<span id="email_confirmationWarning" class="true">Geen 2e email ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>E-mail bevestiging :</label>
                        <input id="email_confirmation" name="email_confirmation" type="email" placeholder="E-mail bevestiging" required value="${client.email}"/>
                    </div>
                    
                   <% if(request.getAttribute("client") == null){ %>
                    <div id="submit_button_container">
                    <input class="submit_button" name="op" type="submit" value="Aanmaken">
                    </div>
                   <% } else{ %>
                    <div id="submit_button_container">
                    <input class="submit_button" name="op" type="submit" value="Opslaan">
                    </div>
                   <% }%>
                </form>
				</paper-shadow>
            </div>
        </core-header-panel>
    </core-drawer-panel>
</body>
</html>
