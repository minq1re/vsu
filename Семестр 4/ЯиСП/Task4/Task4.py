def convertIntToRoman(n):
    if (n >= 1000):
        return 'M' + convertIntToRoman(n - 1000)
            
    elif (n >= 900):
        return 'CM' + convertIntToRoman(n - 900)
    elif (n >= 500):
        return 'D' + convertIntToRoman(n - 500)
    
    elif (n >= 400):
        return 'CD' + convertIntToRoman(n - 400)
    elif (n >= 100):
        return 'C' + convertIntToRoman(n - 100)

    elif (n >= 90):
        return 'XC' + convertIntToRoman(n - 90)
    elif (n >= 50):
        return 'L' + convertIntToRoman(n - 50)

    elif (n >= 40):
        return 'XL' + convertIntToRoman(n - 40)
    elif (n >= 10):
        return 'X' + convertIntToRoman(n - 10)

    elif (n >= 9):
        return 'IX' + convertIntToRoman(n - 9)
    elif (n >= 5):
        return 'V' + convertIntToRoman(n - 5)

    elif (n >= 4):
        return 'IV' + convertIntToRoman(n - 4)
    elif (n >= 1):
        return 'I' + convertIntToRoman(n - 1)
        
    else:
        return ''


roman_map = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}

def convertRomanToInt(s):
    end = len(s) - 1
    # arr = list(s)
    result = roman_map[s[end]]
    
    for i in range(end - 1, -1, -1):
        arabian = roman_map[s[i]]
        if arabian < roman_map[s[i + 1]]:
            result -= arabian
        else:
            result += arabian
    
    return result

with open('input1.txt', 'r') as f1:
    s1 = f1.read()

print(convertIntToRoman(int(s1)))
print()

with open('input2.txt', 'r') as f2:
    s2 = f2.read()

print(convertRomanToInt(s2))
print()

with open('input3.txt', 'r') as f3:
    s3 = f3.read()
if (s3.isdigit()):
    print(convertIntToRoman(int(s3)))
else:
    print('Неверный тип данных, введите тип данных int')
print()

with open('input4.txt', 'r') as f4:
    s4 = f4.read()
if (not s4.isdigit()):
    print(convertRomanToInt(int(s4)))
else:
    print('Неверный тип данных, введите тип данных str')
print()

with open('input5.txt', 'r') as f5:
    s5 = f5.read()
if (not s5.isdigit()):
    print(convertRomanToInt(s5))
else:
    print('Неверный тип данных, введите тип данных str')


