public class Time {
    private String nome;
    private Local casa;
    
    public Time(String nome, String cidade, String estado){
        this.nome = nome;
        this.casa = new Local(cidade,estado);
    }
    public String getNome(){
       return this.nome;
    }
    //Arrumar depois ...
    public static String getNome(String cidade){
        return null;
    }
    public String getCidade(){
        return this.casa.getCidade();
    }
    public String getEstado(){
        return this.casa.getEstado();
    }
    
    public Local getCasa(){
        return this.casa;
    }
    
}
