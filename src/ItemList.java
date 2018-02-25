
import java.util.Comparator;
import java.util.ListIterator;

public abstract class ItemList {

    private Comparator<Item> c;
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public ItemList(Comparator<Item> c) {
        this.c = c;
        first = new Node<>();
        last = new Node<>();
        first.next = last;
        last.prev = first;
        size = 0;
    }

    private static class Node<Item> {

        private Item item;
        private Node<Item> prev;
        private Node<Item> next;

        public Node() {
            this.item = null;
            this.prev = null;
            this.next = null;
        }

        public String toString() {
            StringBuffer s = new StringBuffer();
            s.append(">" + item + "<");
            return s.toString();
        }
    }

    private class ItemIterator implements ListIterator<Item> {

        private int index;
        private Node<Item> current;

        public ItemIterator(int index) {
            this.index = index;
            current = new Node<>();
            current = first.next;
        }

        public ItemIterator() {
            current = new Node<>();
            current = first.next;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                index++;
                return item;
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Item previous() {
            if (hasPrevious()) {
                current = current.prev;
                Item item = current.item;
                index--;
                return item;
            }
            return null;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            Node<Item> x = current.prev;
            Node<Item> y = current.next;
            x.next = y;
            y.prev = x;
            size--;
            index--;
            current = y;
        }

        @Override
        public void set(Item e) {
            current.item = e;
        }

        @Override
        public void add(Item e) {
            Node<Item> x = current.prev;
            Node<Item> y = new Node<Item>();
            y.item = e;
            x.next = y;
            y.prev = x;
            y.next = current;
            current.prev = y;
            current = y;
            size++;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(Item element) {
        ListIterator<Item> ite = this.listIterator(0);
        if (this.contains(element))
            return false;
        while (ite.hasNext()) {
            Item tmp = ite.next();
            if (c.compare(tmp, element) > 0) { //finding the position to insert element into iterator's elements
                ite.previous();
                break;
            }
        }
        ite.add(element);
        return true;
    }

    public Item getItem(int index) {
        ListIterator<Item> ite = this.listIterator();
        Item item;
        while (ite.hasNext()) { //going through iterator's element (index times) to find element at index
            item = ite.next();
            if (index == 0)
                return item;
            index--;
        }
        return null;
    }

    public Node<Item> getNode(int index) {
        ItemIterator ite = (ItemIterator) this.listIterator();
        while (ite.hasNext()) {
            ite.next();
            if (index == 0)
                return ite.current.prev;
            index--;
        }
        return null;
    }

    public int indexOf(Item item) {
        ListIterator<Item> ite = this.listIterator();
        while (ite.hasNext()) {
            if (ite.next().equals(item))
                return ite.previousIndex();
        }
        return -1;
    }

    public int indexOf(Node<Item> node) {
        return indexOf(node.item);
    }

    public boolean contains(Node<Item> node) {
        return contains(node.item);
    }

    public boolean contains(Item item) {
        ListIterator<Item> ite = this.listIterator(0);
        while (ite.hasNext()) {
            Item tmp = ite.next();
            if (tmp.equals(item))
                return true;
        }
        return false;
    }

    public Item remove(int index) {
        ListIterator<Item> ite = this.listIterator();
        Item item;
        if (ite.hasNext()) {
            do {
                item = ite.next(); //ite.next() moves me to the next node, but returns current item
                if (index == 0) {
                    ite.previous(); //i have to go previous before removing because of ite.next();
                    ite.remove();
                    return item;
                }
                index--;
            } while (ite.hasNext());
        }
        return null;
    }

    public boolean remove(Item item) {
        if (!this.contains(item))
            return false;
        ListIterator<Item> ite = this.listIterator(0);
        while (ite.hasNext()) {
            if (ite.next().equals(item)) {
                ite.previous(); //ite.next() moves me to the next node, but returns current item
                ite.remove();    //so i have to go previous before removing, because ite.remove() removes the node from the current position
                return true;
            }
        }
        return false;
    }

    public ListIterator<Item> listIterator(int index) {
        return new ItemIterator(index);
    }

    public ListIterator<Item> listIterator() {
        return new ItemIterator();
    }

    public double getPrice() {
        ListIterator<Item> ite = this.listIterator();
        double sum = 0;
        while (ite.hasNext()) {
            sum += ite.next().getPrice();
        }
        return sum;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("[");
        ListIterator<Item> ite = this.listIterator();
        while (ite.hasNext()) {
            s.append(ite.next() + ", ");
        }
        if (s.length() > 1) {
            s.deleteCharAt(s.length() - 1);
            s.deleteCharAt(s.length() - 1);
        }
        s.append("]");
        return s.toString();
    }
}
