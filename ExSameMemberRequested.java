public class ExSameMemberRequested extends Exception {
    public ExSameMemberRequested() {super("The same member has already requested the item.");}
    public ExSameMemberRequested(String msg) {super(msg);}
}
