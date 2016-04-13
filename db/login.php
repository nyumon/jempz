<?php 
	
	require_once "include/connect.php";

	$db = new DB_connect();

	$username = $_POST['username'];
	$password = $_POST['password'];

	$q 		  = "select * from users where username = '$username' and password = '$password' ";

	$query	  = mysqli_query($db->db_select, $q);

	if(mysqli_num_rows > 0) {
		echo "success";
	} else {
		echo "failed";
	}

	$db->close();

?>