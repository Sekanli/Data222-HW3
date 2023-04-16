import java.util.AbstractList;

public class LDLinkedList<E> extends AbstractList<E> {
    
    private int size;
    private Node<E> head;
    private Node<E> tail;
    private int lazyDeletes;
    /**
     * 
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        boolean isDeleted;  //Lazily delete flag
        
        Node(E element) {
            this.item = element;
            this.next = null;
            this.isDeleted = false;
        }
    }
    /**
     * constructor
     */
    public LDLinkedList() {
        size = 0;
        head = null;
        tail = null;
        lazyDeletes = 0;
    }
    /**
     * return size
     */
    public int size() {
        return size - lazyDeletes;
    }
    /**
     * @param element
     * adds a new element to the list
     * returns true to avoid conflictions with abstractlist extension
     */
    public boolean add(E element) {
        Node<E> node = new Node<E>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }
    /**
     * @param index
     * gets the element at the given index and returns it
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
           System.out.println("Out of bounds error");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }
    /**
     * @param index
     * removes an element from the list at the given index
     * but instead of removing directly
     * it tracks the remove count and removes if 2 remove request is made(lazydelete)
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Out of bounds error");
        }
        Node<E> current = head;
       
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.isDeleted = true;
        lazyDeletes++;
        if (lazyDeletes == 2) {
            removeLazyDeletedNodes();
        }
        size--;
        return current.item;
    }
    /**
     * @param element
     * removes an element from the list
     * but instead of removing directly
     * it tracks the remove count and removes if 2 remove request is made(lazydelete)
     */
    public boolean removeLD(E element) {
        Node<E> current = head;
      
        while (current != null) {
            if (current.item.equals(element)) {
                current.isDeleted = true;
                lazyDeletes++;
                if (lazyDeletes == 2) {
                    removeLazyDeletedNodes();
                }
                size--;
                return true;
            }
          
            current = current.next;
        }
        return false;
    }
    /**
     * @param index
     * @param element
     * sets a new element to the given index
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            System.out.println("Out of bounds error");;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E previousValue = current.item;
        current.item = element;
        return previousValue;   //It gives error for incompability,i dont know what it is used for but i need to return this
    }
        /**
         * makes the remove operation if 2 remove request is made
         * resets the count
         */
    private void removeLazyDeletedNodes() {
        Node<E> current = head;
        Node<E> previous = null;
        int deletedCount = 0;
        while (current != null) {
            if (current.isDeleted) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                deletedCount++;
                if (deletedCount == 2) {
                    break;
                }
            } else {
                previous = current;
            }
            current = current.next;
        }
        lazyDeletes -= deletedCount;
    }
}
