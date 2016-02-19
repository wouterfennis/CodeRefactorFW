		function addInput(groupName) {
			var countBox = parseInt(document.getElementById("counter"
					+ groupName).value);
			countBox += 1;
			var name = "name" + countBox;
			var role = "role" + countBox;
			var age = "age" + countBox;
            var comment = "comment" + countBox;
			var validate = "validate" + countBox;
            var button = "button" + countBox;
			document.getElementById("counter" + groupName).value = countBox;
            
            // de wrapper van alle contact informatie.
            var contact = document.createElement('div');
            contact.className="contact";
            contact.setAttribute("id", groupName + countBox);
            
            // waarschuwing voor de naam mocht het niet goed zijn ingevuld
            var nameWarning = document.createElement('span');
            nameWarning.className="true";
            nameWarning.setAttribute("id", groupName + "warning" + name);
            var nameWarningText = document.createTextNode("Naam niet correct ingevuld!"); 
            nameWarning.appendChild(nameWarningText);
            
            // de input voor de naam
            var informationName = document.createElement('div');
            informationName.className="information";
            informationName.setAttribute("id", groupName + name);
            var labelName = document.createElement('label');
            labelName.className="textLabel";
            var textName = document.createTextNode("Naam:");        
            labelName.appendChild(textName);
            var inputName = document.createElement('input');
            inputName.setAttribute("name", groupName + name);
            inputName.setAttribute("placeholder", "Volledige naam");
            inputName.setAttribute("type", "text");
            
            // verbergen en weergeven knop
            var displayButton = document.createElement('input');
            displayButton.className="displayButton";
            displayButton.setAttribute("id", groupName + button);
            displayButton.setAttribute("type", "button");
            displayButton.setAttribute("onclick","hideContact(" + countBox + "," + "'" + groupName + "'" +")");
            displayButton.setAttribute("value", "Verbergen");
            
            // waarschuwing voor de rol mocht het niet goed zijn ingevuld
            var roleWarning = document.createElement('span');
            roleWarning.className="true";
            roleWarning.setAttribute("id", groupName + "warning" + role);
            var roleWarningText = document.createTextNode("Rol niet correct ingevuld!"); 
            roleWarning.appendChild(roleWarningText);
            
            // de input voor de rol
            var informationRole = document.createElement('div');
            informationRole.className="information";
            informationRole.setAttribute("id", groupName + role);
            var labelRole = document.createElement('label');
            labelRole.className="textLabel";
            var textRole = document.createTextNode("Rol:");        
            labelRole.appendChild(textRole);
            var inputRole = document.createElement('input');
            inputRole.setAttribute("name", groupName + role);
            inputRole.setAttribute("placeholder", "Rol");
            inputRole.setAttribute("type", "text");
            
            // waarschuwing voor de leeftijd mocht het niet goed zijn ingevuld
            var ageWarning = document.createElement('span');
            ageWarning.className="true";
            ageWarning.setAttribute("id", groupName + "warning" + age);
            var ageWarningText = document.createTextNode("Leeftijd niet correct ingevuld!"); 
            ageWarning.appendChild(ageWarningText);
            
            // de input voor de leeftijd
            var informationAge = document.createElement('div');
            informationAge.className="information";
            informationAge.setAttribute("id", groupName + age);
            var labelAge = document.createElement('label');
            labelAge.className="textLabel";
            var textAge = document.createTextNode("Leeftijd:");        
            labelAge.appendChild(textAge);
            var inputAge = document.createElement('input');
            inputAge.setAttribute("name", groupName + age);
            inputAge.setAttribute("placeholder", "Leeftijd");
            inputAge.setAttribute("type", "text");
            
            // de input voor de algemene opmerking
            var informationComment = document.createElement('div');
            informationComment.className="information";
            informationComment.setAttribute("id", groupName + comment);
            var labelComment = document.createElement('label');
            labelComment.className="textLabel";
            var textComment = document.createTextNode("Opmerking:");        
            labelComment.appendChild(textComment);
            var inputComment = document.createElement('textarea');
            inputComment.setAttribute("name", groupName + comment);
            inputComment.setAttribute("placeholder", "Opmerking");
            
            // de input voor het valideren
            var inputValidate = document.createElement('input');
            inputValidate.setAttribute("id", groupName + validate);
            inputValidate.setAttribute("name", groupName + validate);
            inputValidate.setAttribute("type", "hidden");
            inputValidate.setAttribute("value", "true");
            
            // de knop voor het verwijderen van een contact
            
            var removeButton = document.createElement('core-icon-button');
            removeButton.className="remove";
            removeButton.setAttribute("icon", "highlight-remove");
            removeButton.setAttribute("onclick", "removeInput(" + countBox + "," + "'" + groupName + "'" + ")");

            
            //het koppelen van alle tags aan elkaar
            // eerst de waarschuwing mocht de naam niet goed zijn ingevuld
            contact.appendChild(nameWarning);
            
            // de naam input met alle bijbehorende elementen
            informationName.appendChild(labelName);
            informationName.appendChild(inputName);
            informationName.appendChild(displayButton);
            contact.appendChild(informationName);
            
            // eerst de waarschuwing mocht de role niet goed zijn ingevuld
            contact.appendChild(roleWarning);
            
            // de rol input met alle bijbehorende elementen
            informationRole.appendChild(labelRole);
            informationRole.appendChild(inputRole);
            contact.appendChild(informationRole);
            
            // eerst de waarschuwing mocht de leeftijd niet goed zijn ingevuld
            contact.appendChild(ageWarning);

            // de leeftijd input met alle bijbehorende elementen
            informationAge.appendChild(labelAge);
            informationAge.appendChild(inputAge);
            contact.appendChild(informationAge);
            
            informationComment.appendChild(labelComment);
            informationComment.appendChild(inputComment);
            contact.appendChild(informationComment);
            
            contact.appendChild(inputValidate);
            
            contact.appendChild(removeButton);
            
            // het toevoegen aan de juiste div op het scherm
            document.getElementById(groupName).appendChild(contact);
		}

		function removeInput(contactNumber, groupName) {
			document.getElementById(groupName + contactNumber).style.display = 'none';
			document.getElementById(groupName + "validate" + contactNumber).value = "false";
		}
        
        function hideContact(contactNumber, groupname){
            document.getElementById(groupname + "role" + contactNumber).style.display = 'none';
            document.getElementById(groupname + "age" + contactNumber).style.display = 'none';
            document.getElementById(groupname + "comment" + contactNumber).style.display = 'none';
            document.getElementById(groupname + "button" + contactNumber).value = 'Weergeven';
            document.getElementById(groupname + "button" + contactNumber).setAttribute( "onclick", "showContact(" + contactNumber + "," + "'" + groupname + "'" + ")" );
        }
        
        function showContact(contactNumber, groupname){
            document.getElementById(groupname + "role" + contactNumber).style.display = 'block';
            document.getElementById(groupname + "age" + contactNumber).style.display = 'block';
            document.getElementById(groupname + "comment" + contactNumber).style.display = 'block';
            document.getElementById(groupname + "button" + contactNumber).value = 'Verbergen';
            document.getElementById(groupname + "button" + contactNumber).setAttribute( "onclick", "hideContact(" + contactNumber + "," + "'" + groupname + "'" + ")" );
        }