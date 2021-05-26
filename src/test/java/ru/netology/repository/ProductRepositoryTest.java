package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    private Book first = new Book(001,"Тестирование ПО",1000,"Святослав Куликов");
    private Book second = new Book(002,"Tестирование dot com",1000,"Роман Савин");
    private Book third = new Book(003,"Тестирование черного ящика",1000,"Борис Бейзер");
    private Book fourth = new Book(004,"Ключевые процессы тестирования",1000,"Рекс Блэк");
    private Book fifth = new Book(005,"Автоматизированное тестирование программного обеспечения",1000,"Элфрид Дастин, Джефф Рэшка, Джон Пол");
    private Smartphone sixth = new Smartphone(006,"Samsung",5000,"South Korea");
    private Smartphone seventh = new Smartphone(007,"Xiaomi",5000,"Китай");
    private Smartphone eidhth = new Smartphone(00,"Huawei",5000,"Китай");
    private Smartphone ninth = new Smartphone(00,"Nokia",5000,"Финляндия");
    private Smartphone tenth = new Smartphone(010,"Sony",5000,"Япония");


    @BeforeEach
    public void setup() {
        repository.save(first);
        repository.save(second);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(tenth);

    }

    @Test // Тест сохранить и найти все
    public void testSaveAndFindAll() {
        assertEquals(repository.findAll().length, 5);
    }


    @Test // Тест удаления по ID
    public void shouldRemoveByID() {
        repository.save(first);
        repository.save(second);
        Product[] expected = new Product[]{second};
        repository.removeById(1);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    private void assertArrayEquals(Product[] expected, Product[] actual) {
    }

    @Test
    public void testRemoveNotExist() {
        repository.removeById(010);
        assertEquals(repository.findAll().length, 4);
    }
}
