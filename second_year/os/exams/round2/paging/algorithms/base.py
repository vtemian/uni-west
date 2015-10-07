from abc import ABCMeta, abstractmethod


class Algorithm(object):
    __meta__ = ABCMeta

    def __init__(self, data, cache_size):
        self.data = data
        self.cache_size = cache_size

    @abstractmethod
    def compute(self):
        pass
