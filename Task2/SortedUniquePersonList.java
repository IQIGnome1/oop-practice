// --------SortedUniquePersonList.java-------------

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * Collection for holding Person objects.<br>
 * Provides the following guarantees:<br>
 * 1) Elements are guaranteed to be in ascending order, sorted by their ID code value.<br>
 * 2) Elements are guaranteed to have unique ID code values.<br>
 *
 * Uses an underlying array for storing the elements. <br>
 * The object guarantees to not use more than twice the required array size.<br>
 * For example, if currently 10 persons are stored, then the underlying array size might range from 10 to 20, but will not be larger.
 */
public class SortedUniquePersonList {

    /**
     * Returns reference to object at the given index. Checks that the given index is in bounds of the underlying array, returns null if it isn't.
     * @param index Index at which the object is searched.
     * @return Person object at the given index, or null if the index is out of bounds.
     */

    private  Person[] persons; //массив для обьектов (люди)
    private  int size; //количество  добавленных людей.

    //конструктор, даем начальнные значения полям....
    public SortedUniquePersonList(){

        //спросить у препода, как лушче 0 или 1. При нуле размер массива всегда будет 0, так как 0*2=0 всегда
        persons = new Person [1];
        size = 0;

    }


    //знаем индекс, хотим узнать кто находится в этом индексе
    public Person getElementAt(int index) {

        if (index >= size || index< 0){
            return null;

        }else{

            return persons[index];

        }



    }

    /**
     * Returns the index of the object with the given ID code. If an object with the given ID code is not present, returns -1.
     * @param idCode ID code that is searched.
     * @return Index at which the Person object with the given ID code can be found, or -1 if no such ID code is present.
     */
    //знаем ид код, хотим узнать индекс ид кода
    public int indexOf(int idCode) {

        for(int index = 0; index < size; index++){
            if(persons[index].getIdCode()== idCode){
                return index;


            }

        }
        return  -1;

    }

    /**
     * Attempts to add the person to the collection, but only if no person with the same ID code is already present.<br>
     * If an element is added, it is inserted to the correct position according to their ID code. Also, the index of all subsequent elements is then increased.<br>
     * If a Person object with the same ID code is already present, does nothing.
     * @param person Person object to be added.
     * @return true if person was added to the collection, false otherwise.
     */
    public boolean add(Person person) {
       int  newId =person.getIdCode();
       int i = indexOf( newId);
       int pos = 0;
       if(i != -1) return false;

       //двигаемся  вправо, пока текущий id меньше нового//
        // остановился → значит нашёл место вставки
       while (pos < size && persons[pos].getIdCode() < newId){
           pos++;

       }

        if(size == persons.length){
            persons =Arrays.copyOf(persons ,persons.length *2);

               /*можно через цикл
               * for (int i = 0; i < size; i++) {
                    bigger[i] = a[i];
                 }*/

        }

        for (int j = size; j > pos; j--) {
            persons[j] = persons[j - 1];

        }
        persons[pos] = person;
        size++;
        return  true;

    }

    /**
     * Attempts to remove the person with the given ID code from the collection. Does nothing if no Person object with the given ID code is present.<br>
     * In the case of a successful removal of an object, decreases the index of all subsequent elements.
     * @param idCode ID code that is searched.
     * @return true if the person with the given ID code was removed, false otherwise.
     */
    public boolean removeElement(int idCode) {

        int index = indexOf(idCode);

        if (index == -1) {
            return false;
        }

        // сдвигаем все элементы справа на 1 влево
        for (int i = index; i < size - 1; i++) {
            persons[i] = persons[i + 1];
        }

        // очищаем последнюю ячейку (чтобы не держать "мусор" в памяти)
        persons[size - 1] = null;


        size--;

        // если массив стал слишком большим — уменьшаем.
    // Делим на 4, чтобы проверить: массив заполнен на 25% или меньше (слишком пустой)
        if (size > 0 && size <= persons.length / 4) {
            persons = Arrays.copyOf(persons, persons.length / 2);
        }

        return true;



    }

    /**
     * Calculates and returns the size of the collection.
     * @return Number of elements in the collection.
     */
    public int size() {
        return size;

    }
}
