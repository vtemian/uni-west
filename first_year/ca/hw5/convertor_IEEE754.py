#!/bin/python
import sys


def decimal_to_binary(number):
    binary = ""
    reminder = number % 2

    while number > 0:
        number = number / 2
        binary += str(reminder)
        reminder = number % 2

    return binary[::-1]


def compute_mantisa(decimal):
    mantisa = ""
    number = float("0.%s" % decimal)

    while number > 0.0 and len(mantisa) < 23:
        try:
            real, rational = str(float(number * 2.0)).split(".")
        except:
            real = number = "0"
        mantisa += real
        number = float("0.%s" % rational)

    return mantisa


def convert_dec_to_ieee(number):
    sign = 1 if number[0] == "-" else 0
    if number[0] == "-":
        number = number[1:]

    try:
        exponent, mantisa = number.split(".")
    except:
        exponent = number
        mantisa = ""

    # compute exponent and mantisa
    exponent = decimal_to_binary(int(exponent))
    mantisa = "%s%s" % (exponent[1:], compute_mantisa(mantisa))

    exponent = 127 + len(exponent) - 1
    exponent = decimal_to_binary(exponent)

    while len(mantisa) < 23:
        mantisa += "0"

    mantisa = mantisa[:23]

    return "%s%s%s" % (sign, exponent, mantisa)


def binary_to_dec(number):
    number = number[::-1]
    result = 0
    index = 0

    while index < len(number):
        result += 2**index * int(number[index])
        index += 1

    return result


def hexa_to_binary(number):
    hexa = {
        '0': "0000",
        '1': "0001",
        '2': "0010",
        '3': "0011",
        '4': "0100",
        '5': "0101",
        '6': "0110",
        '7': "0111",
        '8': "1000",
        '9': "1001",
        'A': "1010",
        'B': "1011",
        'C': "1100",
        'D': "1101",
        'E': "1110",
        'F': "1111"
    }
    result = ""
    for i in number:
        result += hexa[i]
    return result


def convert_mantisa_to_dec(mantisa):
    index = 1
    result = 0.0

    while index - 1< len(mantisa):
        result += int(mantisa[index-1]) * 2**(-1 * index)
        index += 1

    return result


def convert_ieee_to_dec(ieee):
    sign = ieee[0]
    exponent = binary_to_dec(ieee[1:9]) - 127
    mantisa = float(convert_mantisa_to_dec(ieee[9:]))
    return str((-1)**int(sign) * (1 + mantisa) * 2**exponent)


def drange(start, stop, step):
    r = start
    while r < stop:
        yield r
        r += step

if __name__ == "__main__":
    print convert_dec_to_ieee("-5.75")

    for i in xrange(-10, 10):
        print "%s -> %s" % (i, convert_dec_to_ieee(str(i)))


    numbers = ["44", "0.15625", "6.125"]
    for number in numbers:
        print "%s -> %s" % (number, convert_dec_to_ieee(number))

    numbers = ["42E48000", "3F880000", "00800000", "C7F00000"]
    for number in numbers:
        binary = hexa_to_binary(number)
        print "%s -> %s" % (number, convert_ieee_to_dec(binary))
