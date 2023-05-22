package it.itispaleocapa.brescianin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        // crea un nuovo ristorante
        Ristorante ristorante = new Ristorante();

        // aggiungi alcuni clienti
        ristorante.aggiungiCliente("Mario", "Rossi", "3331234567");
        ristorante.aggiungiCliente("Luigi", "Verdi", "3337654321");
        ristorante.aggiungiCliente("Maria", "Bianchi", "3331112222");

        // stampa la lista dei clienti
        System.out.println();
        ristorante.stampaListaClienti();

        // aggiungi alcune prenotazioni
        ristorante.aggiungiPrenotazione(ristorante.clienti.get("Mario"), LocalDate.now(), LocalDate.now().plusDays(1), 2);
        ristorante.aggiungiPrenotazione(ristorante.clienti.get("Luigi"), LocalDate.now(), LocalDate.now().plusDays(2), 4);
        ristorante.aggiungiPrenotazione(ristorante.clienti.get("Maria"), LocalDate.now(), LocalDate.now().plusDays(1), 3);


        // stampa la lista delle prenotazioni
        System.out.println();
        ristorante.stampaListaPrenotazioni();

        // modifica una prenotazione
        ristorante.modificaPrenotazione("Luigi", LocalDate.now().plusDays(2), 6);

        // stampa la lista delle prenotazioni aggiornata
        System.out.println();
        ristorante.stampaListaPrenotazioniAggiornate();
        System.out.println();

        // cerca le prenotazioni per cliente
        System.out.println("Prenotazioni di Mario:");
        System.out.println(ristorante.ricercaPrenotazioniPerCliente("Mario"));
        System.out.println();

        // cerca le prenotazioni per data
        System.out.println("Prenotazioni del " + LocalDate.now().plusDays(1) + ":");
        System.out.println(ristorante.ricercaPrenotazioniPerData(LocalDate.now().plusDays(1)));
        System.out.println();

        // numero di coperti per data
        System.out.println("Numero di coperti per il " + LocalDate.now().plusDays(1) + ":");
        System.out.println(ristorante.numeroCopertiPerData(LocalDate.now().plusDays(1)));
        System.out.println();

        // numero di coperti in un range di date
        System.out.println("Numero di coperti dal " + LocalDate.now() + " al " + LocalDate.now().plusDays(2) + ":");
        System.out.println(ristorante.numeroCopertiInRange(LocalDate.now(), LocalDate.now().plusDays(2)));
        System.out.println();

        // cancella una prenotazione
        ristorante.eliminaPrenotazione("Luigi", LocalDate.now().plusDays(2));
        System.out.println("LISTA DELLE PRENOTAZIONI AGGIORNATE ELIMINANDO UNA PRENOTAZIONE");
        ristorante.stampaListaPrenotazioni();
        System.out.println();

        // elimina un cliente
        ristorante.eliminaCliente("Maria");

        // stampa la lista dei clienti aggiornata
        System.out.println("LISTA DEI CLIENTI AGGIORNATA ELIMINANDO UN CLIENTE");
        ristorante.stampaListaClienti();
        System.out.println();

        // modifica un cliente
        ristorante.modificaCliente("Luigi",  "3iu7654321");

        // stampa la lista dei clienti aggiornata
        System.out.println("LISTA DEI CLIENTI MODIFICATA ELIMINANDO UN CLIENTE");
        ristorante.stampaListaClienti();
        System.out.println();

    }
}