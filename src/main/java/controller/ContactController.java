package controller;

import lombok.RequiredArgsConstructor;
import model.Contact;
import org.springframework.web.bind.annotation.*;
import service.ContactService;

import java.util.List;

/**
 * Контроллер для управления контактами.
 * Обрабатывает HTTP-запросы для операций с контактами.
 */
@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    /**
     * Добавляет новый контакт.
     *
     * @param contact объект {@link Contact}, содержащий данные о новом контакте.
     * @return созданный объект {@link Contact}.
     */
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact.getClientId(), contact.getType(), contact.getValue());
    }

    /**
     * Получает список контактов для указанного клиента.
     *
     * @param clientId уникальный идентификатор клиента.
     * @return список объектов {@link Contact}, связанных с указанным клиентом.
     */
    @GetMapping("/client/{clientId}")
    public List<Contact> getContactsByClientId(@PathVariable Long clientId) {
        return contactService.getContactsByClientId(clientId);
    }

    /**
     * Получает список контактов определённого типа для указанного клиента.
     *
     * @param clientId уникальный идентификатор клиента.
     * @param type     тип контакта.
     * @return список объектов {@link Contact} нужного типа, связанных с указанным клиентом.
     */
    @GetMapping("/client/{clientId}/type/{type}")
    public List<Contact> getContactsByClientIdAndType(@PathVariable Long clientId, @PathVariable String type) {
        return contactService.getContactsByClientIdAndType(clientId, type);
    }
}