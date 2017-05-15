import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import org.json.*;
public class Tela {
    public static OpcEntrada opcLeitura(){
        Object[] opcoes1 = { "Escrever Times Manualmente", "Importar Times de um arquivo" };
        
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Escolha uma das opções : "));
        
        int result = JOptionPane.showOptionDialog(null, panel1, "Tabela de Jogos", 
                     JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes1, null);
        if (result == JOptionPane.YES_OPTION){
            return OpcEntrada.Console;
        }else{
            return OpcEntrada.Arquivo;
        }
    }
    
    public static int qtdTimes(){
        int quantidade = 0;
        do{
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de times.\nMinimo: 10 | Maximo: 30 "));
        }while(quantidade < 10 || quantidade > 30);
        return quantidade;
    }
    
    // A tela de interação para pedir: nome - cidade - estado para o usuario
    public static String[] digitaTime() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("Estados.json");
        JSONTokener jsonTokener = new JSONTokener(fr);
        JSONArray a = new JSONArray(jsonTokener);
        String[] opcoes = new String[27];
        
        for (int i = 0; i < a.length(); i++) {
            JSONObject jsonobject = a.getJSONObject(i);
            opcoes[i] = jsonobject.getString("Sigla");
        }
        fr.close();
        
        fr = new FileReader("Cidades.json");
        jsonTokener = new JSONTokener(fr);
        JSONArray cidades = new JSONArray(jsonTokener);
        ArrayList<String> c = new ArrayList<String>();
        
        JFrame janela = new JFrame("Tabela de Jogos");
        janela.setLocation(500, 300);
        LayoutManager layout = new GridLayout(5, 3);
        layout.preferredLayoutSize(janela);
        janela.setLayout(layout);
        
        Container container = janela.getContentPane();

        container.add(new JLabel("Nome : "));
        JTextField textFieldNome = new JTextField(10);
        container.add(textFieldNome);
        
        JComboBox cursosBox = new JComboBox(opcoes);
        container.add(cursosBox);

        JComboBox discBox = new JComboBox();
        container.add(discBox);

        JButton botao = new JButton("Enviar");
        container.add(botao);

        janela.pack();
        discBox.setVisible(false);

        cursosBox.addActionListener((ActionEvent e) -> {
            int indexEstado = cursosBox.getSelectedIndex();
            if(!(c.isEmpty())){
                    c.clear();
                }
            for (int i = 0; i < cidades.length(); i++) {
                JSONObject jsonobject = cidades.getJSONObject(i);
                if (jsonobject.getString("Estado").equals(Integer.toString(indexEstado+1))){
                    c.add(jsonobject.getString("Nome"));
                }
            }
            String[] stringModel = c.toArray(new String[c.size()]);
            DefaultComboBoxModel model = new DefaultComboBoxModel(stringModel);
            discBox.setModel(model);
            discBox.setVisible(true);
            janela.pack();
            
        });
        
        botao.addActionListener((ActionEvent e) -> {
            janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
        });

        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // CIDADE
//        panel.add(new JLabel("Cidade : "));
//        String[] options = { "SP","RJ","ES","RS","SC","RO","AC" };
//        JComboBox comboBox = new JComboBox(options);
//        panel.add(comboBox);
//        comboBox.addActionListener((ActionEvent e)-> {
//            System.out.println("oi");
//            comboBox.setVisible(true);
//            panel.revalidate();
//            panel.repaint();
//        });
////        JTextField textFieldCidade = new JTextField(10);
////        panel2.add(textFieldCidade);
//        // ESTADO
//        panel.add(new JLabel("Estado : "));
//        JTextField textFieldEstado = new JTextField(10);
//        panel.add(textFieldEstado);
//        textFieldEstado.setVisible(false);
//        
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        // ARRUMAR O RETORNO DESSE PONTO !!!
//        panel.setVisible(true);
        int result = 0;
        if (result == JOptionPane.YES_OPTION){
            return null;
        }else{
            return null;
        }
    
}
}
