import sys, io
from app_config import app, db
from model import *

from flask import request, render_template, redirect, url_for

from flask_login import LoginManager, login_required, login_user, logout_user

login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = "login"


@app.route('/')
def index():
    return render_template(
        'index.html',
        title='Mega Olympics for cool dudes'
    )


# login page
@app.route("/login", methods=["GET", "POST"])
def login():
    feedback = ''
    if request.method == 'POST':
        if request.form['cmd'] == 'Вход':
            u = db.session.query(User).\
                filter(User.user_login == request.form['login']).\
                filter(User.user_password == request.form['password']).\
                one_or_none()
            if u is None:
                feedback = "Неверное имя пользователя или пароль"
            else:
                login_user(u)
                return redirect(request.args.get("next") or url_for('index'))

    return render_template('login.html', feedback=feedback)


@login_manager.user_loader
def load_user(user_id):
    return db.session.query(User).filter(User.user_id == int(user_id)).one_or_none()


@app.route("/logout", methods=["GET", "POST"])
@login_required
def logout():
    logout_user()
    return redirect(url_for('login'))


@app.route("/tasks", methods=["GET", "POST"])
@login_required
def tasks():
    tasks = db.session.query(Task).order_by(Task.task_id).all()
    return render_template('tasks.html', title='Список вопросов', tasks=tasks)


@app.route("/task_space/<int:task_id>", methods=["GET", "POST"])
@login_required
def task_space(task_id):
    task_name = db.session.query(Task).filter(Task.task_id == task_id).one().name
    desc = db.session.query(Task).filter(Task.task_id == task_id).one().description
    return render_template('task_space.html', title='Задача',
                           task_name=task_name, task_id=task_id, status="Ожидание запроса", description=desc)


@app.route("/check_task/<int:task_id>", methods=["GET", "POST"])
@login_required
def check_task(task_id):
    tes = db.session.query(Test).filter(Test.task_id == task_id).order_by(Test.test_id).all()
    res = 'Успешно'
    if len(tes) == 0:
        res = 'Нет тестов'

    prog = request.form['prog_text']
    code = compile(prog, '<string>', 'exec')

    old_stdout = sys.stdout
    old_stdin = sys.stdin

    name_qw = db.session.query(Task).filter(Task.task_id == task_id).one()
    task_name = name_qw.name
    desc = name_qw.description

    try:
        sys.stdout = io.StringIO()

        c = 0
        for test in tes:
            local_vars = {'input': lambda: test.input}
            c += 1
            try:
                exec(code, {}, local_vars)
            except Exception as ex:
                res = 'Ошибка компиляции'
                return render_template('task_space.html', title='Задача', task_name=task_name, task_id=task_id,
                                       status=res, description=desc)

            output = sys.stdout.getvalue()
            sys.stdout = io.StringIO()
            if output.strip() != test.output.strip():
                res = 'Ошибка на тесте ' + str(c)
                break

    finally:
        sys.stdout = old_stdout
        sys.stdin = old_stdin

    return render_template('task_space.html', title='Задача', task_name=task_name, task_id=task_id, status=res,
                           description=desc)


@app.route("/check_task_file/<int:task_id>", methods=["GET", "POST"])
@login_required
def check_task_file(task_id):
    name_qw = db.session.query(Task).filter(Task.task_id == task_id).one()
    task_name = name_qw.name
    desc = name_qw.description

    tes = db.session.query(Test).filter(Test.task_id == task_id).order_by(Test.test_id).all()
    res = 'Успешно'
    if len(tes) == 0:
        res = 'Нет тестов'

    if 'file' not in request.files or not request.files['file']:
        res = 'Нет файла'
        return render_template('task_space.html', title='Задача', task_name=task_name, task_id=task_id,
                               status=res, description=desc)
    prog = request.files['file'].stream.read()
    code = compile(prog, '<string>', 'exec')

    old_stdout = sys.stdout
    old_stdin = sys.stdin


    try:

        sys.stdout = io.StringIO()

        c = 0
        for test in tes:
            local_vars = {'input': lambda: test.input}
            c += 1
            try:
                exec(code, {}, local_vars)
            except Exception as ex:
                res = 'Ошибка компиляции'
                return render_template('task_space.html', title='Задача', task_name=task_name, task_id=task_id,
                                       status=res, description=desc)

            output = sys.stdout.getvalue()
            sys.stdout = io.StringIO()
            if output.strip() != test.output.strip():
                res = 'Ошибка на тесте ' + str(c)
                break

    finally:
        sys.stdout = old_stdout
        sys.stdin = old_stdin

    return render_template('task_space.html', title='Задача', task_name=task_name, task_id=task_id, status=res,
                           description=desc)


if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)
