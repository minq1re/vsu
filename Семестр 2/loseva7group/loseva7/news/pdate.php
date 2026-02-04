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
	<title>Обновление списка пользователей</title>
</head>
<body>
	<h3>Обновить пользователя</h3>
		<form action="vendor/update.php" method="post">
			<input type="hidden" name="id" value="<?= $news['id'] ?>">
			<p>Имя пользователя</p>
			<input type="text" name="title" value="<?= $news['title'] ?>">
			<p>Дата регистрации</p>
			<input type="date" name="date" value="<?= $news['date'] ?>">
			<p>Описание профиля</p>
			<textarea name="contacts"><?= $news['contacts'] ?></textarea>
			<br> <br>
			<button type="submit">Обновить пользователя</button>
		</form>
</body>
</html>