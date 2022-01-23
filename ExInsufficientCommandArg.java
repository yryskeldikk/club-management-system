public class ExInsufficientCommandArg extends Exception {
    public ExInsufficientCommandArg() {super("Insufficient command arguments.");}
    public ExInsufficientCommandArg(String msg) {super(msg);}
    
}
