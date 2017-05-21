import java.util.Calendar;
import javax.swing.*;
public class Tela {
    public static OpcEntrada opcLeitura(){
        Object[] opcoes = { "Escrever Times Manualmente", "Importar Times de um arquivo" };
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Escolha uma das opções : "));
        
        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            return OpcEntrada.Console;
        }else{
            return OpcEntrada.Arquivo;
        }
    }
    
    public static int qtdTimes(){
        int quantidade = 0;
        do{
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de times.\nMinimo: 1 | Maximo: 30 "));
        }while(quantidade < 1 || quantidade > 30);
        return quantidade;
    }
    
    public static String[] getInfoLeituraArquivo(){
        Object[] opcoes = { "OK", "Cancelar" };
        
        JPanel panel = new JPanel();
        
        JLabel labelNomeArquivo = new JLabel("Nome do arquivo: ");
        panel.add(labelNomeArquivo);
        JTextField textFieldNomeArquivo= new JTextField(10);
        panel.add(textFieldNomeArquivo);
        
        JLabel labelSeparador = new JLabel("Separador das informações do time: ");
        panel.add(labelSeparador);
        JTextField textFieldSeparador = new JTextField(10);
        panel.add(textFieldSeparador);

        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            String arquivo = textFieldNomeArquivo.getText();
            String separador = textFieldSeparador.getText();
            if (arquivo.isEmpty() || arquivo.equals(" ") ||
                    separador.isEmpty() || separador.equals(" ")){
                return null;
            }
            return new String[] {arquivo,separador};
        }else{
            return null;
        }
    }
    
      // A tela de interação para pedir: nome - cidade - estado para o usuario
    public static String[] digitaTime(){
        Object[] opcoes = { "OK", "Cancelar" };
        
        JPanel panel = new JPanel();
        
        JLabel labelTime = new JLabel("Time: ");
        panel.add(labelTime);
        JTextField textFieldNome = new JTextField(10);
        panel.add(textFieldNome);
        
        JLabel labelCidade = new JLabel("Cidade: ");
        panel.add(labelCidade);
        JTextField textFieldCidade = new JTextField(10);
        panel.add(textFieldCidade);
        
        JLabel labelEstado = new JLabel("Estado: ");
        panel.add(labelEstado);
        JTextField textFieldEstado = new JTextField(10);
        panel.add(textFieldEstado);

        int result = JOptionPane.showOptionDialog(null, panel, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, null);
        if (result == JOptionPane.YES_OPTION){
            String nome = textFieldNome.getText();
            String cidade = textFieldCidade.getText();
            String estado = textFieldEstado.getText();
            if (nome.isEmpty() || nome.equals(" ") ||
                    cidade.isEmpty() || cidade.equals(" ") ||
                    estado.isEmpty() || estado.equals(" ")){
                return null;
            }
            return new String[] {nome,cidade,estado};
        }else{
            return null;
        }
    }

    //Implementar método que exibe uma tela para a pessoa colocar a data inicial dos jogos
    static Calendar inicioDosJogos() {
        Calendar c = Calendar.getInstance();
        c.set(2017, 6, 10);
        return c;
    }

    //Implementar uma tela onde o usuario pode escolher entre 3 botoes: pesquisar, gerar arquivo e sair
    static OpcMenu menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Implementar uma tela onde o usuario pode escolher entre as seguintes pesquisas:
    //jogos de um determinado time; jogos que ocorrerâo em uma determinada cidade;
    //jogos que ocorrerâo em um determinado estado; (d) jogos que ocorrerâo em um determinado mês.
    static Filtro escolheFormaPesquisa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //exibir um ComboBox com os nomes dos times, retornar indice escolhido
    static int escolhaTime(Time[] times) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //permitir que a pessoa digite o nome da cidade
    static String leCidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //permitir que a pessoa digite o nome do estado
    static String leEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //exibir combobox com os meses do ano, retornar o numero do mes
    static int escolheMes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //exibir resultado da pesquisa
    static void exibeResultado(String resposta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}