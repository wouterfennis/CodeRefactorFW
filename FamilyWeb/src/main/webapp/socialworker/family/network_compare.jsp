<!DOCTYPE html>
<html>
<head>
<title>Netwerken vergelijken</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-cable" content="yes">
<meta charset="utf-8">
<script
	src="/FamilyWeb/bower_components/webcomponentsjs/webcomponents.min.js"></script>
<link rel="import" href="/FamilyWeb/elements.html">
<link rel="import"
	href="/FamilyWeb/custom_elements/responsive-menu-socialworker.html">
<link rel="import" href="/FamilyWeb/custom_elements/options-menu.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-media-query/core-media-query.html">
<link rel="import"
	href="/FamilyWeb/bower_components/paper-tabs/paper-tabs.html">
<link rel="import"
	href="/FamilyWeb/bower_components/core-icon-button/core-icon-button.html">
<link rel="stylesheet" href="/FamilyWeb/styles/network_compare.css">
<link rel="import" href="/FamilyWeb/bower_components/paper-slider/paper-slider.html">

</head>
<body fullbleed layout vertical>
	<core-drawer-panel responsivewidth="1400px"> <core-header-panel
		drawer> <core-toolbar> <paper-icon-button class="toolbarButton" core-drawer-toggle icon="close"></paper-icon-button>
                <div class="menuTitle" flex>Menu</div>
	</core-toolbar> <responsive-menu-socialworker current="1"></responsive-menu-socialworker>
	</core-header-panel> <core-header-panel main> <core-toolbar
		class="medium-tall"> <core-icon-button
		core-drawer-toggle icon="menu"></core-icon-button>
	<div id="title" flex>Netwerken vergelijken</div>
	<options-menu></options-menu>
	<div class="bottom fit" horizontal layout>
		<paper-tabs id="scrollableTabs" selected="1" flex scrollable noink>

		<paper-tab> <a href="/FamilyWeb/socialworker/family/family_members_overview.jsp"
			horizontal center-center layout>Gezinsleden</a></paper-tab> <paper-tab>
		<a href="/FamilyWeb/socialworker/family/network_compare.jsp" horizontal center-center layout>Netwerken</a></paper-tab>
		<paper-tab> <a href="/FamilyWeb/socialworker/family/new_network_contacts.jsp" horizontal
			center-center layout>Nieuw Netwerk</a></paper-tab> <paper-tab> <a
			href="/FamilyWeb/socialworker/family/share_networks.jsp" horizontal center-center layout>Netwerk(en)
			delen</a></paper-tab> <paper-tab> <a href="/FamilyWeb/socialworker/family/transfer.jsp" horizontal
			center-center layout>Overdragen</a></paper-tab> </paper-tabs>
	</div>
	</core-toolbar>
	<div class="content" fit layout horizontal>
		<div class="network_container" id="container1">
            <div class="title">
            <div class="date" id="datumnetwork1"> Netwerk gemaakt op 21-02-2015</div>
            
            <form action="network_single_view.jsp" method="POST">
            	<input type="hidden" id="hidden1" name="selectedSlider" value="">
            	<input type="hidden" id="hidden2" name="selectedPerson" value="1">
            	<button class="displayButton" type="submit">Fullscreen</button>
<!--             	<core-icon-button class="fullscreen" icon="fullscreen" type ="submit"></core-icon-button> -->
			</form>
			</div>
			<div class="network" id = "network1">
			</div>
			<div class="sidebar">
			<div class="interviewee">
			<h1 class="title">Netwerken van:</h1>
				 <select class="selectInput" id = "network1SelectList" onChange = "changePerson(this)">
				</select> 
			</div>
			<div class="contact_groups" >
			<h1 class="title">Contactgroepen</h1>
			<ul id = "network1contact_groups">
			</ul>
			</div>
			</div>
			<div class="timeline">
            	<h1 class="title">Tijdlijn met gemaakte netwerken</h1>
                <input type = "text" id="rangedatumnetwork1">
                <div id = "network1range">
				<input class="slider" type="range" name="points" min="0" max="10">
			</div>
         </div>
			<div class="general_comment">
				<h1 class="title">Algemeen commentaar</h1>
				<p id = "commentnetwork1">Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting</p>
			</div>
		</div>
		<div class="network_container" id="container2">
			<div class="title">
            <div class="date" id="datumnetwork2"> Netwerk gemaakt op 21-02-2015</div>
           <form action="network_single_view.jsp" method="POST">
            	<input type="hidden" id="hidden5" name="selectedSlider" value=Network2Slidervalue>
            	<input type="hidden" id="hidden6" name="selectedPerson" value=Network2ChosenPerson>
            	<button class="displayButton" type="submit">Fullscreen</button>
