import java.util.Date;

public class Notification {

    public Date date;
    NotificationType type;
    public int departmentID;
    public int itemID;

    public Notification(Date date, NotificationType type, int departmentID, int itemID) {
        this.date = date;
        this.type = type;
        this.departmentID = departmentID;
        this.itemID = itemID;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(this.type + ";" + this.itemID + ";" + this.departmentID);
        return s.toString();
    }

    public boolean equals(Object o) {
        Notification not = (Notification) o;
        return (not.date.equals(this.date)) && not.type.equals(this.type) && (not.departmentID == this.departmentID) && (not.itemID == this.itemID);
    }

}
