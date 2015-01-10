from random import randint

from .base import BaseInputDevice


class DummyInput(BaseInputDevice):
    def read(self):
        content = ["friday", "saturday", "sunday", "dog", "cat", "apple"]
        self.keep = randint(1, 5) % 4 == 0
        return content[randint(0, len(content) - 1)]
