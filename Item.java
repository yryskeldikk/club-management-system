import java.util.ArrayList;

public class Item implements Comparable<Item>{
    private String id;
    private String name;
    private Day arrivalDate;
    private ItemStatus status;
    private ArrayList<Member> requestQueue;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
        this.arrivalDate = SystemDate.getInstance().clone();
        this.status = new ItemStatusAvailable();
        this.requestQueue = new ArrayList<>();
        Club.getInstance().addItem(this);
    }
    public String toString() {
        return String.format("%-5s%-17s%11s   %s", id, name, arrivalDate, status.getStatus()); 
    }

    public static String getListingHeader() {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status"); 
    }  

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public int compareTo(Item another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }

    public void setStatus(ItemStatus s) {
        this.status = s;
    }

    public ItemStatus getStatus() {
        return this.status;
    }

    public void addToQueue(Member m) {
        this.requestQueue.add(m);
        m.request(this);
    }

    public int getQueueSize() {
        return this.requestQueue.size();
    }

    public void removeFromQueue(Member m) {
        this.requestQueue.remove(m);
        m.removeRequest(this);
    }

    public String getMemberIDs() {
        String result = "";
        for (Member m: requestQueue) {
            result += " " + m.getID();
        }
        return result;
    }

    public Member findMemberRequested(String id) {
        for (Member m: requestQueue) {
            if (m.getID().equals(id))
                return m;
        }
        return null;
    }

    public Member getOnholdMember() {
        return this.requestQueue.get(0);
    }

    public ArrayList<Member> getRequestQueue() {
        return this.requestQueue;
    }
}
