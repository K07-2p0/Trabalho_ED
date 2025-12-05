package core.model.divisao;

import core.model.ator.Jogador;
import core.model.itens.Enigma;

/**
 * Representa uma Divisão que contém um Enigma que deve ser resolvido
 * para o jogador poder continuar o seu percurso.
 */
public class SalaEnigma extends Divisao {

    private Enigma enigmaAssociado;
    private boolean enigmaResolvido;

    /**
     * Construtor para a Sala do Enigma.
     *
     * @param id O identificador único da divisão.
     * @param nome O nome da divisão.
     * @param descricao A descrição detalhada da sala.
     * @param enigma O enigma que esta sala apresenta ao jogador.
     */
    public SalaEnigma(int id, String nome, String descricao, Enigma enigma) {
        super(id, nome, descricao);
        this.enigmaAssociado = enigma;
        this.enigmaResolvido = false; // Começa sempre não resolvido
    }

    /**
     * Implementação do método abstrato da classe Divisao.
     * Quando um jogador entra, é confrontado com o enigma.
     *
     * @param jogador O jogador que acabou de entrar na divisão.
     * @return true se o enigma já estiver resolvido (permitindo a passagem), 
     * false se o jogador falhar a resposta (bloqueando a passagem e exigindo nova tentativa).
     */
    @Override
    public boolean processarEntrada(Jogador jogador) {
        // Atualiza a posição do jogador
        setJogadorAtual(jogador);
        
        System.out.println(jogador.getNome() + " entrou em " + getNome() + ".");

        if (enigmaResolvido) {
            System.out.println("Enigma já resolvido! A passagem está aberta.");
            return true; // Enigma resolvido, pode continuar
        }

        // Se não resolvido, o jogador deve tentar responder
        System.out.println("\n--- ENIGMA ---");
        System.out.println(enigmaAssociado.getPergunta());

        // *** AQUI ENTRA A INTERAÇÃO DA UI/MOTOR DE JOGO ***
        // O MotorJogo (ou a UI) irá solicitar a resposta do jogador (humano ou bot)
        
        // Exemplo: Simulação da obtenção da resposta (Esta parte será gerida pela UI/MotorJogo)
        // String respostaJogador = MotorJogo.solicitarResposta(jogador); 
        
        // Assumimos que o MotorJogo recebe a resposta e a passa para um método de verificação.
        return tentarResolverEnigma(jogador);
    }
    
    /**
     * Método auxiliar para tentar resolver o enigma e atualizar o estado.
     * Este método seria chamado pelo MotorJogo após o jogador fornecer uma resposta.
     * * @param jogador O jogador que tenta a resolução.
     * @return true se o enigma for resolvido, false se falhar.
     */
    public boolean tentarResolverEnigma(Jogador jogador) {
        // Esta é uma simulação. Na implementação real, usará a resposta do jogador.
        boolean acertou = simularResposta(jogador); // Método simula a verificação da resposta

        if (acertou) {
            System.out.println(jogador.getNome() + " acertou no enigma! Pode continuar.");
            this.enigmaResolvido = true; // Permite a passagem em jogadas futuras (para todos)
            jogador.getHistorico().adicionarEnigmaResolvido(enigmaAssociado);
            return true;
        } else {
            System.out.println(jogador.getNome() + " falhou. Terá de tentar novamente numa jogada futura.");
            // O MotorJogo deve manter o jogador nesta divisão e passar o turno (retornando false).
            return false;
        }
    }
    
    /**
     * (Apenas para demonstração) Simula a verificação da resposta do jogador.
     * Na implementação final, a lógica de comparação de strings estaria aqui.
     * @param jogador O jogador que tenta responder.
     * @return true para simular um acerto, false para simular um erro.
     */
    private boolean simularResposta(Jogador jogador) {
        // Implementação real: compara a resposta do jogador com enigmaAssociado.getRespostaCorreta()
        // Por exemplo, bots poderiam ter uma probabilidade de acerto.
        // Para este esqueleto, retornamos um valor fixo para a simulação:
        return Math.random() > 0.5; 
    }

    /**
     * Atualiza o estado da divisão. Se resolvido, nada mais a fazer.
     */
    @Override
    public void atualizarEstado() {
        // Nada a fazer, o estado muda apenas na tentativa de resolução.
    }

    // --- Getters ---

    public Enigma getEnigmaAssociado() {
        return enigmaAssociado;
    }

    public boolean isEnigmaResolvido() {
        return enigmaResolvido;
    }
}