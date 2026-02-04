import pytest
from app.logic.products import (
    create_product, 
    get_product,
    update_product,
    delete_product,
    search_products,
    list_products_sorted
)

# фикстура для тестовой БД
@pytest.fixture
def test_product():
    return {
        "name": "Апельсин",
        "calories": 43,
        "proteins": 0.9,
        "fats": 0.1,
        "carbs": 8.2
    }


def test_create_product(test_product):
    product_id = create_product(**test_product)
    assert isinstance(product_id, int)


def test_get_product(test_product):
    product_id = create_product(**test_product)
    product = get_product(product_id)

    assert product["name"] == test_product["name"]
    assert product["calories"] == test_product["calories"]


def test_update_product(test_product):
    product_id = create_product(**test_product)

    update_product(product_id, name="Новый апельсин", calories=50)

    updated = get_product(product_id)

    assert updated["name"] == "Новый апельсин"
    assert updated["calories"] == 50


def test_delete_product(test_product):
    product_id = create_product(**test_product)

    delete_product(product_id)

    assert get_product(product_id) is None


def test_search_products(test_product):
    create_product(**test_product)
    results = search_products("ельсин")  # часть слова

    assert len(results) >= 1
    assert test_product["name"] in [p["name"] for p in results]


def test_list_products_sorted(test_product):
    create_product(**test_product)
    create_product("Яблоко", 52, 0.3, 0.2, 14)

    sorted_list = list_products_sorted("calories")

    assert len(sorted_list) >= 2
    assert sorted_list[0]["calories"] <= sorted_list[1]["calories"]
