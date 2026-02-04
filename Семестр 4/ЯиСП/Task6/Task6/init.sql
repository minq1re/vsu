insert into user
(user_login, user_name, user_password) values ('solaire', 'Solaire of Astora', 'sun');

insert into task
(name, description) values ('+2', 'Прибавить к введенному числе 2. Пример входных данных: 2. Выходные данные: 4');

insert into test
(input, output, task_id) values ('2', '4', 1);

insert into test
(input, output, task_id) values ('3', '5', 1);

insert into task
(name, description) values ('Квадрат', 'Вернуть квадрат введенного числа. Пример входных данных: 3. Выходные данные: 9');

insert into test
(input, output, task_id) values ('2', '4', 2);

insert into test
(input, output, task_id) values ('3', '9', 2);