// SinglyLinkedList.java
public class SinglyLinkedList {
    private static class Node {
        String value;
        Node next;
        Node(String v) { value = v; }
    }

    private Node head;

    public void add(String v) {
        Node n = new Node(v);
        if (head == null) head = n;
        else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
    }

    public void printAll() {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
