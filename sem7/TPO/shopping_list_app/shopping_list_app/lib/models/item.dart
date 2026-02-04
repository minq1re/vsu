class Item {
  final String id;
  final String listId;
  final String name;
  final double quantity;
  final String category;
  final String? comment;
  final bool isBought;

  Item({
    required this.id,
    required this.listId,
    required this.name,
    required this.quantity,
    required this.category,
    this.comment,
    this.isBought = false,
  });

  Item copyWith({
    String? id,
    String? listId,
    String? name,
    double? quantity,
    String? category,
    String? comment,
    bool? isBought,
  }) {
    return Item(
      id: id ?? this.id,
      listId: listId ?? this.listId,
      name: name ?? this.name,
      quantity: quantity ?? this.quantity,
      category: category ?? this.category,
      comment: comment ?? this.comment,
      isBought: isBought ?? this.isBought,
    );
  }
}
