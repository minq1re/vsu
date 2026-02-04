import 'dart:collection';
import '../models/item.dart';
import 'database_service.dart';
import 'package:sqflite/sqflite.dart';

class ItemsService {
  final List<Item> _items = [];
  int _nextId = 1;

  UnmodifiableListView<Item> get items => UnmodifiableListView(_items);

  Future<void> loadFromDb() async {
    final db = await DatabaseService.database;
    final rows = await db.query('items');

    _items.clear();

    int maxId = 0;

    for (final row in rows) {
      final idStr = row['id'] as String;
      final idInt = int.tryParse(idStr) ?? 0;
      if (idInt > maxId) maxId = idInt;

      _items.add(
        Item(
          id: idStr,
          listId: row['listId'] as String,
          name: row['name'] as String,
          quantity: (row['quantity'] as num).toDouble(),
          category: row['category'] as String,
          comment: row['comment'] as String?,
          isBought: (row['isBought'] as int) == 1,
        ),
      );
    }

    _nextId = maxId + 1;
  }

  Item addItem({
    required String listId,
    required String name,
    required double quantity,
    required String category,
    String? comment,
  }) {
    // Проверки (твои)
    if (name.trim().isEmpty) {
      throw ArgumentError('Название товара не может быть пустым');
    }
    if (name.length > 100) {
      throw ArgumentError('Название должно быть ≤ 100 символов');
    }
    if (comment != null && comment.length > 300) {
      throw ArgumentError('Комментарий должен быть ≤ 300 символов');
    }
    if (quantity <= 0) {
      throw ArgumentError('Количество должно быть > 0');
    }

    final item = Item(
      id: (_nextId++).toString(),
      listId: listId,
      name: name,
      quantity: quantity,
      category: category,
      comment: comment,
      isBought: false,
    );

    _items.add(item);

    DatabaseService.database.then((db) {
      db.insert('items', {
        'id': item.id,
        'listId': item.listId,
        'name': item.name,
        'quantity': item.quantity,
        'category': item.category,
        'comment': item.comment,
        'isBought': item.isBought ? 1 : 0,
      }, conflictAlgorithm: ConflictAlgorithm.replace);
    });

    return item;
  }

  void updateItem(
    String id, {
    String? name,
    double? quantity,
    String? category,
    String? comment,
    bool? isBought,
  }) {
    final index = _items.indexWhere((item) => item.id == id);
    if (index == -1) return;

    final old = _items[index];

    final newName = name ?? old.name;
    final newQuantity = quantity ?? old.quantity;
    final newComment = comment ?? old.comment;

    // Проверки
    if (newName.trim().isEmpty) throw ArgumentError('Название пустое');
    if (newName.length > 100) throw ArgumentError('Название длиннее 100');
    if (newComment != null && newComment.length > 300) {
      throw ArgumentError('Комментарий длиннее 300');
    }
    if (newQuantity <= 0) throw ArgumentError('Количество <= 0');

    final updated = old.copyWith(
      name: newName,
      quantity: newQuantity,
      category: category ?? old.category,
      comment: newComment,
      isBought: isBought ?? old.isBought,
    );

    _items[index] = updated;

    DatabaseService.database.then((db) {
      db.update(
        'items',
        {
          'name': updated.name,
          'quantity': updated.quantity,
          'category': updated.category,
          'comment': updated.comment,
          'isBought': updated.isBought ? 1 : 0,
        },
        where: 'id = ?',
        whereArgs: [id],
      );
    });
  }

  void deleteItem(String id) {
    _items.removeWhere((i) => i.id == id);

    DatabaseService.database.then((db) {
      db.delete('items', where: 'id = ?', whereArgs: [id]);
    });
  }

  void toggleBought(String id) {
    final index = _items.indexWhere((i) => i.id == id);
    if (index == -1) return;

    final old = _items[index];
    final updated = old.copyWith(isBought: !old.isBought);

    _items[index] = updated;

    DatabaseService.database.then((db) {
      db.update(
        'items',
        {'isBought': updated.isBought ? 1 : 0},
        where: 'id = ?',
        whereArgs: [id],
      );
    });
  }

  List<Item> search({
    String? query,
    String? category,
    bool? isBought,
    String? listId,
  }) {
    Iterable<Item> result = _items;

    if (listId != null) {
      result = result.where((i) => i.listId == listId);
    }

    if (query != null && query.trim().isNotEmpty) {
      final low = query.toLowerCase();
      result = result.where((i) => i.name.toLowerCase().contains(low));
    }

    if (category != null && category.isNotEmpty) {
      result = result.where((i) => i.category == category);
    }

    if (isBought != null) {
      result = result.where((i) => i.isBought == isBought);
    }

    final list =
        result.toList()..sort(
          (a, b) => a.name.toLowerCase().compareTo(b.name.toLowerCase()),
        );

    return list;
  }
}
