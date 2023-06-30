import java.time.LocalDateTime;
import java.time.Month;

public class ListaSinalizacao {

    public class Node {
        public Sinalizacao sinalizacao;
        public Node next;

        public Node(Sinalizacao s) {
            this.sinalizacao = s;
            this.next = null;
        }
    }

    public final Node inicio;
    public final Node fim;
    private Node atualAux;
    private int count;

    public ListaSinalizacao() {
        this.inicio = new Node(null);
        this.fim = new Node(null);
        inicio.next = fim;
        this.count = 0;
    }

    public void Add(Sinalizacao s) {
        // Adiciona a sinalização na lista.
        Node aux = inicio.next;
        Node nNode = new Node(s);

        if (count == 0) {
            inicio.next = nNode;
            nNode.next = fim;
        } else {
            while (aux != fim) {
                if (aux.next == fim) {
                    aux.next = nNode;
                    nNode.next = fim;
                }
                aux = aux.next;
            }
        }

        count++;
    }

    public int Size() {
        // retorna o total de sinalizações
        return count;
    }

    public Month getMes(int index) {
        // retorna o mês de implantação da i-ésima sinalização
        Node aux = inicio.next;

        if (count == 0) {
            return null;
        }

        for (int idx = 0; idx < index; idx++) {
            if (idx == index) {
                return aux.sinalizacao.getImplantacao().getMonth();
            }
            aux = aux.next;
        }

        return null;
    }

    public LocalDateTime getImplementacao(int index) {
        // retorna data de implantação da i-ésima sinalização
        Node aux = inicio.next;

        if (count == 0) {
            return null;
        }

        for (int idx = 0; idx < index; idx++) {
            if (idx == index) {
                return aux.sinalizacao.getImplantacao();
            }
            aux = aux.next;
        }

        return null;
    }

    public LocalDateTime getMenorData() {
        // retorna a data da primeira sinalização instalada
        // (considerando esta lista)
        Node aux = inicio.next;
        LocalDateTime menor = aux.sinalizacao.getImplantacao();
        // Se a lista está vazia retorna nulo.
        if (count == 0) {
            return null;
        }

        while (aux != fim) {
            if (aux.sinalizacao.getImplantacao().isBefore(menor)) {
                menor = aux.sinalizacao.getImplantacao();
            }
            aux = aux.next;
        }

        return menor;
    }

    public LocalDateTime getMaiorData() {
        // retorna a data da última sinalização instalada
        // (considerando esta lista)
        Node aux = inicio.next;
        LocalDateTime maior = aux.sinalizacao.getImplantacao();
        // Se a lista está vazia retorna nulo.
        if (count == 0) {
            return null;
        }

        while (aux != fim) {
            if (aux.sinalizacao.getImplantacao().isAfter(maior)) {
                maior = aux.sinalizacao.getImplantacao();
            }
            aux = aux.next;
        }

        return maior;
    }

    public void next() {
        // Avança para a próxima sinalização
        this.atualAux = atualAux.next;
    }

    public void Reset() {
        this.inicio.next = fim;
        this.count = 0;
    }

}

