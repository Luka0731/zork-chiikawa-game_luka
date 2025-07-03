package ch.noseryoung.blj.util.frontendSamples;

import java.util.List;


public class Output {

    // |----- format text -----|

    public static String formatStyle(String text, ConsoleStyle style) {
        return style.format(text);
    }

    public static void printStyled(String text, ConsoleStyle style) {
        System.out.print(style.format(text));
    }

    public static void printlnStyled(String text, ConsoleStyle style) {
        System.out.println(style.format(text));
    }



    // |----- error output -----|

    public static String formatError (String text, String cause) {
        if (cause == null) {
            return ConsoleStyle.RED.format("ERROR: " + text + "! ");
        } else {
            return ConsoleStyle.RED.format("ERROR: '" + cause + "' " + text + "! ");
        }
    }

    public static void printError (String text, String cause) {
        System.out.print(formatError(text, cause));
    }

    public static void printError (String text) {
        System.out.print(formatError(text, null));
    }

    public static void printlnError (String text, String cause) {
        System.out.println(formatError(text, cause));
    }

    public static void printlnError (String text) {
        System.out.println(formatError(text, null));
    }



    // |----- other methods -----|

    public static int menu (String title, List<String> options) {
        String optionText = "| ";
        int index = 0;
        for (String option : options) {
            optionText += index++ + " = " + option + " | ";
        }
        String upperBarText = "/// " + formatStyle(title, ConsoleStyle.BOLD) + " ///"
                + "/".repeat(optionText.length() - 8 - title.length());
        String lowerBarText = "/".repeat(optionText.length());
        System.out.print(upperBarText + "\n" + Language.MENU_OPTIONS_EXPLANATION.getText()
                + "\n" + optionText + "\n" + lowerBarText + "\n");
        return Input.getInteger(": ", 0, options.size() - 1);
    }

    public static void printProgressBar(int current, int total, int barLength) {
        float percent = (float) current / total;
        int progress = (int) (barLength * percent);

        String bar = "[" + "=".repeat(progress) + " ".repeat(barLength - progress) + "] "
                + (int)(percent * 100) + "%";

        printlnStyled(bar, ConsoleStyle.GREEN);
    }

    public static void printlnMultiColored(String text, ConsoleStyle... styles) {
        if (styles.length == 0) {  System.out.println(text); return;  }
        int segmentLength = text.length() / styles.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < styles.length; i++) {
            int start = i * segmentLength;
            int end = (i == styles.length - 1) ? text.length() : (i + 1) * segmentLength;
            result.append(styles[i].format(text.substring(start, end)));
        }
        System.out.println(result);
    }

    public static void printlnRainbowColored(String text) {
        printlnMultiColored(text, ConsoleStyle.RED, ConsoleStyle.BRIGHT_RED, ConsoleStyle.YELLOW, ConsoleStyle.GREEN,
                ConsoleStyle.CYAN, ConsoleStyle.BLUE, ConsoleStyle.PURPLE);
    }
}
