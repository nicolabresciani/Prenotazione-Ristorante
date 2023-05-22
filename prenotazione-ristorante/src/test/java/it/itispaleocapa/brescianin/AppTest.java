package it.itispaleocapa.brescianin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

/**
 * Unit test for simple App.
 */
class AppTest {
    public Ristorante ristorante = new Ristorante();
    public Cliente cliente;
    public LocalDate dataPrenotazione;
    public Prenotazione prenotazione;

    @Test
    public void testAggiungiCliente() throws Exception {
        ristorante.aggiungiCliente("Mario", "Rossi", "3331234567");
        Assertions.assertEquals(1, ristorante.clienti.size());
        Assertions.assertTrue(ristorante.clienti.containsKey("Mario"));
        Assertions.assertThrows(Exception.class, () -> ristorante.aggiungiCliente("Mario", "Bianchi", "3337654321"));
    }

    @Test
    public void testModificaCliente() throws Exception {
        ristorante.aggiungiCliente("Mario", "Rossi", "3331234567");
        ristorante.modificaCliente("Mario", "3337654321");
        Assertions.assertEquals("3337654321", ristorante.clienti.get("Mario").getTelefono());
        Assertions.assertThrows(Exception.class, () -> ristorante.modificaCliente("Giovanni", "3337654321"));
    }

    @Test
    public void testEliminaCliente() throws Exception {
        ristorante.aggiungiCliente("Mario", "Rossi", "3331234567");
        ristorante.aggiungiCliente("Giovanni", "Bianchi", "3337654321");
        ristorante.eliminaCliente("Mario");
        Assertions.assertFalse(ristorante.clienti.containsKey("Mario"));
        Assertions.assertEquals(1, ristorante.clienti.size());
        Assertions.assertThrows(Exception.class, () -> ristorante.eliminaCliente("Luigi"));
    }

    @Test
    public void aggiungiPrenotazione() throws Exception {
        Cliente cliente = ristorante.aggiungiCliente("Mario", "Rossi", "1234567890");
        LocalDate dataInserimento = LocalDate.of(2023, 5, 11);
        LocalDate dataPrenotazione = LocalDate.of(2023, 5, 15);
        int numeroCoperti = 4;

        Prenotazione prenotazione = ristorante.aggiungiPrenotazione(cliente, dataInserimento, dataPrenotazione, numeroCoperti);

        Assertions.assertNotNull(prenotazione);
        Assertions.assertEquals(cliente, prenotazione.getCliente());
        Assertions.assertEquals(dataInserimento, prenotazione.getDataInserimento());
        Assertions.assertEquals(dataPrenotazione, prenotazione.getDataPrenotazione());
        Assertions.assertEquals(numeroCoperti, prenotazione.getNumeroCoperti());
    }

    @Test
    public void testEliminaPrenotazione() throws Exception {
        cliente = new Cliente("Mario", "Rossi", "1234567890");
        dataPrenotazione = LocalDate.of(2023, 5, 11);
        ristorante.aggiungiPrenotazione(cliente, LocalDate.now(), dataPrenotazione, 4);
        ristorante.eliminaPrenotazione(cliente.getNome(), dataPrenotazione);
        Assertions.assertTrue(ristorante.prenotazioni.isEmpty());
    }


    @Test
    public void testModificaPrenotazione() throws Exception {
        // Crea un cliente
        cliente = new Cliente("Mario", "Rossi", "3331234567");
        // Aggiungi la prenotazione
        LocalDate dataInserimento = LocalDate.now();
        LocalDate dataPrenotazione = dataInserimento.plusDays(1);
        int numeroCoperti = 2;
        prenotazione = ristorante.aggiungiPrenotazione(cliente, dataInserimento, dataPrenotazione, numeroCoperti);
        // Modifica la prenotazione con un nuovo numero di coperti
        ristorante.modificaPrenotazione(cliente.getNome(), dataPrenotazione, 4);
        // Verifica che il numero di coperti sia stato modificato correttamente
        assertEquals(4, prenotazione.getNumeroCoperti());
    }
}
