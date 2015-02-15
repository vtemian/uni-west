from abc import ABCMeta, abstractmethod


class OutputDevice(object):
    __meta__ = ABCMeta

    @abstractmethod
    def pprint(self, content):
        pass

    @abstractmethod
    def table(self, results):
        pass