public class Cidadao extends Usuario {

    private boolean anonimo;

    public Cidadao(String nome, String cpf, String email, Integer idade, String cidade, String telefone, boolean anonimo) {
        super(nome, cpf, email, idade, cidade, telefone);
        this.anonimo = anonimo;
    }

    public boolean isAnonimo() {
        return anonimo;
    }
}