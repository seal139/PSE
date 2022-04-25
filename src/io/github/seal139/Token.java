/**
 * 
 */
package io.github.seal139;

/**
 * @author Septian Pramana R
 *
 */
public class Token {
    private double  operand;
    private char    operator;
    private boolean opr;

    public Token(double operand) {
        this.opr     = true;
        this.operand = operand;
    }

    public Token(Character operator) {
        this.opr      = false;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.opr ? String.valueOf(this.operand) : String.valueOf(this.operator);
    }

    /**
     * @return the operand
     */
    public double getOperand() { return this.operand; }

    /**
     * @param operand the operand to set
     */
    public void setOperand(double operand) { this.operand = operand; }

    /**
     * @return the operator
     */
    public Character getOperator() { return this.operator; }

    /**
     * @param operator the operator to set
     */
    public void setOperator(Character operator) { this.operator = operator; }

}
