import 'package:flutter_test/flutter_test.dart';

import 'package:shopping_list_app/services/items_service.dart';
import 'package:shopping_list_app/services/database_service.dart';
import 'package:shopping_list_app/models/item.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';

void main() {
  sqfliteFfiInit();
  databaseFactory = databaseFactoryFfi;

  DatabaseService.useInMemory = true;

  late ItemsService service;

  setUp(() async {
    await DatabaseService.reset();
    service = ItemsService();
    await service.loadFromDb();
  });

  test("Добавление товара сохраняет в БД", () async {
    service.addItem(
      listId: "1",
      name: "Молоко",
      quantity: 2,
      category: "Продукты",
    );

    await service.loadFromDb();

    expect(service.items.length, 1);
    expect(service.items.first.name, "Молоко");
  });

  test("Пустое имя вызывает ошибку", () {
    expect(
      () => service.addItem(
        listId: "1",
        name: "",
        quantity: 1,
        category: "Продукты",
      ),
      throwsA(isA<ArgumentError>()),
    );
  });

  test("Название > 100 символов вызывает ошибку", () {
    expect(
      () => service.addItem(
        listId: "1",
        name: "a" * 101,
        quantity: 1,
        category: "Продукты",
      ),
      throwsA(isA<ArgumentError>()),
    );
  });

  test("Комментарий > 300 символов вызывает ошибку", () {
    expect(
      () => service.addItem(
        listId: "1",
        name: "Молоко",
        quantity: 1,
        category: "Продукты",
        comment: "a" * 301,
      ),
      throwsA(isA<ArgumentError>()),
    );
  });

  test("Количество = 0 вызывает ошибку", () {
    expect(
      () => service.addItem(
        listId: "1",
        name: "Молоко",
        quantity: 0,
        category: "Продукты",
      ),
      throwsA(isA<ArgumentError>()),
    );
  });

  test("Количество < 0 вызывает ошибку", () {
    expect(
      () => service.addItem(
        listId: "1",
        name: "Молоко",
        quantity: -1,
        category: "Продукты",
      ),
      throwsA(isA<ArgumentError>()),
    );
  });

  test("Можно добавить дробное количество", () {
    final item = service.addItem(
      listId: "1",
      name: "Соль",
      quantity: 0.5,
      category: "Продукты",
    );

    expect(item.quantity, 0.5);
  });

  test("Можно добавлять товары с одинаковым названием", () async {
    service.addItem(
      listId: "1",
      name: "Молоко",
      quantity: 1,
      category: "Продукты",
    );

    service.addItem(
      listId: "1",
      name: "Молоко",
      quantity: 2,
      category: "Продукты",
    );

    await service.loadFromDb();

    expect(service.items.length, 2);
    expect(service.items[0].name, "Молоко");
    expect(service.items[1].name, "Молоко");
    expect(service.items[0].id != service.items[1].id, true);
  });

  test("Редактирование работает корректно", () async {
    final item = service.addItem(
      listId: "1",
      name: "Хлеб",
      quantity: 1,
      category: "Продукты",
    );

    service.updateItem(
      item.id,
      name: "Черный хлеб",
      quantity: 3,
      category: "Другое",
    );

    await service.loadFromDb();

    expect(service.items.first.name, "Черный хлеб");
    expect(service.items.first.quantity, 3);
    expect(service.items.first.category, "Другое");
  });

  test("Переключение куплено/не куплено", () async {
    final item = service.addItem(
      listId: "1",
      name: "Соль",
      quantity: 1,
      category: "Продукты",
    );

    service.toggleBought(item.id);
    await service.loadFromDb();
    expect(service.items.first.isBought, true);

    service.toggleBought(item.id);
    await service.loadFromDb();
    expect(service.items.first.isBought, false);
  });

  test("Поиск без учета регистра работает", () async {
    service.addItem(
      listId: "1",
      name: "Апельсины",
      quantity: 1,
      category: "Продукты",
    );

    final result = service.search(query: "апе", listId: "1");

    expect(result.length, 1);
    expect(result.first.name, "Апельсины");
  });

  test("Поиск по всем спискам работает", () async {
    service.addItem(
      listId: "1",
      name: "Молоко",
      quantity: 1,
      category: "Продукты",
    );
    service.addItem(
      listId: "2",
      name: "Молочный коктейль",
      quantity: 1,
      category: "Продукты",
    );

    final results = service.search(query: "мол");

    expect(results.length, 2);
  });

  test("Результаты сортируются по алфавиту", () async {
    service.addItem(
      listId: "1",
      name: "Яблоко",
      quantity: 1,
      category: "Продукты",
    );
    service.addItem(
      listId: "1",
      name: "Апельсин",
      quantity: 1,
      category: "Продукты",
    );
    service.addItem(
      listId: "1",
      name: "Банан",
      quantity: 1,
      category: "Продукты",
    );

    final results = service.search(listId: "1");

    expect(results[0].name, "Апельсин");
    expect(results[1].name, "Банан");
    expect(results[2].name, "Яблоко");
  });

  test("Фильтрация по категории работает", () async {
    service.addItem(
      listId: "1",
      name: "Хлеб",
      quantity: 1,
      category: "Продукты",
    );
    service.addItem(
      listId: "1",
      name: "Мыло",
      quantity: 1,
      category: "Хозтовары",
    );

    final result = service.search(category: "Продукты", listId: "1");

    expect(result.length, 1);
    expect(result.first.name, "Хлеб");
  });

  test("Фильтрация по статусу работает", () async {
    final a = service.addItem(
      listId: "1",
      name: "Хлеб",
      quantity: 1,
      category: "Продукты",
    );
    final b = service.addItem(
      listId: "1",
      name: "Молоко",
      quantity: 1,
      category: "Продукты",
    );

    service.toggleBought(b.id);
    await service.loadFromDb();

    final bought = service.search(isBought: true, listId: "1");
    final notBought = service.search(isBought: false, listId: "1");

    expect(bought.length, 1);
    expect(notBought.length, 1);
    expect(bought.first.name, "Молоко");
    expect(notBought.first.name, "Хлеб");
  });

  test("Удаление товара удаляет из БД", () async {
    final item = service.addItem(
      listId: "1",
      name: "Сыр",
      quantity: 1,
      category: "Продукты",
    );

    service.deleteItem(item.id);
    await service.loadFromDb();

    expect(service.items.isEmpty, true);
  });
}
