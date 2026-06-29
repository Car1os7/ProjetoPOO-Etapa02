// ARQUIVO: src/util/Relatorio.java



import java.util.List;


/**
 * CLASSE RELATORIO
 * 
 * Conceitos aplicados:
 * - POLIMORFISMO: métodos que trabalham com coleções de tipos genéricos
 * - SOBRECARGA: métodos com mesmo nome e parâmetros diferentes
 * - LIGAÇÃO DINÂMICA: exibirResumo() executado conforme tipo real
 */
public class Relatorio {
    
    // SOBRECARGA: relatório geral sem parâmetros
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos) {
        System.out.println("\n=== RELATÓRIO GERAL ===");
        System.out.println("Total de consultas: " + consultas.size());
        System.out.println("Total de atendimentos: " + atendimentos.size());
        System.out.println("----------------------------------------");
        
        for (Consulta c : consultas) {
            System.out.println(c.exibirResumo());
        }
    }
    
    // SOBRECARGA: relatório por profissional
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos, String nomeProfissional) {
        System.out.println("\n=== RELATÓRIO POR PROFISSIONAL: " + nomeProfissional + " ===");
        int count = 0;
        
        for (Consulta c : consultas) {
            if (c.getNomeProfissional().equalsIgnoreCase(nomeProfissional)) {
                System.out.println(c.exibirResumo());
                count++;
            }
        }
        
        System.out.println("Total de consultas: " + count);
        System.out.println("========================================");
    }
    
    // SOBRECARGA: relatório por período
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos, 
                                      String dataInicio, String dataFim) {
        System.out.println("\n=== RELATÓRIO POR PERÍODO: " + dataInicio + " a " + dataFim + " ===");
        int count = 0;
        
        // Comparação simplificada de datas (apenas string)
        for (Consulta c : consultas) {
            String data = c.getData();
            if (data.compareTo(dataInicio) >= 0 && data.compareTo(dataFim) <= 0) {
                System.out.println(c.exibirResumo());
                count++;
            }
        }
        
        System.out.println("Total de consultas no período: " + count);
        System.out.println("========================================");
    }
    
    // Relatório financeiro
    public static void gerarResumoFinanceiro(List<Consulta> consultas, List<Pagamento> pagamentos, 
                                            List<Double> multas) {
        System.out.println("\n=== RESUMO FINANCEIRO ===");
        
        double totalPagamentos = 0;
        for (Pagamento p : pagamentos) {
            // LIGAÇÃO DINÂMICA: cada tipo calcula de forma diferente
            totalPagamentos += p.calcularValorFinal();
        }
        
        double totalMultas = 0;
        for (Double m : multas) {
            totalMultas += m;
        }
        
        System.out.println("Total de pagamentos: " + pagamentos.size());
        System.out.println("Valor total de pagamentos: R$" + String.format("%.2f", totalPagamentos));
        System.out.println("Total de multas aplicadas: " + multas.size());
        System.out.println("Valor total de multas: R$" + String.format("%.2f", totalMultas));
        System.out.println("Saldo final (pagamentos - multas): R$" + 
                          String.format("%.2f", totalPagamentos - totalMultas));
        System.out.println("========================================");
    }
}
