package core.game;

import core.io.LeitorJSON;
import core.model.ator.Jogador;
import core.model.ator.JogadorBot;
import core.model.divisao.Divisao;
import core.model.divisao.PontoPartida;
import core.model.itens.Enigma;
import Exceptions.EmptyCollectionException;
import Graphs.Network;
import Queues_Stacks.CircularArrayQueue;
import Queues_Stacks.QueueADT;
import Lists.DoublyLinkedList;
import Lists.ListADT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * MotorJogo.java
 * Classe responsável por controlar os turnos e as regras de vitória do jogo.
 * Usa estruturas da API (Network, CircularArrayQueue) para a lógica central.
 */
public class MotorJogo {
    
    // --- Estruturas de Dados da API ---
    // 1. Fila para gerir os turnos sequenciais dos jogadores
    private QueueADT<Jogador> filaDeTurnos;
    // 2. Lista para guardar todos os jogadores
    private ListADT<Jogador> todosOsJogadores;
    // 3. O GestorMapa irá encapsular a Network (Grafo Ponderado)
    private GestorMapa gestorMapa; 
    
    // --- Variáveis de Controlo ---
    private boolean running = false;
    private final List<Enigma> enigmas = new ArrayList<>(); // Mantemos List/Map do Java por enquanto para o JSON I/O

    public MotorJogo() {
        // Inicializa as estruturas da API
        this.filaDeTurnos = new CircularArrayQueue<>();
        this.todosOsJogadores = new DoublyLinkedList<>();
        this.gestorMapa = new GestorMapa(); // GestorMapa deve ser implementado para usar Network
    }

    public void start() {
        running = true;
        System.out.println("MotorJogo iniciado.");
        
        // Simulação: Adicionar Jogadores e iniciar turnos
        simularSetupInicial();
        
        gameLoop();
    }
    
    /**
     * Simula a configuração inicial do jogo, adicionando jogadores à fila de turnos.
     */
    private void simularSetupInicial() {
        System.out.println("--- SETUP SIMULADO ---");
        
        // Cria uma Divisao para o ponto de partida simulado
        Divisao pontoInicial = new PontoPartida(0, "Porta Principal", "O ponto de entrada.");
        
        // Cria jogadores e adiciona à lista e à fila (QueueADT para turnos)
        Jogador j1 = new JogadorBot("Bot Alpha", pontoInicial);
        Jogador j2 = new JogadorBot("Bot Beta", pontoInicial);
        
        adicionarJogador(j1);
        adicionarJogador(j2);

        System.out.println("Jogadores adicionados à fila de turnos: " + todosOsJogadores.size());
        // A lógica de carregar/simular o mapa real deve ocorrer aqui.
    }
    
    /**
     * Adiciona um jogador à lista de jogadores e à fila de turnos.
     * @param novoJogador O jogador a ser adicionado.
     */
    public void adicionarJogador(Jogador novoJogador) {
        this.todosOsJogadores.addLast(novoJogador); // addLast é um método da DoublyLinkedList
        this.filaDeTurnos.enqueue(novoJogador);
    }


    private void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBem-vindo ao MotorJogo. Digite 'ajuda' para ver comandos, 'sair' para terminar.");
        
