import 'package:flutter/material.dart';
import '../app_services.dart';
import '../models/shopping_list.dart';

class ArchivePage extends StatefulWidget {
  const ArchivePage({super.key});

  @override
  State<ArchivePage> createState() => _ArchivePageState();
}

class _ArchivePageState extends State<ArchivePage> {
  @override
  Widget build(BuildContext context) {
    final archivedLists = AppServices.lists.archivedLists;

    return Scaffold(
      appBar: AppBar(title: const Text('Архив')),
      body:
          archivedLists.isEmpty
              ? const Center(child: Text('Архив пуст'))
              : ListView.builder(
                itemCount: archivedLists.length,
                itemBuilder: (context, index) {
                  final list = archivedLists[index];

                  return ListTile(
                    title: Text(list.name),
                    subtitle: Text('В архиве с: ${list.archivedAt}'),
                    trailing: IconButton(
                      icon: const Icon(Icons.restore),
                      onPressed: () {
                        setState(() {
                          AppServices.lists.restoreList(list.id);
                        });
                      },
                    ),
                  );
                },
              ),
    );
  }
}
