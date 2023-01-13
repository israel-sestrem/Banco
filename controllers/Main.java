package Banco.controllers;

import Banco.models.BancoModel;
import Banco.models.ContaModel;
import Banco.services.BancoService;
import Banco.services.ContaService;

import java.util.Scanner;

public class Main {
    static Gestor gestor = new Gestor();
    static ContaModel contaAcessada;
    static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        String op;

        do{
            System.out.println("1 - Banco");
            System.out.println("2 - Conta");
            System.out.println("3 - Relatório");
            System.out.println("4 - Sair");
            System.out.print("Escolha a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    banco();
                    break;
                case "2":
                    conta();
                    break;
                case "3":
                    relatorio();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        } while(!op.equals("4"));
    }

    public static void banco(){
        String op;

        do{
            System.out.println("1 - Listar");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Remover");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    BancoService.listarBancos(gestor);
                    break;
                case "2":
                    BancoService.cadastrarBanco(gestor);
                    break;
                case "3":
                    BancoService.removerBanco(gestor);
                    break;
                case "4":
                    BancoService.atualizarBanco(gestor);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        }while(!op.equals("5"));
    }

    public static void conta(){
        String op;

        do{
            System.out.println("1 - Acessar conta");
            System.out.println("2 - Operações");
            System.out.println("3 - Voltar");
            op = leitor.next();

            switch (op){
                case "1":
                    ContaModel conta = ContaService.acessarConta(gestor);
                    if(conta != null){
                        contaAcessada = conta;
                        contaAcessada();
                    }
                    break;
                case "2":
                    operacoesConta();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        }while(!op.equals("3"));
    }

    public static void contaAcessada(){
        String op;

        do{
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Saldo");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    ContaService.sacar(contaAcessada);
                    break;
                case "2":
                    ContaService.depositar(contaAcessada);
                    break;
                case "3":
                    ContaService.transferir(gestor, contaAcessada);
                    break;
                case "4":
                    ContaService.saldo(contaAcessada);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        }while(!op.equals("5"));
    }

    public static void operacoesConta(){
        String op;

        do{
            System.out.println("1 - Listar");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Remover");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    ContaService.listarContas(gestor);
                    break;
                case "2":
                    ContaService.cadastrarConta(gestor);
                    break;
                case "3":
                    ContaService.removerConta(gestor);
                    break;
                case "4":
                    ContaService.atualizarConta(gestor);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        }while(!op.equals("5"));
    }

    public static void relatorio(){
        if(gestor.getBancos().size() == 0){
            System.out.println("Não há bancos nem contas cadastradas.");
            return;
        }

        long bancosCount;

        for(BancoModel banco : gestor.getBancos()){
            bancosCount = gestor.getContas()
                    .stream()
                    .filter(c -> c.getBanco().equals(banco))
                    .count();

            System.out.println(banco + " : " + bancosCount + " contas");
            if(bancosCount > 0) {
                System.out.println("Contas : ");
                gestor.getContas()
                        .stream()
                        .filter(c -> c.getBanco().equals(banco))
                        .forEach(c -> System.out.println(c.relatorio()));
            }
        }
    }

}
