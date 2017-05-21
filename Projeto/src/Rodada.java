
import java.util.Calendar;

/**
 *
 * @author osniellopesteixeira
 */
public class Rodada {
    
    private Calendar data;
    private Partida[] partidas;
    private int proxIndice;
    
    public Rodada(int qtdPartidas){
        this.partidas = new Partida[qtdPartidas];
        this.proxIndice = 0;
    }
    
    public void add(Partida p){
        this.partidas[proxIndice] = p;
        this.proxIndice++;
    }
    
    public void setData(Calendar c){
        this.data = c;
    }
    
    public Calendar getData(){
        return this.data;
    }
    
    public String getDataFormatada(){
        return Integer.toString(this.data.get(Calendar.DAY_OF_MONTH))+
                "/"+Integer.toString(this.data.get(Calendar.MONTH))+
                "/"+Integer.toString(this.data.get(Calendar.YEAR));
    }
    
    public Partida getPartida(int i){
        return this.partidas[i];
    }
    
    public int getQtdPartidas(){
        return this.partidas.length;
    }
}