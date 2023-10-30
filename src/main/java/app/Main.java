package app;

import app.model.Pessoa;
import app.service.PessoaService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Renan");
        pessoa.setSenha("123");

        PessoaService pessoaService = new PessoaService();

        pessoaService.create(pessoa);

        pessoaService.find(pessoa);

        pessoa.setNome("Renan Rodrigues");
        pessoa.setSenha("1234");
        pessoaService.update(pessoa);

        pessoaService.delete(pessoa);

        List<Pessoa> pessoas = pessoaService.findAll();
        // for in pessoas
        for (Pessoa p : pessoas) {
            System.out.println(p.getNome());
        }

        pessoaService.close();
    }
}
