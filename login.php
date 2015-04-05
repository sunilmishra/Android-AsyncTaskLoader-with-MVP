<?php
  header('Content-Type: application/json');
  $jsonData = file_get_contents('php://input');
  $obj = json_decode($jsonData);
  
  class StatusMessage {
       public $title = "";
       public $message  = "";
   }
   
    $statusMessage = new StatusMessage();
	if($obj->userName=="sunil" ) {
		$statusMessage->title = "Success";
		$statusMessage->message = "Wooho, You are logged In!!";
	}
	else {
		$statusMessage->title = "Failure";
		$statusMessage->message = "UserName or Password is wrong!";
	}
	
    echo json_encode($statusMessage);
?>