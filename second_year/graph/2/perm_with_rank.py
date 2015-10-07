from factorial import factorial


def perm_with_rank(n, rank):
    if n <= 0:
        return []

    first_element = int(rank / factorial(n - 1)) + 1
    new_rank = rank - ((first_element - 1) * factorial(n - 1))

    return [first_element] + perm_with_rank(n - 1, new_rank)


print perm_with_rank(5, 50)
