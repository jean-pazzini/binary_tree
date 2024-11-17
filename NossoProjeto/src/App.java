import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
       

        do {
            System.out.println("----------------------");
            System.out.println("1. Carregar texto");
            System.out.println("2. Exibir estatísticas");
            System.out.println("3. Busca por palavra");
            System.out.println("4. Ordem Alfabetica e quantidade");
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
                            
                            qtdLinhas++;
                            String linha = scMusica.nextLine();
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
                        System.out.println("TEXTO CARREGADO");
                        scMusica.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado.");
                    }
                    break;
                

                case 2:

                    System.out.println("Total de linhas: " + qtdLinhas);
                    System.out.println("Total de palavras: " + totalPalavras);
                    System.out.println("Total de palavras distintas: " + totalPalavrasDistintas);
                    System.out.println("Palavra mais longa: " + palavraMaisLonga);
                    break;
                
                case 3:
                    System.out.println("Digite uma palavra: ");
                    String palavra = scanner.next();
                    qntOcorrencias = arvore.buscar(palavra);
                    System.out.println("Quantidade de ocorrencias da palavra " + palavra + " : " +qntOcorrencias + " Vezes");
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
                break; 


                case 0:
                    System.out.println("Saindo...");
                    break;


                default:
                    System.out.println("Opção inválida.");

            }
                
        } while (opcao != 0);
        scanner.close();
    }
}
