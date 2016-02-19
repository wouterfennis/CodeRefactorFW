function validateForm() {

    var isGood = true;

    // Standard REGEX patterns
    // check if letters are present
    var digitsReg = /\d/g;
    
    // check if numbers are present
    var nonDigitsReg = /\D/g;
    
    // array with all the possible groups
    var groups = ["household", "family", "friends", "colleagues", "neighbours", "acquaintance", "education", "club", "religion", "careinstitution", "youthcare", "bureauhalt", "justice"];
    
    for(i = 0; i < groups.length; i++){
        var counter = document.forms["group_form"]["counter" + groups[i]].value;
        console.log("counter: " + counter);
        if(counter > 0){
            // gaan we verder met valideren
            // tweede for loop om door alle contacten van die groep te doorlopen
            for(x = 1; x <= counter; x++){
                // eerst gaan we kijken of het contact nog wel gebruikt gaat worden (en dus niet verwijderd is)
                var validatequery = groups[i] + "validate" + x;
            	var validate = document.forms["group_form"][groups[i] + "validate" + x].value;
                if(validate == "true"){
                    // ophalen van de velden van één contact
                    var name = document.forms["group_form"][groups[i] + "name" + x ].value;
                    var role = document.forms["group_form"][groups[i] + "role" + x].value;
                    var age = document.forms["group_form"][groups[i] + "age" + x].value;

                    // validatie voor de naam
                    name = name.trim();
                    if (name == null || name == "" || digitsReg.test(name)) {
                    	var test = groups[i] + "name" + "warning" + x;
                        document.getElementById(groups[i] + "warningname" + x).className = "false";
                        isGood = false;
                    } else {
                        document.getElementById(groups[i] + "warningname" + x).className = "true";
                    } 
                    
                    // validatie voor de rol
                    role = role.trim();
                    if (role == null || role == "" || digitsReg.test(role)) {
                        document.getElementById(groups[i] + "warningrole" + x).className = "false";
                        isGood = false;
                    } else {
                        document.getElementById(groups[i] + "warningrole" + x).className = "true";
                    }
                    
                    // validatie voor de leeftijd
                    age = age.trim();
                    if (age == null || age == "" || nonDigitsReg.test(age)) {
                    	if(!(parseInt(age) <= 0)){
                        document.getElementById(groups[i] + "warningage" + x).className = "false";
                        isGood = false;
                    	}
                    } else {
                        document.getElementById(groups[i] + "warningage" + x).className = "true";
                    }
                }
            }
        }
    }
    return isGood;
}
