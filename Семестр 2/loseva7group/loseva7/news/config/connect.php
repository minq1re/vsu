<?php

$connect = mysqli_connect('localhost', 'root', '', 'NEWS');

if (!$connect) {
	die("Error connect to database");
}
