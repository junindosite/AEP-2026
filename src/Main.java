import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();

        while (true) {
            System.out.println("\n--- SISTEMA OBSERVAÇÃO ---");
            System.out.println("1 - Perfil Cidadão");
            System.out.println("2 - Perfil Admin");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int tipo = scanner.nextInt();

            if (tipo == 1) {
                menuCidadao(scanner, servico);
            } else if (tipo == 2) {
                menuAdmin(scanner, servico);
            } else if (tipo == 0) {
                System.out.println("Encerrando sistema...");
                break;
            }
        }
        scanner.close();
    }

    public static void menuCidadao(Scanner scanner, ServicoSolicitacoes servico) {
        int op;
        do {
            System.out.println("\n>>> MENU CIDADÃO <<<");
            System.out.println("1 - Nova solicitação");
            System.out.println("2 - Buscar por Protocolo");
            System.out.println("9 - Trocar perfil");
            System.out.println("0 - Sair do Programa");
            System.out.print("Opção: ");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Seu Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Seu CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Seu Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Deseja ser anônimo? (s/n): ");
                    boolean anonimo = scanner.nextLine().equalsIgnoreCase("s");

                    System.out.print("Rua/Local do problema: ");
                    String rua = scanner.nextLine();

                    System.out.print("Descrição da ocorrência: ");
                    String desc = scanner.nextLine();

                    System.out.println("Prioridade: 1-BAIXA | 2-MÉDIA | 3-ALTA");
                    int p = scanner.nextInt();
                    scanner.nextLine();
                    String prioridade = (p == 1) ? "BAIXA" : (p == 2) ? "MEDIA" : "ALTA";

                    int protocolo = (int) (Math.random() * 1000);


                    Cidadao cidadao = new Cidadao(nome, cpf, email, anonimo);
                    Solicitacao s = new Solicitacao(protocolo, desc, prioridade, rua, cidadao);

                    servico.novaSolicitacao(s);

                    System.out.println("\n✅ Solicitação registrada com sucesso!");
                    System.out.println("Guarde seu Protocolo: #" + protocolo);
                    break;

                case 2:
                    System.out.print("Digite o protocolo para busca: ");
                    int busca = scanner.nextInt();

                    Solicitacao res = servico.buscarPorProtocolo(busca);

                    if (res != null) {

                        res.imprimirRelatorio(false);
                    } else {
                        System.out.println("⚠️ Protocolo não encontrado.");
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
            System.out.println("\n>>> MENU ADMINISTRADOR <<<");
            System.out.println("1 - Listar todas as solicitações");
            System.out.println("2 - Atualizar status de uma solicitação");
            System.out.println("9 - Trocar perfil");
            System.out.println("0 - Sair do Programa");
            System.out.print("Opção: ");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    servico.listarSolicitacoes(false);
                    break;

                case 2:
                    System.out.print("Protocolo que deseja atualizar: ");
                    int protocolo = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao s = servico.buscarPorProtocolo(protocolo);

                    if (s != null) {
                        System.out.println("Novo Status: 1-TRIAGEM | 2-EM EXECUÇÃO | 3-RESOLVIDO | 4-ENCERRADO");
                        int escolha = scanner.nextInt();
                        scanner.nextLine();

                        Status status = null;
                        switch (escolha) {
                            case 1: status = Status.TRIAGEM; break;
                            case 2: status = Status.EM_EXECUCAO; break;
                            case 3: status = Status.RESOLVIDO; break;
                            case 4: status = Status.ENCERRADO; break;
                            default: status = Status.TRIAGEM;
                        }

                        System.out.print("Comentário técnico: ");
                        String comentario = scanner.nextLine();

                        s.registrarMovimentacao(status, comentario, "Administrador");
                        System.out.println("✅ Status atualizado!");
                    } else {
                        System.out.println("⚠️ Solicitação não encontrada.");
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