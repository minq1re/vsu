import bcrypt
import psycopg2.extras
from app.db.database import get_connection


def hash_password(password: str) -> str:
    """Создаёт безопасный bcrypt-хеш"""
    salt = bcrypt.gensalt()
    return bcrypt.hashpw(password.encode("utf-8"), salt).decode("utf-8")


def verify_password(password: str, password_hash: str) -> bool:
    """Проверяет пароль"""
    return bcrypt.checkpw(password.encode("utf-8"), password_hash.encode("utf-8"))


def create_user(email: str, password: str):
    """Создать нового пользователя"""
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    password_hash = hash_password(password)

    cur.execute("""
        INSERT INTO users (email, password_hash)
        VALUES (%s, %s)
        RETURNING id;
    """, (email, password_hash))

    user_id = cur.fetchone()["id"]
    conn.commit()
    conn.close()
    return user_id


def get_user_by_email(email: str):
    """Возвращает пользователя по email или None"""
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("SELECT * FROM users WHERE email = %s;", (email,))
    user = cur.fetchone()
    conn.close()

    return dict(user) if user else None


def authenticate_user(email: str, password: str):
    """
    Проверка входа:
    1) находит пользователя по email
    2) сравнивает пароли
    3) возвращает словарь user или None
    """
    user = get_user_by_email(email)
    if not user:
        return None

    if not verify_password(password, user["password_hash"]):
        return None

    return user
