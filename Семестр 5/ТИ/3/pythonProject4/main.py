import math as math
def unary_encode(number):
    if number < 1:
        raise ValueError("Unary encoding is not defined for numbers less than 1")
    return '1' * number + '0'
#код голомба
m=3
t=2**m
i=43
u=i/t+1
o=i % t
print("код голомба")
print(unary_encode(math.floor(u)),bin(o))
#скорость кода R
m=math.log2(21)
n=85
r=m/n
print("скорость кода R")
print(r)
#код элайеса
l= math.floor(math.log2(61)) + 2 * math.floor(math.log2(math.floor(math.log2(61)))) + 2
print("код элайеса")
print(l)
