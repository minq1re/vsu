import math

import numpy
import numpy as np
import pandas as pd
from tabulate import tabulate


def entropy(probabilities):
    return -sum([p * numpy.log2(p) for p in probabilities.values() if p > 0])


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


def remove_binary_prefix(binary_number):
    return binary_number.replace('0.', '')


def truncate_binary(binary_number, num_digits_after_point):
    point_index = binary_number.find('.')

    if point_index != -1:
        return binary_number[:point_index + num_digits_after_point + 1]
    else:
        return binary_number


def bin_for_arr(arr):
    res = []
    for i in arr:
        res.append(float_to_binary(i))
    return res


def get_codes(arr, array_of_lengths):
    res = []
    for i, l in zip(arr, array_of_lengths):
        res.append(remove_binary_prefix(truncate_binary(i, l)))
    return res


def get_len(p):
    return math.ceil(-math.log(p, 2)) + 1


def get_len_for_arr(arr):
    res = []
    for i in arr:
        res.append(get_len(i))

    return res


def get_croft(lengths):
    k = 0
    for i in lengths:
        k += math.pow(2, -i)
    return k


def check_probabilities(probabilities):
    total = sum(probabilities.values())
    if not math.isclose(total, 1.0, rel_tol=1e-9):
        raise ValueError(f"Sum of probabilities is {total}, but should be close to 1.")


def calculate_cumulative_prob(probabilities):
    return [sum(probabilities[:i]) for i in range(1, len(probabilities) + 1)]


def calculate_delta(probabilities, cumulative):
    return cumulative + probabilities / 2


if __name__ == '__main__':
    probabilities = {
        "Z1": 0.355,
        "Z2": 0.03,
        "Z3": 0.01,
        "Z4": 0.1,
        "Z5": 0.28,
        "Z6": 0.073,
        "Z7": 0.034,
        "Z8": 0.046,
        "Z9": 0.018,
        "Z10": 0.054
    }
    try:
        check_probabilities(probabilities)
        prob_val = np.array(list(probabilities.values()))

        sorted_freqs = sorted(probabilities.items(), key=lambda x: x[1], reverse=True)
        print("Sorted Frequencies:")
        for char, freq in sorted_freqs:
            print(f"{char}={freq}")
        print('\n')

        cumulative_prob = (np.array(calculate_cumulative_prob(prob_val)) - prob_val)

        print("Cumulative prob:")
        for i in range(len(cumulative_prob)):
            print(f"q{i + 1}={cumulative_prob[i]}")
        print('\n')

        deltas = calculate_delta(prob_val, cumulative_prob)
        deltas_bin = bin_for_arr(deltas)

        print("Delta:")
        for i in range(len(deltas)):
            print(f"ẟ{i + 1}={deltas[i]}={deltas_bin[i]}")
        print('\n')

        lengths = np.array(get_len_for_arr(prob_val))

        print("Lengths:")
        for i in range(len(probabilities)):
            print(f"l{i + 1}={lengths[i]}")
        print('\n')

        codes = get_codes(deltas_bin, lengths)

        print("Codes:")
        for i in range(len(probabilities)):
            print(f"z{i + 1}={codes[i]}")
        print('\n')

        # Создаем DataFrame
        df = pd.DataFrame(columns=['X_m', 'P_m', 'q_m', 'ẟ_m', 'ẟ_m (двоичный код)', 'l_m', 'Код'])

        # Заполняем DataFrame
        for key, value in probabilities.items():
            i = list(probabilities.keys()).index(key)
            df = df._append({
                'X_m': key,
                'P_m': value,
                'q_m': round(cumulative_prob[i], 10),
                'ẟ_m': round(deltas[i], 10),
                'ẟ_m (двоичный код)': truncate_binary(deltas_bin[i], 13),
                'l_m': lengths[i],
                'Код': codes[i]
            }, ignore_index=True)

        # Выводим таблицу
        df.round(10)
        pd.set_option('display.precision', 10)
        print(tabulate(df, headers='keys', tablefmt='pretty'))
        print('\n')
        with open('output.txt', 'w', encoding='utf-8') as f:
            f.write(tabulate(df, headers='keys', tablefmt='pretty'))

        L = sum(lengths * prob_val)
        H = entropy(probabilities)
        print(f"L(средняя длина кода)={L}")
        print(f"H ={H}")
        print(f"r(избыточность)={L - H}")
        print(f"k(неравенстов Крофта)={get_croft(lengths)}<=1")

    except ValueError as e:
        print(e)
