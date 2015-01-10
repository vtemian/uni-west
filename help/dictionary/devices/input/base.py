from abc import ABCMeta, abstractmethod


class BaseInputDevice(object):
    __meta__ = ABCMeta

    def __init__(self):
        self.keep = True

    @abstractmethod
    def read(self):
        pass
