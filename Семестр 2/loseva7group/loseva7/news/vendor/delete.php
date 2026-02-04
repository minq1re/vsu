<?php

require_once '../config/connect.php';

$id = $_GET['id'];

mysqli_query($connect, "DELETE FROM `followers` WHERE `followers`.`id` = '$id'");

header('Location: /');