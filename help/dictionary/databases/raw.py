from .base import BaseDatabase


class RawDatabase(BaseDatabase):
    def __init__(self, *args, **kwargs):
        super(RawDatabase, self).__init__(*args, **kwargs)
        self.content = {}
        self.delimiter = ","

    def connect(self):
        with open(self.connection_string) as db:
            content = db.read().replace("\r\n", "\n")

            for line in content.split("\n"):
                result = line.split(self.delimiter)
                if len(result) != 2:
                    continue
                key, value = line.split(self.delimiter)
                self.content[key] = value

    def __getitem__(self, key):
        key = key.upper()
        return self.content[key] if key in self.content else None

    def __setitem__(self, key, value):
        key = key.upper()
        self.content[key] = value

        with open(self.connection_string, "a") as db:
            db.write("%s%s%s" % (key.upper(), self.delimiter, value.upper()))
