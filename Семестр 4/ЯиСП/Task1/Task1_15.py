def shift(mas, step):
    if step == 0:
        return mas
    elif step < 0:
        for i in range(abs(step)):
            mas.append(mas.pop(0))
    else:
        for i in range(step):
            mas.insert(0, mas.pop())

def shift2(mas, _step):
    step = _step % len(mas)
    print(step)
    if step == 0:
        return mas
    return (mas[-step:] + mas[:-step])
    

with open('input1.txt') as f:
    content = f.read().strip()
    nums = [int(num) for num in content.split(', ')]

step = int(input("Введиет число шагов: "))
nums = shift2(nums, step)
print(nums)

output_content = ', '.join(str(num) for num in nums)
with open('output1.txt', 'w') as output_file:
    output_file.write(output_content)
