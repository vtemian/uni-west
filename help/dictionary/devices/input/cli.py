from .base import BaseInputDevice


class CLIInput(BaseInputDevice):
    def read(self):
        keep = raw_input("Do you want to continue? [Y/n]").upper()
        self.keep = keep == 'Y'

        content = raw_input("Get your text here: ")
        return content
