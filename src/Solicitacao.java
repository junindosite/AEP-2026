import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    private int protocolo;
    private String descricao;
    private String prioridade;
    private Cidadao solicitante;
    private List<HistoricoStatus> historico = new ArrayList<>();

    public Solicitacao(int protocolo, String descricao, String prioridade, Cidadao solicitante) {

        if (descricao == null || descricao.length() < 10) {
            throw new IllegalArgumentException("Descrição muito curta. Explique melhor o problema.");
        }

        this.protocolo = protocolo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.solicitante = solicitante;
    }

    public void registrarMovimentacao(Status status, String comentario, String responsavel) {
        historico.add(new HistoricoStatus(status, comentario, responsavel));
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void imprimirRelatorio() {
        System.out.println("\n====================================================");
        System.out.println("DETALHES DO PROTOCOLO: #" + protocolo);
        System.out.println("SOLICITANTE: " + solicitante.getIdentidadeParaExibicao());
        System.out.println("DESCRIÇÃO: " + descricao);
        System.out.println("PRIORIDADE: " + prioridade);
        System.out.println("----------------------------------------------------");
        System.out.println("HISTÓRICO DE ATENDIMENTO:");

        for (HistoricoStatus h : historico) {
            System.out.println(h);
        }

        System.out.println("====================================================");
    }
}