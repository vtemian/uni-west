from dictionary import Dictionary
from dictionary.databases import RawDatabase

from dictionary.devices.input import CLIInput
from dictionary.devices.output import DummyOutput


if __name__ == '__main__':
    input_device = CLIInput()
    out_device = DummyOutput()
    db = RawDatabase(connection_string='dicten-ro.txt')

    dictionary = Dictionary(db, input_device, out_device)
    dictionary.start()
