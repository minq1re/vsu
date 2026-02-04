<?php

require_once '../config/connect.php';

$title = $_POST['title'];
$date = $_POST['date'];
$contacts = $_POST['contacts'];

mysqli_query($connect, "INSERT INTO `followers` (`id`, `title`, `date`, `contacts`) VALUES (NULL, '$title', '$date', '$contacts')");

header('Location: /');