<?php 
	
	require_once __DIR__ . "include/connect.php";

	$db = new DB_connect();

	if(isset($_POST['username']) && isset($_POST['password'])) {

		$username = $_POST['username'];
		$password = $_POST['password'];

		$query	  = mysql_query("select * from users where username = '$username' and password = '$password' ");
		$fetch	  = mysql_fetch_object($query);

		if(isset($fetch)) {
			echo "Success";
		}
		else {
			echo "Failed";
		}

		$db->close();

	}

?>