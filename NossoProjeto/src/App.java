import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        ArvoreBinariaDeBusca arvore = new ArvoreBinariaDeBusca();
        Scanner scanner = new Scanner(System.in);
        

        int opcao;
        int qtdLinhas = 0;
        int totalPalavras = 0;
        int totalPalavrasDistintas = 0;
        String palavraMaisLonga = "";

        do {
            System.out.println("1. Carregar texto");
            System.out.println("2. Exibir estatísticas");
            System.out.println("0. Sair");
            System.out.println("Opção: ");
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
                                totalPalavrasDistintas++;
                            }
                            
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