import sys

n = int(raw_input())
total = int(raw_input())
nr = map(int, raw_input().split())

index = total / 2
if nr[index] == n:
    print index
    sys.exit(0)

while index < total and index >= 0:
    if nr[index] == n:
        print index
        sys.exit(0)

    print "before: ", index, nr[index]
    if nr[index] < n:
        index = index + (total - index) / 2
    else:
        total = index
        index = index - (index / 2 + 1)

    print "after: ", index
