public class ArvoreBinariaDeBusca {
    Node root;
    int totalPalavrasDistintas;

    public void insereNovoNo(Palavra palavra) {
        root = insereRecursivo(root, palavra);
    }

    private Node insereRecursivo(Node root, Palavra palavra) {
        if (root == null) {
            totalPalavrasDistintas++;
            return new Node(palavra);
        }

        if (palavra.compareTo(root.palavra) < 0) {
            root.left = insereRecursivo(root.left, palavra);
        } else if (palavra.compareTo(root.palavra) > 0) {
            root.right = insereRecursivo(root.right, palavra);
        }else {
            root.palavra.incrementaOcorrencia();
        }

        return root;
    }

    public int getTotalPalavrasDistintas() {
        return totalPalavrasDistintas;
    }


    public int buscar(String palavra) {
        return buscarRecursivo(root, palavra);
    }

    private int buscarRecursivo(Node no, String palavra) {
        if (no == null) {
            return 0; // Palavra não encontrada
        }

        int comparacao = palavra.compareTo(no.palavra.getPalavra());
        if (comparacao < 0) {
            return buscarRecursivo(no.left, palavra);
        } else if (comparacao > 0) {
            return buscarRecursivo(no.right, palavra);
        } else {
            return no.palavra.getOcorrencia(); // Palavra encontrada, retorna as ocorrências
        }
    }

    
}


