import sys

t = int(raw_input())

while t:
    m, n = map(int, raw_input().split())
    r1 = r2 = 0
    s = 0
    k = 1
    f, to = map(int, raw_input().split())
    move = abs(f - to)
    queries = [
        [(move, to), (0, 0)],
        [(0, 0), (move, to)]
    ]
    small = 0
    while k < n:
        f, to = map(int, raw_input().split())
        move = abs(f - to)
        new_queries = []
        small = sys.maxint
        for branch in queries:
            r1 = branch[0]
            r2 = branch[1]
            if r1[0] == 0:
                move_r1 = move
            else:
                move_r1 = abs(r1[1] - f) + r1[0] + move

            if r2[0] == 0:
                move_r2 = move
            else:
                move_r2 = abs(r2[1] - f) + r2[0] + move

            new_queries.append([(move_r1, to), (r2[0], r2[1])])
            new_queries.append([(r1[0], r1[1]), (move_r2, to)])

            small = min(move_r1 + r2[0], move_r2 + r1[0], small)

        queries = new_queries
        k += 1

    print small
    t -= 1
