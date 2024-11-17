import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

// +--------------------------------------------------------------------------------------+
// |                        Universidade Presbiteriana Mackenzie                          |
// |                                                                                      |
// |                                        FCI                                           |
// |                                                                                      |
// |                                     S.I - 03J                                        |
// |                                                                                      |
// |                               Bruna Franca Martinez                                  |
// |                                    RA: 10420225                                      |
// |                                                                                      |
// |                               Jean Pazzini Domingues                                 |
// |                                    RA: 10420319                                      |
// +--------------------------------------------------------------------------------------+

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        ArvoreBinariaDeBusca arvore = new ArvoreBinariaDeBusca();
        Scanner scanner = new Scanner(System.in);
        

        int opcao;
        int qtdLinhas = 0;
        int totalPalavras = 0;
        int totalPalavrasDistintas = 0;
        int qntOcorrencias = 0;
        Map<String, Integer> Ordena = new TreeMap<>();
        String palavraMaisLonga = "";
        int numero = 0;

        limparConsole();
        apresentacao();
        temporizador(2);

        do {
            limparConsole();
            System.out.println("----------------------");
            System.out.println("Projeto - Binary tree");
            System.out.println("----------------------");
            System.out.println("1. Carregar texto");
            System.out.println("2. Exibir estatísticas");
            System.out.println("3. Busca por palavra");
            System.out.println("4. Ordem Alfabetica e quantidade");
            System.out.println("5. Top 3 palavras com maior ocorrência");
            System.out.println("0. Sair");
            System.out.println("----------------------");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        File musica = new File("/workspaces/binary_tree/NossoProjeto/src/musica.txt");
                        Scanner scMusica = new Scanner(musica);
                        while (scMusica.hasNext()) {
                            
                            String linha = scMusica.nextLine();
                            if(!linha.isEmpty()) {
                                qtdLinhas++;
                                String[] palavras = linha.split("\\s+");
                                totalPalavras += palavras.length;
                                for (String palavra : palavras) {
                                    arvore.insereNovoNo(new Palavra(palavra));
                                    numero = arvore.buscar(palavra);
                                    Ordena.put(palavra, numero);
                                
                                    if(palavra.length() > palavraMaisLonga.length()){
                                        palavraMaisLonga = palavra;
                                    }
                                }
                                totalPalavrasDistintas = arvore.getTotalPalavrasDistintas();
                            }
                        }
                        System.out.println("TEXTO CARREGADO");
                        scMusica.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado.");
                    }
                    temporizador(2);
                    break;
                

                case 2:

                    System.out.println("Total de linhas: " + qtdLinhas);
                    System.out.println("Total de palavras: " + totalPalavras);
                    System.out.println("Total de palavras distintas: " + totalPalavrasDistintas);
                    System.out.println("Palavra mais longa: " + palavraMaisLonga);
                    temporizador(4);
                    break;
                
                case 3:
                    System.out.println("Digite uma palavra: ");
                    String palavra = scanner.next();
                    qntOcorrencias = arvore.buscar(palavra);
                    System.out.println("Quantidade de ocorrencias da palavra " + palavra + " : " +qntOcorrencias + " Vezes");
                    temporizador(4);
                    break;
                
                case 4: 
                // Um HashMap é criado para armazenar as palavras e suas respectivas contagens.
                // Os pares (palavra, contagem) são adicionados ao Map usando o método put().
                // Map.Entry: Representa uma associação chave-valor.
                // entry.getKey(): Obtém a chave (palavra).
                // entry.getValue(): Obtém o valor (contagem).
                
                for (Map.Entry<String, Integer> entry : Ordena.entrySet()) {
                    String palavraMap = entry.getKey(); // Obtém a chave (palavra)
                    int contagem = entry.getValue(); // Obtém o valor (contagem)
                    System.out.println("A palavra '" + palavraMap + "' aparece " + contagem + " vezes.");
                }
                temporizador(6);
                break; 

                case 5:
                    // Ordenar o TreeMap por contagem em ordem decrescente usando PriorityQueue
                    PriorityQueue<Map.Entry<String, Integer>> filaPrioridade = new PriorityQueue<>(
                        (a, b) -> b.getValue() - a.getValue() // Comparador para ordenar por valor decrescente
                    );
                    filaPrioridade.addAll(Ordena.entrySet());

                    // Imprimir as 3 palavras com maior ocorrência
                    System.out.println("Top 3 palavras com maior ocorrência:");
                    for (int i = 1; i < 4 && !filaPrioridade.isEmpty(); i++) {
                        Map.Entry<String, Integer> entry = filaPrioridade.poll();
                        System.out.println(i + "º lugar: " + entry.getKey() + ": " + entry.getValue());
                    }
                    temporizador(4);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    temporizador(2);
                    limparConsole();
                    apresentacao();
                    break;


                default:
                    System.out.println("Opção inválida.");
                    temporizador(1);
            }
                
        } while (opcao != 0);
        scanner.close();
    }

    public static void limparConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao limpar o console: " + e.getMessage());
        }
    }

    public static void temporizador(int tempo) {
        try {
            // Pausa a execução por 1 segundo (ajuste o tempo conforme necessário)
            TimeUnit.SECONDS.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void apresentacao() {
    System.out.println("                +--------------------------------------------------------------------------------------+");
    System.out.println("                |                        Universidade Presbiteriana Mackenzie                          |");
    System.out.println("                |                                                                                      |");
    System.out.println("                |                                        FCI                                           |");
    System.out.println("                |                                                                                      |");
    System.out.println("                |                                     S.I - 03J                                        |");
    System.out.println("                |                                                                                      |");
    System.out.println("                |                               Bruna Franca Martinez                                  |");
    System.out.println("                |                                    RA: 10420225                                      |");
    System.out.println("                |                                                                                      |");
    System.out.println("                |                               Jean Pazzini Domingues                                 |");
    System.out.println("                |                                    RA: 10420319                                      |");
    System.out.println("                +--------------------------------------------------------------------------------------+");
    }
}
