package queuepractice;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class QueuePractice {

    public static void switchAndRetain(Stack<Integer> P1, Stack<Integer> P2){
        int[] counters = new int[] {0,0};
        Stack<Integer> PAux = new Stack();
        System.out.println("Stack 1: " + P1.toString());
        System.out.println("Stack 2: " + P2.toString());
        while(!P1.isEmpty()){
            PAux.push(P1.pop());
            counters[0]++;
        }
        while(!P2.isEmpty()){
            PAux.push(P2.pop());
            counters[1]++;
        }
        while(counters[1]!= 0){
            P1.push(PAux.pop());
            counters[1]--;
        }
        while(counters[0]!= 0){
            P2.push(PAux.pop());
            counters[0]--;
        }
        System.out.println("Stack 1 after switch: " + P1.toString());
        System.out.println("Stack 2 after switch: " + P2.toString());
    }
    
    public static Stack<Integer> pilaDescendente(Stack<Integer> ascendente, Stack<Integer> descendente){
        Stack<Integer> retornable, auxiliar;
        retornable = new Stack();
        auxiliar = new Stack();
        
        while(!ascendente.isEmpty()){
            retornable.push(ascendente.pop());
        }
        
        while (!descendente.isEmpty()){
           if(retornable.isEmpty()){
               retornable.push(descendente.pop());
               while(!auxiliar.isEmpty()){
                   if (auxiliar.peek() != retornable.peek()) {
                       retornable.push(auxiliar.pop());
                   } else {
                       auxiliar.pop();
                   }
               }
           } else if(descendente.peek() > retornable.peek()){
               retornable.push(descendente.pop());
               while(!auxiliar.isEmpty()){
                   if (auxiliar.peek() != retornable.peek()) {
                       retornable.push(auxiliar.pop());
                   } else {
                       auxiliar.pop();
                   }
               }
           } else if(descendente.peek() == retornable.peek()){
               descendente.pop();
               while(!auxiliar.isEmpty()){
                   if (auxiliar.peek() != retornable.peek()) {
                       retornable.push(auxiliar.pop());
                   } else {
                       auxiliar.pop();
                   }
               }
           } else {
               auxiliar.push(retornable.pop());
           }
        }  
        return retornable;
    }
    
    public static void main(String[] args) {
        Stack<Integer> P1, P2, P3;
        P1 = new Stack();
        P2 = new Stack();
        Collections.sort(P1);
        
        for(int i = 0; i<10;i++){
            P1.push( (int) (Math.random()*50));
            P2.push( (int) (Math.random()*50));
        }
        Collections.sort(P1);
        Collections.sort(P2);
        Collections.reverse(P1);
        System.out.println("Stack 1: " + P1.toString());
        System.out.println("Stack 2: " + P2.toString());
        
        P3 = pilaDescendente(P1, P2);
        
        System.out.println("Stack 3: " + P3.toString());
       
    }
    
}
