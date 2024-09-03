package controller;

import model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.ContactService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Добавление нового контакта")
    void testAddContact() {
        Contact contact = new Contact();
        contact.setClientId(1L);
        contact.setType("email");
        contact.setValue("test@example.com");

        when(contactService.addContact(contact.getClientId(), contact.getType(), contact.getValue())).thenReturn(contact);

        Contact createdContact = contactController.addContact(contact);

        assertNotNull(createdContact, "Контакт не должен быть null");
        assertEquals("email", createdContact.getType(), "Тип контакта должен совпадать");
        assertEquals("test@example.com", createdContact.getValue(), "Значение контакта должно совпадать");
        verify(contactService, times(1)).addContact(contact.getClientId(), contact.getType(), contact.getValue());
    }

    @Test
    @DisplayName("Получение контактов по ID клиента")
    void testGetContactsByClientId() {
        Long clientId = 1L;
        List<Contact> mockContacts = new ArrayList<>();
        mockContacts.add(new Contact());
        mockContacts.add(new Contact());

        when(contactService.getContactsByClientId(clientId)).thenReturn(mockContacts);

        List<Contact> contacts = contactController.getContactsByClientId(clientId);

        assertNotNull(contacts, "Список контактов не должен быть null");
        assertEquals(2, contacts.size(), "Должно быть два контакта");
        verify(contactService, times(1)).getContactsByClientId(clientId);
    }

    @Test
    @DisplayName("Получение контактов по ID клиента и типу")
    void testGetContactsByClientIdAndType() {
        Long clientId = 1L;
        String type = "email";
        List<Contact> mockContacts = new ArrayList<>();
        mockContacts.add(new Contact());

        when(contactService.getContactsByClientIdAndType(clientId, type)).thenReturn(mockContacts);

        List<Contact> contacts = contactController.getContactsByClientIdAndType(clientId, type);

        assertNotNull(contacts, "Список контактов не должен быть null");
        assertEquals(1, contacts.size(), "Должен быть один контакт");
        verify(contactService, times(1)).getContactsByClientIdAndType(clientId, type);
    }
}