package Banco.models;

import java.time.LocalDate;

public class ContaModel {

    private BancoModel banco;
    private String nomeDono;
    private String usuario;
    private String senha;
    private Double saldo;
    private final LocalDate dataAbertura;

    public ContaModel(BancoModel banco, String nomeDono, String usuario, String senha, Double saldo) {
        this.banco = banco;
        this.nomeDono = nomeDono;
        this.usuario = usuario;
        this.senha = senha;
        this.saldo = saldo;
        this.dataAbertura = LocalDate.now();
    }

    public ContaModel(Double saldo){
        this.saldo = saldo;
        this.dataAbertura = LocalDate.now();
    }

    public BancoModel getBanco() {
        return banco;
    }

    public void setBanco(BancoModel banco) {
        this.banco = banco;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public String relatorio(){
        return "Conta{" +
                "nomeDono='" + nomeDono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                ", dataAbertura=" + dataAbertura +
                '}';
    }

    @Override
    public String toString() {
        return "Conta{" +
                "banco=" + banco +
                ", nomeDono='" + nomeDono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                ", dataAbertura=" + dataAbertura +
                '}';
    }
}
