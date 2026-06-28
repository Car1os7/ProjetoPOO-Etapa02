// ARQUIVO: src/models/Pessoa.java
package models;

/**
 * CLASSE ABSTRATA: Pessoa
 * Conceitos aplicados:
 * - ABSTRACAO: classe que nao pode ser instanciada, serve como base para subclasses
 * - ENCAPSULAMENTO: atributos privados com getters/setters
 * - METODO ABSTRATO: exibirResumo - obriga subclasses a implementarem
 * - HERANCA: sera estendida por Paciente e Profissional
 */
public abstract class Pessoa {
    
    // ATRIBUTOS PRIVADOS (ENCAPSULAMENTO)
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;
    
    //CONSTRUTORES (SOBRECARGA) 
    // SOBRECARGA: mesmo nome, parametros diferentes - resolvido em tempo de compilacao
    
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = "";
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = "";
    }
    
    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
}