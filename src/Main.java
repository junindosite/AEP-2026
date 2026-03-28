import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Solicitacao> lista = new ArrayList<>();

        Categoria categoria = new Categoria("Iluminação");

        Solicitacao s = new Solicitacao(
                1,
                "Poste sem luz na rua X",
                "Centro",
                "Alta",
                true,
                categoria
        );

        HistoricoStatus h = new HistoricoStatus(
                "ABERTO",
                "Criado",
                LocalDate.now().toString(),
                "Sistema"
        );

        s.adicionarHistorico(h);

        lista.add(s);

        // Buscar
        for (Solicitacao sol : lista) {
            if (sol.getProtocolo() == 1) {
                System.out.println("Encontrado: " + sol.getProtocolo());
            }
        }
    }
}