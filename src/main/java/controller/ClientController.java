package controller;

import lombok.RequiredArgsConstructor;
import model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.List;

/**
 * Контроллер для управления клиентами.
 * Обрабатывает HTTP-запросы для операций с клиентами.
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Получить список всех клиентов.
     *
     * @return список объектов {@link Client}.
     */
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getClients();
    }

    /**
     * Получить клиента по ID.
     *
     * @param id уникальный идентификатор клиента.
     * @return объект {@link Client} в обёртке {@link ResponseEntity},
     * если клиент найден, иначе возвращается ответ 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Создать нового клиента.
     *
     * @param client объект {@link Client}, содержащий информацию о новом клиенте.
     * @return созданный объект {@link Client}.
     */
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client.getName());
    }
}