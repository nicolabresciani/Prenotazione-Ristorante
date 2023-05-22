package it.itispaleocapa.brescianin;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ristorante {

    public HashMap<String, Cliente> clienti; // mappa dei clienti con il loro nome come chiave
    public List<Prenotazione> prenotazioni; // lista delle prenotazioni

    public Ristorante() {
        clienti = new HashMap<>();
        prenotazioni = new LinkedList<>();
    }

    public Cliente aggiungiCliente(String nome, String cognome, String telefono) throws Exception {
        if (clienti.containsKey(nome) || clienti.containsKey(cognome) || clienti.containsKey(telefono)) {
            throw new Exception();
        }
        return clienti.put(nome, new Cliente(nome,cognome, telefono));
    }

    public void modificaCliente(String nome, String nuovoTelefono) throws Exception {
        if (!clienti.containsKey(nome)) {
            throw new Exception(   );
        }
        Cliente cliente = clienti.get(nome);
        cliente.setTelefono(nuovoTelefono);
    }

    public void eliminaCliente(String nome) throws Exception {
        if (!clienti.containsKey(nome)) {
           throw new Exception();
        }
        // eliminare tutte le prenotazioni associate al cliente
        prenotazioni.removeIf(prenotazione-> prenotazione.getCliente().equals(nome));
        clienti.remove(nome);
    }


    public Prenotazione aggiungiPrenotazione(Cliente nomeCliente, LocalDate dataInserimento, LocalDate dataPrenotazione, int numeroCoperti) throws Exception {
        if (!clienti.containsKey(nomeCliente)) {
            Prenotazione prenotazione = new Prenotazione(nomeCliente, dataInserimento, dataPrenotazione, numeroCoperti);
            prenotazioni.add(prenotazione);
            return prenotazione;
        } else {
            throw new Exception("Il cliente esiste già");
        }
    }

    public void modificaPrenotazione(String nomeCliente, LocalDate dataPrenotazione, Integer nuovoNumeroCoperti) throws Exception {
        if (nomeCliente == null || dataPrenotazione == null || nuovoNumeroCoperti == null) {
            throw new Exception();
        }
        boolean trovata = false;
        for (Prenotazione prenotazione : prenotazioni) {
            Cliente cliente = prenotazione.getCliente();

            if (cliente != null && cliente.getNome().equals(nomeCliente) && prenotazione.getDataPrenotazione().equals(dataPrenotazione)) {
                prenotazione.setNumeroCoperti(nuovoNumeroCoperti);
                trovata = true;
                break;
            }
        }
        if (!trovata) {
            throw new Exception();
        }
    }



    public void eliminaPrenotazione(String nomeCliente, LocalDate dataPrenotazione) throws Exception {
        if (nomeCliente == null || dataPrenotazione == null) {
            throw new IllegalArgumentException();
        }

        boolean trovata = false;
        Iterator<Prenotazione> iterator = prenotazioni.iterator();
        while (iterator.hasNext()) {
            Prenotazione prenotazione = iterator.next();
            Cliente cliente = prenotazione.getCliente();
            LocalDate data = prenotazione.getDataPrenotazione();
            if (cliente != null && cliente.getNome() != null && cliente.getNome().equals(nomeCliente) && data != null && data.equals(dataPrenotazione)) {
                iterator.remove();
                trovata = true;
                break;
            }
        }
        if (!trovata) {
            throw new Exception("Prenotazione non trovata");
        }
    }


    public List<Prenotazione> ricercaPrenotazioniPerCliente(String nomeCliente) {
        return prenotazioni.stream()
            .filter(p -> p.getCliente().getNome().equals(nomeCliente))
            .collect(Collectors.toList());
    }
    
    public List<Prenotazione> ricercaPrenotazioniPerData(LocalDate dataPrenotazione) {
        return prenotazioni.stream()
                .filter(p -> p.getDataPrenotazione().equals(dataPrenotazione))
                .collect(Collectors.toList());
    }
    public int numeroCopertiPerData(LocalDate dataPrenotazione) {
        int[] numeroCoperti = { 0 };
        Consumer<Prenotazione> consumer = prenotazione -> numeroCoperti[0] += prenotazione.getNumeroCoperti();
        prenotazioni.stream()
                .filter(prenotazione -> prenotazione.getDataPrenotazione().equals(dataPrenotazione))
                .forEach(consumer);
        return numeroCoperti[0];
    }
    public int numeroCopertiInRange(LocalDate dataInizio, LocalDate dataFine) {
        int[] numeroCoperti = {0};
        Predicate<Prenotazione> prenotazionePredicate = prenotazione ->
                !prenotazione.getDataPrenotazione().isBefore(dataInizio) && !prenotazione.getDataPrenotazione().isAfter(dataFine);
        Consumer<Prenotazione> consumer = prenotazione -> numeroCoperti[0] += prenotazione.getNumeroCoperti();
        prenotazioni.stream()
                .filter(prenotazionePredicate)
                .forEach(consumer);
        return numeroCoperti[0];
    }
    public void stampaListaClienti() {
        System.out.println("Lista dei clienti:");
        for (Cliente cliente : clienti.values()) {
            System.out.println(cliente.getNome() + " " + cliente.getCognome() + " - " + cliente.getTelefono());
        }
    }

    public void stampaListaPrenotazioni() {
        System.out.println("Lista delle prenotazioni:");
        for (Prenotazione prenotazione : prenotazioni) {
            System.out.println(prenotazione.getCliente().getNome() + " " + prenotazione.getCliente().getCognome() + " - " +
                    prenotazione.getDataPrenotazione() + " - " + prenotazione.getNumeroCoperti());
        }
    }

    public void stampaListaPrenotazioniAggiornate() {
        System.out.println("Lista delle prenotazioni aggiornate:");
        for (Prenotazione prenotazione : prenotazioni) {
            System.out.println(prenotazione.getCliente().getNome() + " " + prenotazione.getCliente().getCognome() + " - " +
                    prenotazione.getDataPrenotazione() + " - " + prenotazione.getNumeroCoperti());
        }
    }
}
