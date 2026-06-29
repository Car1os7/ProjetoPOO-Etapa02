
/**
 * CLASSE FISIOTERAPEUTA - Especialização de Profissional
 * 
 * Conceitos aplicados:
 * - HERANÇA: extends Profissional (Fisioterapeuta é um Profissional)
 * - HIERARQUIA DE 3 NÍVEIS: Pessoa → Profissional → Fisioterapeuta
 * - SOBRESCRITA: @Override de exibirResumo() e registrarEspecifico()
 * - LIGAÇÃO DINÂMICA: métodos executados baseado no tipo real do objeto
 */
public class Fisioterapeuta extends Profissional {
    
    // ATRIBUTO ESPECÍFICO
    private int totalSessoesPrevistas;
    
    // ========== CONSTRUTORES ==========
    
    public Fisioterapeuta(String nome, String cpf) {
        super(nome, cpf, "fisioterapia");
        this.totalSessoesPrevistas = 10; // valor padrão
    }
    
    public Fisioterapeuta(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta);
        this.totalSessoesPrevistas = 10;
    }
    
    public Fisioterapeuta(String nome, String cpf, String registroProfissional, 
                          double valorConsulta, int totalSessoesPrevistas) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta);
        setTotalSessoesPrevistas(totalSessoesPrevistas);
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public int getTotalSessoesPrevistas() {
        return totalSessoesPrevistas;
    }
    
    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) {
        // VALIDAÇÃO: número de sessões deve ser positivo
        if (totalSessoesPrevistas <= 0) {
            throw new IllegalArgumentException("Total de sessões previstas deve ser maior que zero!");
        }
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }
    
    // ========== SOBRESCRITA DE MÉTODOS ==========
    
    @Override
    public String exibirResumo() {
        // Chama o resumo da superclasse e adiciona informações específicas
        return super.exibirResumo() + 
               " | Sessões previstas: " + totalSessoesPrevistas + 
               " [FISIOTERAPEUTA]";
    }
    
    @Override
    public String registrarEspecifico(Atendimento atendimento) {
        // Registra informações específicas da fisioterapia no atendimento
        String info = "FISIOTERAPIA: Sessões previstas = " + totalSessoesPrevistas;
        atendimento.adicionarInfoEspecialidade(info);
        return "Registro fisioterapêutico: " + totalSessoesPrevistas + " sessões planejadas.";
    }
}