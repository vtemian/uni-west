package application;

import stateEngine.interfaces.IState;

import java.util.*;

public class ApplicationController {
    private InputDevice in;
    private OutputDevice out;

    public ApplicationController(){
        in = new InputDevice();
        out = new OutputDevice();
    }

    public void run(){
        List<IState> states = new ArrayList<IState>();
    }
}
