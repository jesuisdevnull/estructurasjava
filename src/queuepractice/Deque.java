package queuepractice;


public class Deque {
    public class Node{
        private int element;
        private Node next;
        
        public Node(int nuev){
            element = nuev;
            next = null;
        }
    }
    private Node front;
    private Node back;
    private int size;
    
    public Deque(){
        front = null;
        back = null;
        size = 0;
    }
    
    public boolean isEmpty(){
        return ((front==null)||(back==null));
    }
    
    public void addBack(int newElement){
        if(isEmpty()){
            front = new Node(newElement);
            size++;
            back = front;
        } else {
            back.next = new Node(newElement);
            back = back.next;
            size++;
        }
    }
    
    public void addFront(int newElement){
        if(isEmpty()){
            front = new Node(newElement);
            size++;
            back = front;
        } else {
            Node aux = new Node(newElement);
            aux.next = front;
            front = aux;
            size++;
        }
    }
    
    public Node removeBack() throws Exception{
        if (!isEmpty()){
            Node auxPoint = front;
            if(front==back){
                front = null;
                back = null;
                size--;
                return auxPoint;
            }
            while(auxPoint.next != back){
                auxPoint = auxPoint.next;
            }
            back = auxPoint;
            auxPoint = auxPoint.next;
            back.next = null;
            size--;
            return auxPoint;
        }
        throw new Exception();
    }
    
    public Node removeFront() throws Exception{
        if(!isEmpty()){
            Node auxPoint = front;
            if(front==back){
                front = null;
                back = null;
                size--;
                return auxPoint;
            }
            front = front.next;
            size--;
            return auxPoint;
        }
        throw new Exception();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(isEmpty()){
            sb.append("Deque is empty.");
        } else {
            Node auxPoint = front;
            sb.append("[ ").append(auxPoint.element).append(" ");
            while(auxPoint.next!=null){
                auxPoint = auxPoint.next;
                sb.append(auxPoint.element).append(" ");
            }
            sb.append("]");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws Exception {
        Deque test = new Deque();
        test.addBack(2);
        test.addBack(4);
        test.addBack(6);
        test.addBack(8);
        test.addBack(10);
        System.out.println(test.toString() + "(" + test.size + " elements)");
        test.removeBack();
        test.removeBack();
        System.out.println(test.toString() + "(" + test.size + " elements)");
        test.removeFront();
        test.removeFront();
        System.out.println(test.toString() + "(" + test.size + " elements)");
        test.addFront(3);
        test.addFront(1);
        System.out.println(test.toString() + "(" + test.size + " elements)");
    }
}
