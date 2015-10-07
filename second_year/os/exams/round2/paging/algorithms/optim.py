from copy import copy

from .base import Algorithm


class OptimalAlgorithm(Algorithm):
    def compute(self):
        items = 0
        table = []
        cache = []
        data = self.data[::-1]

        while len(data):
            items += 1
            item = data.pop()

            if item not in cache:
                if len(cache) == self.cache_size:
                    max_index = len(data) + 1
                    for i in cache:
                        if i not in data:
                            cache.pop(cache.index(i))
                            break

                        index = data.index(i)
                        if index <= max_index:
                            max_index = index

                    else:
                        if max_index != len(data) + 1:
                            cache.pop(cache.index(data[max_index]))

                cache.insert(0, item)
            table.append(copy(cache))
        return table
