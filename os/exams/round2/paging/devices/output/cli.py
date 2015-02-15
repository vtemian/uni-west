from pprint import pprint as pp

from .base import OutputDevice


class CliOutputDevice(OutputDevice):
    def pprint(self, content):
        pp(content)

    def table(self, result):
        pp(result)
        faults = len(set(["".join(map(str, x)) for x in result]))
        print "we have %s faults and %s hits" % (faults, len(result) - faults)
