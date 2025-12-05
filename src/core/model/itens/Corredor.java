package core.model.itens;

import core.model.ator.Jogador;

/**
 * Representa um Corredor no Labirinto, atuando como a aresta (Edge) do grafo.
 * Contém o estado de bloqueio e a lógica para eventos aleatórios.
 */
public class Corredor {

    private final int id;
    private final String descricao;
    private final double peso; // Pode ser um custo de movimento, compatível com a Network da API
    private boolean estaBloqueado;
    
    // O evento que pode ocorrer ao atravessar este corredor
    private Evento eventoAleatorio; 

    /**
     * Construtor para o Corredor.
     *
     * @param id O identificador único do corredor.
     * @param descricao A descrição do corredor.
     * @param peso O "custo" ou peso associado a atravessar este corredor (e.g., tempo).
     * @param evento O evento aleatório que pode ser desencadeado (pode ser null).
     */
    public Corredor(int id, String descricao, double peso, Evento evento) {
        this.id = id;
        this.descricao = descricao;
        this.peso = peso;
        this.eventoAleatorio = evento;
        // Começa desbloqueado, a menos que uma SalaAlavanca o controle
        this.estaBloqueado = false; 
    }

    /**
     * Tenta atravessar o corredor, verificando o bloqueio e ativando o evento.
     * Esta lógica seria chamada pelo MotorJogo.
     *
     * @param jogador O jogador que está a atravessar.
     * @return true se a passagem for permitida, false se estiver bloqueado.
     */
    public boolean atravessar(Jogador jogador) {
        if (estaBloqueado) {
            System.out.println("O corredor " + this.id + " está bloqueado. Não pode passar!");
            return false;
        }

        System.out.println(jogador.getNome() + " atravessa: " + descricao);
        
        // 1. Aplicar o Evento Aleatório (se existir)
        if (eventoAleatorio != null) {
            System.out.println("! ALERTA: Evento Aleatório Desencadeado !");
            // O MotorJogo ou uma classe de serviço terá de processar o efeito
            // Aqui, apenas registamos que o evento ocorreu.
            // Ex: MotorJogo.processarEvento(jogador, eventoAleatorio);
            jogador.getHistorico().adicionarEventoAplicado(eventoAleatorio); 
        }

        // 2. Aplicar o custo (o peso)
        // O MotorJogo pode usar este peso para calcular estatísticas ou o ranking.

        return true; // Passagem permitida
    }
    
    // --- Getters e Setters para a SalaAlavanca ---

    public boolean isEstaBloqueado() {
        return estaBloqueado;
    }

    /**
     * Permite à SalaAlavanca (via GestorMapa) alterar o estado do corredor.
     * @param estaBloqueado O novo estado de bloqueio.
     */
    public void setEstaBloqueado(boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
        if (estaBloqueado) {
             System.out.println("[Mapa] Corredor " + this.id + " foi BLOQUEADO.");
        } else {
             System.out.println("[Mapa] Corredor " + this.id + " foi DESBLOQUEADO.");
        }
    }
    
    // --- Outros Getters ---

    public int getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public Evento getEventoAleatorio() {
        return eventoAleatorio;
    }

    // Métodos essenciais para o uso nas estruturas da API (se necessário, dependendo da Network)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corredor corredor = (Corredor) o;
        return id == corredor.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}