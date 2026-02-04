import 'package:flutter/material.dart';
import '../services/lists_service.dart';
import '../models/shopping_list.dart';
import '../app_services.dart';
import 'archive_page.dart';
import 'items_page.dart';

class ListsPage extends StatefulWidget {
  const ListsPage({super.key});

  @override
  State<ListsPage> createState() => _ListsPageState();
}

class _ListsPageState extends State<ListsPage> {
  final ListsService _service = AppServices.lists;

  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadLists();
  }

  Future<void> _loadLists() async {
    await _service.loadFromDb();
    setState(() {
      _isLoading = false;
    });
  }

  void _addList() {
    final controller = TextEditingController();

    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
          ),
          title: const Text('Создать список'),
          content: TextField(
            controller: controller,
            decoration: const InputDecoration(labelText: 'Название списка'),
          ),
          actions: [
            TextButton(
              child: const Text('Отмена'),
              onPressed: () => Navigator.pop(context),
            ),
            FilledButton(
              child: const Text('Создать'),
              onPressed: () {
                _service.addList(controller.text);
                Navigator.pop(context);
                setState(() {});
              },
            ),
          ],
        );
      },
    );
  }

  void _openList(ShoppingList list) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (_) => ItemsPage(listId: list.id, listName: list.name),
      ),
    ).then((_) => setState(() {}));
  }

  @override
  Widget build(BuildContext context) {
    if (_isLoading) {
      return const Scaffold(body: Center(child: CircularProgressIndicator()));
    }

    final lists = _service.activeLists;

    return Scaffold(
      appBar: AppBar(
        title: const Text("Списки покупок"),
        centerTitle: true,
        actions: [
          IconButton(
            icon: const Icon(Icons.archive),
            tooltip: "Архив",
            onPressed: () async {
              await Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => const ArchivePage()),
              );
              setState(() {});
            },
          ),
        ],
      ),

      body: ListView.builder(
        padding: const EdgeInsets.all(12),
        itemCount: lists.length,
        itemBuilder: (_, index) {
          final list = lists[index];

          return Card(
            elevation: 2,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(14),
            ),
            margin: const EdgeInsets.symmetric(vertical: 8),
            child: ListTile(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(14),
              ),
              title: Text(
                list.name,
                style: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.w600,
                ),
              ),
              subtitle: Text(
                "Создан: ${list.createdAt.toLocal()}",
                style: TextStyle(fontSize: 13, color: Colors.grey.shade700),
              ),

              trailing: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                    icon: const Icon(Icons.delete, color: Colors.red),
                    onPressed: () {
                      _service.deleteList(list.id);
                      setState(() {});
                    },
                  ),
                ],
              ),

              onTap: () => _openList(list),
            ),
          );
        },
      ),

      floatingActionButton: FloatingActionButton.extended(
        onPressed: _addList,
        label: const Text("Новый список"),
        icon: const Icon(Icons.add),
      ),
    );
  }
}
