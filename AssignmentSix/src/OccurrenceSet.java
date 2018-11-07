import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class OccurrenceSet<T> implements Set<T> {
	
	public HashMap<T,Integer> myMap = new HashMap<>();

	@Override
    public boolean add(T element) {
            if (!myMap.containsKey(element)){
            	myMap.put(element, 1);
                    return true;
            }
            else {
            	myMap.put(element, myMap.get(element) + 1);
                    return false;
            }
    }// end add
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		HashMap<T, Integer> original = myMap;

        for (T e : c)
                if (myMap.containsKey(e)){
                        int count = myMap.get(e);
                        myMap.put(e, count++);
                }

                else{
                	
                	myMap.put(e, 1);
                }

        if (original == myMap){
        	
                return false;
        }

        else{
                return true;
        }
	}// end addAll
	
	@Override
	public boolean remove(Object o) {
		
		if(myMap.containsKey(0)){
			
			myMap.remove(o);
			return true;
		}
		else{
			
			return false;
		}
		
	}// end remove
	
	@Override
	public boolean removeAll(Collection<?> c) {
		HashMap<T, Integer> original = myMap;

        for (Object e : c)
                if (myMap.containsKey(e)){
                	myMap.remove(e);
                }

        if (original == myMap){
        	
                return false;
        }

        else{
                return true;
        }
	}// end removeAll
	
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;

		Iterator<T> it = this.iterator();


		while (it.hasNext()){

		T obj = it.next();

		if (!(c.contains(obj))){

		this.remove(obj);

		changed = true;

		}

		}

		return changed;
        
	}// end retainAll
	
	@Override
	public boolean contains(Object o) {
		
		if(myMap.containsKey(o) == true){
			return true;
		}
		
		return false;
	}// end contains
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
            if (myMap.containsKey(e)){
                    continue;
            }

            else{
                    return false;
            }

    return true;
    
	}// end containsAll

	
	@Override
	public int size() {

		return myMap.size();
		
	}// end size
	
	@Override
	public void clear() {
		
		myMap.clear();
		
	}// end clear

	@Override
	public boolean isEmpty() {
		
		return myMap.isEmpty();
		
	}// end isEmpty

	@Override
	public Iterator<T> iterator() {
		
		ArrayList<T> arrT = new ArrayList<T>();

		ArrayList<Map.Entry<T, Integer>> arr = new ArrayList<Map.Entry<T, Integer>>(myMap.entrySet());

		Comparator<Map.Entry<T,Integer>> c = new Comparator<Map.Entry<T,Integer>>(){

		@Override
		public int compare(Map.Entry<T,Integer> o1 ,Map.Entry<T,Integer> o2){

		return o1.getValue().compareTo(o2.getValue());

		}

		};


		Collections.sort(arr, c);

		for (Map.Entry<T, Integer> obj:arr){

		arrT.add(obj.getKey());

		}


		return arrT.iterator();
		
	}// end iterator

	@Override
	public Object[] toArray() {
		ArrayList<T> arrT = new ArrayList<T>();

		ArrayList<Map.Entry<T, Integer>> arr = new ArrayList<Map.Entry<T, Integer>>(myMap.entrySet());

		Comparator<Map.Entry<T,Integer>> c = new Comparator<Map.Entry<T,Integer>>(){

		@Override
		public int compare(Map.Entry<T,Integer> o1 ,Map.Entry<T,Integer> o2){

		return o1.getValue().compareTo(o2.getValue());

		}

		};

		Collections.sort(arr, c);

		for (Map.Entry<T, Integer> obj:arr){

		arrT.add(obj.getKey());

		}


		return arrT.toArray();
		
	}// end toArray

	@Override
	public <T> T[] toArray(T[] a) {

		   a = ((Set<T>) myMap).toArray(a);
		   
		   return (T[]) a;
		
	}// end toArray
	
	// told to leave blank
	
	public ArrayList<Map.Entry<T,Integer>> arraylist_sort(){
        ArrayList<Map.Entry<T,Integer>> entry_list = new ArrayList<>(myMap.entrySet());
        Collections.sort(entry_list, new occurrenceSetCompare());
        return entry_list;
	}// end arraylist_sort
	
	@Override
    public String toString(){
            String str = "[";
            for (int i = 0; i < arraylist_sort().size(); i++){
                    str = str + arraylist_sort().get(i).getKey() + ",";
            }
            if (str.length()>1) {
                    str = str.substring(0, str.length() - 1);
            }
            str = str + "]";
            return str;
    }// end toString
	
}// end class
