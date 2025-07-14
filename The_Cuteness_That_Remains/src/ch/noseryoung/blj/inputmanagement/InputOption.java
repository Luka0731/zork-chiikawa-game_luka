package ch.noseryoung.blj.inputmanagement;

import ch.noseryoung.blj.actions.Action;

import java.util.List;

public class InputOption {
    private String key;
    private List<InputParameter> parameters;
    private Action action;

    public boolean matches(String inputKey) {
        return inputKey.equalsIgnoreCase(key);
    }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public List<InputParameter> getParameters() { return parameters; }
    public void setParameters(List<InputParameter> parameters) { this.parameters = parameters; }
    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }
}