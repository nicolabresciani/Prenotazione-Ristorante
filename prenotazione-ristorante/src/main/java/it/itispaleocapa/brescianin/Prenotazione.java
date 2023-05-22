package it.itispaleocapa.brescianin;

import java.time.LocalDate;

public class Prenotazione {
    public Cliente cliente;
    public LocalDate dataInserimento;
    public LocalDate dataPrenotazione;
    public int numeroCoperti;
    public Prenotazione(Cliente cliente, LocalDate dataInserimento, LocalDate dataPrenotazione, int numeroCoperti) {
        this.cliente = cliente;
        this.dataInserimento = dataInserimento;
        this.dataPrenotazione = dataPrenotazione;
        this.numeroCoperti = numeroCoperti;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataInserimento() {return dataInserimento;}
    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }
    public int getNumeroCoperti() {
        return numeroCoperti;
    }
    public void setNumeroCoperti(int numeroCoperti) {
        this.numeroCoperti = numeroCoperti;
    }
}