import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    private int protocolo;
    private String descricao;
    private String prioridade;
    private String rua;
    private Cidadao solicitante;
    private List<HistoricoStatus> historico = new ArrayList<>();

    public Solicitacao(int protocolo, String descricao, String prioridade, String rua, Cidadao solicitante) {

        if (descricao == null || descricao.length() < 10) {
            throw new IllegalArgumentException("Descrição muito curta.");
        }

        this.protocolo = protocolo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.rua = rua;
        this.solicitante = solicitante;
    }

    public void registrarMovimentacao(Status status, String comentario, String responsavel) {
        historico.add(new HistoricoStatus(status, comentario, responsavel));
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void imprimirRelatorio(boolean isAdmin) {
        System.out.println("\n====================================================");
        System.out.println("PROTOCOLO: #" + protocolo);

        if (isAdmin) {
            System.out.println("SOLICITANTE: " + solicitante.getNome());
            System.out.println("CPF: " + solicitante.getCpf());
            System.out.println("EMAIL: " + solicitante.getEmail());
        } else {
            System.out.println("SOLICITANTE: " + solicitante.getIdentidadeParaExibicao());
        }

        System.out.println("RUA: " + rua);
        System.out.println("DESCRIÇÃO: " + descricao);
        System.out.println("PRIORIDADE: " + prioridade);

        System.out.println("----------------------------------------------------");
        System.out.println("HISTÓRICO:");

        for (HistoricoStatus h : historico) {
            System.out.println(h);
        }

        System.out.println("====================================================");
    }
}