from copy import copy

from .base import Algorithm


class ClockAlgorithm(Algorithm):
    def compute(self):
        table = []
        cache = []
        references = []
        data = self.data[::-1]

        while len(data):
            item = data.pop()

            if item not in cache:
                if len(cache) == self.cache_size:
                    out = False
                    n = len(cache) - 1
                    while n >= 0:
                        if cache[n] in references:
                            references.pop(references.index(cache[n]))
                            n -= 1
                            continue
                        else:
                            out = True
                            cache.pop(n)
                            break

                    if not out:
                        cache.pop()

                cache.insert(0, item)
            else:
                references.append(item)

            table.append(copy(cache))
        return table
