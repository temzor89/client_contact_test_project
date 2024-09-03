package repository;

import model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для управления сущностями контактов в базе данных.
 * <p>
 * Этот интерфейс предоставляет основные CRUD операции (создание, чтение, обновление и удаление)
 * для сущности {@link Contact}, а также определяет дополнительные методы для поиска контактов
 * по идентификатору клиента и типу контакта.
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * Возвращает список контактов для указанного клиента.
     *
     * @param clientId идентификатор клиента
     * @return список контактов, принадлежащих указанному клиенту
     */
    List<Contact> findByClientId(Long clientId);

    /**
     * Возвращает список контактов для указанного клиента и типа контакта.
     *
     * @param clientId идентификатор клиента
     * @param type     тип контакта
     * @return список контактов, принадлежащих указанному клиенту и соответствующих указанному типу
     */
    List<Contact> findByClientIdAndType(Long clientId, String type);
}