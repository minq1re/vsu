<?php

require_once 'config\connect.php';

?>

<!DOCTYPE html>
<html lang="ru">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Подписчики</title>
</head>
<style>
	th, td {
		padding: 10px;
	}

	th {
		background: #606060
		color: #fff;
	}
	td {
		background: #b5b5b5;
	}
</style>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Имя пользователя</th>
			<th>Дата регистрации</th>
			<th>Описание профиля</th>
		</tr>
		<?php
			$followers = mysqli_query($connect, "SELECT * FROM `followers`");
			$followers = mysqli_fetch_all($followers);
			foreach ($followers as $follower) {
		?>

		<tr>
			<td><?= $follower[0] ?></td>
			<td><?= $follower[1] ?></td>
			<td><?= $follower[2] ?></td>
			<td><?= $follower[3] ?></td>
			<td><a href="follower.php?id=<?= $follower[0] ?>">Просмотр пользователя</a></td>
			<td><a href="pdate.php?id=<?= $follower[0] ?>">Обновить</a></td>
			<td><a style="color: red" href="vendor/delete.php?id=<?= $follower[0] ?>">Удалить</a></td>
		</tr>

		<?php
			}
		?>

	</table>
	<h3>Добавить нового пользователя</h3>
	<form action="vendor/create.php" method="post">
		<p>Имя пользователя</p>
		<input type="text" name="title">
		<p>Дата регистрации</p>
		<input type="date" name="date">
		<p>Описание профиля</p>
		<textarea name="contacts"></textarea>
		<br> <br>
		<button type="submit">Добавить нового пользователя</button>
	</form>
</body>
</html>