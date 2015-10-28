import collections


class Graph(object):
    def __init__(self):
        self.edges = {}

    def neighbors(self, neighbord):
        return self.edges[neighbord]


class Queue(object):
    def __init__(self):
        self.elements = collections.deque()

    def empty(self):
        return len(self.elements) == 0

    def put(self, x):
        self.elements.append(x)

    def get(self):
        return self.elements.popleft()
