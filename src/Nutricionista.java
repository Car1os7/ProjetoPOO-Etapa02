

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
 // ========== GETTERS E SETTERS ==========
    
    public String getPlanoAlimentar() {
        return planoAlimentar;
    }
    
    public void setPlanoAlimentar(String planoAlimentar) {
        if (planoAlimentar == null || planoAlimentar.trim().isEmpty()) {
            throw new IllegalArgumentException("Plano alimentar não pode ser vazio!");
        }
        this.planoAlimentar = planoAlimentar;
    }
    
    // ========== SOBRESCRITA ==========
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + 
               " | Plano Alimentar: " + planoAlimentar + 
               " [NUTRICIONISTA]";
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento) {
        String info = "NUTRIÇÃO: Plano = " + planoAlimentar;
        atendimento.adicionarInfoEspecialidade(info);
        return "Registro nutricional com plano: " + planoAlimentar;
    }
}

