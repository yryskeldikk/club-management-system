public class CmdStartNewDay extends RecordedCommand {
    private String d1;
    private String d2;
    @Override
    public void execute(String cmdParts[]) {
        SystemDate sd = SystemDate.getInstance();
        d1 = sd.toString();
        d2 = cmdParts[1];
        sd.set(d2);
        Club club = Club.getInstance();
        for (Item i: club.getAllItems()) {
            if (i.getStatus() instanceof ItemStatusOnhold) {
                if (sd.getDay() > ((ItemStatusOnhold)i.getStatus()).getDueDate().getDay()) {
                    System.out.println("On hold period is over for " + i.getID() + " " + i.getName() + ".");
                    if (i.getQueueSize() > 0) {
                        Member onhold = i.getOnholdMember();
                        i.removeFromQueue(onhold);
                        i.setStatus(new ItemStatusOnhold(onhold, i));
                        System.out.printf("Item [%s] is ready for pick up by [%s].  On hold due on %s.\n", 
                                i.getID() + " " + i.getName(), onhold.getID() + " " + onhold.getName(), 
                                ((ItemStatusOnhold)i.getStatus()).getDueDate().toString());
                    }
                    else {
                        i.setStatus(new ItemStatusAvailable());
                    }
                }
            }
        }
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        SystemDate sd = SystemDate.getInstance();
        sd.set(d1);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate sd = SystemDate.getInstance();
        sd.set(d2);
        addUndoCommand(this);
    }
}
