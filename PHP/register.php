
<?php


	include('dbConnect.php');
	$conn = mysqli_connect(HOST,USER,PASS,DB) or die('Failed to connect with BDB');


	// Retrieve the information of the application using GET
	$name   = urldecode($_GET['name']);
	$first_name   = urldecode($_GET['first_name']);
	$phone_number  = urldecode($_GET['phone_number']);
	$email  = urldecode($_GET['email']);
	$password   = md5(urldecode($_GET['password']));

	// SQL query 
	$sql = "INSERT INTO `User`(`name`, `first name`, `phone_number`, `email`, `password`) 
	VALUES ('$name','$first_name','$phone_number','$email','$password')";

	if (mysqli_query($conn, $sql)) {
	    echo "New record created successfully";
	} else {
	    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
	}

	echo "Data Insert";

	
	

mysqli_close($conn);

?>