function convert(str) {
	    var date = new Date(str),
	      mnth = ("0" + (date.getMonth() + 1)).slice(-2),
	      day = ("0" + date.getDate()).slice(-2);
	    return [date.getFullYear(), mnth, day].join("-");
	  }
	function validateForm(){
	var x = document.registerform.name.value;
	var y = document.registerform.contact.value;
	var alphaExp = /^[a-zA-Z ]*$/;
	var numericExpression = /^[0-9]+$/;
	var dates = document.registerform.dob.value;
	var date = dates.split("-");
	var year = parseInt(date[0]);
	var month = date[1];
	var day = date[2];
	var age = 18;
	var newyear=year+age;
	var setDate = new Date(newyear,month-1,day);
	var Date1= convert(setDate);
	var currdate = new Date();
	var Date2 = convert(currdate);
	console.log(Date1);
	console.log(Date2);
	//Validate Now
	if (x.length==0|| !x.match(alphaExp)) {
	document.getElementById("div").style.display= "block";
	document.getElementById("error").innerHTML="Name should only contains alphabets.";
	return false;
	}
	else if (y.length!=10 || !y.match(numericExpression)) {
		document.getElementById("div").style.display= "block";
		document.getElementById("error").innerHTML="invalid contact number";
	    return false;
	  }
	else if(Date2<Date1){
		document.getElementById("div").style.display= "block";
		document.getElementById("error").innerHTML="Minimum age should be 18.";
	  return false;
	}
	else
	             return true;
	}