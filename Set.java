// This code defines a generic Set class that stores a collection of elements in a singly linked list.
public class Set <T> {
    LinearNode<T> setStart;

    // Constructor for creating an empty set.
    public Set() {
        setStart = null;
    }

    // Adds a new element to the set.
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        // If the set is empty, make the new node the start of the linked list.
        if (setStart == null) {
            setStart = newNode;
        }
        // Otherwise, insert the new node at the beginning of the linked list.
        else {
            newNode.setNext(setStart);
            setStart = newNode;
        }
    }

    // Returns the number of elements in the set.
    public int getLength(){
        int counter = 0;
        LinearNode<T> current4 = setStart;
        while (current4 != null) {
            counter += 1;
            current4 = current4.getNext(); // Traverse the linked list to count the elements.
        }
        return counter;
    }

    // Returns the element at a given index in the set.
    public T getElement(int i) {
        int k = 0;
        LinearNode<T> current = setStart;
        while (current != null) {
            if (k == i) {
                return current.getElement(); // Traverse the linked list to find the element at the given index.
            }
            k += 1;
            current = current.getNext();
        }
        // If the index is out of bounds, throw an exception.
        throw new IndexOutOfBoundsException("Index out of Bounds");
    }

    // Checks if the set contains a given element.
    public boolean contains(T element) {
        LinearNode<T> current2 = setStart;
        while (current2 != null){
            if (current2.getElement().equals(element)) {
                return true; // Traverse the linked list to find if the element exists in the set.
            }
            else {
                current2 = current2.getNext();
            }

        }
        return false; // If the element is not found, return false.
    }

    // Returns a string representation of the set.
    public String toString() {
        LinearNode<T> current3 = setStart;
        StringBuilder sb = new StringBuilder();
        while (current3 != null) {
            sb.append(current3.getElement()).append(" "); // Traverse the linked list to create a string of elements.
            current3 = current3.getNext();
        }
        return sb.toString();
    }


}




