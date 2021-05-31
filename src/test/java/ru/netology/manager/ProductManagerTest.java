package ru.netology.manager;

import com.sun.source.doctree.AuthorTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book first = new Book(001, "Тестирование ПО", 1000, "Святослав Куликов");
    private Book second = new Book(002, "Tестирование dot com", 1000, "Роман Савин");
    private Book third = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
    private Book fourth = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
    private Book fifth = new Book(005, "Автоматизированное тестирование программного обеспечения", 1000, "Элфрид Дастин, Джефф Рэшка, Джон Пол");
    private Smartphone sixth = new Smartphone(006, "Samsung", 5000, "Южная Корея");
    private Smartphone seventh = new Smartphone(007, "Xiaomi", 5000, "Китай");
    private Smartphone eidhth = new Smartphone(00, "Huawei", 5000, "Китай");
    private Smartphone ninth = new Smartphone(00, "Nokia", 5000, "Финляндия");
    private Smartphone tenth = new Smartphone(010, "Sony", 5000, "Япония");


    @BeforeEach
    public void setup() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);

    }

    //А где тест на то что должно находиться несколько элементов?

    @Test // Тест добавления еще одного элемента
    public void shouldAddOneMore() {
        manager.add(eidhth);
        Product[] expected = new Product[]{first, second, third, fourth, fifth, sixth, seventh, eidhth};
        Product[] actual = manager.findAll();
        assertArrayEquals(expected, actual);
    }
    
    @Test // Тест использование метода переопределения
    public void shouldEseOverridedMethod() {
        Product product = new Book();
        product.toString();
    }

    @Test // Тест поиска с одинаковыми данными
    public void shouldUseEquals() {
        Product third = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
        Product fourth = new Book(003, "Тестирование черного ящика", 1000, "Борис Бейзер");
        assertEquals(third, fourth);
    }

    @Test // Тест сохранения списка элементов
    void shouldGetSave() {
        Product[] expected = new Product[]{first, second,third, fourth, fifth, sixth, seventh};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test // Тест поиск книги по автору
    public void shouldFindAuthorExistBook() {
        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy("Святослав Куликов");
        assertArrayEquals(expected, actual);
    }

    @Test // Тест поик книги по названию
    public void shouldFindByBookName() {
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Tестирование dot com");
        assertArrayEquals(expected, actual);
    }

    @Test
        //Тест поиска книги по автору которой нет в списке
    void shouldFindAuthoNotExistBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Пушкин А. С.");
        assertArrayEquals(expected, actual);
    }

    @Test
        //Тест поиска книги по названию которой нет в списке
    void shouldFindNameNotExistBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Руслан и Людмила");
        assertArrayEquals(expected, actual);
    }

    @Test //Тест поиска телефон по модели
    public void shouldFindBySmartphoneManufacturer() {
        Product[] expected = new Product[]{sixth};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test // Тест поиска телефон по стране производителя
    public void shouldFindBySmartphoneTitle() {
        Product[] expected = new Product[]{sixth};
        Product[] actual = manager.searchBy("Южная Корея");
        assertArrayEquals(expected, actual);
    }

    @Test
        //Тест поиска смартфона по стране производителя которого нет в списке
    void shouldFindМanufacturerNotExistSmartphone() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Индия");
        assertArrayEquals(expected, actual);
    }

    @Test
        //Тест поиска смартфона по названию модели которой нет в списке
    void shouldFindNameNotExistSmartphone() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }
}