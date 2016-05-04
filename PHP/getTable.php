<?php

	/* This PHP script connect to the bdd and return all the row from eventshistory in a JSON format */

	// Include file which contain BDD info
	include('dbConnect.php');

	// Connection to the BDD
	$mysqli = new mysqli(HOST,USER,PASS,DB) or die('Failed to connect with BDB');
	$myArray = array();
	
	// SQL query
	$sql = "SELECT * FROM eventshistory";

	// Check if the query successed
	
	if ($result = $mysqli->query($sql)) {
		echo "Query success"."<br>";

		// MYSQL_ASSOC is a constant that enable to use fetch_assoc()

	    while($row = $result->fetch_array(MYSQL_ASSOC)) {
	            $myArray[] = $row;
	    }
	    echo json_encode($myArray);
	}

	$result->close();
	$mysqli->close();

?>