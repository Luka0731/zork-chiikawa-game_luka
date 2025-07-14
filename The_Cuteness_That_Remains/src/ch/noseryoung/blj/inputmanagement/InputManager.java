package ch.noseryoung.blj.inputmanagement;

import ch.noseryoung.blj.engine.Place;
import ch.noseryoung.blj.loaders.InputOptionLoader;
import ch.noseryoung.blj.util.frontendSamples.Input;
import ch.noseryoung.blj.util.frontendSamples.Output;

import java.util.Arrays;
import java.util.List;

public class InputManager {
    public static boolean isRunning;
    private static InputManager instance;
    private final List<InputOption> inputOptions;

    private InputManager() {
        InputOptionLoader loader =  new InputOptionLoader();
        Place storagePlace = new Place(); // todo: find a more efficent way of storing the input options
        inputOptions = loader.loadInputs("data/gamesetup/inputoptions", storagePlace);

    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public String getInput(Place currentPlace) {
        String line = Input.getString("").toLowerCase().trim();
        String[] parts = line.split(" ");
        if (parts.length == 0) return null;

        String key = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        for (InputOption opt : inputOptions) {
            if (opt.matches(key)) {
                List<InputParameter> params = opt.getParameters();
                // check parameter count and if already activated
                if (params.size() != args.length || currentPlace.isInputOptionActivated(key)) {
                    printNotValidInputMessage();
                    return null;
                }
                // save input to state
                for (int i = 0; i < params.size(); i++) {
                    String stateKey = "parameter_" + params.get(i).getName();
                    currentPlace.addState(stateKey, args[i], String.class);
                }
                // execute the action and return the key
                opt.getAction().execute(currentPlace);
                // delete input to state
                for (int i = 0; i < params.size(); i++) {
                    String stateKey = "parameter_" + params.get(i).getName();
                    currentPlace.removeState(stateKey);
                }
                return key;
            }
        }

        printNotValidInputMessage();
        return null;
    }

    private void printNotValidInputMessage() {
        Output.printlnError("Invalid input. Try again."); // todo: make unique messages
    }
}