from base import Parser


class TextParser(Parser):
    def parse(self, delimiter=" "):
        procs = []

        with open(self.source) as f:
            for index, line in enumerate(list(f)):
                proc = line.strip().split(delimiter)
                procs.append({
                    'arrival': int(proc[0]),
                    'burst': int(proc[1]),
                    'index': index + 1
                })

        return procs
