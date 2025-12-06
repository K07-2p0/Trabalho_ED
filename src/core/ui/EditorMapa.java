package core.ui;


import java.util.Scanner;

/**
 * EditorMapa.java
 * Classe responsável por gerir a interface de edição do mapa em consola.
 * Permite ao utilizador criar, modificar e guardar labirintos.
 * Esta é uma funcionalidade extra do projeto.
 */
public class EditorMapa {

    private final GestorMapa gestorMapa;
    private final Scanner scanner;

    public EditorMapa(GestorMapa gestorMapa) {
        this.gestorMapa = gestorMapa;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia o loop principal do editor de mapa.
     */
    public void iniciarEdicao() {
        int opcao;
        do {
            apresentarOpcoes();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarNovoMapa();
                    break;
                case 2:
                    carregarEModificarMapa();
                    break;
                case 3:
                    // Exemplo: gestorMapa.mostrarGrafo();
                    System.out.println("Pré-visualização do mapa...");
                    break;
                case 0:
                    System.out.println("\nSaindo do Editor de Mapa.");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta as opções do Editor de Mapa.
     */
    private void apresentarOpcoes() {
        System.out.println("\n--- Opções do Editor de Mapa ---");
        System.out.println("1. Criar Novo Mapa (Definir Divisões e Corredores)");
        System.out.println("2. Carregar e Modificar Mapa Existente");
        System.out.println("3. Pré-visualizar Mapa Atual");
        System.out.println("0. Voltar ao Menu Principal");
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
            return -1;
        }
    }

    /**
     * Inicia o processo de criação de um novo mapa.
     */
    private void criarNovoMapa() {
        System.out.println("\n-- Criar Novo Mapa --");
        // Aqui o GestorMapa seria usado para criar o grafo e adicionar nós/arestas.
        // gestorMapa.iniciarNovoGrafo();
        System.out.println("Funcionalidade de criação ainda não detalhada.");
    }

    /**
     * Carrega um mapa existente para modificação.
     */
    private void carregarEModificarMapa() {
        System.out.println("\n-- Carregar e Modificar Mapa --");
        System.out.print("Insira o nome/ID do mapa a carregar: ");
        String mapaId = scanner.nextLine();
        // A lógica de I/O estaria no LeitorJSON, mas a chamada é feita aqui.
        // gestorMapa.carregar(mapaId);
        System.out.println("Mapa '" + mapaId + "' carregado para edição. (Funcionalidade em desenvolvimento)");
    }
}
