import 'package:flutter/material.dart';
import 'package:shopping_list_app/services/database_service.dart';
import 'package:sqflite_common_ffi/sqflite_ffi.dart';
import 'screens/lists_page.dart';

Future<void> main() async {
  // Обязательная инициализация Flutter до async-кода
  WidgetsFlutterBinding.ensureInitialized();

  // Инициализация SQLite FFI для Windows / Desktop
  sqfliteFfiInit();
  databaseFactory = databaseFactoryFfi;

  runApp(const ShoppingListApp());
}

class ShoppingListApp extends StatelessWidget {
  const ShoppingListApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,

      theme: ThemeData(
        useMaterial3: true,
        colorSchemeSeed: Colors.green,
        scaffoldBackgroundColor: const Color(0xFFF8FAF8),
        brightness: Brightness.light,
      ),

      home: const ListsPage(),
    );
  }
}
