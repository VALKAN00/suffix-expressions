public class MyStack {
    class Node{
        String value ;
        Node next;

        public Node(String value) {
            this.value = value;
        }
    }
    private Node head;

    public boolean isEmpty(){
        if (head == null) {
            return true;
        }
        return false;
    }
    public void push(String s){
        if (isEmpty()){
            head =new Node(s);
        }else{
            Node newnode=new Node(s);
            newnode.next=head;
            head =newnode;
        }
    }
    public String pop(){
        if (isEmpty()) {
            return "stack is empty";
        } else {
            Node temp=head;
            head=head.next;
            return temp.value;
        }
    }
    public void getTop(){
        System.out.print(head.value);
    }

    public void printStack(){
        while(head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
    }


}

