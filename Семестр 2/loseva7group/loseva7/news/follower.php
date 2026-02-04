<?php
	
	require_once 'config/connect.php';
	

	$news_id = $_GET['id'];
	$news = mysqli_query($connect, "SELECT * FROM `followers` WHERE `id` = '$news_id'");
	$news = mysqli_fetch_assoc($news);

?>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Пользователь</title>
</head>
<body>
	<h2>Имя пользователя: <?= $news['title'] ?></h2>
	<h4>Дата регистрации: <?= $news['date'] ?></h4>
	<h4>Описание: <?= $news['contacts'] ?></h4>
</body>
</html>