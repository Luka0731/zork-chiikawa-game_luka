package ch.noseryoung.blj.engine;

public enum Direction {
    NORTH, SOUTH, EAST, WEST, UP, DOWN, IN, OUT;

    public static Direction fromString(String input) {
        return switch (input.trim().toLowerCase()) {
            case "north", "n" -> NORTH;
            case "south", "s" -> SOUTH;
            case "east", "e" -> EAST;
            case "west", "w" -> WEST;
            case "up", "u" -> UP;
            case "down", "d" -> DOWN;
            case "in" -> IN;
            case "out" -> OUT;
            default -> null;
        };
    }
}
