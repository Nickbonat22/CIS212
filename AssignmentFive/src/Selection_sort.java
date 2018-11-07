import java.util.ArrayList;

public class Selection_sort {

	public static ArrayList<Phonebook_line> selectionSort(ArrayList<Phonebook_line> phones){
        ArrayList<Phonebook_line> new_phones = phones;
        Phonebook_line temp = new Phonebook_line(phones.get(0).Getline());

        int mindindx;
        for(int i = 0; i < new_phones.size() - 1; i++){
                mindindx = i;
                for(int j = i + 1; j < new_phones.size(); j++){
                        if (new_phones.get(j).Getlastname().compareTo(new_phones.get(mindindx).Getlastname())<= 0){
                                mindindx = j;
                                }
                }
                if(mindindx != i){
                        temp = phones.get(i);
                        new_phones.set(i, new_phones.get(mindindx));
                        new_phones.set(mindindx, temp);
                }
        }
        return new_phones;
	}// end selectionSort
}
