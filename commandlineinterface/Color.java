package commandlineinterface;

class Color {
    
    private static final class ANSI {
        static final String RESET = "\u001b[0m";
        static final String YELLOW = "\u001b[33m";
        static final String BLACK = "\u001B[30m";
        static final String RED = "\u001B[31m";
        static final String GREEN = "\u001B[32m";
        static final String BLUE = "\u001B[34m";
        static final String PURPLE = "\u001B[35m";
        static final String CYAN = "\u001B[36m";
        static final String WHITE = "\u001B[37m";
    }

    public static final String makeYellow (String string) {
        return ANSI.YELLOW + string + ANSI.RESET;
    }

    public static final String makeWhite (String string) {
        return ANSI.WHITE + string + ANSI.RESET;
    }

    public static final String makeRed (String string) {
        return ANSI.RED + string + ANSI.RESET;
    }

}