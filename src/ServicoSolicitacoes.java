import java.util.ArrayList;
import java.util.List;

public class ServicoSolicitacoes {

    private List<Solicitacao> repositorio = new ArrayList<>();

    // A função novaSolicitacao centraliza a criação e registro das solicitações.
    // Aplica encapsulamento, garantindo que toda solicitação seja adicionada corretamente.
    // Também define automaticamente o status inicial da solicitação.
    // Possui validação para evitar dados inválidos.
    // Isso melhora a confiabilidade do sistema e facilita manutenção futura.
    public void novaSolicitacao(Solicitacao s) {
        if (s != null) {
            repositorio.add(s);
            s.registrarMovimentacao(
                    Status.ABERTO,
                    "Solicitação criada pelo cidadão.",
                    "Sistema"
            );
        }
    }

    public Solicitacao buscarPorProtocolo(int protocolo) {
        return repositorio.stream()
                .filter(s -> s.getProtocolo() == protocolo)
                .findFirst()
                .orElse(null);
    }

    public void listarSolicitacoes(boolean isAdmin) {
        for (Solicitacao s : repositorio) {
            s.imprimirRelatorio(isAdmin);
        }
    }
}