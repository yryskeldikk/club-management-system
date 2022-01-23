public class ExItemIdInUse extends Exception{
    public ExItemIdInUse(Item i) { super("Item ID already in use: " + i.getID() + " " + i.getName()); }
    public ExItemIdInUse(String message) { super(message); }
}
