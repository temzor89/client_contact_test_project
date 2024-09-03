package controller;

import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Получение всех клиентов")
    void testGetAllClients() {
        List<Client> mockClients = new ArrayList<>();
        mockClients.add(new Client());
        mockClients.add(new Client());

        when(clientService.getClients()).thenReturn(mockClients);

        List<Client> clients = clientController.getAllClients();

        assertNotNull(clients, "Список клиентов не должен быть null");
        assertEquals(2, clients.size(), "Должно быть два клиента");
        verify(clientService, times(1)).getClients();
    }

    @Test
    @DisplayName("Получение клиента по ID")
    void testGetClientById() {
        Long clientId = 1L;
        Client mockClient = new Client();
        mockClient.setId(clientId);

        when(clientService.getClientById(clientId)).thenReturn(Optional.of(mockClient));

        ResponseEntity<Client> response = clientController.getClientById(clientId);

        assertNotNull(response.getBody(), "Ответ не должен быть null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Статус ответа должен быть OK");
        assertEquals(clientId, response.getBody().getId(), "ID клиента в ответе должен совпадать");
        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    @DisplayName("Клиент не найден по ID")
    void testGetClientByIdNotFound() {
        Long clientId = 1L;

        when(clientService.getClientById(clientId)).thenReturn(Optional.empty());

        ResponseEntity<Client> response = clientController.getClientById(clientId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Статус ответа должен быть NOT_FOUND");
        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    @DisplayName("Создание нового клиента")
    void testCreateClient() {
        Client mockClient = new Client();
        mockClient.setName("Test Client");

        when(clientService.createClient(mockClient.getName())).thenReturn(mockClient);

        Client createdClient = clientController.createClient(mockClient);

        assertNotNull(createdClient, "Созданный клиент не должен быть null");
        assertEquals("Test Client", createdClient.getName(), "Имя клиента должно совпадать");
        verify(clientService, times(1)).createClient(mockClient.getName());
    }
}