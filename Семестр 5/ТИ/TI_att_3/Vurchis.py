import math
from Huffman_code import huffman_coding_with_node_final

def generate_set(T):
    prob = {}
    for j in range(T):
        name = 's' + str(j+1)
        prob[name] = 1/T
    return prob


def huffman_code(T, r):
    prob = generate_set(T)
    root = huffman_coding_with_node_final(prob)
    res = root.preorder_leaf_traversal()
    return res[r]




def golomb_rice_code(i, T):
    """
    Compute the Golomb-Rice code for a number i with parameter T,
    using Huffman coding for the remainder.
    """
    logor = math.log(T, 2)
    m = math.floor(math.log(T, 2))

    quotient = math.floor(i/T)
    print(quotient)

    # Compute unary part
    unary_quotient = '1' * (quotient-1) + '0'

    # Compute Huffman code for the remainder
    remainder = i % T
    binary_remainder = huffman_code(T,remainder)  # Предполагается, что функция huffman_encode реализована

    return [unary_quotient,binary_remainder]

i = 43
T = 5
golomb_rice_code_21 = golomb_rice_code(i, T)
print(golomb_rice_code_21)
