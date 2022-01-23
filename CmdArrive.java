public class CmdArrive extends RecordedCommand {
    private Item i;
    @Override
    public void execute(String cmdParts[]) throws ExItemIdInUse  {
        Club club = Club.getInstance();
        Item i2 = club.findItem(cmdParts[1]);
        if (i2 != null)
            throw new ExItemIdInUse(i2);
        i = new Item(cmdParts[1], cmdParts[2]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Club club = Club.getInstance();
        club.removeItem(i);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Club club = Club.getInstance();
        club.addItem(i);
        addUndoCommand(this);
    }
}
