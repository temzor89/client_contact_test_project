package service;

import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Создание клиента")
    void testCreateClient() {
        String clientName = "Иван";
        Client mockClient = new Client();
        mockClient.setName(clientName);

        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);

        Client createdClient = clientService.createClient(clientName);

        assertNotNull(createdClient, "Клиент не должен быть null");
        assertEquals(clientName, createdClient.getName(), "Имена клиентов должны совпадать");
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    @DisplayName("Получение всех клиентов")
    void testGetClients() {
        List<Client> mockClients = Arrays.asList(new Client(), new Client());

        when(clientRepository.findAll()).thenReturn(mockClients);

        List<Client> clients = clientService.getClients();

        assertNotNull(clients, "Список клиентов не должен быть null");
        assertEquals(2, clients.size(), "Должны быть два клиента");
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Получение клиента по ID")
    void testGetClientById() {
        Long clientId = 1L;
        Client mockClient = new Client();
        mockClient.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(mockClient));

        Optional<Client> client = clientService.getClientById(clientId);

        assertTrue(client.isPresent(), "Клиент должен присутствовать");
        assertEquals(clientId, client.get().getId(), "ID клиента должны совпадать");
        verify(clientRepository, times(1)).findById(clientId);
    }
}