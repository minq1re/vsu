import pytest
from app.logic.meals import (
    create_meal_list,
    get_meal_list,
    add_product_to_meal,
    update_meal_product,
    remove_product_from_meal,
    copy_meal_list_to_date
)

@pytest.fixture
def sample_user():
    # Вставь ID реально существующего пользователя из базы
    return 1

@pytest.fixture
def sample_date():
    return "2024-01-01"

@pytest.fixture
def sample_product():
    return {
        "product_id": 1,
        "amount": 100,
        "calories": 52
    }

def test_create_meal_list(sample_user, sample_date):
    meal_id = create_meal_list(
        user_id=sample_user,
        date=sample_date,
        meal_type=0
    )
    
    assert isinstance(meal_id, int)


def test_add_product_to_meal(sample_user, sample_date, sample_product):
    meal_id = create_meal_list(sample_user, sample_date, 0)

    product_entry_id = add_product_to_meal(
        meal_list_id=meal_id,
        product_id=sample_product["product_id"],
        amount=sample_product["amount"],
        calories=sample_product["calories"]
    )

    assert isinstance(product_entry_id, int)


def test_get_meal_list(sample_user, sample_date, sample_product):
    meal_id = create_meal_list(sample_user, sample_date, 0)

    add_product_to_meal(meal_id, **sample_product)

    meal = get_meal_list(meal_id)

    assert meal["meal_type"] == 0
    assert len(meal["products"]) == 1
    assert meal["products"][0]["amount"] == 100


def test_update_meal_product(sample_user, sample_date, sample_product):
    meal_id = create_meal_list(sample_user, sample_date, 0)

    product_entry_id = add_product_to_meal(meal_id, **sample_product)

    update_meal_product(product_entry_id, amount=150)

    meal = get_meal_list(meal_id)

    assert meal["products"][0]["amount"] == 150


def test_remove_product_from_meal(sample_user, sample_date, sample_product):
    meal_id = create_meal_list(sample_user, sample_date, 0)

    product_entry_id = add_product_to_meal(meal_id, **sample_product)

    remove_product_from_meal(product_entry_id)

    meal = get_meal_list(meal_id)
    assert len(meal["products"]) == 0


def test_copy_meal_list_to_date(sample_user, sample_product):
    original_date = "2024-01-01"
    new_date = "2024-01-02"

    original_meal = create_meal_list(sample_user, original_date, 0)
    add_product_to_meal(original_meal, **sample_product)

    new_meal = copy_meal_list_to_date(original_meal, new_date)

    assert original_meal != new_meal

    copied_meal = get_meal_list(new_meal)

    assert copied_meal["meal_type"] == 0
    assert len(copied_meal["products"]) == 1
