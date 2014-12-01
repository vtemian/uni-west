def is_permutation(n, permutation):
    # remove duplicate entries
    permutation = set(permutation)
    if len(permutation) > n:
        return False

    # check to see if we have all the elements
    for element in range(1, n + 1):
        if element not in permutation:
            return False

    return True

if __name__ == "__main__":
    n = raw_input("n: ")
    permutation = raw_input("permutation: ")

    if is_permutation(int(n), map(int, permutation.split())):
        print "is a permutation"
    else:
        print "not a permutation"
