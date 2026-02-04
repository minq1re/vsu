def shift_rows(matrix, steps):
    
  

    shifted_matrix = []

    for row in matrix:
        _steps = steps % len(row)
        if _steps == 0:
            shifted_row = row[:]
        else:        
            shifted_row = row[-_steps:] + row[:-_steps]
        shifted_matrix.append(shifted_row)

    return shifted_matrix


with open('input1.txt') as f:
    matrix = [[int(num) for num in line.split()] for line in f]

steps = int(input("Введите число шагов: "))

shifted_matrix = shift_rows(matrix, steps)
for row in shifted_matrix:
    print(row)

with open('output1.txt', 'w') as f:
    for row in shifted_matrix:
        row_str = ' '.join(map(str, row))
        f.write(row_str + '\n')

