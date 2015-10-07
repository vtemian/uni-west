from factorial import factorial


def rank_permutation(n, permutation):
    if n == 1:
        return 0
    else:
        new_permutation = []
        for element in permutation[1:]:
            if element > permutation[0]:
                new_permutation.append(element - 1)
            else:
                new_permutation.append(element)

        k = (permutation[0] - 1) * factorial(n - 1)
        return k + rank_permutation(n - 1, new_permutation)


if __name__ == "__main__":
     n = int(raw_input("n: "))
     permutation = map(int, raw_input("n: ").split())
     print "rank of %s is %s" % (permutation, rank_permutation(n, permutation))
