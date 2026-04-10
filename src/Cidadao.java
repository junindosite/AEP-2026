public class Cidadao extends Usuario {
    private boolean anonimo;

    public Cidadao(String nome, String cpf, String email, boolean anonimo) {
        super(nome, cpf, email);
        this.anonimo = anonimo;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public String getIdentidadeParaExibicao() {
        if (this.anonimo) {
            return "USUÁRIO ANÔNIMO)";
        }
        return getNome();
    }
}