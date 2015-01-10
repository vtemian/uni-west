from abc import abstractmethod, ABCMeta


class BaseDatabase(object):
    __meta__ = ABCMeta

    def __init__(self, *args, **kwargs):
        for key, value in kwargs.iteritems():
            setattr(self, key, value)

    @abstractmethod
    def connect(self):
        pass

    @abstractmethod
    def __getitem__(self):
        pass

    @abstractmethod
    def __setitem__(self):
        pass
