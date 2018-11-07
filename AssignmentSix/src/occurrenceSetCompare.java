import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class occurrenceSetCompare<T> implements Comparator<Map.Entry<T,Integer>> {
	
	@Override
	public int compare(Entry<T, Integer> E1, Entry<T, Integer> E2){
		return E1.getValue().compareTo(E2.getValue());
	}

}
