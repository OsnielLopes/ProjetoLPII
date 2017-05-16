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

}