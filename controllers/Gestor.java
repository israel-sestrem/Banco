package Banco.controllers;

import Banco.models.BancoModel;
import Banco.models.ContaModel;

import java.util.ArrayList;
import java.util.List;

public class Gestor {

    private List<BancoModel> bancos;
    private List<ContaModel> contas;

    public Gestor(){
        this.bancos = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public List<BancoModel> getBancos() {
        return bancos;
    }

    public void setBancos(List<BancoModel> bancos) {
        this.bancos = bancos;
    }

    public List<ContaModel> getContas() {
        return contas;
    }

    public void setContas(List<ContaModel> contas) {
        this.contas = contas;
    }

    @Override
    public String toString() {
        return "Gestor{" +
                "bancos=" + bancos +
                ", contas=" + contas +
                '}';
    }
}
