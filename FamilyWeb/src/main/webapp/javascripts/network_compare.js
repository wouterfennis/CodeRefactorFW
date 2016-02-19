 // nodes is een array waarin elk uniek contact instaat. Er staat een naam bij, bij welke groep het contact hoort (vrienden, Buren etc)
    var nodesNetwork = ${nodesNetwork};
    //{"allNetworks" : [ 
                            
//    						{"person1" :
//    										[
//    											{"datum": "13-05-2015", "commentaar": "Commentaar persoon 1 13-05-2015", "nodes": 
//    												[	
//    												   {"name":"De Client","group":0, "commentaar": ""},
//    	//											   {"name":"Harry","group":1, "commentaar": ""},
//    	//											   {"name":"Hans","group":1, "commentaar": ""},
//    												   {"name":"John","group":2, "commentaar": ""},
//    												   {"name":"Wouter","group":2, "commentaar": ""},
//    												   {"name":"Billy","group":2, "commentaar": ""},
//    												   {"name":"Bert","group":3, "commentaar": ""},
//    												   {"name":"Sjaak","group":3, "commentaar": ""},
//    												   {"name":"Klaas","group":3, "commentaar": ""}
//    												]
//    											}
//    										]
//    									}
//    								]
//    							}
//     							{"allNetworks":
//    								[
//    								  {"Sjaak Haak":
//    									[
//    									  {"nodes":
//    												[
//    													{"name":"Sjaak Haak","group":0},
//    													{"name":"bert","group":1},
//    													{"name":"Hans","group":2},
//    													{"name":"Burjongen","group":3}
//    												],
//    												"datum":"2015-06-26","commentaar":"Dit netwerk is een test"
//    											  }
//    										  ]
//    									  }
//    								  ]
//    							  };

     
    			/*{"allNetworks" : [ 
    						{"person1" :
    										[
    											{"datum": "13-05-2015", "commentaar": "Commentaar persoon 1 13-05-2015", "nodes": 
    												[	
    												   {"name":"De Client","group":0, "commentaar": ""},
    	//											   {"name":"Harry","group":1, "commentaar": ""},
    	//											   {"name":"Hans","group":1, "commentaar": ""},
    												   {"name":"John","group":2, "commentaar": ""},
    												   {"name":"Wouter","group":2, "commentaar": ""},
    												   {"name":"Billy","group":2, "commentaar": ""},
    												   {"name":"Bert","group":3, "commentaar": ""},
    												   {"name":"Sjaak","group":3, "commentaar": ""},
    												   {"name":"Klaas","group":3, "commentaar": ""}
    												]
    											},
    											{"datum": "14-05-2015", "commentaar": "commentaar persoon 1 14-05-2015", "nodes":
    												[ 
    												   {"name":"De Client","group":0, "commentaar": ""},
    												   {"name":"Harry","group":1, "commentaar": ""},
    												   {"name":"Hans","group":1, "commentaar": ""},
    												   {"name":"Wouter","group":2, "commentaar": ""},
    												   {"name":"Billy","group":2, "commentaar": ""},
    												   {"name":"Bert","group":3, "commentaar": ""},
    												   {"name":"Sjaak","group":3, "commentaar": ""},
    												   {"name":"Klaas","group":3, "commentaar": ""}
    												]
    											},
    											{"datum": "15-05-2015", "commentaar": "commentaar persoon 1 15-05-2015", "nodes":
    												[ 
    												   {"name":"De Client","group":0, "commentaar": ""},
    												   {"name":"Harry","group":1, "commentaar": ""},
    												   {"name":"Hans","group":1, "commentaar": ""},
    												   {"name":"Wouter","group":2, "commentaar": ""},
    												   {"name":"Billy","group":2, "commentaar": ""},
    												   {"name":"Bert","group":3, "commentaar": ""},
    												   {"name":"Sjaak","group":3, "commentaar": ""},
    												   {"name":"Klaas","group":3, "commentaar": ""},
    												   {"name":"Bert","group":4, "commentaar": ""},
    												]
    											}
    									    ]
    									  },
    						{"person2" :
    									  [
    									    {"datum": "17-05-2015", "commentaar": "commentaar persoon 2 17-05-2015", "nodes":
    										  [
        										  {"name":"De Client","group":0, "commentaar": ""},
        										  {"name":"Truus","group":1, "commentaar": ""},
        										  {"name":"Buurmeisje","group":1, "commentaar": ""},
        										  {"name":"John","group":2, "commentaar": ""},
        										  {"name":"Henkie","group":2, "commentaar": ""},
        										  {"name":"Sjaak","group":3, "commentaar": ""},
        										  {"name":"Eline","group":4, "commentaar": ""}
      										  ]
    									    },
    									    {"datum": "19-05-2015", "commentaar": "commentaar persoon 2 17-05-2015", "nodes":
    											  [
    	    										  {"name":"De Client","group":0, "commentaar": ""},
    	    										  {"name":"Truus","group":1, "commentaar": ""},
    	    										  {"name":"Buurmeisje","group":1, "commentaar": ""},
    	    										  {"name":"John","group":2, "commentaar": ""},
    	    										  {"name":"Henkie","group":2, "commentaar": ""},
    	    										  {"name":"Sjaak","group":3, "commentaar": ""},
    	    										  {"name":"Eline","group":4, "commentaar": ""}
    	  										  ]
    										 }
    									  ]
    									}
    								]
    							}*/

    			
    console.dir(nodesNetwork);

    // Hieronder staan de links dit zijn de verbinden tussen de notes. Elke verbinding heeft een "source", van welke node hij vandaan komt. En een 
    // "target", Waar hij naar toe gaat. Alle verbinden moeten naar 0 gaan dat is de eerste node die ook meteen de Client is.
    // Verder heb je distance wat de afstand tussen de twee betekend. De strength dat de hoeveelheid contact aangeeft. En het type wat het medium aangeeft 

    var linksNetwork = ${linksNetwork};
