package com.tilseier.studying.screens.collections;

import android.util.ArraySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCollections {

    public void checkAllCollections(){

        Collection<Integer> collection = new ArrayList<Integer>();
        //ArrayList - динамічний список, який процює на основі масиву;
        //швидкий пошук; повільне добавлення та видалення елементів НЕ в кінець масиву
        List<Integer> arrayList = new ArrayList<Integer>();

        //LinkedList - динамічний список, який працює на основі елементів, які звязані між собою
        //швидке видалення та добавлення елементів; повільний пошук; використовує додаткову пам'ять на збереження зв'язків
        List<Integer> linkedList = new LinkedList<Integer>();

        //HashSet - працює на основі хеш-таблиці
        //елементи не впорядковані; не допускає повторень; для швидкого пошуку
        //Коэффициент загрузки = Количество хранимых элементов в таблице / размер хэш-таблицы
        //new HashSet(int initialCapacity, float loadFactor);
        //Коэффициент загрузки, равный 0.75, в среднем обеспечивает хорошую производительность. Если этот параметр увеличить, тогда уменьшится нагрузка на память (так как это уменьшит количество операций ре-хэширования и перестраивания), но это повлияет на операции добавления и поиска.
        //операції добавлення, видалення, пошуку в основному виконуються за константний час (О(1))
        //на практике обычно выбирается реализация HashSet, оптимизированная для быстрого поиска
        Set<Integer> hashSet = new HashSet<Integer>();//Начальная емкость по умолчанию – 16, коэффициент загрузки – 0,75.
        Set<Integer> hashSet2 = new HashSet<Integer>(3, 0.75f);

        //LinkedHashSet - працює на основі хеш-таблиці
        //елементи зберігаються в порядку добавтення; не допускає повторень; підтримує зв'язний список елементів
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();

        //TreeSet - працює на основі хеш-таблиці
        //елементи впорядковані по зростанню; не допускає повторень;
        Set<Integer> treeSet = new TreeSet<Integer>();
        SortedSet<Integer> treeSet2 = new TreeSet<Integer>();

        //HashMap
        //
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

//        Queue queue = new ArrayList<>();


        //Специально для Android был разработан новый класс ArraySet, который более эффективен
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ArraySet<String> arraySet = new ArraySet<String>();
        }

        collection.add(2);
        collection.add(45);
        collection.add(8);

        arrayList.add(2);
        arrayList.add(45);
        arrayList.add(8);

        linkedList.add(2);
        linkedList.add(45);
        linkedList.add(8);

        hashSet.add(2);
        hashSet.add(45);
        hashSet.add(8);

        hashSet2.add(2);
        hashSet2.add(45);
        hashSet2.add(8);

        hashMap.put(2, "Piter");
        hashMap.put(45, "Norton");
        hashMap.put(8, "Gabby");


        System.out.println("======================================");
        System.out.println("HashSet: " + hashSet);
        System.out.println("List contains 45 or not:" +
                hashSet.contains(45));
        hashSet.remove(45);
        System.out.println("List after removing 45: "+hashSet);
        System.out.println("======================================");


    }

    public void arrayList(int[] arr){
        //ArrayList - динамічний список, який процює на основі масиву;
        //швидкий пошук; повільне добавлення та видалення елементів НЕ в кінець масиву
        List<Integer> arrayList = new ArrayList<Integer>();

        for (int num: arr){
            arrayList.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("ArrayList: " + arrayList);
        System.out.println("List contains "+arr[center]+" or not:" +
                arrayList.contains(arr[center]));
        arrayList.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+arrayList);
        System.out.println("======================================");

    }
    public void linkedList(int[] arr){
        //LinkedList - динамічний список, який працює на основі елементів, які звязані між собою
        //швидке видалення та добавлення елементів; повільний пошук; використовує додаткову пам'ять на збереження зв'язків
        List<Integer> linkedList = new LinkedList<Integer>();

        for (int num: arr){
            linkedList.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("LinkedList: " + linkedList);
        System.out.println("List contains "+arr[center]+" or not:" +
                linkedList.contains(arr[center]));
        linkedList.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+linkedList);
        System.out.println("======================================");
    }
    public void hashSet(int[] arr){
        //HashSet - працює на основі хеш-таблиці
        //елементи не впорядковані; не допускає повторень; для швидкого пошуку
        //Коэффициент загрузки = Количество хранимых элементов в таблице / размер хэш-таблицы
        //new HashSet(int initialCapacity, float loadFactor);
        //Коэффициент загрузки, равный 0.75, в среднем обеспечивает хорошую производительность. Если этот параметр увеличить, тогда уменьшится нагрузка на память (так как это уменьшит количество операций ре-хэширования и перестраивания), но это повлияет на операции добавления и поиска.
        //операції добавлення, видалення, пошуку в основному виконуються за константний час (О(1))
        //на практике обычно выбирается реализация HashSet, оптимизированная для быстрого поиска
        Set<Integer> hashSet = new HashSet<Integer>();//Начальная емкость по умолчанию – 16, коэффициент загрузки – 0,75.
        Set<Integer> hashSet2 = new HashSet<Integer>(3, 0.75f);

        for (int num: arr){
            hashSet.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("HashSet: " + hashSet);
        System.out.println("List contains "+arr[center]+" or not:" +
                hashSet.contains(arr[center]));
        hashSet.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+hashSet);
        System.out.println("======================================");

    }
    public void linkedHashSet(int[] arr){
        //LinkedHashSet - працює на основі хеш-таблиці
        //елементи зберігаються в порядку добавтення; не допускає повторень; підтримує зв'язний список елементів
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();

        for (int num: arr){
            linkedHashSet.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("LinkedHashSet: " + linkedHashSet);
        System.out.println("List contains "+arr[center]+" or not:" +
                linkedHashSet.contains(arr[center]));
        linkedHashSet.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+linkedHashSet);
        System.out.println("======================================");
    }
    public void treeSet(int[] arr){
        //TreeSet - працює на основі хеш-таблиці
        //елементи впорядковані по зростанню; не допускає повторень;
        Set<Integer> treeSet = new TreeSet<Integer>();
        SortedSet<Integer> treeSet2 = new TreeSet<Integer>();

        for (int num: arr){
            treeSet.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("TreeSet: " + treeSet);
        System.out.println("List contains "+arr[center]+" or not:" +
                treeSet.contains(arr[center]));
        treeSet.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+treeSet);
        System.out.println("======================================");
    }
    public void sortedTreeSet(int[] arr){
        //TreeSet - працює на основі хеш-таблиці
        //елементи впорядковані по зростанню; не допускає повторень;
        SortedSet<Integer> sortedTreeSet = new TreeSet<Integer>();

        for (int num: arr){
            sortedTreeSet.add(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("SortedTreeSet: " + sortedTreeSet);
        System.out.println("List contains "+arr[center]+" or not:" +
                sortedTreeSet.contains(arr[center]));
        sortedTreeSet.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+sortedTreeSet);
        System.out.println("======================================");
    }
    public void queue(int[] arr){
        //Queue - працює на основі хеш-таблиці
        //елементи впорядковані по зростанню; не допускає повторень;

        //хотите Exception (т.е. если пустая очередь - недопустимое состояние) - берите remove()
        //Если нет - берите poll()
        Queue<Integer> queue = new PriorityQueue<Integer>();

        for (int num: arr){
            queue.add(num);
//            or
//            queue.offer(num);
        }

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("Queue: " + queue);
        System.out.println("List contains "+arr[center]+" or not:" +
                queue.contains(arr[center]));
        queue.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+queue);
        System.out.println("======================================");

    }
    public void hashMap(int[] arr){
        //HashMap использует хеш-таблицу для хранения карточки,
        //обеспечивая быстрое время выполнения запросов get() и put() при больших наборах.
        //не гарантирует порядка элементов.

        //K - это Key (ключ), V - Value (значение)
        //class HashMap<K, V>
        Map<Integer, String> hashMap = new HashMap<Integer, String>();

        hashMap.put(1, "Audi");
        hashMap.put(3, "Tesla");
        hashMap.put(4, "BMW");

        hashMap.size();

        hashMap.containsKey(4);
        hashMap.containsValue("Tesla");

        System.out.println("======================================");
        int center = arr.length/2;
        System.out.println("HashMap: " + hashMap);
        System.out.println("List contains "+arr[center]+" or not:" +
                hashMap.containsKey(arr[center]));

        for (Integer key : hashMap.keySet()) {
            System.out.println("Key: " + key);
        }
        for (String value : hashMap.values()) {
            System.out.println("Value: " + value);
        }

        // Получаем набор элементов
        Set<Map.Entry<Integer, String>> set = hashMap.entrySet();
        for (Map.Entry entry : set) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }

        // Добавляем значение
        String value = hashMap.get(1);
        hashMap.put(1, value + 3);

        hashMap.remove(arr[center]);
        System.out.println("List after removing arr[center]: "+hashMap);
        System.out.println("======================================");

