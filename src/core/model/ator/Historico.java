package core.model.ator;

import Lists.DoublyLinkedList; // API: Lista para manter a ordem cronológica
import Lists.ListADT;           // Interface
import core.model.divisao.Divisao;
import core.model.itens.Enigma;
import core.model.itens.Evento;

/**
 * Classe responsável por registar o percurso, obstáculos enfrentados, 
 * enigmas resolvidos e efeitos aplicados a um jogador. 
 * Utilizada para gerar o relatório final em JSON.
 */
public class Historico {

    private final String nomeJogador;
    // Usamos a lista duplamente ligada (DoublyLinkedList) da API para registar logs cronológicos
    private final ListADT<String> logs; 
    private final ListADT<Divisao> percurso;
    private final ListADT<Enigma> enigmasResolvidos;
    private final ListADT<Evento> eventosAplicados;

    /**
     * Construtor do Histórico.
     * @param nomeJogador O nome do jogador associado a este histórico.
     */
    public Historico(String nomeJogador) {
        this.nomeJogador = nomeJogador;
        // As listas da sua API são usadas como a estrutura de dados para armazenamento
        this.logs = new DoublyLinkedList<>(); 
        this.percurso = new DoublyLinkedList<>();
        this.enigmasResolvidos = new DoublyLinkedList<>();
        this.eventosAplicados = new DoublyLinkedList<>();
    }
    
    // --- Métodos de Registo ---

    public void adicionarMovimento(Divisao novaDivisao) {
        this.percurso.addToRear(novaDivisao);
        this.logs.addToRear(nomeJogador + " moveu-se para " + novaDivisao.getNome());
    }

    public void adicionarEnigmaResolvido(Enigma enigma) {
        this.enigmasResolvidos.addToRear(enigma);
        this.logs.addToRear(nomeJogador + " resolveu o Enigma #" + enigma.getId());
    }
    
    public void adicionarEventoAplicado(Evento evento) {
        this.eventosAplicados.addToRear(evento);
        this.logs.addToRear("Evento aplicado: " + evento.getDescricao());
    }
    
    // --- Getters para Exportação (JSON) ---
    
    /**
     * Retorna a lista completa de logs cronológicos para o relatório.
     * @return Uma lista (ListADT) de strings com o registo de eventos.
     */
    public ListADT<String> getLogs() {
        return logs;
    }
    
    public ListADT<Divisao> getPercurso() {
        return percurso;
    }

    public ListADT<Enigma> getEnigmasResolvidos() {
        return enigmasResolvidos;
    }

    public ListADT<Evento> getEventosAplicados() {
        return eventosAplicados;
    }
}