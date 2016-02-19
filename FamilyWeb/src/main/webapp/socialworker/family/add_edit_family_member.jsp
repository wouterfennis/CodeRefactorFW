<!DOCTYPE html>
<html>
<head>
	<title>Gezinslid toevoegen/bijwerken</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
	<link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/add_edit_family_member.css">
	<script type="text/javascript" src="/FamilyWeb/javascripts/validate_add_edit_family_member.js"></script>
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
            <div id="title" flex>Gezinslid toevoegen/bijwerken</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div id="content" >
        	<% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<paper-shadow z="3" animated="true">
                <form id="add_edit_family_member_form" action="/FamilyWeb/FamilyMemberServlet.do" method="post">                    
                   <% if(request.getAttribute("familymember") == null){ %>
                   <p>
                        Hieronder kunt u een nieuw gezinslid toevoegen aan de client.<br>
                        Let op dat sommige velden goed ingevuld worden.<br>
                        Na de gegevens te hebben ingevoerd kunt u het gezinslid toevoegen,<br>
                        door op de knop "Aanmaken" te drukken.<br>
                    </p>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                   <p>
                        Hieronder kunt u een gezinslid wijzigen.<br>
                        Let op dat sommige velden goed ingevuld worden.<br> 
                        Na de gegevens te wijzigen kunt u de gegevens opslaan,<br>
                        door op de knop "Opslaan" te drukken.<br>
                    </p>
                    <input type="hidden" name="option" value="update">
                    <input type="hidden" name=familymemberID value="${familymember.member_id}">
                   <% }%> 
					<span id="forenameWarning" class="true">Geen voornaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Voornaam :</label>
                        <input id="forename" name="forename" type="text" placeholder="Voornaam" required value="${familymember.forename}" />
                    </div>
					<span id="surnameWarning" class="true">Geen achternaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Achternaam :</label>
                        <input id="surname" name="surname" type="text" placeholder="Achternaam" required value="${familymember.surname}"/>
                    </div>
					<span id="dateofbirthWarning" class="true">Geen geboortedatum ingevuld</span>
					<div class="information">
                        <label>Geboortedatum :</label>
                        <input id="dateofbirth" name="dateofbirth" type="date" placeholder="DD-MM-JJJJ" value="${familymember.dateOfBirth}" />
                    </div>
					<span id="nationalityWarning" class="true">Geen nationaliteit ingevuld</span>
					<div class="information">
                        <label>Nationaliteit :</label>
                        <input id="nationality" name="nationality" type="text" placeholder="Nationaliteit" value="${familymember.nationality}"/>
                    </div>
					<span id="streetWarning" class="true">Geen straatnaam ingevuld</span>
                    <div class="information">
                        <label>Straat :</label>
                        <input id="street" name="street" type="text" placeholder="Straat" value="${familymember.street}"/>
                    </div>
					<span id="streetnumberWarning" class="true">Geen huisnummer ingevuld</span>
                    <div class="information_short">
                        <label>Huisnummer :</label>
                        <input id="streetnumber" name="streetnumber" type="text" placeholder="Huisnr" value="${familymember.houseNumber}" />
                    </div>
					<span id="postcodeWarning" class="true">Geen postcode ingevuld</span>
                    <div class="information_short">
                        <label>Postcode :</label>
                        <input id="postcode" name="postcode" type="text" placeholder="Postcode" value="${familymember.postcode}"/>
                    </div>
					<span id="cityWarning" class="true">Geen woonplaats ingevuld</span>
                    <div class="information">
                        <label>Woonplaats :</label>
                        <input id="city" name="city" type="text" placeholder="Woonplaats" value="${familymember.city}"/>
                    </div>
					<span id="phonenumberWarning" class="true">Geen telefoonnummer ingevuld ingevuld</span>
                    <div class="information">
                        <label>Telefoonnummer vast :</label>
                        <input id="phonenumber" name="phonenumber" type="tel" placeholder="Telefoonnummer" value="${familymember.telephoneNumber}"/>
                    </div>
					<span id="mobileWarning" class="true">Geen mobiel nummer ingevuld</span>
					<div class="information">
                        <label>Mobiel nummer :</label>
                        <input id="mobile" name="mobile" type="tel" placeholder="Mobiel nummer" value="${familymember.mobilePhoneNumber}"/>
                    </div>
					<span id="emailWarning" class="true">Geen email ingevuld</span>
                    <div class="information">
                        <label>E-mail :</label>
                        <input id="email" name="email" type="email" placeholder="E-mail" value="${familymember.email}"/>
                    </div>
                    
                   <% if(request.getAttribute("familymember") == null){ %>
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