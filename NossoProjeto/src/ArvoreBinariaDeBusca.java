public class ArvoreBinariaDeBusca {
    Node root;

    public void insereNovoNo(Palavra palavra) {
        root = insereRecursivo(root, palavra);
    }

    private Node insereRecursivo(Node root, Palavra palavra) {
        if (root == null) {
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

}