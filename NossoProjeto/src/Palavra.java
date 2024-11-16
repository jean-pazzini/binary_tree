public class Palavra implements Comparable<Palavra> {

    private String palavra;
    private int ocorrencia;

    Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencia = 1;
    }

    @Override
    public int compareTo(Palavra outraPalavra) {
        return this.palavra.compareTo(outraPalavra.palavra);
    }

    public String getPalavra() {
        return palavra;
    }

    public int getOcorrencia() {
        return ocorrencia;
    }

    public void incrementaOcorrencia() {
        ocorrencia++;
    }
    
}
