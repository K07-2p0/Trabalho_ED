package core.model.ator;

import java.util.LinkedList;
import java.util.List;
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
    // Usamos listas Java para registar logs cronológicos
    private final List<String> logs; 
    private final List<Divisao> percurso;
    private final List<Enigma> enigmasResolvidos;
    private final List<Evento> eventosAplicados;

    /**
     * Construtor do Histórico.
     * @param nomeJogador O nome do jogador associado a este histórico.
     */
    public Historico(String nomeJogador) {
        this.nomeJogador = nomeJogador;
        // Usamos LinkedList do Java para manter a ordem cronológica
        this.logs = new LinkedList<>(); 
        this.percurso = new LinkedList<>();
        this.enigmasResolvidos = new LinkedList<>();
        this.eventosAplicados = new LinkedList<>();
    }
    
    // --- Métodos de Registo ---

    public void adicionarMovimento(Divisao novaDivisao) {
        this.percurso.add(novaDivisao);
        this.logs.add(nomeJogador + " moveu-se para " + novaDivisao.getNome());
    }

    public void adicionarEnigmaResolvido(Enigma enigma) {
        this.enigmasResolvidos.add(enigma);
        this.logs.add(nomeJogador + " resolveu o Enigma #" + enigma.getId());
    }
    
    public void adicionarEventoAplicado(Evento evento) {
        this.eventosAplicados.add(evento);
        this.logs.add("Evento aplicado: " + evento.getDescricao());
    }
    
    // --- Getters para Exportação (JSON) ---
    
    /**
     * Retorna a lista completa de logs cronológicos para o relatório.
     * @return Uma lista (ListADT) de strings com o registo de eventos.
     */
    public List<String> getLogs() {
        return logs;
    }
    
    public List<Divisao> getPercurso() {
        return percurso;
    }

    public List<Enigma> getEnigmasResolvidos() {
        return enigmasResolvidos;
    }

    public List<Evento> getEventosAplicados() {
        return eventosAplicados;
    }
}