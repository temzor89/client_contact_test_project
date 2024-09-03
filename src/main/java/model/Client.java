package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Класс, представляющий клиента.
 * Используется для хранения информации о клиенте в базе данных.
 */
@Entity
@Data
public class Client {

    /**
     * Уникальный идентификатор клиента.
     * Генерируется автоматически при создании нового клиента.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя клиента.
     */
    private String name;
}