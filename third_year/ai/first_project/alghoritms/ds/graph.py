class Graph(object):
    def __init__(self):
        self.edges = {}

    def neighbors(self, id):
        return self.edges[id]


class SquareGrid(object):
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.walls = []

    def in_bounds(self, id):
        (x, y) = id
        return 0 <= x < self.width and 0 <= y < self.height

    def passable(self, id):
        return id not in self.walls

    def neighbors(self, id):
        (x, y) = id[2:]
        results = [(x+1, y), (x, y-1), (x-1, y), (x, y+1)]
        if (x + y) % 2 == 0: results.reverse() # aesthetics
        results = filter(self.in_bounds, results)
        results = filter(self.passable, results)
        results = [(self.scale_x(x), self.scale_y(y), x, y) for x,y in results]
        return results

    def scale_x(self, x):
        return ((x - 1) * (self.max_x - self.min_x)) / 7 + self.min_x

    def scale_y(self, y):
        return ((y - 1) * (self.max_y - self.min_y)) / 7 + self.min_y


class GridWithWeights(SquareGrid):
    def __init__(self, width, height):
        super(GridWithWeights, self).__init__(width, height)
        self.weights = {}

    def cost(self, a, b):
        return self.weights.get(b, 1)
