package service;

import lombok.RequiredArgsConstructor;
import model.Contact;
import org.springframework.stereotype.Service;
import repository.ContactRepository;
import java.util.List;

/**
 * Сервис для управления данными о контактах клиентов.
 * <p>
 * Этот класс предоставляет методы для добавления контакта, получения контактов
 * по идентификатору клиента, а также получения контактов по идентификатору клиента и типу.
 * <p>
 * Аннотация {@link Service} указывает, что данный класс является сервисом,
 * а также делает его доступным для внедрения в другие компоненты.
 * Аннотация {@link RequiredArgsConstructor} автоматически создает конструктор с обязательными полями.
 */
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    /**
     * Добавляет новый контакт для клиента и сохраняет его в базе данных.
     *
     * @param clientId идентификатор клиента, которому принадлежит контакт
     * @param type тип контакта (например, телефон, email)
     * @param value значение контакта (например, номер телефона, адрес электронной почты)
     * @return созданный и сохраненный контакт
     */
    public Contact addContact(Long clientId, String type, String value) {
        Contact contact = new Contact();
        contact.setClientId(clientId);
        contact.setType(type);
        contact.setValue(value);
        return contactRepository.save(contact);
    }

    /**
     * Возвращает список всех контактов для указанного клиента.
     *
     * @param clientId идентификатор клиента, контакты которого необходимо получить
     * @return список контактов клиента
     */
    public List<Contact> getContactsByClientId(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    /**
     * Возвращает список всех контактов определенного типа для указанного клиента.
     *
     * @param clientId идентификатор клиента, контакты которого необходимо получить
     * @param type тип контакта (например, телефон, email)
     * @return список контактов клиента заданного типа
     */
    public List<Contact> getContactsByClientIdAndType(Long clientId, String type) {
        return contactRepository.findByClientIdAndType(clientId, type);
    }
}