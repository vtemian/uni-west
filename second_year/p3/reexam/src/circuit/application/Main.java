package circuit.application;

import circuit.application.devices.input.CliInputDevice;
import circuit.application.devices.output.CliOutputDevice;

public class Main {
    public static void main(String [ ] args) {
        CliInputDevice in = new CliInputDevice();
        CliOutputDevice out = new CliOutputDevice();

        Application app = new Application(in, out);
        app.run();
    }
}
