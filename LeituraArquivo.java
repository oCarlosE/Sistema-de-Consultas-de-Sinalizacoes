import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LeituraArquivo {
    private final ListadeRuas ltRuas = new ListadeRuas();

    public void leArquivo() {
        String[] linhas = new String[110000];
        int numLinhas = 0;

        Path filePath = Paths.get("dataEditado.csv");

        // Ler o arquivo
        try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String line;
            reader.readLine(); // Ignorar a primeira linha de cabeçalho
            while ((line = reader.readLine()) != null) {
                linhas[numLinhas] = line;
                numLinhas++;
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: %s%n", e.getMessage());
        }

        for (int i = 0; i < numLinhas; i++) {
            String[] campos = linhas[i].split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(campos[0], formatter);
            /*
            int anoDataExtracao = dateTime.getYear();
            int mesDataExtracao = dateTime.getMonthValue();
            int diaDataExtracao = dateTime.getDayOfMonth();
            int horaDataExtracao = dateTime.getHour();
            int minDataExtracao = dateTime.getMinute();
            */

            String descricao = campos[1];
            /*String estado = campos[2];
            String complemento = campos[3];
            int anoImplantacao = 0;
            int mesImplantacao = 0;
            int diaImplantacao = 0;

            if (!campos[4].isEmpty()) {
                if (campos[4].contains("-"))
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                else
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(campos[4], formatter);
                anoImplantacao = date.getYear();
                mesImplantacao = date.getMonthValue();
                diaImplantacao = date.getDayOfMonth();
            }*/

            String logradouro = campos[5].split(" ", 2)[0];
            String nomeLog = campos[5].split(" ", 2)[1];

            double numInicial = campos[6].isEmpty() ? 0 : Double.parseDouble(campos[6]);
            double numFinal = campos[7].isEmpty() ? 0 : Double.parseDouble(campos[7]);

            /*String defronte = campos[8];
            String cruzamento = campos[9];*/
            String lado = campos[10];
            /*String fluxo = campos.length >= 12 ? campos[11] : "";*/
            String localInstalacao = campos.length >= 13 ? campos[12] : "";

            Sinalizacao s = new Sinalizacao(descricao, dateTime, numInicial, numFinal, lado, localInstalacao);
            ltRuas.insereOrdenado(logradouro, nomeLog, s);
        }
    }

    public ListadeRuas getLtRuas() {
        return ltRuas;
    }
}

