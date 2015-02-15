from abc import ABCMeta, abstractmethod


class Parser(object):
    __meta__ = ABCMeta

    def __init__(self, source):
        self.source = source

    @abstractmethod
    def schedule(self):
        pass
