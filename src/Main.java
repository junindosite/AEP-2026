import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();

        while (true) {
            System.out.println("\n1 - Cidadão");
            System.out.println("2 - Admin");
            System.out.println("0 - Sair");

            int tipo = scanner.nextInt();

            if (tipo == 1) {
                menuCidadao(scanner, servico);
            } else if (tipo == 2) {
                menuAdmin(scanner, servico);
            } else if (tipo == 0) {
                break;
            }
        }

        scanner.close();
    }

    // A função menuCidadao foi criada para organizar melhor o fluxo do programa.
    // Antes, toda a lógica estava no main, dificultando leitura e manutenção.
    // Foi aplicada a técnica de extração de método, separando a responsabilidade.
    // Agora, essa função tem apenas uma responsabilidade: gerenciar ações do cidadão.
    // Isso melhora organização, clareza e facilita futuras manutenções.
    public static void menuCidadao(Scanner scanner, ServicoSolicitacoes servico) {

        int op;

        do {
            System.out.println("\n1 - Nova solicitação");
            System.out.println("2 - Buscar");
            System.out.println("9 - Trocar perfil");
            System.out.println("0 - Sair");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Anônimo? (s/n): ");
                    boolean anonimo = scanner.nextLine().equalsIgnoreCase("s");

                    System.out.print("Rua do problema: ");
                    String rua = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();

                    System.out.println("1-BAIXA 2-MEDIA 3-ALTA");
                    int p = scanner.nextInt();
                    scanner.nextLine();

                    String prioridade = (p == 1) ? "BAIXA" : (p == 2) ? "MEDIA" : "ALTA";

                    int protocolo = (int) (Math.random() * 1000);

                    Solicitacao s = new Solicitacao(protocolo, desc, prioridade, rua,
                            new Cidadao(nome, cpf, email, anonimo));

                    servico.novaSolicitacao(s);

                    System.out.println("Protocolo: " + protocolo);
                    break;

                case 2:
                    System.out.print("Protocolo: ");
                    int busca = scanner.nextInt();

                    Solicitacao res = servico.buscarPorProtocolo(busca);

                    if (res != null) {
                        res.imprimirRelatorio(false);
                    } else {
                        System.out.println("Não encontrado");
                    }
                    break;

                case 9:
                    return;

                case 0:
                    System.exit(0);
            }

        } while (true);
    }

    public static void menuAdmin(Scanner scanner, ServicoSolicitacoes servico) {

        int op;

        do {
            System.out.println("\n1 - Listar");
            System.out.println("2 - Atualizar status");
            System.out.println("9 - Trocar perfil");
            System.out.println("0 - Sair");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {

                case 1:
                    servico.listarSolicitacoes(true);
                    break;

                case 2:
                    System.out.print("Protocolo: ");
                    int protocolo = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao s = servico.buscarPorProtocolo(protocolo);

                    if (s != null) {
                        System.out.println("1-TRIAGEM 2-EM_EXECUCAO 3-RESOLVIDO 4-ENCERRADO");
                        int escolha = scanner.nextInt();
                        scanner.nextLine();

                        Status status = null;

                        switch (escolha) {
                            case 1: status = Status.TRIAGEM; break;
                            case 2: status = Status.EM_EXECUCAO; break;
                            case 3: status = Status.RESOLVIDO; break;
                            case 4: status = Status.ENCERRADO; break;
                        }

                        System.out.print("Comentário: ");
                        String comentario = scanner.nextLine();

                        s.registrarMovimentacao(status, comentario, "Admin");
                    }
                    break;

                case 9:
                    return;

                case 0:
                    System.exit(0);
            }

        } while (true);
    }
}