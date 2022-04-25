package io.github.seal139;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Septian Pramana R
 *
 */
public class Postfix {
    protected Stack<Character> operatorStack = new Stack<Character>();
    protected Stack<Double>    operandStack  = new Stack<Double>();

    protected LinkedList<Token> tokenizer = new LinkedList<Token>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token t : this.tokenizer) {
            sb.append(t.toString() + " ");
        }

        return sb.toString() + " = " + String.valueOf(this.operandStack.pop());
    }

    protected String rawString;

    public Postfix(String raw) {
        this.rawString = raw;
    }

    void addOperand(StringBuilder sb) {
        if (sb.length() == 0) {
            return;
        }

        Double value = Double.valueOf(sb.toString());
        Token  token = new Token(value);
        this.operandStack.add(value);
        this.tokenizer.add(token);
    }

    void addOperator(boolean push, char[] skip) {
        while (!this.operatorStack.isEmpty()) {
            Character prevToken = this.operatorStack.pop();

            for (char c : skip) {
                if (!prevToken.equals(c)) {
                    continue;
                }

                if (!push) {
                    return;
                }

                this.operatorStack.push(c);
                return;
            }

            Token t = new Token(prevToken);
            evaluate(prevToken);
            this.tokenizer.add(t);
        }
    }

    void tokenize() throws ParseException {
        int           count    = 0;
        int           indent   = 0;
        boolean       oprValid = false;
        StringBuilder sb       = new StringBuilder();
        char[]        rawChar  = this.rawString.toCharArray();

        for (Character c : rawChar) {
            count += 1;
            boolean push     = true;
            char[]  skipChar = new char[] {
                    '(', ';', (char) 0, (char) 0 };

            switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
            case '.':
                oprValid = true;
                sb.append(c);
                break;

            case '^':
            case '*':
            case '/':
            case '%':
                skipChar[2] = '+';
                skipChar[3] = '-';

            case '+':
            case '-':
                if (!oprValid) {
                    sb.append("-");
                    break;
                }

                oprValid = false;

                addOperand(sb);
                sb = new StringBuilder();

                addOperator(push, skipChar);

                this.operatorStack.push(c);
                break;

            case ')':
                indent -= 1;
                if (indent < 0) {
                    throw new ParseException("Invalid token ) :" + String.valueOf(count), count);
                }

                oprValid = true;

            case ';':
                addOperand(sb);
                sb = new StringBuilder();

                addOperator(false, skipChar);
                break;

            case '(':
                indent += 1;
                oprValid = false;
                this.operatorStack.push(c);
                break;

            default:
                break;
            }
        }
    }

    void evaluate(Character operator) {
        double operand2 = this.operandStack.pop();
        double operand1 = this.operandStack.pop();

        switch (operator) {
        case '+':
            this.operandStack.push(operand1 + operand2);
            break;

        case '-':
            this.operandStack.push(operand1 - operand2);
            break;

        case '*':
            this.operandStack.push(operand1 * operand2);
            break;

        case '/':
            this.operandStack.push(operand1 / operand2);
            break;

        case '%':
            this.operandStack.push(operand1 % operand2);
            break;

        case '^':
            this.operandStack.push(Math.pow(operand1, operand2));
            break;

        }
    }

}
