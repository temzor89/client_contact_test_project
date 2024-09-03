package service;

import lombok.RequiredArgsConstructor;
import model.Client;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления данными о клиентах.
 * <p>
 * Этот класс предоставляет методы для создания, получения списка клиентов,
 * а также поиска клиента по его идентификатору. Сервис использует {@link ClientRepository}
 * для взаимодействия с базой данных.
 * <p>
 * Аннотация {@link Service} указывает, что данный класс является сервисом,
 * а также делает его доступным для внедрения в другие компоненты.
 * Аннотация {@link RequiredArgsConstructor} автоматически создает конструктор с обязательными полями.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Создает нового клиента с указанным именем и сохраняет его в базе данных.
     *
     * @param name имя клиента
     * @return созданный и сохраненный клиент
     */
    public Client createClient(String name) {
        Client client = new Client();
        client.setName(name);
        return clientRepository.save(client);
    }

    /**
     * Возвращает список всех клиентов из базы данных.
     *
     * @return список всех клиентов
     */
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * Ищет клиента по его идентификатору.
     *
     * @param id идентификатор клиента
     * @return объект {@link Optional}, содержащий клиента, если он найден, или пустой, если клиент не найден
     */
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}