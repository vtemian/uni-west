class Dictionary(object):
    def __init__(self, db, input_device, output_device):
        self.db = db
        self.input_device = input_device
        self.output_device = output_device

    def start(self):
        self.db.connect()

        while(self.input_device.keep):
            self.output_device.show(self.db[self.input_device.read()])
