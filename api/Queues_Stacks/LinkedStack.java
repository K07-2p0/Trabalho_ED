package Queues_Stacks;

import Exceptions.EmptyCollectionException;
import Lists.DoubleNode;

/**
 * Implementação de uma pilha usando lista ligada
 */
public class LinkedStack<T> implements StackADT<T> {
    private DoubleNode<T> top;
    private int count;
    
    public LinkedStack() {
        this.top = null;
        this.count = 0;
    }
    
    @Override
    public void push(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        newNode.setNext(top);
        if (top != null) {
            top.setPrevious(newNode);
        }
        top = newNode;
        count++;
    }
    
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Pilha vazia");
        }
        
        T element = top.getElement();
        top = top.getNext();
        if (top != null) {
            top.setPrevious(null);
        }
        count--;
        
        return element;
    }
    
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Pilha vazia");
        }
        return top.getElement();
    }
    
    @Override
    public boolean isEmpty() {
        return count == 0;
    }
    
    @Override
    public int size() {
        return count;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[Topo] ");
        DoubleNode<T> current = top;
        
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        
        return sb.toString();
    }
}