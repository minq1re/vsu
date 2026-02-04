from app.db.database import get_connection, fetchone_dict, fetchall_dict
import psycopg2.extras


def create_product(name, calories, proteins=0, fats=0, carbs=0):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("""
        INSERT INTO products (name, calories, proteins, fats, carbs)
        VALUES (%s, %s, %s, %s, %s)
        RETURNING id;
    """, (name, calories, proteins, fats, carbs))

    product_id = cur.fetchone()["id"]
    conn.commit()
    conn.close()
    return product_id


def get_product(product_id):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("SELECT * FROM products WHERE id = %s;", (product_id,))
    product = fetchone_dict(cur)
    conn.close()
    return product


def update_product(product_id, **kwargs):
    if not kwargs:
        return

    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    columns = ", ".join([f"{k} = %s" for k in kwargs])
    values = list(kwargs.values())
    values.append(product_id)

    cur.execute(f"""
        UPDATE products
        SET {columns}
        WHERE id = %s;
    """, values)

    conn.commit()
    conn.close()


def delete_product(product_id):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("DELETE FROM products WHERE id = %s;", (product_id,))

    conn.commit()
    conn.close()


def search_products(query: str):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("""
        SELECT * FROM products
        WHERE LOWER(name) LIKE LOWER(%s);
    """, (f"%{query}%",))

    results = fetchall_dict(cur)
    conn.close()
    return results


def list_products_sorted(field: str):
    allowed = ["calories", "proteins", "fats", "carbs", "name"]

    if field not in allowed:
        raise ValueError("Недопустимое поле сортировки")

    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute(f"""
        SELECT * FROM products
        ORDER BY {field} ASC;
    """)

    results = fetchall_dict(cur)
    conn.close()
    return results
