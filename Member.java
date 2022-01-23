import java.util.ArrayList;

public class Member implements Comparable<Member> {
    private String id;
    private String name;
    private Day joinDate;
    private ArrayList<Item> borrowedItems;
    private ArrayList<Item> requestedItems;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
        this.borrowedItems = new ArrayList<>();
        this.requestedItems = new ArrayList<>();
        Club.getInstance().addMember(this);
        
    }
    public String toString() {
        //Learn: "-" means left-aligned
        return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, this.getNoBorrowedItems(), this.getNoRequestedItems());
    }

    public static String getListingHeader() {
        return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name",
       "Join Date ", "#Borrowed", "#Requested");
    }

    @Override
    public int compareTo(Member another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void borrow(Item i) {
        this.borrowedItems.add(i);
        i.setStatus(new ItemStatusBorrowed(this, i));
    }

    public void unborrow(Item i) {
        this.borrowedItems.remove(i);
        i.setStatus(new ItemStatusAvailable());
    }
    
    public int getNoBorrowedItems() {
        return this.borrowedItems.size();
    }

    public Item findBorrowedItem(String id) {
        for (Item i: borrowedItems) {
            if (i.getID().equals(id))
                return i;
        }
        return null;
    }

    public void request(Item i) {
        this.requestedItems.add(i);
    }

    public int getNoRequestedItems() {
        return this.requestedItems.size();
    }

    public void removeRequest(Item i) {
        this.requestedItems.remove(i);
    }
}
