package com.se.ecostruxure_mmirzakhani.bll;


import com.se.ecostruxure_mmirzakhani.be.Script;
import com.se.ecostruxure_mmirzakhani.be.Employee;


public class ConfigService {
    // ToDo: Implement methods to handle user custom configs based on Config entity using Math class.

    /**
     * Constructor
     */
    public ConfigService(){

    }

    /**
     * Translate the config operation and replace symbols with actual values from employee properties.
     * This method takes an Employee object and a Config object as parameters. It retrieves a formula
     * from the Config object, which contains symbols representing properties of the employee. It then
     * replaces these symbols with the actual values from the corresponding properties of the Employee
     * object. The translated formula is returned as a String.
     *
     * @param employee The Employee object containing the properties to be placed into the formula.
     * @param script The Config object containing the formula with symbols to be replaced.
     * @return The translated formula with actual values substituted.
     */
    public static String translate(Employee employee, Script script){
        // String to replace user input symbols with actual values
        String translatedFormula = script.getFormula();

        // Replace symbols with the actual values
        translatedFormula = translatedFormula.replace(TranslateSymbols.ANNUAL_SALARY.getValue(), Double.toString(employee.getAnnualSalary()));
        translatedFormula = translatedFormula.replace(TranslateSymbols.ANNUAL_WORK_HOURS.getValue(), Double.toString(employee.getAnnualWorkHours()));


        return translatedFormula;
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
