from .base import Parser


class TextParser(Parser):

    def __init__(self, source):
        self.source = source

    def parse(self):
        with open(self.source) as f:
            return map(int, f.read().split())
