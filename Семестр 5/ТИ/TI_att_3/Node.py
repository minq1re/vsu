class Node:

    def __init__(self, name=None, prob=None):
        self.name = name

        self.prob = prob

        self.code = ""

        self.left = None

        self.right = None

    def __lt__(self, other):
        return self.prob < other.prob

    def __hash__(self):
        return hash((self.name, self.prob))

    def __eq__(self, other):
        if isinstance(other, Node):
            return (self.name, self.prob) == (other.name, other.prob)

        return False

    def set_children(self, left, right):
        self.left = left
        self.right = right

    def build_code(self, code=""):
        self.code = code
        if self.left:
            self.left.build_code(code + "0")
        if self.right:
            self.right.build_code(code + "1")

    def preorder_leaf_traversal(self):
        leaf_values = []
        self._preorder_leaf_traversal_helper(leaf_values)
        return leaf_values

    def _preorder_leaf_traversal_helper(self, leaf_values):
        if self.left is None and self.right is None:
            # Если текущий узел - лист, добавляем его значение в список
            leaf_values.append(self.code)
        if self.left:
            self.left._preorder_leaf_traversal_helper(leaf_values)
        if self.right:
            self.right._preorder_leaf_traversal_helper(leaf_values)





