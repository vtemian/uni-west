from .base import BaseOutputDevice


class DummyOutput(BaseOutputDevice):
    def show(self, output):
        if output is None:
            print "Sorry, I can't find any answer for you"
        else:
            print "The answer you are looking for is %s" % output
