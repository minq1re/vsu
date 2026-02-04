import psycopg2
from app.db.config import DB_NAME, DB_USER, DB_PASSWORD, DB_HOST, DB_PORT


def get_connection():
    return psycopg2.connect(
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD,
        host=DB_HOST,
        port=DB_PORT
    )


def fetchone_dict(cur):
    row = cur.fetchone()
    return dict(row) if row else None


def fetchall_dict(cur):
    return [dict(r) for r in cur.fetchall()]
