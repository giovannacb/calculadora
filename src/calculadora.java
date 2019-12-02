import java.util.Scanner;

public class calculadora {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String expressao;
        char ch;
        double n1, n2;
        boolean potencia=false;
        Pilha<String> pilhaoperador = new Pilha<String>();
        Pilha<Double> pilha = new Pilha<Double>();

        System.out.println("Insira sua expressão utilizando parênteses: ");
        expressao = teclado.nextLine();
        for (int i = 0; i < expressao.length(); i++) {
            ch = expressao.charAt(i);
            if (ch == '(') {
                pilhaoperador.push(String.valueOf(ch));
            } else if (ch == '-' || ch == '+') {
                if(pilhaoperador.isEmpty()){
                    pilhaoperador.push(String.valueOf(ch));
                } else {
                    while (pilhaoperador.top().equals("+") ||
                            pilhaoperador.top().equals("-") ||
                            pilhaoperador.top().equals("*") ||
                            pilhaoperador.top().equals("^") ||
                            pilhaoperador.top().equals("/")) {

                        n1 = pilha.top();
                        pilha.pop();
                        n2 = pilha.top();
                        pilha.pop();

                        if (pilhaoperador.isEmpty()) {
                            break;
                        }
                        if (pilhaoperador.top().equals("+")) {
                            pilhaoperador.pop();
                            pilha.push(n2 + n1);
                        } else if (pilhaoperador.top().equals("-")) {
                            pilha.push(n2 - n1);
                            pilhaoperador.pop();
                        } else if (pilhaoperador.top().equals("*")) {
                            pilha.push(n2 * n1);
                            pilhaoperador.pop();
                        } else if (pilhaoperador.top().equals("^")) {
                            pilha.push(Math.pow(n2, n1));
                            pilhaoperador.pop();
                        } else if (pilhaoperador.top().equals("/")) {
                            pilha.push(n2 / n1);
                            pilhaoperador.pop();
                        }
                    }
                    pilhaoperador.push(String.valueOf(ch));
                }
            } else if (ch == '*' || ch == '/') {
                if(pilhaoperador.isEmpty()){
                    pilhaoperador.push(String.valueOf(ch));
                } else {
                    while (pilhaoperador.top().equals("*") ||
                            pilhaoperador.top().equals("^") ||
                            pilhaoperador.top().equals("/")) {

                        n1 = pilha.top();
                        pilha.pop();
                        n2 = pilha.top();
                        pilha.pop();

                        if (pilhaoperador.isEmpty()) {
                            break;
                        }
                        if (pilhaoperador.top().equals("^")) {
                            pilha.push(Math.pow(n2, n1));
                            pilhaoperador.pop();
                        } else if (pilhaoperador.top().equals("*")) {
                            pilha.push(n2 * n1);
                            pilhaoperador.pop();
                        } else if (pilhaoperador.top().equals("/")) {
                            pilha.push(n2 / n1);
                            pilhaoperador.pop();
                        }
                    }
                    pilhaoperador.push(String.valueOf(ch));
                }
            } else if (ch == '^') {
                if(pilhaoperador.isEmpty()){
                    pilhaoperador.push(String.valueOf(ch));
                } else {
                    while (pilhaoperador.top().equals("^")) {
                        n1 = pilha.top();
                        pilha.pop();
                        n2 = pilha.top();
                        pilha.pop();
                        pilha.push(Math.pow(n2, n1));
                        pilhaoperador.pop();
                        if (pilhaoperador.isEmpty()) {
                            break;
                        }
                    }
                    if (expressao.charAt(i + 1) == '(') {
                        potencia = true;
                    }
                    pilhaoperador.push(String.valueOf(ch));
                }
            } else if (ch == ')') {
                while (!pilhaoperador.top().equals("(")) {
                    n1=pilha.top();
                    pilha.pop();
                    n2=pilha.top();
                    pilha.pop();

                    if(pilhaoperador.isEmpty()){
                        break;
                    }
                    if (pilhaoperador.top().equals("+")) {
                        pilhaoperador.pop();
                        pilha.push(n2+n1);
                    } else if (pilhaoperador.top().equals("-")) {
                        pilha.push(n2-n1);
                        pilhaoperador.pop();
                    } else if (pilhaoperador.top().equals("*")) {
                        pilha.push(n2*n1);
                        pilhaoperador.pop();
                    } else if (pilhaoperador.top().equals("^")){
                        pilha.push(Math.pow(n2, n1));
                        pilhaoperador.pop();
                    } else if (pilhaoperador.top().equals("/")){
                        pilha.push(n2/n1);
                        pilhaoperador.pop();
                    }
                }
                if(potencia==true){
                    pilhaoperador.pop();
                    n1 = pilha.top();
                    pilha.pop();
                    n2 = pilha.top();
                    pilha.pop();
                    pilha.push(Math.pow(n2, n1));
                    potencia=false;
                }
                pilhaoperador.pop();
            } else if (ch == ' '){

            } else {
                String vl="";
                for(; i<expressao.length(); i++){
                    if(Character.isDigit(expressao.charAt(i)) || expressao.charAt(i)=='.') {
                        vl += Character.toString(expressao.charAt(i));
                    } else{
                        i--;
                        break;
                    }
                }
                pilha.push(Double.parseDouble(vl));
            }
        }

        while (!pilhaoperador.isEmpty()) {
            n1=pilha.top();
            pilha.pop();
            n2=pilha.top();
            pilha.pop();

            if(pilhaoperador.isEmpty()){
                break;
            }
            if (pilhaoperador.top().equals("+")) {
                pilhaoperador.pop();
                pilha.push(n2+n1);
            } else if (pilhaoperador.top().equals("-")) {
                pilha.push(n2-n1);
                pilhaoperador.pop();
            } else if (pilhaoperador.top().equals("*")) {
                pilha.push(n2*n1);
                pilhaoperador.pop();
            } else if (pilhaoperador.top().equals("^")){
                pilha.push(Math.pow(n2, n1));
                pilhaoperador.pop();
            } else if (pilhaoperador.top().equals("/")){
                pilha.push(n2/n1);
                pilhaoperador.pop();
            }
        }
        System.out.println(pilha.top());
    }
}