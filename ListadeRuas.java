import java.time.Month;

public class ListadeRuas {
    private class Node {
        public ListaSinalizacao lista= new ListaSinalizacao();
        public String nomeRua;
        public String idRua;
        public Node next;
        public Node prev;

        public Node(String nr, String ir, Sinalizacao s) {
            this.lista.Add(s);
            this.nomeRua = nr;
            this.idRua = ir;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "Nome da Rua='" + nomeRua;
        }
    }

    private final Node inicio;
    private final Node fim;
    private Node atualAux;
    private int count;

    public ListadeRuas() {
        this.inicio = new Node(null, null, null);
        this.fim = new Node(null, null, null);
        this.count = 0;
        inicio.next = fim;
        fim.prev = inicio;
    }

    public void insereOrdenado(String idRua, String nomeRua, Sinalizacao sinalizacao) {
        Node aux = Contem(nomeRua);

        if (aux == null) {
            Node nNode = new Node(nomeRua, idRua, sinalizacao);

            if (inicio.next == fim) {
                // se a lista está vazia
                nNode.prev = fim;
                nNode.next = fim;
                fim.prev = nNode;
                inicio.next = nNode;
                atualAux = nNode;
            } else if (nomeRua.compareTo(inicio.next.nomeRua) < 0) {
                // se for menor que o primeiro, insere no início
                nNode.next = inicio.next;
                nNode.prev = inicio;
                inicio.next = nNode;
                nNode.next.prev = nNode;
            } else if (nomeRua.compareTo(fim.prev.nomeRua) > 0) {
                // se for maior que o último, insere no final
                nNode.next = fim;
                nNode.prev = fim.prev;
                fim.prev.next = nNode;
                fim.prev = nNode;
            } else {
                // senão procura a posição correta para inserção
                aux = inicio.next;
                boolean inseriu = false;

                while (aux != fim && !inseriu) {
                    if (nomeRua.compareTo(aux.nomeRua) < 0) {
                        inseriu = true;
                        nNode.next = aux;
                        nNode.prev = aux.prev;
                        aux.prev.next = nNode;
                        aux.prev = nNode;
                    }
                    aux = aux.next;
                }
            }
            count++;
        }
        else{
            Node auxN= inicio.next;
            while(auxN!=fim){
                if(auxN.nomeRua.equals(nomeRua)){
                    auxN.lista.Add(sinalizacao);
                    break;
                }
                auxN= auxN.next;
            }
        }
    }

    public String getRuaComMaisSinalizacoes() {
        if (count == 0) {
            return "Não há ruas cadastradas.";
        }

        Node aux = inicio.next;
        Node ruaComMaisSinalizacoes = aux;
        int qntSinalizacoes = aux.lista.Size();

        while (aux != fim) {
            int numSinalizacoes = aux.lista.Size();
            if (numSinalizacoes > qntSinalizacoes) {
                qntSinalizacoes = numSinalizacoes;
                ruaComMaisSinalizacoes = aux;
            }
            aux = aux.next;
        }

        return "Rua com mais sinalizações: " + ruaComMaisSinalizacoes.nomeRua +
                " (" + qntSinalizacoes + " sinalizações)";
    }

    public Month getMesComMaisSinalizacoes() {
        Month mesComMaisSinalizacoes = null;
        int qntSinalizacoes = 0;

        Node aux = inicio.next;
        while (aux != fim) {
            int[] sinalizacoesPorMes = new int[13]; //Não vamos usar o indice 0, pq os meses vão de 1-12

            //Recebe a lista de sinalização da rua
            ListaSinalizacao lista = aux.lista;
            //uma sinalização para percorrer a lista
            ListaSinalizacao.Node sinalizacaoAux = lista.inicio.next;

            //percorre enquanto a sinalização for diferente do fim da lista
            while (sinalizacaoAux != lista.fim) {
                //pega o mes em que foi implementada
                Month mesImplantacao = sinalizacaoAux.sinalizacao.getImplantacao().getMonth();
                //adiciona no indice correspondente ao valor do mes
                sinalizacoesPorMes[mesImplantacao.getValue()]++;
                //pula para a proxima sinalização
                sinalizacaoAux = sinalizacaoAux.next;
            }
            //percorre o array
            for (int i = 1; i < sinalizacoesPorMes.length; i++) {
                if (sinalizacoesPorMes[i] > qntSinalizacoes) {
                    qntSinalizacoes = sinalizacoesPorMes[i];
                    mesComMaisSinalizacoes = Month.of(i);
                }
            }
            aux = aux.next;
        }
        //retorna o mes da sinalização
        return mesComMaisSinalizacoes;
    }



    public Node Contem(String nomeRua) {
        Node aux = inicio.next;

        while (aux != fim) {
            if (aux.nomeRua.equals(nomeRua)) {
                return aux;
            }
            aux = aux.next;
        }

        return null;
    }

    public void Reset() {
        this.inicio.next = fim;
        this.fim.prev = inicio;
        // Esvazia a lista.
    }

    public int Size() {
        return count;
    }

    public String atual() {
        return "Logradouro: "+atualAux.nomeRua+"\nNúmero de Sinalizações: "+atualAux.lista.Size()+"\nPrimeira Sinalização: "+atualAux.lista.getMenorData()+"\nÚltima Sinalização: "+atualAux.lista.getMaiorData();
    }

    public void prev() {
        this.atualAux = atualAux.prev;
        // Retorna para a rua anterior.
    }

    public void next() {
        // Avança para a próxima rua.
        this.atualAux = atualAux.next;
    }

    @Override
    public String toString() {
        return "Nome da Rua: \n" + atualAux.nomeRua + "Primeira Sinalização instalada: \n" + atualAux.lista.getMenorData()
                + "Última sinalização instalada: \n" + atualAux.lista.getMaiorData() + "Quantidade de sinalizações: \n"
                + atualAux.lista.Size();
    }
}

