function validateForm() {

    var isGood = true;
    var employeeid = document.forms["add_edit_employee_form"]["employeeid"].value;
    var forename = document.forms["add_edit_employee_form"]["forename"].value;
    var surname = document.forms["add_edit_employee_form"]["surname"].value;
    var dateofbirth = document.forms["add_edit_employee_form"]["dateofbirth"].value;
    var nationality = document.forms["add_edit_employee_form"]["nationality"].value;
    var street = document.forms["add_edit_employee_form"]["street"].value;
    var streetnumber = document.forms["add_edit_employee_form"]["streetnumber"].value;
    var postcode = document.forms["add_edit_employee_form"]["postcode"].value;
    var city = document.forms["add_edit_employee_form"]["city"].value;
    var phonenumber = document.forms["add_edit_employee_form"]["phonenumber"].value;
    var mobile = document.forms["add_edit_employee_form"]["mobile"].value;
    var email = document.forms["add_edit_employee_form"]["email"].value;
    var email_confirmation = document.forms["add_edit_employee_form"]["email_confirmation"].value;

    // Standar REGEX patterns
    var digitsReg = /\d/g ;
    var nonDigitsReg = /\D/g ;
    // Custom REGEX Patterns
    var dateReg = /^\d{2}[./-]\d{2}[./-]\d{4}$/ ;
    var postcodeReg = /^\d{4}[-/ ]\D{2}$/       ;
    var emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;


    if (employeeid == null || employeeid == "" || nonDigitsReg.test(employeeid)) {
        document.getElementById("employeeidWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("employeeidWarning").className = "true";
    }

    if (forename == null || forename == "" || digitsReg.test(forename)) {
        document.getElementById("forenameWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("forenameWarning").className = "true";
    }

    if (surname == null || surname == "" || digitsReg.test(surname)) {
        document.getElementById("surnameWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("surnameWarning").className = "true";
    }
    
    console.log("Date reg: " + dateReg.test(dateofbirth));

    if (dateofbirth == null || dateofbirth == "" || dateReg.test(dateofbirth)) {
        document.getElementById("dateofbirthWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("dateofbirthWarning").className = "true";
    }

    if (nationality == null || nationality == "" || digitsReg.test(nationality)) {
        document.getElementById("nationalityWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("nationalityWarning").className = "true";
    }
    
    if (street == null || street == "" || DigitsReg.test(street)) {
        document.getElementById("streetWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("streetWarning").className = "true";
    }

    if (streetnumber == null || streetnumber == "" || nonDigitsReg.test(streetnumber)) {
        document.getElementById("streetnumberWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("streetnumberWarning").className = "true";
    }

    if (postcode == null || postcode == "" || postcodeReg.test(postcode)) {
        document.getElementById("postcodeWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("postcodeWarning").className = "true";
    }

    if (city == null || city == "" || digitsReg.test(city)) {
        document.getElementById("cityWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("cityWarning").className = "true";
    }

    if (phonenumber == null || phonenumber == "" || nonDigitsReg.test(phonenumber) || phonenumber.length > 15) {
        document.getElementById("phonenumberWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("phonenumberWarning").className = "true";
    }
    //
    if (mobile == null || mobile == "" || nonDigitsReg.test(mobile) || mobile.length > 15) {
        document.getElementById("mobileWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("mobileWarning").className = "true";
    }
    
    if (email == null || email == "" || email != email_confirmation || !emailReg.test(email)) {
        document.getElementById("emailWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("emailWarning").className = "true";
    }
    
    if (email_confirmation == null || email_confirmation == "" || email_confirmation != email || !emailReg.test(email_confirmation)) {
        document.getElementById("email_confirmationWarning").className = "false";
        isGood = false;
    } else {
        document.getElementById("email_confirmationWarning").className = "true";
    }
    return isGood;
}
