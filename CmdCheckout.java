public class CmdCheckout extends RecordedCommand {
    private Member m;
    private Item i;
    private ItemStatus s;

    @Override
    public void execute(String cmdParts[]) throws ExMemberNotFound, ExItemNotAvailable, ExItemNotFound, ExLoanQuotaExceeded, ExInsufficientCommandArg {
        if (cmdParts.length < 3)
            throw new ExInsufficientCommandArg();
        Club club = Club.getInstance();
        m = club.findMember(cmdParts[1]);
        i = club.findItem(cmdParts[2]);
        if (m == null)
            throw new ExMemberNotFound();
        if (i == null) 
            throw new ExItemNotFound();
        if (i.getStatus() instanceof ItemStatusBorrowed)
            throw new ExItemNotAvailable();
        if (i.getStatus() instanceof ItemStatusOnhold && m != ((ItemStatusOnhold)i.getStatus()).getOnholdMember()) 
            throw new ExItemNotAvailable();
        if (m.getNoBorrowedItems() == 6)
            throw new ExLoanQuotaExceeded();
        s = i.getStatus();
        m.borrow(i);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        if (s instanceof ItemStatusOnhold) {
            m.unborrow(i);
            i.setStatus(new ItemStatusOnhold(m, i));
        }
        else {
            m.unborrow(i);
        }
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        m.borrow(i);  
        addUndoCommand(this);
    }
}