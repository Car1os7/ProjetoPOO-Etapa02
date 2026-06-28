public class ClinicoGeral extends Profissional {
    
    private String encaminhamento;
    
    // ========== CONSTRUTORES ==========
    
    public ClinicoGeral(String nome, String cpf) {
        super(nome, cpf, "clinica geral");
        this.encaminhamento = "Nenhum";
    }
    
    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = "Nenhum";
    }
    
    public ClinicoGeral(String nome, String cpf, String registroProfissional, 
                        double valorConsulta, String encaminhamento) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        setEncaminhamento(encaminhamento);
    }
