<?php
	$link = mysqli_connect('localhost', 'phpmyadmin', 'afpa1442', 'notesDB') or
	die('Could not connect: ' . mysql_error());
	header('Content-type: application/json');
	// Check for the path elements
	$path = $_SERVER['PATH_INFO'];
	
	if ($path != null) {
		$path_params = explode ("/", $path);
	}
	
	if ($_SERVER['REQUEST_METHOD'] == 'GET') {
		if ($path_params[1] != null) {
			settype($path_params[1], 'integer');
			$query = "SELECT n.id, n.title, n.text, n.solved, n.project_id, n.dev_id
						FROM notes AS n
						WHERE n.id = $path_params[1]";
		}
		
		else {
			$query = "SELECT n.id, n.title, n.text, n.solved, n.project_id, n.dev_id
						FROM notes AS n";
		}
		
		$result = mysqli_query($link,$query) or die('Query failed: ' .
		mysql_error());
		
		//Création d’un array list identifié par la clé books
		$response["notes"] = array();
		
		//Parcours du fichier et creation d’un array pour récupérer les informations concernant un livre. Chaque livre est compris dans un Array
		while ($row = mysqli_fetch_assoc($result)) {
			$note = array();
			$note["id"] = $row["id"];
			$note["title"] = $row["title"];
			$note["text"] = $row["text"];
			$note["solved"] = $row["solved"];
			$note["project_id"] = $row["project_id"];
			$note["dev_id"] = $row["dev_id"];
			// Chaque array de livre est poussé dans l’array global identifé par books
			array_push($response["notes"], $note);
		}
		mysqli_free_result($result);
	}
	
	//Pour convertir l’array en array json
	$encode_donnees = json_encode($response);
	
	//met a disposition de l’application android les données json
	print_r($encode_donnees);
	
	mysqli_close($link);
?>
