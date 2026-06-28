// ARQUIVO: src/models/Paciente.java
package models;

/**
 * CLASSE PACIENTE - Especializacao de Pessoa
 * 
 * Conceitos aplicados:
 * - HERANCA: extends Pessoa (Paciente e uma Pessoa)
 * - ENCAPSULAMENTO: atributos privados
 * - SOBRECARGA: construtores sobrecarregados
 */
public class Paciente extends Pessoa {
    
    // ATRIBUTOS ESPECIFICOS DE PACIENTE
    private int idade;
    private String convenioNome;
    private boolean ativo;
    private Convenio convenio; // ASSOCIACAO: paciente pode ter um convenio
    
    //  CONSTRUTORES SOBRECARREGADOS 
    // SOBRECARGA: diferentes formas de criar um paciente
    
    // Cadastro minimo
    public Paciente(String nome, String cpf) {
        super(nome, cpf); // CHAMADA AO CONSTRUTOR DA SUPERCLASSE
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    // Com idade e telefone
    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone); // CHAMADA AO CONSTRUTOR DA SUPERCLASSE
        this.idade = idade;
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    // Cadastro completo com nome do convenio
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone); // CHAMADA AO CONSTRUTOR DA SUPERCLASSE
        this.idade = idade;
        this.convenioNome = convenioNome;
        this.ativo = true;
        this.convenio = null;
    }
    
    // Cadastro completo com objeto Convenio
    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone); // CHAMADA AO CONSTRUTOR DA SUPERCLASSE
        this.idade = idade;
        this.convenio = convenio;
        this.convenioNome = (convenio != null) ? convenio.getNome() : "";
        this.ativo = true;
    }
}