//    					{"allNetworks":
//    						[
//    							{"Sjaak Haak":
//    								[
//    						 		 {"2015-06-26":
//    								 	[
//    									  {"distance":5,"source":1,"strength":5,"target":0,"group":1,"type":4},
//    									  {"distance":2,"source":2,"strength":2,"target":0,"group":2,"type":1},
//    									  {"distance":5,"source":3,"strength":4,"target":0,"group":3,"type":3}
//    									]
//    								  }
//    								]
//    							  }
//    							]
//    						  };
//    					{"allNetworks" : [
//    						{ "person1":
//    							[
//    								{"13-05-2015":
//    								  [
////    								  { "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
////    								  { "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
//    								  { "source": 1, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
//    								  { "source": 2, "target": 0, "distance": 4, "strength": 2, "type": 4, "group":2},
//    								  { "source": 3, "target": 0, "distance": 5, "strength": 4, "type": 5, "group":2},
//    								  { "source": 4, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
//    								  { "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
//    								  { "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3}
//    								  ]
//    								}
//    							]
//    						}
//    					]
//    				}
    				/*{"allNetworks" : [
    						{ "person1":
    							[
    								{"13-05-2015":
    								  [
//    								  { "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
//    								  { "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
    								  { "source": 1, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
    								  { "source": 2, "target": 0, "distance": 4, "strength": 2, "type": 4, "group":2},
    								  { "source": 3, "target": 0, "distance": 5, "strength": 4, "type": 5, "group":2},
    								  { "source": 4, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3}
    								  ]
    								},
    								{"14-05-2015":
    								  [
    								  { "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
    								  { "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
    								  { "source": 3, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
    								  { "source": 4, "target": 0, "distance": 4, "strength": 2, "type": 4, "group":2},
    								  { "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 7, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3}
    								  ]
    								},
    								{"15-05-2015":
    								  [
    								  { "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
    								  { "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
    								  { "source": 3, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
    								  { "source": 4, "target": 0, "distance": 4, "strength": 2, "type": 4, "group":2},
    								  { "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 7, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    								  { "source": 8, "target": 0, "distance": 3, "strength": 4, "type": 3, "group":4}
    								  ]
    								},
    							]
    						},
    						{ "person2":
    							[
    								{"17-05-2015":
    								  [
    									{ "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
    									{ "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
    									{ "source": 3, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
    									{ "source": 4, "target": 0, "distance": 5, "strength": 4, "type": 5, "group":2},
    									{ "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    									{ "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":4}
    								  ]
    								},
    								{"19-05-2015":
    									  [
    										{ "source": 1, "target": 0, "distance": 1, "strength": 5, "type": 1, "group":1},
    										{ "source": 2, "target": 0, "distance": 2, "strength": 1, "type": 2, "group":1},
    										{ "source": 3, "target": 0, "distance": 3, "strength": 3, "type": 3, "group":2},
    										{ "source": 4, "target": 0, "distance": 5, "strength": 4, "type": 5, "group":2},
    										{ "source": 5, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":3},
    										{ "source": 6, "target": 0, "distance": 2, "strength": 2, "type": 4, "group":4}
    									  ]
    									}
    							]
    						}
    					]
    				}*/
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

    /*function createSliders(network) {
      var slider = document.createElement("input");
      slider.type = "range";
      slider.id = network + "range";
      slider.value = function(d) { return d.datum };
      slider.defaultValue = 0;
      slider.max = thisPersonDates.length - 1;
      slider.min = 0;
      console.log(network);
      slider.onchange = function () { 
      	onChangeSlider(slider,network);
      }
     						 
       console.log(network);
      document.getElementById(network + "range").appendChild(slider);
      console.log(network);
    }

    function onChangeSlider(slider,network) {s
    	
    	console.log(slider.id);
    	console.log(network);
    	d3.select("#"+network).select('svg').remove();
    	console.log(document.getElementById(network + "contact_groups"));
    	console.log(network, chosenPerson, slider.value)
     //   document.getElementById(networkName + "contact_groups").innerHTML == "";
    	draw_graphs(network,chosenPerson,slider.value);
      }*/

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
    //  allNetworksDatesThisPerson.forEach(function(nodesArray) { 
