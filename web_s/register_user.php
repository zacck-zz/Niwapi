<?php 
include 'm_connector.php';

$user_name = $_REQUEST['user_name'];
$user_phone = $_REQUEST['user_phone];

$qstring = "INSERT INTO niwapi_users(niwapi_user_name, niwapi_user_phone) VALUES('$user_name', '$user_phone')";

if(!mysqli_query($con, $qstring))
{
	die('Error: ' . mysqli_error($con).'\n Please Try again ');

}
else 
{

	$uid = array("user_id", mysqli_last_insert_id($con));
	echo json_encode($uid);

?>
