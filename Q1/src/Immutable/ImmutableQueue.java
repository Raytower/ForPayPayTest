package Immutable;
import Immutable.Stack;
import Immutable.ImmutableStack;
import java.util.*;
public final class ImmutableQueue<T> implements Queue<T> {
	private final Stack<T> backwards;
    private final Stack<T> forwards;
    
    //initialize
    private ImmutableQueue(Stack<T> forwards, Stack<T> backwards)
    {
        this.forwards = forwards;
        this.backwards = backwards;
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final static Stack reverseStack(Stack stack) throws Exception
    {
        Stack r = ImmutableStack.getEmptyStack();
        while(!stack.isEmpty()){
        	r = r.push(stack.head());   
        	stack = stack.pop();
        }
      
        return r;
    }
	
	@Override
	public Queue<T> enQueue(T t) {
		return new ImmutableQueue<T>(forwards, backwards.push(t));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Queue<T> deQueue() {
		Stack<T> f = null;
		try {
			f = forwards.pop();
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (!f.isEmpty()){
            return new ImmutableQueue<T>(f, backwards);
        }
        else if (backwards.isEmpty()){
        	return ImmutableQueue.getEmptyQueue();        }
        else {
            try {
				return new ImmutableQueue<T>(ImmutableQueue.reverseStack(backwards), ImmutableStack.getEmptyStack());
			} catch (Exception e) {				
				e.printStackTrace();
				return ImmutableQueue.getEmptyQueue();			}
        }
	}
	
	@SuppressWarnings({ "rawtypes" })
	public final static Queue getEmptyQueue(){
		return EmptyQueue.getInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T head() {
		try {
			return forwards.head();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) Collections.emptyList();
		}
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	@SuppressWarnings("unused")
	private static final class EmptyQueue<T> implements Queue<T>{
		
		@SuppressWarnings("rawtypes")
		private final static EmptyQueue emptyQueue = new EmptyQueue();
		
		@SuppressWarnings("rawtypes")
		public final static EmptyQueue getInstance(){
			return emptyQueue;
		}
		
		@SuppressWarnings("unchecked")
		public final Queue<T> enQueue(T t){
			return new ImmutableQueue<T>(ImmutableStack.getEmptyStack().push(t), ImmutableStack.getEmptyStack());
		}
		
		public final Queue<T> deQueue(){
			//TODO
			return null;
		}
		
		public final T head(){
			//TODO
			return null;
		}
		
		public final boolean isEmpty(){
			return true;
		}
	}

}
