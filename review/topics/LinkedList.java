public class LinkedList {
    static class Node {
        int val; Node next;
        Node(int v){val=v;}
    }

    Node head;

    public void add(int v){
        if (head==null) head=new Node(v);
        else {
            Node cur=head; while(cur.next!=null) cur=cur.next;
            cur.next=new Node(v);
        }
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        Node cur=head; while(cur!=null){ sb.append(cur.val); if(cur.next!=null) sb.append(" -> "); cur=cur.next; }
        return sb.toString();
    }

    public static void main(String[] args){
        LinkedList ll=new LinkedList();
        ll.add(3); ll.add(5); ll.add(7);
        System.out.println("LinkedList: " + ll);
    }
}