import pandas as pd
import math
import numpy as np
from tabulate import tabulate


def calculate_cumulative_prob(probabilities):
    return [sum(probabilities[:i]) for i in range(1, len(probabilities) + 1)]


def initialize_dataframe(columns):
    return pd.DataFrame(columns=columns)

def get_last_f_g(df):

    last_row = df.iloc[-1]

    return last_row['F(si)'], last_row['G(si)']


def get_len(g):
    return math.ceil(-math.log(g,2)) + 1

def get_x_decimal_fraction_of_a_binary_number(f, g):
    print(f + g / 2)
    return float_to_binary(f + g / 2)

def truncate_binary(binary_number, num_digits_after_point):

    point_index = binary_number.find('.')

    if point_index != -1:
        return binary_number[:point_index + num_digits_after_point + 1]
    else:
        return binary_number

def float_to_binary(num):

    integer_part = int(num)
    fractional_part = num - integer_part
    binary_integer_part = bin(integer_part)[2:]
    binary_fractional_part = '.'
    while fractional_part:

        fractional_part *= 2
        if fractional_part >= 1:

            binary_fractional_part += '1'

            fractional_part -= 1

        else:

            binary_fractional_part += '0'
    binary_number = binary_integer_part + binary_fractional_part

    return binary_number

def binary_to_decimal(binary_str):

    parts = binary_str.split('.')
    integer_part = parts[0]
    fractional_part = parts[1] if len(parts) > 1 else '0'

    integer_decimal = int(integer_part, 2)

    fractional_decimal = 0
    for i, digit in enumerate(fractional_part):
        fractional_decimal += int(digit) * 2 ** -(i + 1)

    decimal_number = integer_decimal + fractional_decimal

    return decimal_number

def remove_binary_prefix(binary_number):
    return binary_number.replace('0.', '')

def are_floats_equal(a, b, tolerance=1e-10):
    return abs(a - b) <= tolerance

def arithmetic_coding(sequence, symbols, probabilities, cumulative_prob):
    df = initialize_dataframe(['Шаг k', 'si', 'sk', 'p(si)', 'q(si)', 'F(si)', 'G(si)'])
    F, G = 0, 1
    G_previous = 1
    F_list, G_list = [0], [1]

    # Добавление нулевого шага
    df = df._append({'Шаг k': '0', 'si': '-', 'sk': '-', 'p(si)': '-', 'q(si)': '-', 'F(si)': 0, 'G(si)': G},
                   ignore_index=True)

    for k, s in enumerate(sequence):
        symbol = symbols[s]
        p_si = probabilities[s]
        q_si = cumulative_prob[s] - p_si

        # Вычисление интервалов
        F_si = F + q_si * G_previous
        G_si = p_si * G_previous
        F_list.append(F_si)
        G_list.append(G_si)

        # Обновление интервалов для следующего шага
        G_previous = G_si
        F = F_si
        G = F + G_si

        df = df._append({
            'Шаг k': str(k + 1),
            'si': symbol,
            'sk': ''.join([symbols[seq] for seq in sequence[:k + 1]]),
            'p(si)': p_si,
            'q(si)': q_si,
            'F(si)': F_si,
            'G(si)': G_si
        }, ignore_index=True)

    df.round(10)
    pd.set_option('display.precision', 10)

    return df,F_list, G_list

def arithmetic_decoding(x, F_list, G_list, cumulative_prob, probabilities, symbols, length_of_sequence):
    # Инициализация
    decoded_sequence = []

    # Создание DataFrame для вывода информации
    columns = ['Шаг k', 'Fk', 'Gk', 'Гипотеза s', 'q(s_i)', 'Проверка F_k+q_i*G_k < x', 'Искомый символ', 'Его вероятность']
    df = pd.DataFrame()

    for k in range(length_of_sequence):
        F = F_list[k]
        G = G_list[k]
        symbol_selected = None


        for i, symbol in enumerate(symbols):
            p_si = probabilities[i]
            q_si = cumulative_prob[i]
            Fk_next = F + q_si * G

            # Проверка условия для выбора символа
            condition = not are_floats_equal(Fk_next, x, tolerance=1e-10) and Fk_next > x
            if condition:
                res = f'{Fk_next} > x'
            else:
                res = f'{Fk_next} < x'


            # Запись в DataFrame
            df_row = {'Шаг k': k + 1, 'Fk': F, 'Gk': G, 'Гипотеза s': symbol, 'q(s_i)': q_si, 'Проверка F_k+q_i*G_k < x': res, 'Искомый символ': '', 'Его вероятность': ''}
            df = df._append(df_row, ignore_index=True)

            if condition and symbol_selected is None:
                symbol_selected = symbols[i - 1]
            elif symbol_selected is None and i == 3:
                symbol_selected = symbols[i]

        if symbol_selected:
            decoded_sequence.append(symbol_selected)
            df.at[df.index[-1], 'Искомый символ'] = symbol_selected
            df.at[df.index[-1], 'Его вероятность'] = probabilities[symbols.index(symbol_selected)]

        separator = {col: '---' for col in columns}
        df = df._append(separator, ignore_index=True)

    return decoded_sequence, df

def print_dataframe(df):
    print(df)


if __name__ == "__main__":
    probabilities = np.array([0.2, 0.2, 0.1, 0.5])
    cumulative_prob = np.array(calculate_cumulative_prob(probabilities))
    sequence = [3,1,2,4,3,2,1]

    sequence = np.array(sequence) - 1
    symbols = ['s1', 's2', 's3', 's4']
    print('Комутативные вероятности')
    print(symbols)
    print(cumulative_prob - probabilities)
    print('\n')

    df, F_list, G_list = arithmetic_coding(sequence, symbols, probabilities, cumulative_prob)
    df['F+G'] = df['F(si)'] + df['G(si)']
    print_dataframe(df)
    print('\n')

    f,g = get_last_f_g(df)
    leng = get_len(g)
    print('Длина кода равна:')
    print(leng)
    print('\n')

    x_bin = get_x_decimal_fraction_of_a_binary_number(f, g)
    print(x_bin)
    x_bin_with_leng = truncate_binary(x_bin, leng)
    print(x_bin_with_leng)
    x = remove_binary_prefix(x_bin_with_leng)
    print('Закодированное слово:')
    print(x)
    print('Закодированное слово в десятичой форме:')
    float_x = binary_to_decimal(x_bin_with_leng)
    print(float_x)

    decode, df = arithmetic_decoding(float_x, F_list, G_list, cumulative_prob - probabilities, probabilities, symbols, len(sequence))
    df.round(10)
    pd.set_option('display.precision', 10)
    print(tabulate(df, headers='keys', tablefmt='pretty'))
    print('\n')
    with open('output.txt', 'w', encoding='utf-8') as f:
        f.write(tabulate(df, headers='keys', tablefmt='pretty'))
