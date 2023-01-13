package Banco.models;

public class BancoModel {

    private String nome;
    private String cnpj;
    private Double precoAbertura;

    public BancoModel(String nome, String cnpj, Double precoAbertura){
        this.nome = nome;
        this.cnpj = cnpj;
        this.precoAbertura = precoAbertura;
    }

    public BancoModel(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getPrecoAbertura() {
        return precoAbertura;
    }

    public void setPrecoAbertura(Double precoAbertura) {
        this.precoAbertura = precoAbertura;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", precoAbertura=" + precoAbertura +
                '}';
    }
}
