class Node {
    int data;
    Node next;
}

class LinkedList{
    Node head;
    Node tail;
    int size;
    public void insert(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (head == null){
            head = node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
            tail = node;
        }
        size++;
    }

    public void insertAtStart(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        //assign new node next to head and assign the new node as head
        node.next = head;
        head = node;
        size++;
    }

    public void insertAt(int index, int data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (index == 0) {
            insertAtStart(data);
        } else {
            Node n = head;
            for (int i = 0; i < index-1; i++){
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
            size++;
        }
        
    }

    public void deleteAt(int index){
        if (index == 0){
            head = head.next;
        } else {
            Node n = head;
            Node n1 = null;
            for(int i = 0; i < index - 1; i++){
                n = n.next;
            }
            n1 = n.next;
            n.next = n1.next;
            n1 = null; //eligible for garbage collection
            // System.out.println("n1 " + n1.data);
        }
    }

    public void show(){
        Node node = head;
        while (node.next != null) {
            System.out.println(node.data);
            node = node.next;
        }
        System.out.println(node.data);
        System.out.println("Tail" + tail.data);
    }

    public void rotate(int k){
        Node current = head;
        if (current == null || current.next ==null || k == 0) return;
        k = k % size;
        if (k == 0) return;

        //connect the tail to the head
        tail.next = head;

        //find new tail location
        int tailPosition = size - k - 1;
        for (int i = 0; i < tailPosition; i++){
             current = current.next;
        }

        //set new head and tail
        head = current.next;
        current.next = null;
        tail = current;
        }
}

public class MyLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        // list.insertAtStart(4);
        // list.insertAtStart(5);
        // list.insert(6);


        // list.insertAt(0, 55);

        // list.deleteAt(2);
        list.rotate(1);
        list.show();

    }
}
