import java.util.*;
public class Partida {
    private Date data;
    private Time timeA;
    private Time timeB;
    
    public Partida(Time timeA, Time timeB, Date data){
        this.timeA = timeA;
        this.timeB = timeB;
        this.data = data;

    }
    
    public Date getData(){
        return this.data;
    }
    
//    public String getLocal(){
//        return timeA.getLocal();
//    }
    
    public Time[] getAdversários(){
        Time adversarios[] = {timeA, timeB};
        return adversarios;
    }
}
