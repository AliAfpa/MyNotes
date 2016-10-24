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
			$query = "SELECT p.id, p.name
						FROM projects AS p
						WHERE p.id = $path_params[1]";
		}
		
		else {
			$query = "SELECT p.id, p.name
						FROM projects AS p";
		}
		
		$result = mysqli_query($link,$query) or die('Query failed: ' .
		mysql_error());
		
		//Création d’un array list identifié par la clé books
		$response["projects"] = array();
		
		//Parcours du fichier et creation d’un array pour récupérer les informations concernant un livre. Chaque livre est compris dans un Array
		while ($row = mysqli_fetch_assoc($result)) {
			$project = array();
			$project["id"] = $row["id"];
			$project["name"] = $row["name"];

			// Chaque array de livre est poussé dans l’array global identifé par books
			array_push($response["projects"], $project);
		}
		mysqli_free_result($result);
	}
	
	//Pour convertir l’array en array json
	$encode_donnees = json_encode($response);
	
	//met a disposition de l’application android les données json
	print_r($encode_donnees);
	
	mysqli_close($link);
?>
