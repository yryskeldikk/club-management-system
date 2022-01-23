public class ExItemNotBorrowedByMember extends Exception {
    public ExItemNotBorrowedByMember() {super("The item is not borrowed by this member.");}
    public ExItemNotBorrowedByMember(String msg) {super(msg);}
}
