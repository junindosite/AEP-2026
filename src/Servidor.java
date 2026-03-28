public class Servidor extends Usuario {

    private String cargo;

    public Servidor(String nome, String cpf, String email, Integer idade, String cidade, String telefone, String cargo) {
        super(nome, cpf, email, idade, cidade, telefone);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}