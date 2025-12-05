package core.model.ator;

import Lists.ListADT;
import core.model.divisao.Divisao;

/**
 * Representa um Jogador Humano (Modo Manual).
 * A escolha do movimento é delegada à Interface de Utilizador (Menu).
 */
public class JogadorHumano extends Jogador {

    /**
     * Construtor para Jogador Humano.
     *
     * @param nome O nome do jogador.
     * @param posicaoInicial A divisão de início.
     */
    public JogadorHumano(String nome, Divisao posicaoInicial) {
        super(nome, posicaoInicial);
    }

    /**
     * Implementa a lógica de escolha de movimento para um jogador humano.
     * Para o modo manual, este método simula a interação com a UI,
     * devolvendo null, e o MotorJogo irá intercetar a escolha real do utilizador.
     *
     * @param caminhosPossiveis Uma lista (ListADT) das divisões adjacentes disponíveis.
     * @return null, pois a escolha real será tratada pela UI/MotorJogo.
     */
    @Override
    public Divisao escolherMovimento(ListADT<Divisao> caminhosPossiveis) {
        // Num cenário real, esta classe pediria à UI para mostrar as opções e esperar pelo input.
        // O MotorJogo deve chamar a UI (Menu.java) para apresentar os 'caminhosPossiveis'
        // e obter a Divisao de destino.
        
        System.out.println("\n" + getNome() + ", é a sua vez.");
        System.out.println("Caminhos disponíveis:");
        
        // Simulação de listagem das opções para a UI
        for (int i = 0; i < caminhosPossiveis.size(); i++) {
            Divisao d = caminhosPossiveis.get(i);
            System.out.println("[" + (i + 1) + "] -> " + d.getNome() + " (ID: " + d.getId() + ")");
        }
        
        System.out.println("Aguardando input do utilizador...");
        
        // Retorna null ou um valor especial para indicar ao MotorJogo que deve aguardar a entrada do utilizador.
        return null; 
    }
}