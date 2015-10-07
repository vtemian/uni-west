package circuit.application.devices.output;

public class CliOutputDevice implements OutputDevice{

    @Override
    public void print(String print) {
        System.out.println(print);
    }
}
