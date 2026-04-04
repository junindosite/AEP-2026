import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();

        int opcao;

        do {
            System.out.println("\n====== SISTEMA DE SOLICITAÇÕES ======");
            System.out.println("1 - Nova solicitação");
            System.out.println("2 - Buscar por protocolo");
            System.out.println("3 - Listar todas");
            System.out.println("4 - Atualizar status");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("\n--- NOVA SOLICITAÇÃO ---");

                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Deseja ser anônimo? (s/n): ");
                    boolean anonimo = scanner.nextLine().equalsIgnoreCase("s");

                    Cidadao cidadao = new Cidadao(nome, cpf, email, anonimo);

                    System.out.println("\nDescreva o problema (ex: poste sem luz, buraco na rua):");
                    String descricao = scanner.nextLine();

                    System.out.print("Prioridade (BAIXA / MEDIA / ALTA): ");
                    String prioridade = scanner.nextLine();

                    int protocolo = (int) (Math.random() * 1000);

                    try {
                        Solicitacao s = new Solicitacao(protocolo, descricao, prioridade, cidadao);
                        servico.novaSolicitacao(s);

                        System.out.println("Solicitação criada com sucesso!");
                        System.out.println("Protocolo: " + protocolo);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.print("Digite o protocolo: ");
                    int p = scanner.nextInt();

                    Solicitacao encontrada = servico.buscarPorProtocolo(p);

                    if (encontrada != null) {
                        encontrada.imprimirRelatorio();
                    } else {
                        System.out.println("Protocolo não encontrado.");
                    }

                    break;

                case 3:
                    servico.listarSolicitacoes();
                    break;

                case 4:
                    System.out.print("Digite o protocolo: ");
                    int protocoloAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao solicitacao = servico.buscarPorProtocolo(protocoloAtualizar);

                    if (solicitacao != null) {

                        System.out.println("Escolha o novo status:");
                        System.out.println("1 - TRIAGEM");
                        System.out.println("2 - EM_EXECUCAO");
                        System.out.println("3 - RESOLVIDO");
                        System.out.println("4 - ENCERRADO");

                        int escolhaStatus = scanner.nextInt();
                        scanner.nextLine();

                        Status novoStatus = null;

                        switch (escolhaStatus) {
                            case 1:
                                novoStatus = Status.TRIAGEM;
                                break;
                            case 2:
                                novoStatus = Status.EM_EXECUCAO;
                                break;
                            case 3:
                                novoStatus = Status.RESOLVIDO;
                                break;
                            case 4:
                                novoStatus = Status.ENCERRADO;
                                break;
                            default:
                                System.out.println("Status inválido!");
                                break;
                        }

                        System.out.print("Digite um comentário: ");
                        String comentario = scanner.nextLine();

                        solicitacao.registrarMovimentacao(novoStatus, comentario, "Atendente");

                        System.out.println("Status atualizado com sucesso!");

                    } else {
                        System.out.println("Protocolo não encontrado.");
                    }

                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}