<!--             	<core-icon-button class="fullscreen" icon="fullscreen" type ="submit"></core-icon-button> -->
			</form>
			</div>
			<div class="network" id = "network2">
			</div>
			<div class="sidebar">
			<div class="interviewee">
			<h1 class="title" >Netwerken van:</h1>
                 <select  class="selectInput" id = "network2SelectList" onChange = "changePerson(this)">
				</select> 
          
			</div>
			<div class="contact_groups">
			<p>Contactgroepen</p>
			<ul id = "network2contact_groups">
			<li>Family<input type="checkbox" value="test"></li>
			<li>Collega's<input type="checkbox" value="test"></li>
			<li>Justitie<input type="checkbox" value="test"></li>
			<li>Religie<input type="checkbox" value="test"></li>
			<li>Family<input type="checkbox" value="test"></li>
			<li>Justitie<input type="checkbox" value="test"></li>
			<li>Religie<input type="checkbox" value="test"></li>
			<li>Family<input type="checkbox" value="test"></li>
			</ul>
			</div>
			</div>
			<div class="timeline">
				<h1 class="title">Tijdlijn met gemaakte netwerken</h1>
                 <input type = "text" id="rangedatumnetwork2">
                <div id = "network2range">
				<input class="slider" type="range" name="points" min="0" max="10">
                </div>
			</div>
			<div class="general_comment">
				<h1 class="title">Algemeen commentaar</h1>
				<p id = "commentnetwork2">Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting</p>
			</div>
		</div>
		<div class="resolution_warning_picture"></div>
	</div>
	</core-header-panel> </core-drawer-panel>
	<core-media-query id="mediaQuery" query="max-width: 640px"></core-media-query>

	<script>
		document.querySelector('#mediaQuery').addEventListener(
				'core-media-change',
				function(e) {
					document.body.classList.toggle('core-narrow',
							e.detail.matches);
					document.querySelector('#scrollableTabs').updateBar();
				});
	</script>
<script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
    <script>
    // nodes is een array waarin elk uniek contact instaat. Er staat een naam bij, bij welke groep het contact hoort (vrienden, Buren etc)
    var nodesNetwork = ${nodesNetwork};

    // Hieronder staan de links dit zijn de verbinden tussen de notes. Elke verbinding heeft een "source", van welke node hij vandaan komt. En een 
    // "target", Waar hij naar toe gaat. Alle verbinden moeten naar 0 gaan dat is de eerste node die ook meteen de Client is.
    // Verder heb je distance wat de afstand tussen de twee betekend. De strength dat de hoeveelheid contact aangeeft. En het type wat het medium aangeeft 

    var linksNetwork = ${linksNetwork};
