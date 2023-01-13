package Banco.services;

import Banco.controllers.Gestor;
import Banco.models.BancoModel;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BancoService {

    static Scanner leitor = new Scanner(System.in);

    public static void listarBancos(Gestor gestor){
        if(gestor.getBancos().size() == 0){
            System.out.println("Não há bancos cadastrados");
            return;
        }
        gestor.getBancos().forEach(System.out::println);
    }

    public static void cadastrarBanco(Gestor gestor){
        BancoModel banco = new BancoModel();

        System.out.print("Informe o nome do banco: ");
        String nome = leitor.next();
        if(gestor.getBancos()
                .stream()
                .anyMatch(b -> b.getNome().equals(nome))){
            System.out.println("Já existe um banco com este nome");
            return;
        }
        banco.setNome(nome);

        System.out.print("Informe o cnpj do banco: ");
        banco.setCnpj(leitor.next());

        System.out.print("Informe o preço para abertura de uma conta neste banco: ");
        banco.setPrecoAbertura(leitor.nextDouble());

        List<BancoModel> bancos = gestor.getBancos();
        bancos.add(banco);
        gestor.setBancos(bancos);
        System.out.println("Banco cadastrado com sucesso");
    }

    public static void removerBanco(Gestor gestor){
        System.out.print("Informe o nome do banco que deseja deletar: ");
        String nome = leitor.next();

        List<BancoModel> banco = gestor.getBancos()
                .stream()
                .filter(b -> b.getNome().equals(nome))
                .collect(Collectors.toList());

        if(banco.isEmpty()) {
            System.out.println("Não existe nenhum banco com este nome");
            return;
        }

        List<BancoModel> bancos = gestor.getBancos();
        bancos.remove(banco.get(0));
        gestor.setBancos(bancos);
        System.out.println("Banco removido com sucesso");
    }

    public static void atualizarBanco(Gestor gestor){
        System.out.print("Informe o nome do banco que deseja alterar: ");
        String nome = leitor.next();

        if(!gestor.getBancos()
                .stream()
                .anyMatch(b -> b.getNome().equals(nome))){
            System.out.println("Não existe um banco com este nome");
            return;
        }

        BancoModel bancoAntigo = gestor.getBancos()
                .stream()
                .filter(b -> b.getNome().equals(nome))
                .collect(Collectors.toList())
                .get(0);

        BancoModel banco = new BancoModel(bancoAntigo.getNome(), bancoAntigo.getCnpj(), bancoAntigo.getPrecoAbertura());

        System.out.print("Informe o novo nome do banco (informe 0 se não quiser alterar) : ");
        String novoNome = leitor.next();
        if(gestor.getBancos()
                .stream()
                .anyMatch(b -> b.getNome().equals(novoNome))){
            System.out.println("Já existe um banco com este nome");
            return;
        }
        if(!novoNome.equals("0"))
            banco.setNome(novoNome);

        System.out.print("Informe o novo cnpj do banco (informe 0 se não quiser alterar) : ");
        String novoCnpj = leitor.next();
        if(!novoCnpj.equals("0"))
            banco.setCnpj(novoCnpj);

        System.out.print("Informe o novo preço para abertura de uma conta neste banco (informe -1 se não quiser alterar) : ");
        double novoPrecoAbertura = leitor.nextDouble();
        if(novoPrecoAbertura != -1)
            banco.setPrecoAbertura(novoPrecoAbertura);

        List<BancoModel> bancos = gestor.getBancos();
        bancos.remove(bancoAntigo);
        bancos.add(banco);
        gestor.setBancos(bancos);
        System.out.println("Banco atualizado com sucesso");
    }

}
