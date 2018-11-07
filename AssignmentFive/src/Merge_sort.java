import java.util.ArrayList;

public class Merge_sort {
	static ArrayList<Phonebook_line> new_phones = new ArrayList<Phonebook_line>();

    public static ArrayList<Phonebook_line> merge_sort(ArrayList<Phonebook_line> phones){
            if (phones.size() <= 1){
                    return new_phones = phones;
            }

            ArrayList<Phonebook_line> left = new ArrayList<Phonebook_line>(phones.subList(0, phones.size()/2-1));
            ArrayList<Phonebook_line> right = new ArrayList<Phonebook_line>(phones.subList(phones.size()/2, phones.size()-1));

            left = merge_sort(left);
            right = merge_sort(right);
            new_phones =  merge(left,right);
            return new_phones;
    }
    public static ArrayList<Phonebook_line> merge(ArrayList<Phonebook_line> left, ArrayList<Phonebook_line> right){
            ArrayList<Phonebook_line> result = new ArrayList<Phonebook_line>();
            while(left.size() != 0 && right.size() != 0){
                    if (left.get(0).Getlastname().compareTo(right.get(0).Getlastname()) <= 0){
                            result.add(left.get(0));
                            left.remove(0);
                    }
                    else{
                            result.add(right.get(0));
                            right.remove(0);
                    }

            }
            while(left.size() != 0){
                    result.add(left.get(0));
                    left.remove(0);
            }
            while(right.size() != 0){
                    result.add(right.get(0));
                    right.remove(0);
            }
            return result;
    }
}
