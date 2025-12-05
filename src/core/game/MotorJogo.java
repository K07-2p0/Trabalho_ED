package core.game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import core.io.JsonParser;
import core.model.itens.Enigma;

public class MotorJogo {
    private boolean running = false;
    private final List<Enigma> enigmas = new ArrayList<>();

    public MotorJogo() {
    }

    public void start() {
        running = true;
        System.out.println("MotorJogo iniciado.");
        gameLoop();
    }

    public void stop() {
        running = false;
        System.out.println("MotorJogo parado.");
        // Exporta enigmas.json para enigmas.txt ao terminar a partida
        try {
            core.io.JsonExporter.exportJsonToTxt("resources/enigmas/enigmas.json", "resources/enigmas/enigmas.txt");
            System.out.println("Ficheiro enigmas.json exportado para enigmas.txt.");
        } catch (Exception e) {
            System.out.println("Erro ao exportar JSON para TXT: " + e.getMessage());
        }
    }

    private void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao MotorJogo. Digite 'ajuda' para ver comandos, 'sair' para terminar.");
        while (running) {
            System.out.print("> ");
            String line = null;
            try {
                if (!scanner.hasNextLine()) break;
                line = scanner.nextLine().trim();
            } catch (Exception e) {
                break;
            }
            if (line == null) break;
            if ("sair".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)) {
                stop();
                break;
            } else if ("ajuda".equalsIgnoreCase(line) || "help".equalsIgnoreCase(line)) {
                System.out.println("Comandos disponíveis: ajuda, status, carregarMapa <caminho>, carregarEnigmas <caminho>, sair");
            } else if (line.startsWith("carregarMapa ")) {
                String caminho = line.substring("carregarMapa ".length()).trim();
                carregarMapa(caminho);
            } else if (line.startsWith("carregarEnigmas ")) {
                String caminho = line.substring("carregarEnigmas ".length()).trim();
                carregarEnigmas(caminho);
            } else if ("jogar".equalsIgnoreCase(line)) {
                playEnigmas(scanner);
            } else if ("status".equalsIgnoreCase(line)) {
                System.out.println("Estado: " + (running ? "a correr" : "parado"));
            } else if (!line.isEmpty()) {
                System.out.println("Comando desconhecido: " + line);
            }
        }
        scanner.close();
    }

    private void playEnigmas(Scanner scanner) {
        if (enigmas.isEmpty()) {
            System.out.println("Nenhum enigma carregado. Use 'carregarEnigmas <caminho>' antes de jogar.");
            return;
        }
        int correct = 0;
        System.out.println("A iniciar sessão de enigmas. Responda escrevendo a opção (por exemplo: A, B, C)");
        for (int i = 0; i < enigmas.size(); i++) {
            Enigma e = enigmas.get(i);
            System.out.println("\nEnigma " + (i + 1) + ": " + e.getPergunta());
            String[] ops = e.getOpcoesResposta();
            for (int j = 0; j < ops.length; j++) {
                char label = (char) ('A' + j);
                System.out.println(label + ") " + ops[j]);
            }
            System.out.print("Resposta: ");
            String ans = null;
            try {
                if (!scanner.hasNextLine()) break;
                ans = scanner.nextLine().trim();
            } catch (Exception ex) {
                break;
            }
            if (e.verificarResposta(ans)) {
                System.out.println("Correto!");
                correct++;
            } else {
                System.out.println("Errado. Resposta correta: " + e.getRespostaCorreta());
            }
        }
        System.out.println("\nSessão de enigmas terminada. Pontuação: " + correct + "/" + enigmas.size());
    }

    public void carregarMapa(String caminho) {
        System.out.println("carregarMapa: " + caminho + " (implementação pendente)");
    }

    public void carregarEnigmas(String caminho) {
        try {
            Object parsed = JsonParser.parseFile(caminho);
            enigmas.clear();
            if (parsed instanceof List) {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) parsed;
                for (Object o : list) {
                    if (o instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> map = (Map<String, Object>) o;
                        Integer id = JsonParser.getInteger(map, "id");
                        String pergunta = JsonParser.getString(map, "pergunta");
                        List<Object> ops = JsonParser.getArray(map, "opcoes");
                        String resposta = JsonParser.getString(map, "resposta");
                        String[] opcoesArr = new String[0];
                        if (ops != null) {
                            opcoesArr = new String[ops.size()];
                            for (int i = 0; i < ops.size(); i++) {
                                opcoesArr[i] = ops.get(i) != null ? ops.get(i).toString() : "";
                            }
                        }
                        Enigma en = new Enigma(id != null ? id : -1, pergunta != null ? pergunta : "", opcoesArr, resposta != null ? resposta : "");
                        enigmas.add(en);
                    }
                }
                System.out.println("Foram carregados " + enigmas.size() + " enigmas de: " + caminho);
            } else {
                System.out.println("Formato de ficheiro de enigmas inesperado: esperar um array JSON");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar enigmas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MotorJogo motor = new MotorJogo();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String a = args[i];
                if ("--mapa".equals(a) && i + 1 < args.length) {
                    motor.carregarMapa(args[++i]);
                } else if ("--enigmas".equals(a) && i + 1 < args.length) {
                    motor.carregarEnigmas(args[++i]);
                }
            }
        }
        motor.start();
    }
}