//      	 	selectedNetworkNodes = nodesArray.datum;
//    	 	console.dir(selectedNetworkNodes);
    //  })
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
    /*	  for ( var n in nodesNetwork.allnetworks[person]){
    		  allNetworksThisPerson
    	  }
    	}
    	console.dir(allPersons);
    	allNetworksThisPerson = allPersons[chosenPerson];
    	console.dir(allNetworksThisPerson);
    	for ( i = 0; i < allNetworksThisPerson.length; i++ ){
    		selectedNetworkNodes = allNetworksThisPerson[slidervalue];
    	}
    	console.dir(selectedNetworkNodes);
    	console.log(selectedNetworkNodes);*/
    	
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
    	  .linkDistance( function(selectedNetworkLinks) {return selectedNetworkLinks.distance*30})
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
    	  .style("stroke-width", function(d) { return (d.strength * 2); })
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
    	  // When this function executes, the force layout
    	  // calculations have concluded. The layout will
    	  // have set various properties in our nodes and
    	  // links objects that we can use to position them
    	  // within the SVG container.
      
    	  // First let's reposition the nodes. As the force
    	  // layout runs it updates the `x` and `y` properties
    	  // that define where the node should be centered.
    	  // To move the node, we set the appropriate SVG
    	  // attributes to their new values. We also have to
    	  // give the node a non-zero radius so that it's visible
    	  // in the container.
    	  
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
    	
    /*	<div>
                      <span>Ratings</span><span id="ratingsLabel"></span>
                    </div>
    			    <br>
                      <paper-slider id="datums" pin snaps max="10" step="1" value="5"></paper-slider>
                    </div>
    */						   
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
      
      // Dit stopt de drukke animatie in het begin
      //var k = 0;
      //while ((force.alpha() > 1e-2) && (k < 150)) {
       //   force.tick(),
    	//  k = k + 1;
      //}
      
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