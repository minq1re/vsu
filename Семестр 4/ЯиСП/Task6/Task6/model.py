from app_config import db


class Task(db.Model):
    __tablename__ = 'Task'
    task_id = db.Column('task_id', db.INTEGER, primary_key=True, autoincrement=True)
    name = db.Column('name', db.String(60), nullable=False, unique=True)
    description = db.Column('description', db.String(6000))


class Test(db.Model):
    __tablename__ = 'Test'
    test_id = db.Column('test_id', db.INTEGER, primary_key=True, autoincrement=True)
    input = db.Column('input', db.String(600), nullable=False)
    output = db.Column('output', db.String(600), nullable=False)

    task_id = db.Column(db.ForeignKey('Task.task_id'), nullable=False)
    tasks = db.relationship('Task', backref='Test')


class User(db.Model):
    __tablename__ = 'user'
    user_id = db.Column('user_id', db.INTEGER, primary_key=True, autoincrement=True)
    user_login = db.Column('user_login', db.String(20), unique=True)
    user_name = db.Column('user_name', db.String(60), nullable=False)
    user_password = db.Column('user_password', db.String(60), nullable=False)

    def is_active(self):
        """True, as all users are active."""
        return True

    def get_id(self):
        """Return the email address to satisfy Flask-Login's requirements."""
        return str(self.user_id)

    def is_authenticated(self):
        return True

    def is_anonymous(self):
        """False, as anonymous users aren't supported."""
        return False
