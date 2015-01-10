from abc import ABCMeta, abstractmethod


class BaseOutputDevice(object):
    __meta__ = ABCMeta

    @abstractmethod
    def show(self, output):
        pass
