public class ExMemberIdInUse extends Exception {
    public ExMemberIdInUse(Member m) { super("Member ID already in use: " + m.getID() + " " + m.getName()); }
    public ExMemberIdInUse(String message) { super(message); }
}
