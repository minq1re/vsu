<?php

require_once '../config/connect.php';

$id = $_POST['id'];
$title = $_POST['title'];
$date = $_POST['date'];
$contacts = $_POST['contacts'];

mysqli_query($connect, "UPDATE `followers` SET `title` = '$title', `date` = '$date', `contacts` = '$contacts' WHERE `followers`.`id` = '$id'");

header('Location: /');