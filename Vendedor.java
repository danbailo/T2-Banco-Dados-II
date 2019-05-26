package T2_BDII;

public class Vendedor {
    private String idvendedor;
    private String nome;
    private String sexo;
    private String email;
    private String cpf;
    private Double janeiro;
    private Double fevereiro;
    private Double marco;
    
    public Vendedor(){
        
    }

//    public Vendedor(String nome, String sexo, String email, String cpf,
//            String janeiro, String fevereiro, String marco){
//                this(null, nome, sexo, email, cpf, janeiro, fevereiro, marco);
//    }    
    
    public Vendedor(String idvendedor, String nome, String sexo, String email, String cpf,
            Double janeiro, Double fevereiro, Double marco){
        this.idvendedor = idvendedor;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.cpf = cpf;
        this.janeiro = janeiro;
        this.fevereiro = fevereiro;
        this.marco = marco;
    }

    public String getId() {
        return idvendedor;
    }

    public void setId(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getJaneiro() {
        return janeiro;
    }

    public void setJaneiro(Double janeiro) {
        this.janeiro = janeiro;
    }

    public Double getFevereiro() {
        return fevereiro;
    }

    public void setFevereiro(Double fevereiro) {
        this.fevereiro = fevereiro;
    }

    public Double getMarco() {
        return marco;
    }

    public void setMarco(Double marco) {
        this.marco = marco;
    }
}
