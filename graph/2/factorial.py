def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)


if __name__ == "__main__":
    n = raw_input("n: ")
    print "result is:", factorial(int(n))
