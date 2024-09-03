package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Класс, представляющий контактную информацию клиента.
 * Используется для хранения различных типов контактов клиента, таких как номер телефона или email.
 */
@Entity
@Data
public class Contact {

    /**
     * Уникальный идентификатор контактной информации.
     * Генерируется автоматически при создании новой записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Идентификатор клиента, которому принадлежит эта контактная информация.
     */
    private Long clientId;

    /**
     * Тип контактной информации.
     * Например, это может быть "телефон" или "email".
     */
    private String type;

    /**
     * Значение контактной информации.
     * Например, это может быть конкретный номер телефона или адрес электронной почты.
     */
    private String value;
}