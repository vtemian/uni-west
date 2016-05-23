from Queue import Queue


test_cases = int(raw_input())


def build():
    nodes, edges = map(int, raw_input().split())
    graph = {}
    for i in range(nodes):
        graph[i+1] = {
                'distance': -1,
                'kids': []
            }


    while edges:
        parent, child = map(int, raw_input().split())
        if child not in graph[parent]['kids']:
            graph[parent]['kids'].append(child)
        if parent not in graph[child]['kids']:
            graph[child]['kids'].append(parent)
        edges -= 1

    return nodes, graph


def solve():
    out = ""
    nodes, graph = build()
    root = int(raw_input())
    graph[root]['distance'] = 0

    queue = Queue()
    queue.put(root)

    while not queue.empty():
        current = queue.get()

        for node in graph[current]['kids']:
            if graph[node]['distance'] == -1:
                graph[node]['distance'] = graph[current]['distance'] + 1
                queue.put(node)

    for node in graph:
        if node == root:
            continue

        if graph[node]["distance"] > -1:
            out += "%s " % (graph[node]["distance"] * 6)
        else:
            out += "-1 "

    print out

while test_cases:
    solve()
    test_cases -= 1
