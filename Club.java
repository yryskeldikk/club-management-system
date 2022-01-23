import java.util.ArrayList;
import java.util.Collections;
public class Club {
    private ArrayList<Member> allMembers;
    private ArrayList<Item> allItems;
    private static Club instance = new Club();
    private Club() { 
        allMembers = new ArrayList<>();
        allItems = new ArrayList<>(); 
    }
    public static Club getInstance() { return instance; }

    public void addMember(Member member) {
        allMembers.add(member);
        Collections.sort(allMembers); 
    }

    public void removeMember(Member member) {
        allMembers.remove(member);
        Collections.sort(allMembers); 
    }

    public void listClubMembers() {
        System.out.println(Member.getListingHeader()); 
        for (Member m: allMembers)
        System.out.println(m.toString()); 
    }

    public void addItem(Item item) {
        allItems.add(item);
        Collections.sort(allItems);
    }

    public void listClubItems() {
        System.out.println(Item.getListingHeader()); 
        for (Item i: allItems)
        System.out.println(i.toString()); 
    }

    public Item findItem(String id) {
        for (Item i: allItems) {
            if (i.getID().equals(id))
                return i;
        }
        return null;
    }

    public Member findMember(String id) {
        for (Member m: allMembers) {
            if (m.getID().equals(id))
                return m;
        }
        return null;
    }

    public void removeItem(Item i) {
        allItems.remove(i);
        Collections.sort(allItems); 
    }

    public ArrayList<Item> getAllItems() {
        return this.allItems;
    }
}
