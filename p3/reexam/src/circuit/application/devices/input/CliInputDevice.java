package circuit.application.devices.input;

import java.io.Console;
import java.util.Scanner;

public class CliInputDevice implements InputDevice{

    @Override
    public String getCSVFilepath() {
        System.out.print("Path to csv file: ");

        Scanner input = new Scanner(System.in);
        return input.next();
    }
}