//    					
    var personArray = [];
    var netwerk1ChosenPerson = 0;
    var netwerk2ChosenPerson = 0;
    var netwerk1Slidervalue = 0;
    var netwerk2Slidervalue = 0;


    var thisPersonDates;
    var contactGroups = ["Client","Gezin", "Familie", "Vrienden","Collega's", "Buren", "Kennissen", "Onderwijs","Verenigingen", "Religie", "Zorginstellingen", "Jeugdzorg","Bureau HALT", "Justitie" ];
    var networkNameArray = ["network1","network2"];

    function createSelectListPersons(network) {
    	var select = document.getElementById(network + "SelectList"); 
    	console.log(network); 

    	for(var i = 0; i < nodesNetwork.allNetworks.length; i++) {
    		console.dir(nodesNetwork.allNetworks)
    		allPersons = nodesNetwork.allNetworks[i];
    		console.log(allPersons);
    		for ( var person in allPersons ){
      			var option = document.createElement("option");
    			option.textContent = person;
    			option.value = i;
    			console.log(option.value);
    			select.appendChild(option);
      		}
    	}
    }

    function changePerson(selectList){
    	var n = selectList.id;
    	console.log(n);
    	var split = n.split('SelectList');
    	var selectedNetwork = split[0];
    	console.log(selectedNetwork);
    		if (selectedNetwork == "network1") {
    			netwerk1ChosenPerson = selectList.value;
    			d3.select("#"+selectedNetwork).select('svg').remove();
    			netwerk1Slidervalue = 0;
    			draw_graphs(selectedNetwork,netwerk1ChosenPerson,netwerk1Slidervalue);
    		}
    		else if (selectedNetwork == "network2") {
    			netwerk2ChosenPerson = selectList.value;
    			d3.select("#"+selectedNetwork).select('svg').remove();
    			netwerk2Slidervalue = 0;
    			console.log(selectedNetwork,netwerk2ChosenPerson,netwerk2Slidervalue);
    			draw_graphs(selectedNetwork,netwerk2ChosenPerson,netwerk2Slidervalue);
    		}
    }

    function onChangeSlider(slider,network) {
    	
    	console.log(slider.id);
    	console.log(slider.value);
    	console.log("slider max = " + slider.max);
    	console.log(network);
    	console.log(document.getElementById(network + "contact_groups"));
    	if (network == "network1") {
    			netwerk1Slidervalue = slider.value;
    			d3.select("#"+network).select('svg').remove();
    			console.log(network,netwerk1ChosenPerson,netwerk1Slidervalue);
    			draw_graphs(network,netwerk1ChosenPerson,netwerk1Slidervalue);
    		}
    	else if (network == "network2") {
    			netwerk2Slidervalue = slider.value;
    			d3.select("#"+network).select('svg').remove();
    			console.log(network,netwerk2ChosenPerson,netwerk2Slidervalue);
    			draw_graphs(network,netwerk2ChosenPerson,netwerk2Slidervalue);
    		}
      }

    console.dir(nodesNetwork.allNetworks);
     
    for ( i = 0; i < networkNameArray.length; i++ ){
      var network = networkNameArray[i];
      console.log(networkNameArray[i].toString());
      createSelectListPersons(network);
      draw_graphs(network, netwerk1ChosenPerson, netwerk1Slidervalue);
    }

    function draw_graphs(networkName, chosenPerson, sliderval){ 
      console.log("doorgestuurde netwerkname: " + networkName);
      console.log("sliderval" + sliderval);
      console.log(chosenPerson);
      var i, selectedPerson, thisDatesNodes, thisPersonLinks, allNetworksDatesThisPerson, selectedNetworkDatesLinks, allNetworksLinksThisPerson, selectedNetworkNodes, selectedNetworkLinks;
       //Selecteer juist netwerkNodes
      for ( i = 0; i < nodesNetwork.allNetworks.length; i++ ) {
    	  selectedPerson = nodesNetwork.allNetworks[chosenPerson];
    	  console.dir(selectedPerson);
      }
      for ( var person in selectedPerson ){
      	 thisPersonDates = selectedPerson[person];
    	 console.dir(thisPersonDates);
      }
      for (i = 0; i < thisPersonDates.length; i++ )
    	{
    	  console.dir(thisPersonDates);
    	  selectedNetworkNodes = thisPersonDates[sliderval].nodes;
    	  console.dir(selectedNetworkNodes);
    	}

    	//Selecteer juist netwerkLinks
      for ( i = 0; i < linksNetwork.allNetworks.length; i++ ) {
    	  thisPersonLinks = linksNetwork.allNetworks[chosenPerson];
    	  console.dir(thisPersonLinks);
    	}
      for (var person in thisPersonLinks)
    	{
    	  allNetworksLinksThisPerson = thisPersonLinks[person];
    	  console.dir(allNetworksLinksThisPerson);
    	}
      for ( i = 0; i < allNetworksLinksThisPerson.length; i++ ) {
    	  selectedNetworkDatesLinks = allNetworksLinksThisPerson[sliderval];
    	  console.dir(selectedNetworkDatesLinks);
    	}
      for ( var dates in selectedNetworkDatesLinks ) {
    	   selectedNetworkLinks = selectedNetworkDatesLinks[dates];
    	   console.dir(selectedNetworkLinks);
    	}
      console.dir(selectedNetworkLinks);	
    	
      var width = 450,
    	  height = 350;
      
      var svg = d3.select("#"+networkName).append('svg')
    	  .attr('width', width)
    	  .attr('height', height);
    	  
      
      // Hier wordt de svg container gedefineerd dit is als het ware het canvas om het netwerk op te tekenen.
      // Wij gebruiken het type Force layout. Dit is het type layout waarmee je nodesNetwork kan verbinden met elkaar we geven hier de size aan (zelfde als de svg)
      // we geven alle nodes mee
      // we geven alle links mee
      // we geven de charge mee die aangeeft hoeveel afstand elke node van elkaar moeten afhouden
      // we geven de linkDistance mee waarmee we meteen de eerste berekening tegenkomen waar de fysieke afstand tussen contact personen in beeld wordt gebracht
      console.dir(selectedNetworkNodes);
      console.log("ben hier");
      var force = d3.layout.force()
    	  .size([width, height])
    	  .nodes(selectedNetworkNodes)
    	  .links(selectedNetworkLinks)
    	  .charge(-100)
    	  .linkDistance( function(selectedNetworkLinks) {return 200 - selectedNetworkLinks.distance*30})
    	  .on('tick', tick)
    	  .start();
      
      // Hier worden de linkjes hun eigenschappen meegegeven
      // We geven aan hoe dik ze moeten zijn dmv Strength wat aangeeft hoevaak er contact is. Hoe dikker hoe meer
      // We geven aan welke kleur ze moeten zijn dmv type wat het meest gebruikte medium voorstelt. Elke kleur eigen medium
      
      var colors = d3.scale.category20().domain(d3.range(0,20));
      
      var link = svg.selectAll('.link')
    	  .data(selectedNetworkLinks)
    	  .enter().append('line')
    	  .attr('class', 'link')
    	  .style("stroke-width", function(d) { return (d.strength); })
    	  .style("stroke", function(d) { return colors(d.group); });
      // Hier worden de eigenschappen van de nodes meegeven
      // Elke node krijgt een kleur op basis van tot welke groep hij behoort
      // verder worden ze nog in cirkels getekend
      var nodePercentage =1/selectedNetworkLinks.length;
      var nodeSizeVariable = 50*nodePercentage;
      var sizeNode = 2 + nodeSizeVariable;
      
      var node = svg.selectAll('.node')
    	  .data(selectedNetworkNodes)
    	  .enter().append('g')
    	  .attr('class', 'node')
    	  .style("fill", function(d) {  if ( d.group != 0 ) {
    		   return colors(d.group);
    		   }
    		  else {
    			  return "#adff2f";
    		  }
    	  })
    								  
    	  .on("mouseover", mouseover)
    	  .on("mouseout", mouseout);
    	  
      node.append("circle")
    	  .attr("r", sizeNode);
      
      node.append("text")
    	  .attr("x", 18)
    	  .attr("dy", ".35em")
    	  .text(function(d) { return d.name; })
    	  .style("fill","black")
    	  .style("display","none");
    	  
      
      // Dit geeft aan of er een animatie in zit of dat eerst alles berekend moet worden voordat het netwerk in beeld komt.
    	  
      //Unieke checkboxes maken
      createCheckboxes();
      var distinct;
      function createCheckboxes(){
    	var unique = {};
    	distinct = [];
    	  for( var i in selectedNetworkNodes ){
    		 if( typeof(unique[selectedNetworkNodes[i].group]) == "undefined"){
    		  distinct.push(selectedNetworkNodes[i].group);
    		  }
    		 unique[selectedNetworkNodes[i].group] = 0;
    	   }
    	console.dir(distinct);
    	var ul = document.createElement("ul");
    	var checkboxlist=document.createElement("LI");
    	for ( i = 1; i < distinct.length; i++ ) {
    		
    		var label= document.createElement("label");
    		var description = document.createTextNode(contactGroups[distinct[i]]);
    		var checkbox = document.createElement("input");
    		var br = document.createElement("br");
    		
    		checkbox.type = "checkbox";    // make the element a checkbox
    		checkbox.name = "slct[]";      // give it a name we can check on the server side
    		checkbox.value = distinct[i].valueOf();         // make its value "pair"
    		checkbox.onclick = function () {
    				onChangeCheckbox (this);
    			};
    		label.appendChild(checkbox);   // add the box to the element
    		label.appendChild(description);// add the description to the element
    		checkboxlist.appendChild(label);
    		checkboxlist.appendChild(br);
    	}
    	console.log(networkName);
    	 document.getElementById(networkName + "contact_groups").innerHTML = "";
    	 document.getElementById(networkName + "contact_groups").appendChild(checkboxlist);
      }
      
     // createDate
     		console.log(networkName);
    	  document.getElementById("rangedatum" + networkName).value = thisPersonDates[sliderval].datum;
    	  document.getElementById("datum" + networkName).innerHTML = "Netwerk gemaakt op " + thisPersonDates[sliderval].datum;
      
      //createCommentaar
      	document.getElementById("comment" + networkName).innerHTML = thisPersonDates[sliderval].commentaar;

      
      createSliders();
      function createSliders() {
    	 console.log("sliderval" + sliderval);
    	var slider = document.createElement("paper-slider");
//    	slider.type = "range";
    	slider.id = networkName + "range";
    	slider.description = function(d) { return d.datum };
    	console.log("meegegeven Max" + thisPersonDates.length)
    	slider.max = thisPersonDates.length - 1;
    	slider.min = 0;
    	slider.value = sliderval;
    	slider.step = "1";
    	slider.snaps = true;
    	slider.pin = true;
    	console.log(networkName);
    	slider.onchange = function () { 
    	  console.log("Networkname: " + networkName);
    	  slidervalue = slider.value;
    	  console.log(slidervalue);
    	  onChangeSlider(slider,networkName);
    	}
    				   
    	console.log(networkName);
    	console.log(document.getElementById(networkName + "range"));
    	document.getElementById(networkName + "range").innerHTML = "";
    	document.getElementById(networkName + "range").appendChild(slider);
    	console.log(document.getElementById(networkName + "range"));
    	console.log(network);
      }
    	  
      var middleX = width / 2;
      var middleY = height / 2; 
      var locations = [{x: middleX, y: middleY}];
      
      var diameterCircle = height - 50;
      var ray = diameterCircle / 2;
      
      createLocations();
      function createLocations(){
    	  for ( i = 1; i < distinct.length; i++ ) {
    		  var xvalue = middleX + ray * Math.cos(2 * Math.PI * i / (distinct.length - 1)); 
    		  var yvalue = middleY + ray * Math.sin(2 * Math.PI * i / (distinct.length - 1)); 
    		  console.log(xvalue);
    		  console.log(yvalue);
    		  locations.push({x: xvalue, y: yvalue});
    	  }
      }
      
      console.dir(locations);
      
      var groupFill = function(d, i) { return colors(i); };
      
      function tick(e) {
    	link
    		.attr("x1", function(d) { return d.source.x; })
    		.attr("y1", function(d) { return d.source.y; })
    		.attr("x2", function(d) { return d.target.x; })
    		.attr("y2", function(d) { return d.target.y; });
      
    	  var k = 0.07 * e.alpha;
      
    		// Push nodes toward their designated focus.
    		selectedNetworkNodes.forEach(function(o, i) {
    		  o.y += (locations[distinct.indexOf(o.group)].y - o.y) * k;
    		  o.x += (locations[distinct.indexOf(o.group)].x - o.x) * k;
    		});
    		
    		node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
      }
      
      // grappige overgang om met je muis aan te wijzen. 
      // de cirkel die je aanwijst wordt een beetje groter en weer kleiner als je met je muis er weer vanaf gaat.
      
      // Ook wordt hier geprobeerd om de tekst van de node te laten zien zodra je er met je muis boven hangt. Het probleem is alleen dat de naam van de Client alleen getoont wordt bij elke node waarboven je boven hangt.
      // Via d3.select(this).select("item of een div waar je muis boven moet staan.").transition() Kan je een element selecteren en hem vloeiend laten veranderen in iets anders
      // Via d3.select(this).select("item of een div waar je muis boven moet staan.").style() Kan je een element selecteren en de css die daar aanvast zit veranderen.
      function mouseover() {
    	d3.select(this).select("circle").transition()
    		.duration(300)
    		.attr("r", sizeNode*2)
    	d3.select(this).select("text").style("display","inline");
      }
      
      function mouseout() {
    	d3.select(this).select("circle").transition()
    		.duration(300)
    		.attr("r", sizeNode)
    	d3.select(this).select("text").style("display","none");
      }
      
      //Als er een checkbox ingedrukt wordt dan zal deze het statement selectgroup of unselectgroup activeren.
      var selectedNodes = [];
      var selectedLinks = [];
      function onChangeCheckbox (checkbox) {
    		d3.select("#" + networkName).selectAll('.node').filter(function(d,i) {
    		return ( d.group != 0 );
    	  })
    				  .transition()
    				  .style("opacity", 0.2);
    			   d3.select("#" + networkName).selectAll('.link')
    				  .transition()
    				  .style("opacity", 0.2);
    			  
    			  if (checkbox.checked) {
    				  selectGroupNode(checkbox.value);
    				  selectGroupLink(checkbox.value);
    			  }
    			  else {
    				  unselectGroupNode(checkbox.value);
    				  unselectGroupLink(checkbox.value);
    			  }
    			  
    			   d3.select("#" + networkName).selectAll('.node')
    				  .each(function(d) {
    				  for ( i = 0; i < selectedNodes.length; i++ ) {
    					  if ( d.group == selectedNodes[i]) {
    						  d3.select(this)
    						  .transition()
    						  .style("opacity", 1);
    						  console.log("selected Node: " + selectedNodes[i]);
    					  }
    				  }
    			  })
    			   d3.select("#" + networkName).selectAll('.link')
    			  .each(function(d) {
    				  for ( i = 0; i < selectedLinks.length; i++ ) {
    					  if ( d.group == selectedLinks[i] ) {
    						  d3.select(this)
    						  .transition()
    						  .style("opacity", 1);
    						  console.log("selected Link: " + selectedLinks[i]);
    					  }
    				  }
    			  })
    		  }
      //Selectgroup selecteert de groep op basis van een loop door alle nodes heen waarin het groepnummer wat bij het meegegeven is in de checkbox vergeleken met het groepnummer
      function selectGroupNode(groepnummer) {
    	  selectedNodes.push(groepnummer);
      }
      
      function selectGroupLink(groepnummer) {
    	  selectedLinks.push(groepnummer);
      }
      
      //unselectgroup selecteert de groep op basis van een loop door alle nodes heen waarin het groepnummer wat bij het meegegeven is in de checkbox vergeleken met het groepnummer
      function unselectGroupNode(groepnummer) {
    	  var index = selectedNodes.indexOf(groepnummer);
    		  if ( index > -1 ) {
    			  selectedNodes.splice(index,1);
    		  }
    	  console.log("unselected Node: " + groepnummer);
      }
      
      function unselectGroupLink(groepnummer) {
    	  var index = selectedLinks.indexOf(groepnummer);
    		  if ( index > -1 ) {
    			  selectedLinks.splice(index,1);
    		  }
    	  console.log("unselected Link: " + groepnummer);
      }
      if (networkName == "network1") {
    	  document.getElementById("hidden1").value = sliderval;
    	  document.getElementById("hidden2").value = chosenPerson;
      }
      else if (networkName == "network2") {
    	  document.getElementById("hidden5").value = sliderval;
    	  document.getElementById("hidden6").value = chosenPerson;
      }
    }

    </script>
    </body>

    </html>