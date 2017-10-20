package priorityQueues;

import java.util.ArrayList;

import net.datastructures.AbstractPriorityQueue;
import net.datastructures.Entry;

public class MyPriorityQueueMin<K, V> extends AbstractPriorityQueue<K, V>{

    int size = 0;
    ArrayList<Entry<Integer,Integer>> queue = new ArrayList<Entry<Integer,Integer>>();
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    	int index = queue.size();
    	Entry<Integer, Integer> newEntry = new Entry<Integer, Integer>(){

			@Override
			public Integer getKey() {
				// TODO Auto-generated method stub
				return (Integer) key;
			}

			@Override
			public Integer getValue() {
				// TODO Auto-generated method stub
				return (Integer) value;
			}
        	
        };
    	
        queue.add(newEntry);
        
        while(queue.get(index).getKey() < queue.get((index - 1)/2).getKey()){
        	Entry<Integer, Integer> temp = queue.get(index);
        	queue.set(index, queue.get(((index - 1) /2)));
        	queue.set((index - 1)/2, temp);        	
        	index = (index - 1) / 2;
        };
        size++;
        
        return (Entry<K, V>) newEntry;
    }

    @Override
    public Entry<K, V> min() {
    	Entry<K, V> min;
    	if(size <= 0){
    		min = null;
    	} else{
    		min = (Entry<K, V>) queue.get(0);
    	}
    	return min;
    }

    @Override
    public Entry<K, V> removeMin() {
    	Entry<K, V> min;
    	if(size <= 0){
    		return null;
    	} else{
    		min = (Entry<K, V>) queue.get(0);
    	}
    	 size--;
         K next = (K) queue.get(0).getKey();
         queue.set(0, queue.get(size));
         int index = 0;
         int LC = (2 * index) + 1;
         int RC = (2 * index) + 2;
         
         while(queue.size() > LC && (queue.get(index).getKey() > queue.get(LC).getKey() || queue.get(index).getKey() > queue.get(RC).getKey())){
         
         	int toSwitch;
         	if(LC >= size){
         		return min;
         	} else if(RC >= size){
         		toSwitch = LC;
         	}else if(queue.get(RC).getKey() < queue.get(LC).getKey()){
         		toSwitch = RC;
         	} else{
         		toSwitch = LC;
         	}
         	Entry<Integer, Integer> temp = queue.get(index);
         	queue.set(index, queue.get(toSwitch));
         	queue.set(toSwitch, temp);  
         	index = toSwitch;
         	LC = (2 * index) + 1;
         	RC = (2 * index) + 2;
         }
        return min;
    }

}
