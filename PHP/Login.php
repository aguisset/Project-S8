<?php
	
	// include Database info
	include('dbConnect.php');

	// Check if login is already in database
	$mysqli = new mysqli(HOST,USER,PASS,DB) or die('Failed to connect with BDB');

	function isLoginExist($mysqli,$email, $phone_number){
		/* Input : - mysqli : Variable which connect to the Database.
				   - email & password : email and password that we want to check */ 		
		
		 
		$query = "SELECT * FROM " ."User"." WHERE email = '$email' AND phone_number = '$phone_number' ";
		$result = $mysqli->query($query);

		if ($result = $mysqli->query($sql)) {
			echo "Query success"."<br>";
		
		if(mysqli_num_rows($result) > 0){
			mysqli_close($mysqli);
			return true;
		}
				
		mysqli_close($mysqli_close);
		return false;		
	}

	// Unit test isLoginExist
	$email ="guisset@gmail.com";
	$phone_number = "5588577";

	if(!isLoginExist($mysqli,$email,$phone_number)) {
		echo "This login already exist ";
	}


?>