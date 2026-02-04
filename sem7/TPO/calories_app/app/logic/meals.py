from app.db.database import get_connection
import psycopg2.extras


def create_meal_list(user_id, date, meal_type):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("""
        INSERT INTO meal_lists (user_id, date, meal_type)
        VALUES (%s, %s, %s)
        RETURNING id;
    """, (user_id, date, meal_type))

    meal_id = cur.fetchone()["id"]
    conn.commit()
    conn.close()
    return meal_id


def add_product_to_meal(meal_list_id, product_id, amount, calories):
    total = (amount / 100) * calories  # пересчет

    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("""
        INSERT INTO meal_products (meal_list_id, product_id, amount, calories_total)
        VALUES (%s, %s, %s, %s)
        RETURNING id;
    """, (meal_list_id, product_id, amount, total))

    insert_id = cur.fetchone()["id"]

    conn.commit()
    conn.close()
    return insert_id


def get_meal_list(meal_list_id):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    # Получаем список
    cur.execute("SELECT * FROM meal_lists WHERE id = %s;", (meal_list_id,))
    meal = cur.fetchone()

    if not meal:
        return None

    meal = dict(meal)

    # Получаем продукты внутри списка
    cur.execute("""
        SELECT * FROM meal_products
        WHERE meal_list_id = %s;
    """, (meal_list_id,))

    products = [dict(row) for row in cur.fetchall()]

    meal["products"] = products

    conn.close()
    return meal


def update_meal_product(entry_id, **kwargs):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    columns = ", ".join([f"{k} = %s" for k in kwargs])
    values = list(kwargs.values())
    values.append(entry_id)

    cur.execute(f"""
        UPDATE meal_products
        SET {columns}
        WHERE id = %s;
    """, values)

    conn.commit()
    conn.close()


def remove_product_from_meal(entry_id):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    cur.execute("DELETE FROM meal_products WHERE id = %s;", (entry_id,))

    conn.commit()
    conn.close()


def copy_meal_list_to_date(meal_list_id, new_date):
    conn = get_connection()
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    # Получаем исходный список
    cur.execute("SELECT * FROM meal_lists WHERE id = %s;", (meal_list_id,))
    meal = cur.fetchone()

    if not meal:
        return None

    meal = dict(meal)

    # Создаем новый список
    cur.execute("""
        INSERT INTO meal_lists (user_id, date, meal_type)
        VALUES (%s, %s, %s)
        RETURNING id;
    """, (meal["user_id"], new_date, meal["meal_type"]))

    new_meal_id = cur.fetchone()["id"]

    # Копируем продукты
    cur.execute("""
        SELECT * FROM meal_products WHERE meal_list_id = %s;
    """, (meal_list_id,))
    products = cur.fetchall()

    for p in products:
        cur.execute("""
            INSERT INTO meal_products (meal_list_id, product_id, amount, calories_total)
            VALUES (%s, %s, %s, %s);
        """, (new_meal_id, p["product_id"], p["amount"], p["calories_total"]))

    conn.commit()
    conn.close()
    return new_meal_id
