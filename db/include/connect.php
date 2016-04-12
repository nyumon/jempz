<?php 

	/**
	* This class is used to handle connection to webserver
	*/
	class DB_connect
	{
		private $connection;
		private $db_select;

		function __construct() {
			$this->connect();
		}

		function __destruct() {
			$this->close();
		}

		# Connectiong to database
		function connect()
		{
			require_once "config.php";

			# Connecting to MySQL database
			$this->connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD);
			$this->db_select  = mysqli_select_db($this->connection, DB_NAME);

			return $this->connection;
		}

		function close() {
			# Closing DB Connection
			mysqli_close($this->connection);
		}
	}

?>