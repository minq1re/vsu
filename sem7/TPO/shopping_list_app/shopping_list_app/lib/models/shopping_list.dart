class ShoppingList {
  final String id;
  final String name;
  final DateTime createdAt;
  final bool archived;
  final DateTime? archivedAt;

  ShoppingList({
    required this.id,
    required this.name,
    required this.createdAt,
    this.archived = false,
    this.archivedAt,
  });

  ShoppingList copyWith({
    String? id,
    String? name,
    DateTime? createdAt,
    bool? archived,
    DateTime? archivedAt,
  }) {
    return ShoppingList(
      id: id ?? this.id,
      name: name ?? this.name,
      createdAt: createdAt ?? this.createdAt,
      archived: archived ?? this.archived,
      archivedAt: archivedAt ?? this.archivedAt,
    );
  }
}
