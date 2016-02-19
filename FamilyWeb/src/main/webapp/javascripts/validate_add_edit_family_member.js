function validateForm() {

    var isGood = true;
    var forename = document.forms["add_edit_family_member_form"]["forename"].value;
    var surname = document.forms["add_edit_family_member_form"]["surname"].value;
    var dateofbirth = document.forms["add_edit_family_member_form"]["dateofbirth"].value;
    var nationality = document.forms["add_edit_family_member_form"]["nationality"].value;
    var street = document.forms["add_edit_family_member_form"]["street"].value;
    var streetnumber = document.forms["add_edit_family_member_form"]["streetnumber"].value;
    var postcode = document.forms["add_edit_family_member_form"]["postcode"].value;
    var city = document.forms["add_edit_family_member_form"]["city"].value;
    var phonenumber = document.forms["add_edit_family_member_form"]["phonenumber"].value;
    var mobile = document.forms["add_edit_family_member_form"]["mobile"].value;
    var email = document.forms["add_edit_family_member_form"]["email"].value;

    // Standar REGEX patterns
    var digitsReg = /\d/g;
    var nonDigitsReg = /\D/g;
    // Custom REGEX Patterns
    var dateReg = /^\d{2}[./-]\d{2}[./-]\d{4}$/;
    var postcodeReg = /^\d{4}[-/ ]\D{2}$/;
    var emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    forename = forenamename.trim();
    if (forename == null || forename == "" || digitsReg.test(forename)) {
        document.getElementById("forenameWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("forenameWarning").className = "true";
    }

    surname = surname.trim();
    if (surname == null || surname == "" || digitsReg.test(surname)) {
        document.getElementById("surnameWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("surnameWarning").className = "true";
    }
    
    // the field below may be empty but if they are filled they must be correct
    dateofbirth = dateofbirth.trim();
    if (!(dateofbirth == null || dateofbirth == "")){
    	if(dateReg.test(dateofbirth)){
    		document.getElementById("dateofbirthWarning").className = "true";
    	} else {
            document.getElementById("dateofbirthWarning").className = "false";
            isGood = false;
    	}
    }
    
    nationality = nationality.trim();
    if (!(nationality == null || nationality == "")){
    	if(digitsReg.test(nationality)){
    		document.getElementById("nationalityWarning").className = "true";
    	} else {
            document.getElementById("nationalityWarning").className = "false";
            isGood = false;
    	}
    }
    
    street = street.trim();
    if (!(street == null || street == "")){
    	if(digitsReg.test(street)){
    		document.getElementById("streetWarning").className = "true";
    	} else {
            document.getElementById("streetWarning").className = "false";
            isGood = false;
    	}
    }

    streetnumber = streetnumber.trim();
    if (!(streetnumber == null || streetnumber == "")){
    	if(nonDigitsReg.test(streetnumber)){
    		document.getElementById("streetnumberWarning").className = "true";
    	} else {
            document.getElementById("streetnumberWarning").className = "false";
            isGood = false;
    	}
    }


    postcode = postcode.trim();
    if (!(postcode == null || postcode == "")){
    	if(postcodeReg.test(postcode)){
    		document.getElementById("postcodeWarning").className = "true";
    	} else {
            document.getElementById("postcodeWarning").className = "false";
            isGood = false;
    	}
    }

    city = city.trim();
    if (!(city == null || city == "")){
    	if(digitsReg.test(city)){
    		document.getElementById("cityWarning").className = "true";
    	} else {
            document.getElementById("cityWarning").className = "false";
            isGood = false;
    	}
    }

    phonenumber = phonenumber.trim();
    if (!(phonenumber == null || phonenumber == "")){
    	if(nonDigitsReg.test(phonenumber) || !(phonenumber.length > 15)){
    		document.getElementById("phonenumberWarning").className = "true";
    	} else {
            document.getElementById("phonenumberWarning").className = "false";
            isGood = false;
    	}
    }

    mobile = mobile.trim();
    if (!(mobile == null || mobile == "")){
    	if(nonDigitsReg.test(mobile) || !(mobile.length > 15)){
    		document.getElementById("mobileWarning").className = "true";
    	} else {
            document.getElementById("mobileWarning").className = "false";
            isGood = false;
    	}
    }

    email = email.trim();
    if (!(email == null || email == "")){
    	if(emailReg.test(email)){
    		document.getElementById("emailWarning").className = "true";
    	} else {
            document.getElementById("emailWarning").className = "false";
            isGood = false;
    	}
    }

    return isGood;
}
