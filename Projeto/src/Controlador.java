import java.io.*;

public class Controlador {
    public static void main(String[] args){
        //Exibir Tela - Opção Arquivo/Texto
        OpcEntrada opc = Tela.opcLeitura();
        if (opc.equals(OpcEntrada.Console)){
            //Fazer um for para a pessoa entrar com os times
        }else if (opc.equals(OpcEntrada.Tela)){
            //Exibir Tela para a pessoa escrever o nome do arquivo
            //Exibir tela para a pessoa colocar o separador
            leitura(filename, separador);
        }
        
        
        
    }
    //Instanciando times a partir de um arquivo
    private void leitura(String filename, String separador) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader in = new BufferedReader(fr);
        int qtdTimes = Integer.parseInt(in.readLine());
        Time times[] = new Time[qtdTimes];
        for (int i = 0; i < qtdTimes; i++){
            String linha[] = in.readLine().split(separador);
            times[i] = new Time(linha[0], linha[1], linha[2]);
        }
        fr.close();
    }
}
