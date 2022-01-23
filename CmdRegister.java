public class CmdRegister extends RecordedCommand {
    private Member m;
    @Override
    public void execute(String cmdParts[]) throws ExMemberIdInUse, ExInsufficientCommandArg {
        if (cmdParts.length < 3)
            throw new ExInsufficientCommandArg();
        Club club = Club.getInstance();
        Member m2 = club.findMember(cmdParts[1]);
        if (m2 != null)
            throw new ExMemberIdInUse(m2);
        m = new Member(cmdParts[1], cmdParts[2]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        Club club = Club.getInstance();
        club.removeMember(m);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Club club = Club.getInstance();
        club.addMember(m);
        addUndoCommand(this);
    }
}
