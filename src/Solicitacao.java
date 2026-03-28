import java.util.ArrayList;
import java.util.List;

public class Solicitacao {

    private int protocolo;
    private String descricao;
    private String localizacao;
    private String prioridade;
    private boolean anonimo;

    private Categoria categoria;
    private List<HistoricoStatus> historico;

    public Solicitacao(int protocolo, String descricao, String localizacao, String prioridade, boolean anonimo, Categoria categoria) {
        this.protocolo = protocolo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.anonimo = anonimo;
        this.categoria = categoria;
        this.historico = new ArrayList<>();
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void adicionarHistorico(HistoricoStatus h) {
        historico.add(h);
    }
}