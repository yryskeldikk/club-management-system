public class ExBorrowedBySameMember extends Exception {
    public ExBorrowedBySameMember() {super("The item is already borrowed by the same member.");}
    public ExBorrowedBySameMember(String msg) {super(msg);}
}

