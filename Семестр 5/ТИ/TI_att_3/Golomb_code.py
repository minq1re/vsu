def golomb_code(i, m):
    # Compute the value of T
    T = 2 ** m

    # Compute unary part of the Golomb code
    quotient = i // T
    unary_code = '1' * quotient + '0'

    # Compute binary part of the Golomb code
    remainder = i % T
    # To ensure the binary code is of length m, we use the format function
    binary_code_format = '{:0' + str(m) + 'b}'
    binary_code = binary_code_format.format(remainder)

    return unary_code, binary_code


"'Ввод данных'"
m = 4
i = 95
golomb_code_95 = golomb_code(i, m)
print(golomb_code_95)
