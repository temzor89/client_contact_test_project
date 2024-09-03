package service;

import model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ContactRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Добавление контакта")
    void testAddContact() {
        Long clientId = 1L;
        String type = "email";
        String value = "example@example.com";
        Contact mockContact = new Contact();
        mockContact.setClientId(clientId);
        mockContact.setType(type);
        mockContact.setValue(value);

        when(contactRepository.save(any(Contact.class))).thenReturn(mockContact);

        Contact createdContact = contactService.addContact(clientId, type, value);

        assertNotNull(createdContact, "Контакт не должен быть null");
        assertEquals(clientId, createdContact.getClientId(), "ID клиента должны совпадать");
        assertEquals(type, createdContact.getType(), "Тип контакта должен совпадать");
        assertEquals(value, createdContact.getValue(), "Значение контакта должно совпадать");
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    @DisplayName("Получение контактов по ID клиента")
    void testGetContactsByClientId() {
        Long clientId = 1L;
        List<Contact> mockContacts = Arrays.asList(new Contact(), new Contact());

        when(contactRepository.findByClientId(clientId)).thenReturn(mockContacts);

        List<Contact> contacts = contactService.getContactsByClientId(clientId);

        assertNotNull(contacts, "Список контактов не должен быть null");
        assertEquals(2, contacts.size(), "Должно быть два контакта");
        verify(contactRepository, times(1)).findByClientId(clientId);
    }

    @Test
    @DisplayName("Получение контактов по ID клиента и типу")
    void testGetContactsByClientIdAndType() {
        Long clientId = 1L;
        String type = "email";
        List<Contact> mockContacts = Arrays.asList(new Contact(), new Contact());

        when(contactRepository.findByClientIdAndType(clientId, type)).thenReturn(mockContacts);

        List<Contact> contacts = contactService.getContactsByClientIdAndType(clientId, type);

        assertNotNull(contacts, "Список контактов не должен быть null");
        assertEquals(2, contacts.size(), "Должно быть два контакта");
        verify(contactRepository, times(1)).findByClientIdAndType(clientId, type);
    }
}