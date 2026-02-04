import math

import numpy
from Node import Node

def huffman_coding_with_node_final(probabilities):
    nodes = [Node(name=symbol, prob=prob) for symbol, prob in probabilities.items()]
    nodes.sort(reverse=True)

    while len(nodes) > 1:
        right, left = nodes.pop(), nodes.pop()

        # Для внутренних узлов
        if len(left.name) > len(right.name) or (len(left.name) == len(right.name) and left.prob > right.prob):
            left, right = right, left

        # Для листовых узлов
        if not left.left and not left.right and not right.left and not right.right:
            if left.prob < right.prob:
                left, right = right, left

        parent = Node(name=left.name + right.name, prob=left.prob + right.prob)
        parent.set_children(left, right)
        nodes.append(parent)
        nodes.sort(reverse=True)

    root = nodes[0]
    root.build_code()

    return root


def extract_codes_from_node(root):
    if not root.left and not root.right:
        return {root.name: root.code}

    codes = {}
    if root.left:
        codes.update(extract_codes_from_node(root.left))
    if root.right:
        codes.update(extract_codes_from_node(root.right))
    return codes


def assign_codes(node, code):
    if node is None:
        return

    node.code = code

    if node.left is not None:
        assign_codes(node.left, code + "0")
    if node.right is not None:
        assign_codes(node.right, code + "1")

def entropy(probabilities):
    return -sum([p * numpy.log2(p) for p in probabilities.values() if p > 0])

def average_code_length(probabilities, huffman_codes):
    return sum([probabilities[symbol] * len(huffman_codes[symbol]) for symbol in probabilities])

def check_probabilities(probabilities):
    total = sum(probabilities.values())
    if not math.isclose(total, 1.0, rel_tol=1e-9):
        raise ValueError(f"Sum of probabilities is {total}, but should be close to 1.")