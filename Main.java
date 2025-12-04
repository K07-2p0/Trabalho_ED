import Graphs.Network; 

public class Main {
    public static void main(String[] args) {
        System.out.println("--- TESTE DE LIGAÇÃO AO JAR ---");

        try {
            System.out.println("1. A tentar aceder à classe Network...");
            
            // Se o .jar não estiver bem ligado, o erro rebenta AQUI:
            Network<String> meuGrafo = new Network<>();
            meuGrafo.addVertex("Teste");
            
            System.out.println("2. Sucesso! O objeto foi criado.");
            System.out.println("✅ O .jar está corretamente ligado ao projeto!");
            
        } catch (NoClassDefFoundError e) {
            // Este é o erro que acontece se o Java não encontrar a biblioteca
            System.err.println("❌ ERRO CRÍTICO: O Java não encontrou a definição da classe!");
            System.err.println("Causa provável: O ficheiro .jar não está no Classpath ou o nome está errado.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Erro genérico: " + e.getMessage());
        }
    }
}