//        Map<Person, List<? extends Pet>> personMap = new HashMap<>();
//
//        personMap.put(new Person("Иван"), Arrays.asList(new Cat("Барсик"), new Cat("Мурзик")));
//        personMap.put(new Person("Маша"), Arrays.asList(new Cat("Васька"), new Dog("Бобик")));
//        personMap.put(new Person("Ирина"), Arrays.asList(new Cat("Рыжик"), new Dog("Шарик"), new Parrot("Гоша")));
//
//        System.out.println("personMap: " + personMap);
//        System.out.println("personMap.keySet(): " + personMap.keySet());
//
//        for(Person person : personMap.keySet()){
//            System.out.println(person + " имеет");
//            for (Pet pet : personMap.get(person)){
//                System.out.println("  " + pet);
//            }
//        }


//        HashMap	                    Array class
//        HashMap<K,V>	            ArrayMap<K,V>
//        HashMap<Integer, Object>	SparseArray<Object>
//        HashMap<Integer, Boolean>	SparseBooleanArray
//        HashMap<Integer, Integer>	SparseIntArray
//        HashMap<Integer, Long>	    SparseLongArray
//        HashMap<Long, Object>	    LongSparseArray<Object>

    }

    public void testHashCode(){

        Test obj1 = new Test(11, 12);
        Test obj2 = new Test(11, 12);
        System.out.println("Объекты :\n\tobj1 = " + obj1 +
                "\n\tobj2 = " + obj2);
        System.out.println("hashCode объектов :" +
                "\n\tobj1.hashCode = " + obj1.hashCode() +
                "\n\tobj2.hashCode = " + obj2.hashCode());
        System.out.println("Сравнение объектов :" +
                "\n\tobj1.equals(obj2) = " + obj1.equals(obj2));


        Set<Test> objects = new HashSet<Test>();
        objects.add(obj1);
        objects.add(obj2);

        System.out.println("Коллекция :\n\t" + objects);

    }

}
