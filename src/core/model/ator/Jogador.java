package core.model.ator;

import core.model.divisao.Divisao;
import java.util.List;

/**
 * Representa um Jogador no Labirinto da Glória.
 * É a classe base (Abstract) para JogadorHumano e JogadorBot.
 * Armazena o estado atual, a posição e o histórico do jogador.
 */
public abstract class Jogador {

    private final String nome;
    private Divisao posicaoAtual;
    private final Historico historico; // Registo de logs para o relatório final
    private int turnosImpedido; // Contador para o efeito de BLOQUEIO_TURNO

    /**
     * Construtor do Jogador.
     *
     * @param nome O nome ou identificador do jogador.
     * @param posicaoInicial A divisão onde o jogador começa a partida (PontoPartida).
     */
    public Jogador(String nome, Divisao posicaoInicial) {
        this.nome = nome;
        this.posicaoAtual = posicaoInicial;
        // Inicializa o histórico usando estruturas da API
        this.historico = new Historico(this.nome);
        this.turnosImpedido = 0;
    }

    /**
     * Método abstrato para definir a forma como o jogador escolhe o próximo movimento.
     * Este é o ponto chave onde a lógica Humana (UI) e Bot (Strategy) se divergem.
     * * @param caminhosPossiveis Uma lista (ListADT) das divisões adjacentes disponíveis.
     * @return A Divisao (próximo vértice) para onde o jogador quer mover-se.
     */
    public abstract Divisao escolherMovimento(List<Divisao> caminhosPossiveis);

    // --- Métodos de Controlo de Turnos ---

    /**
     * Tenta decrementar o contador de impedimento.
     * @return true se o jogador ainda estiver impedido de jogar, false caso contrário.
     */
    public boolean tentarJogar() {
        if (turnosImpedido > 0) {
            System.out.println(nome + " está impedido de jogar. Faltam " + turnosImpedido + " turnos.");
            turnosImpedido--;
            return false; // Não pode jogar
        }
        return true; // Pode jogar
    }

    /**
     * Aplica um bloqueio de turnos (efeito de evento aleatório).
     * @param turnos O número de turnos a ser impedido de jogar.
     */
    public void aplicarBloqueio(int turnos) {
        this.turnosImpedido += turnos;
        System.out.println(nome + " foi BLOQUEADO por " + turnos + " turnos!");
    }
    
    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public Divisao getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Divisao novaPosicao) {
        // Lógica para desocupar a divisão anterior (a ser tratada no MotorJogo)
        this.posicaoAtual = novaPosicao;
        this.historico.adicionarMovimento(novaPosicao); // Registar movimento para o relatório
    }

    public Historico getHistorico() {
        return historico;
    }

    public int getTurnosImpedido() {
        return turnosImpedido;
    }
}