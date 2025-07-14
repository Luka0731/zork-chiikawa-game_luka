package ch.noseryoung.blj.inputmanagement;

public class InputParameter {
    private final String name;
    private final String type;

    public InputParameter(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }
}
