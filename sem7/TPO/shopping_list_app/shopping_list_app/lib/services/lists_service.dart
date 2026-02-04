import 'dart:collection';
import '../models/shopping_list.dart';
import 'package:sqflite/sqflite.dart';
import 'database_service.dart';

class ListsService {
  final List<ShoppingList> _lists = [];
  int _nextId = 1;

  UnmodifiableListView<ShoppingList> get lists => UnmodifiableListView(_lists);

  ShoppingList addList(String name) {
    if (name.trim().isEmpty) {
      throw ArgumentError('Название списка не может быть пустым.');
    }

    if (_lists.any((l) => l.name.toLowerCase() == name.toLowerCase())) {
      throw ArgumentError('Список с таким названием уже существует.');
    }

    final list = ShoppingList(
      id: (_nextId++).toString(),
      name: name,
      createdAt: DateTime.now(),
      archived: false,
      archivedAt: null,
    );

    _lists.add(list);

    // Сохраняем в БД (асинхронно, без await)
    DatabaseService.database.then((db) {
      db.insert('lists', {
        'id': list.id,
        'name': list.name,
        'createdAt': list.createdAt.millisecondsSinceEpoch,
        'archived': list.archived ? 1 : 0,
        'archivedAt': null,
      }, conflictAlgorithm: ConflictAlgorithm.replace);
    });

    return list;
  }

  void deleteList(String id) {
    final index = _lists.indexWhere((l) => l.id == id);
    if (index == -1) return;

    final old = _lists[index];
    final updated = old.copyWith(archived: true, archivedAt: DateTime.now());

    _lists[index] = updated;

    DatabaseService.database.then((db) {
      db.update(
        'lists',
        {
          'archived': 1,
          'archivedAt': updated.archivedAt!.millisecondsSinceEpoch,
        },
        where: 'id = ?',
        whereArgs: [id],
      );
    });
  }

  void restoreList(String id) {
    final index = _lists.indexWhere((l) => l.id == id);
    if (index == -1) return;

    final old = _lists[index];
    final updated = old.copyWith(archived: false, archivedAt: null);

    _lists[index] = updated;

    DatabaseService.database.then((db) {
      db.update(
        'lists',
        {'archived': 0, 'archivedAt': null},
        where: 'id = ?',
        whereArgs: [id],
      );
    });
  }

  List<ShoppingList> get activeLists =>
      _lists.where((l) => !l.archived).toList();

  List<ShoppingList> get archivedLists =>
      _lists.where((l) => l.archived).toList();

  Future<void> loadFromDb() async {
    try {
      print(">>> ЗАГРУЗКА БАЗЫ");

      final db = await DatabaseService.database;

      print(">>> БАЗА ОТКРЫТА");

      final rows = await db.query('lists');

      print(">>> ПОЛУЧЕНО СТРОК: ${rows.length}");

      _lists.clear();

      int maxId = 0;

      for (final row in rows) {
        print(">>> ЧИТАЕМ СТРОКУ: $row");

        final idStr = row['id'] as String;
        final idInt = int.tryParse(idStr) ?? 0;
        if (idInt > maxId) maxId = idInt;

        final createdAtMs = row['createdAt'] as int;
        final archivedInt = row['archived'] as int? ?? 0;
        final archivedAtMs = row['archivedAt'] as int?;

        _lists.add(
          ShoppingList(
            id: idStr,
            name: row['name'] as String,
            createdAt: DateTime.fromMillisecondsSinceEpoch(createdAtMs),
            archived: archivedInt == 1,
            archivedAt:
                archivedAtMs != null
                    ? DateTime.fromMillisecondsSinceEpoch(archivedAtMs)
                    : null,
          ),
        );
      }

      _nextId = maxId + 1;

      print(">>> ЗАГРУЗКА ЗАВЕРШЕНА");
    } catch (e, st) {
      print(">>> ОШИБКА В loadFromDb(): $e");
      print(st);
    }
  }
}
