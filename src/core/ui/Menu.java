package core.ui;


import java.util.Scanner;

/**
 * Menu.java
 * Classe responsável por gerir a interface de utilizador baseada em consola
 * para interagir com o Motor do Jogo.
 *
 * Apresenta as opções principais: Iniciar Jogo, Editar Mapa, Sair.
 */
public class Menu {

    private final MotorJogo motorJogo;
    private final EditorMapa editorMapa;
    private final Scanner scanner;

    public Menu(MotorJogo motorJogo, EditorMapa editorMapa) {
        this.motorJogo = motorJogo;
        this.editorMapa = editorMapa;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia o loop principal do menu.
     */
    public void iniciar() {
        int opcao;
        do {
            apresentarOpcoes();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    acessarEditorMapa();
                    break;
                case 0:
                    System.out.println("\nObrigado por jogar Labirinto da Glória! Até breve.");
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta as opções do menu no ecrã.
     */
    private void apresentarOpcoes() {
        System.out.println("\n===================================");
        System.out.println("        LABIRINTO DA GLÓRIA");
        System.out.println("===================================");
        System.out.println("1. Iniciar Novo Jogo");
        System.out.println("2. Editor de Mapa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Lê a opção escolhida pelo utilizador.
     * @return O número da opção.
     */
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna inválido
        }
    }

    /**
     * Prepara e inicia um novo jogo.
     */
    private void iniciarJogo() {
        System.out.println("\n--- INICIAR JOGO ---");
        // Aqui deve carregar um mapa e configurar os jogadores (Humano e Bots).
        // motorJogo.carregarMapa(ID_DO_MAPA);
        // motorJogo.adicionarJogador(novoJogador);
        // motorJogo.iniciar();

        System.out.println("Funcionalidade de Iniciar Jogo ainda não implementada.");
    }

    /**
     * Acede à funcionalidade do Editor de Mapa.
     */
    private void acessarEditorMapa() {
        System.out.println("\n--- EDITOR DE MAPA ---");
        // Delega o controlo para a classe EditorMapa
        editorMapa.iniciarEdicao();
    }
}