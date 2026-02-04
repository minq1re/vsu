import pytest
from app.logic.nutrition import (
    calculate_product_calories,
    calculate_meal_calories,
    calculate_daily_calories,
)

# фикстура продуктов
@pytest.fixture
def sample_products():
    return [
        {"product_id": 1, "amount": 100, "calories": 52},   # яблоко
        {"product_id": 2, "amount": 50, "calories": 265},   # хлеб
    ]


def test_calculate_product_calories():
    result = calculate_product_calories(100, 52)  # 100 г продукта
    assert result == 52

    result2 = calculate_product_calories(50, 265)  # сумма калорий в 50 г хлеба
    assert round(result2, 2) == 132.5


def test_calculate_meal_calories(sample_products):
    result = calculate_meal_calories(sample_products)
    assert round(result, 2) == round(52 + 132.5, 2)


def test_calculate_daily_calories():
    meals = {
        "breakfast": 300,
        "lunch": 500,
        "dinner": 700,
        "snack": 150
    }

    result = calculate_daily_calories(meals)
    assert result == 1650
