import 'package:flutter/material.dart';
import '../services/items_service.dart';
import '../app_services.dart';
import '../models/item.dart';

const List<String> categories = [
  'Продукты',
  'Хозтовары',
  'Одежда',
  'Бытовая техника',
  'Другое',
];

class ItemsPage extends StatefulWidget {
  final String listId;
  final String listName;

  const ItemsPage({super.key, required this.listId, required this.listName});

  @override
  State<ItemsPage> createState() => _ItemsPageState();
}

class _ItemsPageState extends State<ItemsPage> {
  final _service = AppServices.items;

  bool _loading = true;

  String _searchQuery = '';
  String? _selectedCategory;
  bool? _boughtFilter;

  @override
  void initState() {
    super.initState();
    _loadItems();
  }

  Future<void> _loadItems() async {
    await _service.loadFromDb();
    setState(() => _loading = false);
  }

  @override
  Widget build(BuildContext context) {
    if (_loading) {
      return const Scaffold(body: Center(child: CircularProgressIndicator()));
    }

    final items = _service.search(
      listId: widget.listId,
      query: _searchQuery,
      category: _selectedCategory,
      isBought: _boughtFilter,
    );

    return Scaffold(
      appBar: AppBar(title: Text(widget.listName), centerTitle: true),
      body: Column(
        children: [
          _buildSearchField(),
          _buildCategoryFilter(),
          _buildBoughtFilter(),
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.only(bottom: 80),
              itemCount: items.length,
              itemBuilder: (_, index) {
                final item = items[index];
                return _buildItemCard(item);
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _showAddDialog,
        child: const Icon(Icons.add),
      ),
    );
  }

  Widget _buildSearchField() {
    return Padding(
      padding: const EdgeInsets.all(12),
      child: TextField(
        decoration: InputDecoration(
          hintText: "Поиск...",
          prefixIcon: const Icon(Icons.search),
          filled: true,
          fillColor: Colors.white,
          contentPadding: const EdgeInsets.symmetric(vertical: 12),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(14)),
        ),
        onChanged: (value) => setState(() => _searchQuery = value),
      ),
    );
  }

  Widget _buildCategoryFilter() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 12),
      child: Container(
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(14),
        ),
        padding: const EdgeInsets.symmetric(horizontal: 12),
        child: DropdownButton<String>(
          value: _selectedCategory,
          underline: const SizedBox(),
          isExpanded: true,
          hint: const Text('Фильтр по категории'),
          items: [
            const DropdownMenuItem(value: null, child: Text("Все категории")),
            ...categories.map(
              (c) => DropdownMenuItem(value: c, child: Text(c)),
            ),
          ],
          onChanged: (value) => setState(() => _selectedCategory = value),
        ),
      ),
    );
  }

  Widget _buildBoughtFilter() {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          FilterChip(
            label: const Text("Все"),
            selected: _boughtFilter == null,
            onSelected: (_) => setState(() => _boughtFilter = null),
          ),
          const SizedBox(width: 8),
          FilterChip(
            label: const Text("Куплено"),
            selected: _boughtFilter == true,
            onSelected: (_) => setState(() => _boughtFilter = true),
          ),
          const SizedBox(width: 8),
          FilterChip(
            label: const Text("Не куплено"),
            selected: _boughtFilter == false,
            onSelected: (_) => setState(() => _boughtFilter = false),
          ),
        ],
      ),
    );
  }

  Widget _buildItemCard(Item item) {
    return Card(
      elevation: 2,
      margin: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
        child: Row(
          children: [
            Checkbox(
              value: item.isBought,
              onChanged: (_) {
                _service.toggleBought(item.id);
                setState(() {});
              },
            ),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    item.name,
                    style: TextStyle(
                      fontSize: 17,
                      fontWeight: FontWeight.w600,
                      decoration:
                          item.isBought
                              ? TextDecoration.lineThrough
                              : TextDecoration.none,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(
                    "${item.quantity} — ${item.category}",
                    style: TextStyle(fontSize: 13, color: Colors.grey.shade700),
                  ),
                ],
              ),
            ),
            IconButton(
              icon: const Icon(Icons.edit, color: Colors.blue),
              tooltip: 'Редактировать',
              onPressed: () => _showEditDialog(item),
            ),
            IconButton(
              icon: const Icon(Icons.delete, color: Colors.red),
              tooltip: 'Удалить',
              onPressed: () {
                _service.deleteItem(item.id);
                setState(() {});
              },
            ),
          ],
        ),
      ),
    );
  }

  void _showAddDialog() {
    final nameController = TextEditingController();
    final quantityController = TextEditingController(text: "1");
    final commentController = TextEditingController();

    String? selectedCategory;

    showDialog(
      context: context,
      builder: (_) {
        return StatefulBuilder(
          builder: (context, setStateDialog) {
            return AlertDialog(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              title: const Text("Добавить товар"),
              content: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      controller: nameController,
                      decoration: const InputDecoration(labelText: "Название"),
                    ),
                    TextField(
                      controller: quantityController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: "Количество",
                      ),
                    ),
                    DropdownButtonFormField<String>(
                      value: selectedCategory,
                      decoration: const InputDecoration(labelText: "Категория"),
                      items:
                          categories
                              .map(
                                (c) =>
                                    DropdownMenuItem(value: c, child: Text(c)),
                              )
                              .toList(),
                      onChanged: (value) {
                        setStateDialog(() {
                          selectedCategory = value;
                        });
                      },
                    ),
                    TextField(
                      controller: commentController,
                      decoration: const InputDecoration(
                        labelText: "Комментарий",
                      ),
                    ),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text("Отмена"),
                ),
                ElevatedButton(
                  onPressed: () {
                    if (selectedCategory == null) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text("Выберите категорию")),
                      );
                      return;
                    }

                    try {
                      _service.addItem(
                        listId: widget.listId,
                        name: nameController.text,
                        quantity: double.tryParse(quantityController.text) ?? 1,
                        category: selectedCategory!,
                        comment:
                            commentController.text.isEmpty
                                ? null
                                : commentController.text,
                      );
                      setState(() {});
                      Navigator.pop(context);
                    } catch (e) {
                      ScaffoldMessenger.of(
                        context,
                      ).showSnackBar(SnackBar(content: Text("Ошибка: $e")));
                    }
                  },
                  child: const Text("Добавить"),
                ),
              ],
            );
          },
        );
      },
    );
  }

  void _showEditDialog(Item item) {
    final nameController = TextEditingController(text: item.name);
    final quantityController = TextEditingController(
      text: item.quantity.toString(),
    );
    final commentController = TextEditingController(text: item.comment ?? "");

    String? selectedCategory = item.category;

    showDialog(
      context: context,
      builder: (_) {
        return StatefulBuilder(
          builder: (context, setStateDialog) {
            return AlertDialog(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              title: const Text("Редактировать товар"),
              content: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    TextField(
                      controller: nameController,
                      decoration: const InputDecoration(labelText: "Название"),
                    ),
                    TextField(
                      controller: quantityController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: "Количество",
                      ),
                    ),
                    DropdownButtonFormField<String>(
                      value: selectedCategory,
                      decoration: const InputDecoration(labelText: "Категория"),
                      items:
                          categories
                              .map(
                                (c) =>
                                    DropdownMenuItem(value: c, child: Text(c)),
                              )
                              .toList(),
                      onChanged: (value) {
                        setStateDialog(() {
                          selectedCategory = value;
                        });
                      },
                    ),
                    TextField(
                      controller: commentController,
                      decoration: const InputDecoration(
                        labelText: "Комментарий",
                      ),
                    ),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text("Отмена"),
                ),
                ElevatedButton(
                  onPressed: () {
                    if (selectedCategory == null) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text("Выберите категорию")),
                      );
                      return;
                    }

                    try {
                      _service.updateItem(
                        item.id,
                        name: nameController.text,
                        quantity:
                            double.tryParse(quantityController.text) ??
                            item.quantity,
                        category: selectedCategory,
                        comment:
                            commentController.text.isEmpty
                                ? null
                                : commentController.text,
                      );
                      setState(() {});
                      Navigator.pop(context);
                    } catch (e) {
                      ScaffoldMessenger.of(
                        context,
                      ).showSnackBar(SnackBar(content: Text("Ошибка: $e")));
                    }
                  },
                  child: const Text("Сохранить"),
                ),
              ],
            );
          },
        );
      },
    );
  }
}
