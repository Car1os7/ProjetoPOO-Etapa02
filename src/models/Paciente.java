// ARQUIVO: src/models/Paciente.java
package models;

/**
 * CLASSE PACIENTE - Especializacao de Pessoa
 * 
 * Conceitos aplicados:
 * - HERANCA: extends Pessoa (Paciente e uma Pessoa)
 * - ENCAPSULAMENTO: atributos privados com validacao
 * - SOBRECARGA: construtores sobrecarregados
 * - ASSOCIACAO: Paciente conhece Convenio
 */
public class Paciente extends Pessoa {
    
    // ATRIBUTOS ESPECIFICOS DE PACIENTE
    private int idade;
    private String convenioNome;
    private boolean ativo;
    private Convenio convenio; // ASSOCIACAO: paciente pode ter um convenio
    
    // CONSTRUTORES SOBRECARREGADOS 
    // SOBRECARGA: diferentes formas de criar um paciente
    
    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone);
        setIdade(idade); // USANDO SETTER COM VALIDACAO
        this.convenioNome = "";
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, telefone);
        setIdade(idade); // USANDO SETTER COM VALIDACAO
        this.convenioNome = convenioNome;
        this.ativo = true;
        this.convenio = null;
    }
    
    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone);
        setIdade(idade); // USANDO SETTER COM VALIDACAO
        this.convenio = convenio;
        this.convenioNome = (convenio != null) ? convenio.getNome() : "";
        this.ativo = true;
    }
    
    //  GETTERS E SETTERS 
    // ENCAPSULAMENTO: acesso controlado aos atributos
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        // VALIDACAO: idade nao pode ser negativa
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa!");
        }
        if (idade > 150) {
            throw new IllegalArgumentException("Idade invalida!");
        }
        this.idade = idade;
    }
    
    public String getConvenioNome() {
        return convenioNome;
    }
    
    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public Convenio getConvenio() {
        return convenio;
    }
    
    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
        this.convenioNome = (convenio != null) ? convenio.getNome() : "";
    }
}