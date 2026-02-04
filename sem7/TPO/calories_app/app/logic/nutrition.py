def calculate_product_calories(amount_grams: float, calories_per_100g: float) -> float:
    """
    amount_grams — количество продукта в граммах
    calories_per_100g — ккал на 100 г
    """
    return (amount_grams / 100) * calories_per_100g


def calculate_meal_calories(products: list) -> float:
    """
    products — список вида:
    [
        {"product_id": 1, "amount": 100, "calories": 52},
        {"product_id": 2, "amount": 50, "calories": 265},
    ]
    """
    total = 0
    for item in products:
        total += calculate_product_calories(item["amount"], item["calories"])
    return total


def calculate_daily_calories(meals_dict: dict) -> float:
    """
    meals_dict = {
        "breakfast": число калорий,
        "lunch": ...,
        ...
    }
    """
    return sum(meals_dict.values())
