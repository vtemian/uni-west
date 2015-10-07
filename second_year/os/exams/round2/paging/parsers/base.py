from abc import ABCMeta, abstractmethod


class Parser(object):
    __meta__ = ABCMeta

    @abstractmethod
    def parse(self):
        pass
