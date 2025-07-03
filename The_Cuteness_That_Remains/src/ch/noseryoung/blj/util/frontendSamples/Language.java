package ch.noseryoung.blj.util.frontendSamples;



// This enum makes the quick-set and also your console texts available in the languages you want
// In addition, you can use this enum to store frequently used and specially formatted texts
public enum Language {

    // From quick-set
    EM_WRONG_DATA_TYPE("Invalid data type", "Ungültiger Datentyp"),
    EM_CHAR_WRONG_DATA_TYPE("Please enter a single character", "Bitte gebe ein einzelnes Zeichen ein"),
    EM_BOOLEAN_WRONG_DATA_TYPE("Invalid data type! Please enter true/false (or yes/no, 1/0, y/n)",
            "Ungültiger Datentyp! Bitte wahr/falsch (oder ja/nein, 1/0, j/n) eingeben"),
    EM_OUTSIDE_VALUE_RANGE("Lies outside the valid value range",
            "Liegt außerhalb des gültigen Wertebereichs"),
    EM_OUTSIDE_LENGTH_RANGE("Lies outside the valid text length range",
            "Liegt außerhalb der gültigen Textlänge"),
    MENU_OPTIONS_EXPLANATION("Enter a number", "Gebe eine Zahlen ein");
    // Your texts



    // Languages
    private final String english;
    private final String german;


    // Settings
    public int language = 0;
    private final int amountLanguages = 2;



    Language(String english, String german) {
        this.english = english;
        this.german = german;
    }

    public String getText() {
        return switch (language) {
            case 0 -> english;
            case 1 -> german;
            default -> Output.formatStyle("ERROR: language not found", ConsoleStyle.RED);
        };
    }

    public void changeLanguage(int language) {
        if (language >= 0 && language < amountLanguages) {
            this.language = language;
        } else {
            System.err.println("ERROR: '" + language + "' is not a valid language-index");
        }
    }
}
