import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    // ========== COLEÇÕES PRINCIPAIS ==========
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Profissional> profissionais = new ArrayList<>();
    private static List<Consulta> consultas = new ArrayList<>();
    private static List<Atendimento> atendimentos = new ArrayList<>();
    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static List<Double> multas = new ArrayList<>();
    private static Set<String> cpfsCadastrados = new HashSet<>();
    private static Map<String, Paciente> mapaPacientes = new HashMap<>();
    private static Map<String, Profissional> mapaProfissionais = new HashMap<>();

    public static void main(String[] args) {
        int opcao = -1;
        inicializarDadosTeste();
        
        while (opcao != 0) {
            try {
                System.out.println("\n=== CLINICA VIDAPLENA ===");
                System.out.println("1 - Pacientes");
                System.out.println("2 - Profissionais");
                System.out.println("3 - Consultas");
                System.out.println("4 - Atendimentos");
                System.out.println("5 - Pagamentos");
                System.out.println("6 - Relatorios");
                System.out.println("7 - Exportacao de Dados");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = lerInteiro();
                
                switch (opcao) {
                    case 1: menuPacientes(); break;
                    case 2: menuProfissionais(); break;
                    case 3: menuConsultas(); break;
                    case 4: menuAtendimentos(); break;
                    case 5: menuPagamentos(); break;
                    case 6: menuRelatorios(); break;
                    case 7: menuExportacao(); break;
                    case 0: System.out.println("Sistema encerrado."); break;
                    default: System.out.println("Opcao invalida!"); break;
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    // ========== METODOS AUXILIARES ==========
    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.print("Digite um numero valido: ");
            }
        }
    }
    
    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.err.print("Digite um numero valido: ");
            }
        }
    }
    
    private static String lerString() {
        return sc.nextLine().trim();
    }
    
    private static String lerStringNaoVazia() {
        while (true) {
            String s = lerString();
            if (!s.isEmpty()) return s;
            System.out.print("Campo obrigatorio: ");
        }
    }