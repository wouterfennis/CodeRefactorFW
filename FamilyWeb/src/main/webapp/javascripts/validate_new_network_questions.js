function validateForm() {
    var isGood = true;
    
    var totalContacts = document.forms["questions_form"]["totalContacts"].value;
    console.log("Totaal aantal contacten : " + totalContacts);
    totalContacts = parseInt(totalContacts) + 1;
    console.log("Totaal aantal contacten na verhogen : " + totalContacts);
    var totalQuestions = document.forms["questions_form"]["totalQuestions"].value;
    console.log("Totaal aantal vragen : " + totalQuestions);
    totalQuestions = parseInt(totalQuestions) + 1;
    console.log("Totaal aantal vragen na verhogen : " + totalQuestions);
    for(i = 1; i < totalContacts; i++){
    	console.log("We gaan de vragen van contact : " + i + " valideren");
    	for(x = 1; x < totalQuestions; x++){
    		console.log("We gaan vraag : " + x + "valideren");
    		var question = document.forms["questions_form"][x + ":" + i ].value;
    		console.log("Het antwoord van vraag: " + x + "is : " + question);
    		console.log(question);
          if (question == null || question == "") {
        	console.log("Vraag :" + x + "van contact" + x + "is fout");
            document.getElementById(x + ":" + i + ":warning").className = "false";
            isGood = false;
          } else {
        	console.log("Vraag :  " + x + "van contact" + x + "is goed");
            document.getElementById(x + ":" + i + ":warning").className = "true";
          } 
    	}
    }
    return isGood;
}
