package collection.lesson3;

//    1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
//    - Найти список слов, из которых состоит текст (дубликаты не считать);
//    - Посчитать сколько раз встречается каждое слово (использовать HashMap);
//   2. Написать простой класс PhoneBook(внутри использовать HashMap):
//    - В качестве ключа использовать фамилию
//    - В каждой записи всего два поля: phone, e-mail
//    - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
//       и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько записей.
//       Итого должно получиться 3 класса Main, PhoneBook, Person.


import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();                                                                    // 1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
        words.add("Создать");
        words.add("массив");
        words.add("с");
        words.add("набором");
        words.add("слов");
        words.add("!");
        words.add("20-30");
        words.add("слов");
        words.add("должны");
        words.add("встречаться");
        words.add("дубликаты");
        words.add("Найти");
        words.add("список");
        words.add("слов");

        Set<String> dictionaryWords = new HashSet<>();                                                                  //  - Найти список слов, из которых состоит текст (дубликаты не считать);
        dictionaryWords.addAll(words);

        HashMap<String, Integer> hm = new HashMap<>();
        int countWord = 0;
        for (String word : words) {
            if (hm.get(word) == null) {
                countWord = 0;
            } else {
                countWord = hm.get(word);
            }
            hm.put(word, ++countWord);
        }                                                                                                               // - Посчитать сколько раз встречается каждое слово (использовать HashMap);
        System.out.println("Количество 'слов': " + hm.get("слов"));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhoneBook("Иванов", "+111111111", "ivanov@mail.ru");
        phoneBook.addPhoneBook("Иванов", "+222222222", "ivanov1@mail.ru");
        phoneBook.addPhoneBook("Петров", "+79212133545", "petrov@mail.ru");


        System.out.println("Телефон Иванова" + phoneBook.getPhonesByLastName("Иванов"));
        System.out.println("Телефон Иванова1" + phoneBook.getPhonesByLastName("Иванов1"));
        System.out.println("Почта Иванова1" + phoneBook.getEmailByLastName("Иванов"));
        System.out.println("Это конец");
    }

}
