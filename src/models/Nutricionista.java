public class Nutricionista extends Profissional {
    
    private String planoAlimentar;
    
    // ========== CONSTRUTORES ==========
    
    public Nutricionista(String nome, String cpf) {
        super(nome, cpf, "nutricao");
        this.planoAlimentar = "Não informado";
    }
    
    public Nutricionista(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        this.planoAlimentar = "Não informado";
    }
    
    public Nutricionista(String nome, String cpf, String registroProfissional, 
                         double valorConsulta, String planoAlimentar) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        setPlanoAlimentar(planoAlimentar);
    }
