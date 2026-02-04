from Huffman_code import huffman_coding_with_node_final


def generate_set(T):
    prob = {}
    for j in range(T):
        name = 's' + str(j+1)
        prob[name] = 1/T
    return prob


def huffman_code(T, ost):
    prob = generate_set(T)
    arr = range(T)
    root = huffman_coding_with_node_final(prob)
    res = root.preorder_leaf_traversal()
    return res[ost]


T = 5
code = huffman_code(T, 0)
print(code)