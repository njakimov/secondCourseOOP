package collection.lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    //   2. Написать простой класс PhoneBook(внутри использовать HashMap):
    //    - В качестве ключа использовать фамилию
    //    - В каждой записи всего два поля: phone, e-mail
    //    - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
    //       и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько записей.
    //       Итого должно получиться 3 класса Main, PhoneBook, Person.

    HashMap<String, Person> phoneBook = new HashMap<>();

    public void addPhoneBook(String lastName, String phone, String eMail) {
        Person person;
        if (this.phoneBook.get(lastName) == null) {
            person = new Person(lastName, phone, eMail);
        } else {
            person = this.phoneBook.get(lastName);
            person.addContact(phone, eMail);
        }
        this.phoneBook.put(lastName, person);
    }

    public ArrayList<String> getPhonesByLastName(String lastName) {
        ArrayList<String> phones = new ArrayList();
        if (this.phoneBook.get(lastName) != null) {
            for (Person.Contact contact : this.phoneBook.get(lastName).getContacts()) {
                phones.add(contact.getPhone());
            }
        }
        return phones;
    }

    public ArrayList<String> getEmailByLastName(String lastName) {
        ArrayList<String> phones = new ArrayList();
        if (this.phoneBook.get(lastName) != null) {
            for (Person.Contact contact : this.phoneBook.get(lastName).getContacts()) {
                phones.add(contact.geteMail());
            }
        }
        return phones;
    }

    private class Person {
        ArrayList<Contact> contacts;
        String lastName;

        Person(String lastName, String phone, String eMail) {
            this.lastName = lastName;
            this.contacts = new ArrayList<>();
            Contact contact = new Contact(phone, eMail);
            this.contacts.add(contact);
        }

        private void addContact(String phone, String eMail) {
            Contact contact = new Contact(phone, eMail);
            this.contacts.add(contact);
        }

        private ArrayList<Contact> getContacts() {
            return this.contacts;
        }

        // сделал не оптимальную структуру хранения с точки зрения памяти - получилось, что всегда с телефоном должна лежать почта.
        // собсно переделывать нет смысла, задача показать понимание мапы и умение работать с ней.
        // в общем перемудрил. Но пусть остается так как есть.
        private class Contact {
            String phone;
            String eMail;

            Contact(String phone, String eMail) {
                this.phone = phone;
                this.eMail = eMail;
            }

            public String getPhone() {
                return phone;
            }

            public String geteMail() {
                return eMail;
            }
        }
    }
}