        while (running && !filaDeTurnos.isEmpty()) {
            try {
                // 1. Obter o próximo jogador (dequeue)
                Jogador jogadorAtual = filaDeTurnos.dequeue();
                
                System.out.println("\n--- TURNO: " + jogadorAtual.getNome() + " ---");
                
                // 2. Tenta Jogar (verifica bloqueio por evento)
                if (!jogadorAtual.tentarJogar()) {
                    filaDeTurnos.enqueue(jogadorAtual); // Coloca de volta no final da fila
                    continue; // Passa para o próximo turno
                }

                // 3. Simular a escolha de movimento
                // A Network (grafo) seria usada para obter os vizinhos (Divisoes)
                ListADT<Divisao> caminhosPossiveis = gestorMapa.obterVizinhos(jogadorAtual.getPosicaoAtual());
                
                Divisao proximaDivisao = jogadorAtual.escolherMovimento(caminhosPossiveis);

                if (proximaDivisao != null) {
                    // 4. Lógica de Movimento e Processamento de Entrada
                    // ... (MotorJogo trata Corredor, processa Entrada, verifica vitória)
                    System.out.println(jogadorAtual.getNome() + " move-se para " + proximaDivisao.getNome());
                    
                    // 5. Verifica se é necessário interagir com a UI (se for JogadorHumano)
                    if (jogadorAtual instanceof core.model.ator.JogadorHumano) {
                        System.out.print("Simulação de interação manual: Digite 'OK' para confirmar ou 'SAIR' para terminar: ");
                        String line = scanner.nextLine().trim();
                        if ("SAIR".equalsIgnoreCase(line)) {
                            stop();
                            break;
                        }
                    }
                    
                    // 6. Concluir o turno e enfileirar o jogador para o próximo
                    filaDeTurnos.enqueue(jogadorAtual);
                } else {
                    // Se o jogador não escolheu movimento (e.g., JogadorHumano espera input real)
                    // Colocamo-lo de volta no início da fila para que a UI resolva o movimento.
                    filaDeTurnos.enqueue(jogadorAtual); 
                }

            } catch (EmptyCollectionException e) {
                // A fila de turnos está vazia (todos os jogadores já jogaram e foram removidos, 
                // ou o loop de turnos foi interrompido/terminado).
                System.out.println("Fila de turnos vazia. Fim de jogo ou erro.");
                stop();
                break;
            } catch (Exception e) {
                System.out.println("Ocorreu um erro no loop do jogo: " + e.getMessage());
                e.printStackTrace();
                stop();
                break;
            }
        }
        scanner.close();
    }


    public void stop() {
        running = false;
        System.out.println("MotorJogo parado.");
        // Exporta enigmas.json para enigmas.txt ao terminar a partida
        try {
            // ... Código de exportação ...
            // O relatório final deve usar ListADT<Jogador> todosOsJogadores 
            // para iterar e gerar o JSON de cada Historico.
            core.io.JsonExporter.exportJsonToTxt("resources/enigmas/enigmas.json", "resources/enigmas/enigmas.txt");
            System.out.println("Ficheiro enigmas.json exportado para enigmas.txt.");
        } catch (Exception e) {
            System.out.println("Erro ao exportar JSON para TXT: " + e.getMessage());
        }
    }
    
    // --- Métodos Auxiliares/Antigos (Omitidos para brevidade, mas devem ser mantidos) ---
    // carregarMapa(String caminho) e carregarEnigmas(String caminho)

    public void carregarMapa(String caminho) {
        System.out.println("carregarMapa: " + caminho + " (implementação pendente no GestorMapa)");
    }

    public void carregarEnigmas(String caminho) {
        // Implementação original do ficheiro fornecido
        // ...
        try {
            Object parsed = JsonParser.parseFile(caminho);
            // ... (restante lógica para popular a List<Enigma> enigmas)
            System.out.println("Enigmas carregados.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar enigmas: " + e.getMessage());
        }
    }
    
    // ... main method ...
}

/**
 * Classe de simulação para GestorMapa (deve ser implementada à parte).
 * Na realidade, esta classe usaria a Network<Divisao> da API.
 */
class GestorMapa {
    private Network<Divisao> labirinto; // Usaria a estrutura Network da API

    public GestorMapa() {
        // Inicialização da Network (omitida)
    }

    // Simula a obtenção de vizinhos (arestas) usando a ListADT da API
    public ListADT<Divisao> obterVizinhos(Divisao divisaoAtual) {
        // Na implementação real: labirinto.getNeighbors(divisaoAtual);
        
        // Simulação com DoublyLinkedList para demonstrar o uso da API
        DoublyLinkedList<Divisao> vizinhos = new DoublyLinkedList<>();
        vizinhos.addLast(new PontoPartida(99, "Sala A", "Sala de teste"));
        vizinhos.addLast(new PontoPartida(98, "Sala B", "Sala de teste"));
        return vizinhos;
    }
}