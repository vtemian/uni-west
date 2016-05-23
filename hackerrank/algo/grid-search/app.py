def solve(grid, pattern):
    for line_index, line in enumerate(grid):
        for column_index, letter in enumerate(line):
            # check if we can start checking
            if letter == pattern[0][0]:
                aux_line_index = line_index
                found = True

                for pattern_line_index, pattern_line in enumerate(pattern):
                    aux_column_index = column_index
                    for pattern_column_index, to_check in enumerate(pattern_line):
                        if line_index >= len(grid) or aux_column_index >= len(line):
                            found = False
                            break
                        if to_check != grid[aux_line_index][aux_column_index]:
                            found = False
                            break
                        aux_column_index += 1

                    aux_line_index += 1
                    if not found:
                        break

                if found:
                    return "YES"

    return "NO"


n = int(raw_input())
while n:
    grid = []
    pattern = []
    r, c = map(int, raw_input().split())

    i = 0
    while i < r:
        grid.append(raw_input())
        i += 1

    r1, c1 = map(int, raw_input().split())
    i = 0
    while i < r1:
        pattern.append(raw_input())
        i += 1

    print solve(grid, pattern)

    n -= 1
