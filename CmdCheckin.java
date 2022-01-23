public class CmdCheckin extends RecordedCommand {
    private Member m;
    private Item i;
    private Member onhold;

    @Override
    public void execute(String cmdParts[]) throws ExItemNotBorrowedByMember, ExInsufficientCommandArg {
        if (cmdParts.length < 3)
            throw new ExInsufficientCommandArg();
        Club club = Club.getInstance();
        m = club.findMember(cmdParts[1]);
        i = m.findBorrowedItem(cmdParts[2]);
        if (i == null)
            throw new ExItemNotBorrowedByMember();
        if (i.getQueueSize() > 0) {
            m.unborrow(i);
            onhold = i.getOnholdMember();
            i.removeFromQueue(onhold);
            i.setStatus(new ItemStatusOnhold(onhold, i));

            System.out.printf("Item [%s] is ready for pick up by [%s].  On hold due on %s.\n", 
                                i.getID() + " " + i.getName(), onhold.getID() + " " + onhold.getName(), 
                                ((ItemStatusOnhold)i.getStatus()).getDueDate().toString());
        }
        else {
            m.unborrow(i);
        }
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        if (i.getStatus() instanceof ItemStatusOnhold) {
            m.borrow(i);
            i.setStatus(new ItemStatusBorrowed(m, i));
            i.getRequestQueue().add(0, onhold);
            onhold.request(i);
            System.out.printf("Sorry. %s please ignore the pick up notice for %s.\n", onhold.getID() + " "
                                + onhold.getName(), i.getID() + " " + i.getName());
        }
        else {
            m.borrow(i);
        }
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        if (i.getQueueSize() > 0) {
            m.unborrow(i);
            i.removeFromQueue(onhold);
            i.setStatus(new ItemStatusOnhold(onhold, i));
            System.out.printf("Item [%s] is ready for pick up by [%s].  On hold due on %s.\n", 
                                i.getID() + " " + i.getName(), onhold.getID() + " " + onhold.getName(), 
                                ((ItemStatusOnhold)i.getStatus()).getDueDate().toString());
        }
        else {
            m.unborrow(i);
        }
        addUndoCommand(this);
    }
}
