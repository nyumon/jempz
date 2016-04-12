<?php 
	
	require_once "include/connect.php";

	$db = new DB_connect();

	if(isset($_POST['username']) && isset($_POST['password'])) {

		$username = $_POST['username'];
		$password = $_POST['password'];

		$query	  = mysqli_query("select * from users where username = '$username' and password = '$password' ");
		$fetch	  = mysqli_fetch_object($query);

		if(isset($fetch)) {
			echo "Success";
		}
		else {
			echo "Failed";
		}

		$db->close();

	}

?>