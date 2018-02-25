
public interface Subject {

	public abstract void addObserver(Customer customer);
	public abstract void removeObserver(Customer c);
	public abstract void notifyAllObservers(Notification notification);
}
