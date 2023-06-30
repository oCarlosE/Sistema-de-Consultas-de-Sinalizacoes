import java.time.LocalDateTime;

public class Sinalizacao {
    private final String descricao;
    private final LocalDateTime implantacao;
    private final double nro_inicial;
    private final double nro_final;
    private final String lado;
    private final String local_instalacao;

    public Sinalizacao(String descricao, LocalDateTime implantacao, double nro_inicial, double nro_final, String lado, String local_instalacao) {
        this.descricao = descricao;
        this.implantacao = implantacao;
        this.nro_inicial = nro_inicial;
        this.nro_final = nro_final;
        this.lado = lado;
        this.local_instalacao = local_instalacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getImplantacao() {
        return implantacao;
    }

    public double getNroInicial() {
        return nro_inicial;
    }

    public double getNroFinal() {
        return nro_final;
    }

    public String getLado() {
        return lado;
    }

    public String getLocalInstalacao() {
        return local_instalacao;
    }

    @Override
    public String toString() {
        return "Sinalizacao [descricao=" + descricao + ", implantacao=" + implantacao.toLocalDate() +
                ", nro_inicial=" + nro_inicial + ", nro_final=" + nro_final +
                ", lado=" + lado + ", local_instalacao=" + local_instalacao + "]";
    }
}

