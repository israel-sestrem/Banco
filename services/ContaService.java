package Banco.services;

import Banco.controllers.Gestor;
import Banco.models.BancoModel;
import Banco.models.ContaModel;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContaService {

    static Scanner leitor = new Scanner(System.in);

    public static ContaModel acessarConta(Gestor gestor){
        System.out.print("Informe seu usu�rio: ");
        String usuario = leitor.next();

        List<ContaModel> conta = gestor.getContas()
                .stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList());

        if(conta.isEmpty()){
            System.out.println("N�o existe nenhuma conta com o usu�rio informado");
            return null;
        }

        System.out.print("Informe sua senha: ");
        String senha = leitor.next();

        if(!conta.get(0).getSenha().equals(senha)){
            System.out.println("A senha informada est� incorreta. Tente novamente");
            return null;
        }

        return conta.get(0);
    }

    public static void sacar(ContaModel contaAcessada){
        if(contaAcessada.getSaldo() == 0){
            System.out.println("Voc� n�o possui saldo.");
            return;
        }

        System.out.print("Informe a quantidade que deseja sacar: ");
        Double valor = leitor.nextDouble();

        if(contaAcessada.getSaldo() < valor){
            System.out.println("Saldo indispon�vel");
            return;
        }

        contaAcessada.setSaldo(contaAcessada.getSaldo() - valor);
        System.out.println("Saldo efetuado com sucesso");
    }

    public static void depositar(ContaModel contaAcessada){
        System.out.print("Informe o valor que deseja depositar: ");
        Double valor = leitor.nextDouble();

        contaAcessada.setSaldo(contaAcessada.getSaldo() + valor);
        System.out.println("Valor depositado com sucesso");
    }

    public static void transferir(Gestor gestor, ContaModel contaAcessada){
        System.out.print("Informe o usu�rio da conta que deseja mandar dinheiro: ");
        String usuario = leitor.next();

        List<ContaModel> conta = gestor.getContas()
                .stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList());

        if(conta.isEmpty()){
            System.out.println("N�o existe uma conta com o usu�rio informado");
            return;
        }

        System.out.print("Informe o valor que deseja transferir: ");
        Double valor = leitor.nextDouble();

        if(contaAcessada.getSaldo() < valor){
            System.out.println("Saldo indispon�vel");
            return;
        }

        conta.get(0).setSaldo(conta.get(0).getSaldo() + valor);
        contaAcessada.setSaldo(contaAcessada.getSaldo() - valor);
        System.out.println("Transfer�ncia efetuada com sucesso");
    }

    public static void saldo(ContaModel contaAcessada){
        System.out.println("Saldo: R$" + contaAcessada.getSaldo());
    }

    public static void listarContas(Gestor gestor){
        if(gestor.getContas().size() == 0){
            System.out.println("N�o h� contas cadastradas");
            return;
        }
        gestor.getContas().forEach(System.out::println);
    }

    public static void cadastrarConta(Gestor gestor){
        if(gestor.getBancos().size() == 0){
            System.out.println("N�o existem bancos cadastrados para voc� criar sua conta");
            return;
        }
        System.out.println("Bancos cadastrados: ");
        gestor.getBancos().forEach(System.out::println);

        System.out.print("Informe o nome do banco desejado: ");
        String nomeBanco = leitor.next();

        if(!gestor.getBancos().stream().anyMatch(banco -> banco.getNome().equals(nomeBanco))){
            System.out.println("Este banco n�o existe. Tente novamente");
            return;
        }

        BancoModel banco = gestor.getBancos()
                .stream()
                .filter(b -> b.getNome().equals(nomeBanco))
                .collect(Collectors.toList())
                .get(0);

        if(banco.getPrecoAbertura() > 0){
            System.out.println("Este banco possui um pre�o de abertura: R$" + banco.getPrecoAbertura() + ". Ent�o voc� j� precisa ter uma conta cadastrada com esta quantia para abrir a conta no mesmo. Por agora, escolha um banco em que o pre�o de abertura � zero.");
            return;
        }

        System.out.print("Informe seu nome: ");
        String nome = leitor.next();

        System.out.print("Informe seu usu�rio: ");
        String usuario = leitor.next();

        if(gestor.getContas().stream().anyMatch(conta -> conta.getUsuario().equals(usuario))){
            System.out.println("J� existe uma conta com este usu�rio. Tente outro");
            return;
        }

        System.out.print("Informe sua senha: ");
        String senha = leitor.next();

        ContaModel conta = new ContaModel(banco, nome, usuario, senha, 0d);
        List<ContaModel> contas = gestor.getContas();
        contas.add(conta);
        gestor.setContas(contas);
        System.out.println("Conta cadastrada com sucesso");
    }

    public static void removerConta(Gestor gestor){
        System.out.print("Informe o usu�rio da conta que deseja remover: ");
        String usuario = leitor.next();

        if(gestor.getContas().stream().noneMatch(conta -> conta.getUsuario().equals(usuario))){
            System.out.println("N�o existe nenhuma conta com o usu�rio informado. Tente novamente");
            return;
        }

        ContaModel conta = gestor.getContas()
                .stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList())
                .get(0);

        List<ContaModel> contas = gestor.getContas();
        contas.remove(conta);
        gestor.setContas(contas);
        System.out.println("Conta removida com sucesso");
    }

    public static void atualizarConta(Gestor gestor){
        System.out.print("Informe o usu�rio da conta que deseja atualizar: ");
        String usuario = leitor.next();

        if(gestor.getContas().stream().noneMatch(conta -> conta.getUsuario().equals(usuario))){
            System.out.println("N�o existe nenhuma conta com o usu�rio informado. Tente novamente");
            return;
        }

        ContaModel contaAntiga = gestor.getContas()
                .stream()
                .filter(c -> c.getUsuario().equals(usuario))
                .collect(Collectors.toList())
                .get(0);

        ContaModel conta = new ContaModel(contaAntiga.getBanco(), contaAntiga.getNomeDono(), contaAntiga.getUsuario(), contaAntiga.getSenha(), contaAntiga.getSaldo());

        System.out.println("Bancos dispon�veis: ");
        gestor.getBancos().forEach(System.out::println);

        System.out.print("Informe o nome do novo banco da sua conta (digite '0' caso n�o queira alterar): ");
        String nomeBanco = leitor.next();

        if(!nomeBanco.equals("0")) {
            if (gestor.getBancos().stream().noneMatch(banco -> banco.getNome().equals(nomeBanco))) {
                System.out.println("N�o existe nenhum banco com este nome");
                return;
            }

            BancoModel bancoNovo = gestor.getBancos()
                    .stream()
                    .filter(banco -> banco.getNome().equals(nomeBanco))
                    .collect(Collectors.toList())
                    .get(0);

            if (conta.getSaldo() < bancoNovo.getPrecoAbertura()) {
                System.out.println("Saldo indispon�vel para abrir conta neste banco");
                return;
            }

            conta.setBanco(bancoNovo);
            conta.setSaldo(conta.getSaldo() - bancoNovo.getPrecoAbertura());
        }

        System.out.print("Informe o novo nome (digite '0' caso n�o queira alterar): ");
        String novoNomeDono = leitor.next();

        if(!novoNomeDono.equals("0")){
            conta.setNomeDono(novoNomeDono);
        }

        System.out.print("Informe o novo usu�rio (digite '0' caso n�o queira alterar): ");
        String novoUsuario = leitor.next();

        if(!novoUsuario.equals("0")) {
            if (gestor.getContas().stream().anyMatch(c -> c.getUsuario().equals(novoUsuario))) {
                System.out.println("J� existe um usu�rio com este nome");
                return;
            }

            conta.setUsuario(novoUsuario);
        }

        System.out.print("Informe a nova senha (digite '0' caso n�o queira alterar): ");
        String novaSenha = leitor.next();

        if(!novaSenha.equals("0")){
            conta.setSenha(novaSenha);
        }

        List<ContaModel> contas = gestor.getContas();
        contas.remove(contaAntiga);
        contas.add(conta);
        gestor.setContas(contas);
        System.out.println("Conta atualizada com sucesso");
    }

}
