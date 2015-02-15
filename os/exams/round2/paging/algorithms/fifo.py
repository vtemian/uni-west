from copy import copy

from .base import Algorithm


class FIFOAlgorithm(Algorithm):
    def compute(self):
        table = []
        cache = []
        data = self.data[::-1]

        while len(data):
            item = data.pop()

            if item not in cache:
                if len(cache) == self.cache_size:
                    cache.pop()

                cache.insert(0, item)
            table.append(copy(cache))
        return table
