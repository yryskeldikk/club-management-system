public class ItemStatusBorrowed implements ItemStatus{
    private Member borrowingMember;
    private Day loanDate;
    private Item item;

    public ItemStatusBorrowed(Member m, Item i) {
        this.borrowingMember = m;
        this.item = i;
        loanDate = SystemDate.getInstance().clone();
    }
    public String getStatus() {
        if (this.item.getQueueSize() == 0)
            return "Borrowed by " + borrowingMember.getID() + " " + borrowingMember.getName() + " on " + loanDate.toString();
        else {
            return "Borrowed by " + borrowingMember.getID() + " " + borrowingMember.getName() + " on " + loanDate.toString()
                            + " + " + item.getQueueSize() + " request(s): " + item.getMemberIDs();
        }

    }

    public Member getBorrowingMember() {
        return this.borrowingMember;
    }



}
