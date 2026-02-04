def unary_code(i):
    # The unary code for a number is a series of '1's followed by a '0',
    # where the number of '1's is equal to i - 1.
    # For the number 1, the unary code would be '0'.
    # For the number 2, the unary code would be '10', and so on.
    if i == 1:
        return '0'
    else:
        return '1' * (i - 1) + '0'

# Example: Compute the unary code for the quotient 5 (which is the quotient for 95 divided by 16)
unary_code_example = unary_code(4)
print(unary_code_example)