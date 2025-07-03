package ch.noseryoung.blj.util.frontendSamples;

import java.util.List;
import java.util.Scanner;


public class Input {

    private final static Scanner scanner = new Scanner(System.in);



    // Main input
    private static <T extends Comparable<T>> T readInput(String text, java.util.function.Function<String, T> parser, T min, T max, List<T> allowedValues) {
        while (true) {
            System.out.print(text);
            String line = scanner.nextLine().trim();
            try {
                T value = parser.apply(line);

                if (allowedValues != null && !allowedValues.isEmpty()) {
                    if (allowedValues.contains(value)) {
                        return value;
                    } else {
                        Output.printError(Language.EM_OUTSIDE_VALUE_RANGE.getText(), value.toString());
                        continue;
                    }
                }

                if ((min == null || value.compareTo(min) >= 0) && (max == null || value.compareTo(max) <= 0)) {
                    return value;
                } else {
                    Output.printError(Language.EM_OUTSIDE_VALUE_RANGE.getText(), value.toString());
                }
            } catch (Exception e) {
                Output.printError(Language.EM_WRONG_DATA_TYPE.getText());
            }
        }
    }




    // |----- datatype filtered input -----|

    public static int getInteger(String text) {
        return readInput(text, Integer::parseInt, null, null, null);
    }


    public static long getLong(String text) {
        return readInput(text, Long::parseLong, null, null, null);
    }

    public static short getShort(String text) {
        return readInput(text, Short::parseShort, null, null, null);
    }

    public static byte getByte(String text) {
        return readInput(text, Byte::parseByte, null, null, null);
    }

    public static float getFloat(String text) {
        return readInput(text, Float::parseFloat, null, null, null);

    }

    public static double getDouble(String text) {
        return readInput(text, Double::parseDouble, null, null, null);
    }


    public static boolean getBoolean(String text) {
        while (true) {
            System.out.print(text);
            String input = scanner.nextLine().trim();
            if ( input.equalsIgnoreCase("true")
                    ||input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")
                    || input.equalsIgnoreCase("t") || input.equals("1")
                    || input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("j")
                    || input.equalsIgnoreCase("wahr") || input.equalsIgnoreCase("w")) {
                return true;
            } else if (input.equalsIgnoreCase("false")
                    || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")
                    || input.equalsIgnoreCase("f") || input.equals("0")
                    || input.equalsIgnoreCase("falsch") || input.equalsIgnoreCase("nein")) {
                return false;
            } else {
                Output.printError(Language.EM_BOOLEAN_WRONG_DATA_TYPE.getText());
            }
        }
    }

    public static char getChar(String text) {
        while (true) {
            System.out.print(text);
            String input = scanner.nextLine().trim();
            if (input.length() == 1) {
                return input.charAt(0);
            } else {
                Output.printError(Language.EM_CHAR_WRONG_DATA_TYPE.getText());
            }
        }
    }

    public static String getString(String text) {
        System.out.print(text);
        return scanner.nextLine().trim();
    }

    // TODO: Date




    // |----- input with max and min -----|

    public static int getInteger(String text, int min, int max) {
        return readInput(text, Integer::parseInt, min, max, null);
    }

    public static double getDouble(String text, double min, double max) {
        return readInput(text, Double::parseDouble, min, max, null);
    }



    // |----- input with only selected values -----|

    public static int getInteger(String text, List<Integer> allowedValues) {
        return readInput(text, Integer::parseInt, null, null, null);
    }

    public static String getString(String text, List<String> allowedValues) {
        return readInput(text, String::toString, null, null, allowedValues);
    }

    public static String getString(String text, int minLength, int maxLength) {
        while (true) {
            System.out.print(text);
            String input = scanner.nextLine().trim();

            if (input.length() >= minLength && input.length() <= maxLength) {
                return input;
            } else {
                Output.printError(Language.EM_OUTSIDE_LENGTH_RANGE.getText(), input);
            }
        }
    }

    public static String getString(String text, int mandatoryLength) {
        return getString(text, mandatoryLength, mandatoryLength);
    }



    // |----- other methods -----|

    public static void waitUntilEnterPressed() {
        scanner.nextLine();
    }

}