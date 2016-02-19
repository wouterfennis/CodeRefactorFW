<!DOCTYPE html>
<html>
<head>
	<title>Personeelslid toevoegen/bijwerken</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-cable" content="yes">
    <meta charset="utf-8">
    <script src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
    <link rel="import" href="/FamilyWeb/elements.html">
    <link rel="import" href="/FamilyWeb/custom_elements/responsive-menu-administrator.html">
	<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
	<link rel="import" href="/FamilyWeb/custom_elements/paper-form-element-decorators/paper-submit-button-decorator.html">
	<link rel="stylesheet" href="/FamilyWeb/styles/add_edit_employee.css">
	<script type="text/javascript" src="/FamilyWeb/javascripts/validate_add_edit_employee.js"></script>
	
</head>
<body fullbleed layout vertical>
    <core-drawer-panel responsivewidth="1400px">
        <core-header-panel drawer>
            <core-toolbar>
				<paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
            </core-toolbar>
            <responsive-menu-administrator current="2"></responsive-menu-administrator>
        </core-header-panel>
        <core-header-panel main>
            <core-toolbar>
			<paper-icon-button class="toolbarButton" core-drawer-toggle icon="menu"></paper-icon-button>
            <div id="title" flex>Personeelslid toevoegen/bijwerken</div>
			<options-menu></options-menu>
            </core-toolbar>
            <div id="content" >
       		<% if(!(request.getAttribute("message") == null)){%>
        	<message-window-${messageType} message="${message}"></message-window-${messageType}>
        	<% } %>
			<paper-shadow z="3" animated="true">
                <form id="add_edit_employee_form" onsubmit="return validateForm();" action="/FamilyWeb/EmployeeServlet.do" method="post">
                   <% if(request.getAttribute("employee") == null){ %>
                   <p>
                        Hieronder kunt u een nieuwe zorgprofessional toevoegen.<br>
                        Let op dat sommige velden goed ingevuld worden.<br>
                        Na de gegevens te hebben ingevoerd kunt u de zorgprofessional toevoegen,<br>
                        door op de knop "Aanmaken" te drukken.<br>
                    </p>
                    <input type="hidden" name="option" value="create">
                   <% } else{ %>
                   <p>
                        Hieronder kunt u een zorgprofessional wijzigen.<br>
                        Let op dat sommige velden goed ingevuld worden.<br> 
                        Na de gegevens te wijzigen kunt u de gegevens opslaan,<br>
                        door op de knop "Opslaan" te drukken.<br>
                    </p>
                    <%} %>
                   <% if(request.getAttribute("employee") == null || request.getAttribute("option") == "create"){ %>
                    <input type="hidden" name="option" value="create">
                     <span id="employeeidWarning" class="true">Geen personeelnr ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Personeelnr :</label>
                            <input id="employeeid" name="employeenumber" type="text" placeholder="Personeelnr" required value="${employee.employeeNumber}"/>
                    </div>
                   <% } else{ %>
                    <input type="hidden" name="option" value="update">
                    <input type="hidden" name="userID" value="${employee.user_id}">
                   <% }%>
					<span id="forenameWarning" class="true">Geen voornaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Voornaam :</label>
                        <input id="forename" name="forename" type="text" placeholder="Voornaam" required value="${employee.forename}" />
                    </div>
					<span id="surnameWarning" class="true">Geen achternaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Achternaam :</label>
                        <input id="surname" name="surname" type="text" placeholder="Achternaam" required value="${employee.surname}"/>
                    </div>
					<span id="dateofbirthWarning" class="true">Geen geboortedatum ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Geboortedatum :</label>
                        <input id="dateofbirth" name="dateofbirth" type="date" placeholder="DD-MM-YYYY" required value="${employee.dateOfBirth}" />
                    </div>
					<span id="nationalityWarning" class="true">Geen nationaliteit ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Nationaliteit :</label>
                        <input id="nationality" name="nationality" type="text" placeholder="Nationaliteit" required value="${employee.nationality}"/>
                    </div>
					<span id="streetWarning" class="true">Geen straatnaam ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Straat :</label>
                        <input id="street" name="street" type="text" placeholder="Straat" required value="${employee.street}"/>
                    </div>
					<span id="streetnumberWarning" class="true">Geen huisnummer ingevuld</span>
                    <div class="information_short">
                        <label><span class="required">* </span>Huisnummer :</label>
                        <input class="short_input" id="streetnumber" name="streetnumber" type="text" placeholder="Huisnr" required value="${employee.houseNumber}" />
                    </div>
					<span id="postcodeWarning" class="true">Geen postcode ingevuld</span>
                    <div class="information_short">
                        <label><span class="required">* </span>Postcode :</label>
                        <input class="short_input" id="postcode" name="postcode" type="text" placeholder="Postcode" required value="${employee.postcode}"/>
                    </div>
					<span id="cityWarning" class="true">Geen woonplaats ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Woonplaats :</label>
                        <input id="city" name="city" type="text" placeholder="Woonplaats" required value="${employee.city}"/>
                    </div>
					<span id="phonenumberWarning" class="true">Geen telefoonnummer ingevuld ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>Telefoonnummer vast :</label>
                        <input id="phonenumber" name="phonenumber" type="tel" placeholder="Telefoonnummer" required value="${employee.telephoneNumber}"/>
                    </div>
					<span id="mobileWarning" class="true">Geen mobiel nummer ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>Mobiel nummer :</label>
                        <input id="mobile" name="mobile" type="tel" placeholder="Mobiel nummer" required value="${employee.mobilePhoneNumber}"/>
                    </div>
					<span id="emailWarning" class="true">Geen email ingevuld</span>
                    <div class="information">
                        <label><span class="required">* </span>E-mail :</label>
                        <input id="email" name="email" type="email" placeholder="E-mail" required value="${employee.email}"/>
                    </div>
					<span id="email_confirmationWarning" class="true">Geen 2e email ingevuld</span>
					<div class="information">
                        <label><span class="required">* </span>E-mail bevestiging :</label>
                        <input id="email_confirmation" name="email_confirmation" type="email" placeholder="E-mail bevestiging" required value="${employee.email}"/>
                    </div>
                    <div class="information">
                    	<label>Is actief?</label>
                    	<input id="is_active" name="is_active" type="checkbox" checked">
                    </div>
                    <% if(request.getAttribute("employee") == null || request.getAttribute("option") == "create"){ %>
                    <div class="information">
                    	<label><span class="required">* </span>Gebruikersnaam</label>
                    	<input id="username" name="username" type="text" placeholder="Gebruikersnaam" required value="${employee.username}">
                    </div>
                     <div class="information">
                    	<label>Is administrator?</label>
                    	<input id="is_administrator" name="is_administrator" type="checkbox" >
                    </div>
                   <div id="submit_button_container">
					<input class="submit_button" name="op" type="submit" value="Aanmaken">
					</div>
                   <% } else{ %>
                    <div class="information">
                    	<label>Wachtwoord resetten?</label>
                    	<input id="reset_password" name="reset_password" type="checkbox" >
                    </div>
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

