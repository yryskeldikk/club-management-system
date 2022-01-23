public class CmdRequest extends RecordedCommand {
    private Member m;
    private Item i;

    @Override
    public void execute(String cmdParts[]) throws ExRequestQuotaExceeded, ExMemberNotFound, ExItemNotFound, ExSameMemberRequested, ExBorrowedBySameMember, ExItemCurrentlyAvailable, ExInsufficientCommandArg {
        if (cmdParts.length < 3)
            throw new ExInsufficientCommandArg();
        Club club = Club.getInstance();
        m = club.findMember(cmdParts[1]);
        i = club.findItem(cmdParts[2]);
        if (m == null)
            throw new ExMemberNotFound();
        if (i == null)
            throw new ExItemNotFound();
        if (i.getStatus() instanceof ItemStatusOnhold && ((ItemStatusOnhold)i.getStatus()).getOnholdMember() == m) {
            throw new ExItemCurrentlyAvailable();
        }
        Member m2 = i.findMemberRequested(cmdParts[1]);
        if (m2 != null)
            throw new ExSameMemberRequested();
        if (i.getStatus() instanceof ItemStatusAvailable)
            throw new ExItemCurrentlyAvailable();
        if (i.getStatus() instanceof ItemStatusBorrowed) {
            if (m == ((ItemStatusBorrowed)i.getStatus()).getBorrowingMember())
                throw new ExBorrowedBySameMember();
        }
        if (m.getNoRequestedItems() == 3)
            throw new ExRequestQuotaExceeded();
        i.addToQueue(m);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done. This request is no. " + i.getQueueSize() + " in the queue.");
    }

    @Override 
    public void undoMe() {
        i.removeFromQueue(m);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        i.addToQueue(m);
        addUndoCommand(this);
    }

}
