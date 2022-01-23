public class CmdCancelRequest extends RecordedCommand {
    private Member m;
    private Item i;
    private int idx;
    @Override
    public void execute(String cmdParts[]) throws ExMemberNotFound, ExItemNotFound, ExRequestRecordNotFound, ExInsufficientCommandArg {
        if (cmdParts.length < 3)
            throw new ExInsufficientCommandArg();
        Club club = Club.getInstance();
        m = club.findMember(cmdParts[1]);
        i = club.findItem(cmdParts[2]);
        if (m == null)
            throw new ExMemberNotFound();
        if (i == null)
            throw new ExItemNotFound();
        Member m2 = i.findMemberRequested(cmdParts[1]);
        if (m2 == null)
            throw new ExRequestRecordNotFound();
        idx = i.getRequestQueue().indexOf(m);
        i.removeFromQueue(m);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        i.getRequestQueue().add(idx, m);
        m.request(i);
        addRedoCommand(this);
    }
    public void redoMe() {
        i.removeFromQueue(m);
        addUndoCommand(this);
    }
    
}
