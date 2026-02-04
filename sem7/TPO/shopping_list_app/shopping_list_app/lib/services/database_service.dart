import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class DatabaseService {
  static Database? _db;
  static bool useInMemory = false; // ← Для тестов

  static Future<Database> get database async {
    if (_db != null) return _db!;
    _db = await _initDb();
    return _db!;
  }

  static Future<Database> _initDb() async {
    if (useInMemory) {
      return await openDatabase(
        inMemoryDatabasePath,
        version: 1,
        onCreate: _onCreate,
      );
    }

    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'shopping_list.db');

    return await openDatabase(path, version: 1, onCreate: _onCreate);
  }

  static Future _onCreate(Database db, int version) async {
    await db.execute('''
      CREATE TABLE lists (
        id TEXT PRIMARY KEY,
        name TEXT,
        createdAt INTEGER,
        archived INTEGER,
        archivedAt INTEGER
      )
    ''');

    await db.execute('''
      CREATE TABLE items (
        id TEXT PRIMARY KEY,
        listId TEXT,
        name TEXT,
        quantity REAL,
        category TEXT,
        comment TEXT,
        isBought INTEGER
      )
    ''');
  }

  /// Используется в тестах — полностью сбрасывает БД
  static Future reset() async {
    if (_db != null) await _db!.close();
    _db = null;
  }
}
