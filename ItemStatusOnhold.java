public class ItemStatusOnhold implements ItemStatus {
    private Member onholdMember;
    private Item item;
    private Day dueDate;

    public ItemStatusOnhold(Member m, Item i) {
        this.onholdMember = m;
        Day d = SystemDate.getInstance().clone();
        d.setDueDate();
        this.dueDate = d;
        this.item = i;
    }

    public String getStatus() {
        if (this.item.getQueueSize() == 0)
            return "On holdshelf for " + onholdMember.getID() + " " + onholdMember.getName() + " until " + dueDate.toString();
        else {
            return "On holdshelf for " + onholdMember.getID() + " " + onholdMember.getName() + " until " + dueDate.toString()
             + " + " + item.getQueueSize() + " request(s): " + item.getMemberIDs();
        }
    }

    public Member getOnholdMember() {
        return this.onholdMember;
    }
    
    public Day getDueDate() {
        return this.dueDate;
    }
}
