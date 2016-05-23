import sys
from Queue import PriorityQueue


def dijkstra(graph, start_node):
    queue = PriorityQueue()

    for end_node in graph[str(start_node)]["neighbours"]:
        queue.put((graph[str(start_node)]["neighbours"][str(end_node)], (start_node,
                   end_node)))

    visited = []

    while not queue.empty():
        cost, (start_node, end_node) = queue.get()
        if [start_node, end_node] in visited:
            continue

        distance = graph[str(end_node)]["distance"]
        if distance == sys.maxint or distance > cost:
            graph[str(end_node)]["distance"] = cost

        new_cost = graph[str(end_node)]["distance"]
        for new_node in graph[str(end_node)]["neighbours"]:
            cost = graph[str(end_node)]["neighbours"][str(new_node)] + new_cost
            queue.put((cost, (end_node, new_node)))

        visited.append([start_node, end_node])



if __name__ == "__main__":
    graph = {}
    nodes = []
    t = int(raw_input())

    for i in range(t):
        n, m = map(int, raw_input().split(" "))
        for j in range(m):
            x, y, r = map(int, raw_input().split(" "))

            if str(x) not in graph:
                graph[str(x)] = {
                    "distance": sys.maxint,
                    "neighbours": {},
                }
            if str(y) not in graph:
                graph[str(y)] = {
                    "distance": sys.maxint,
                    "neighbours": {},
                }

            graph[str(x)]["neighbours"][str(y)] = r
            graph[str(y)]["neighbours"][str(x)] = r
        s = raw_input()

        dijkstra(graph, int(s))

        total_cost = ""
        for i in range(2, n+1):
            cost = graph[str(i)]["distance"]
            if cost == sys.maxint:
                cost = -1

            total_cost += "%s " % cost

        print total_cost
