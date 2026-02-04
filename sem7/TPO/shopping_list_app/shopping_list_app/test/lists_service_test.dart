import 'package:flutter_test/flutter_test.dart';

import 'package:shopping_list_app/services/lists_service.dart';
import 'package:shopping_list_app/services/database_service.dart';
import 'package:shopping_list_app/models/shopping_list.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';

void main() {
  sqfliteFfiInit();
  databaseFactory = databaseFactoryFfi;

  DatabaseService.useInMemory = true;

  late ListsService service;

  setUp(() async {
    await DatabaseService.reset();
    service = ListsService();
    await service.loadFromDb();
  });

  test("Создание списка сохраняется в БД", () async {
    service.addList("Пятёрочка");
    await service.loadFromDb();

    expect(service.activeLists.length, 1);
    expect(service.activeLists.first.name, "Пятёрочка");
  });

  test("Пустое имя вызывает ошибку", () {
    expect(() => service.addList(""), throwsA(isA<ArgumentError>()));
  });

  test("Удаление переносит список в архив", () async {
    final list = service.addList("Магнит");

    service.deleteList(list.id);
    await service.loadFromDb();

    expect(service.activeLists.length, 0);
    expect(service.archivedLists.length, 1);
  });

  test("Восстановление списка работает", () async {
    final list = service.addList("Лента");

    service.deleteList(list.id);
    await service.loadFromDb();

    service.restoreList(list.id);
    await service.loadFromDb();

    expect(service.archivedLists.length, 0);
    expect(service.activeLists.length, 1);
  });
}
