package com.compiladoresufabc.ptbrlangcompiler.commons.generator;

import com.compiladoresufabc.ptbrlangcompiler.commons.enums.Constants;
import com.compiladoresufabc.ptbrlangcompiler.commons.enums.LanguageType;
import com.compiladoresufabc.ptbrlangcompiler.domains.Types;
import com.compiladoresufabc.ptbrlangcompiler.domains.Var;

public class AtribCommand extends Command {

	private Var var;
	private String exprString;
	private String strOp;

	public AtribCommand() {
		super();
	}

	public AtribCommand(Var var) {
		super();
		this.var = var;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	public String getExprString() {
		return exprString;
	}

	public void setExprString(String exprString) {
		this.exprString = exprString;
	}

	public String getStrOp() {
		return strOp;
	}

	public void setStrOp(String strOp) {
		this.strOp = strOp;
	}

	@Override
	public String generateCode(LanguageType language, int indentLevel) {
		return switch (language) {
			case JAVA -> generateJavaCode();
			case C -> generateCCode();
			case PYTHON -> generatePythonCode();
			default -> null;
		};
	}

	private String generateJavaCode() {
		StringBuilder atribBuild = new StringBuilder();
		if (strOp.equalsIgnoreCase("++") || strOp.equalsIgnoreCase("--")) {
			atribBuild.append(var.getId() + strOp);
		} else {
			if (strOp.equalsIgnoreCase(":=")) {
				strOp = "=";
			}
			atribBuild.append(var.getId() + " " + strOp  + " ");
		}
		
		if (null != exprString) {
			if (Constants.VERDADEIRO.getValue().equalsIgnoreCase(exprString)) {
				exprString = exprString.replace(exprString, "true");
			} else if (Constants.FALSO.getValue().equalsIgnoreCase(exprString)) {
				exprString = exprString.replace(exprString, "false");
			}
			atribBuild.append(exprString);
		}
		atribBuild.append(";\n");

		return atribBuild.toString();
	}


	private String generateCCode() {
		StringBuilder atribBuild = new StringBuilder();

	    if (strOp.equalsIgnoreCase("++") || strOp.equalsIgnoreCase("--")) {
	        atribBuild.append(var.getId() + strOp);  // Adiciona o operador ao ID da variável

	    } else {
	        if (strOp.equalsIgnoreCase(":=")) {
	            strOp = "=";
	        }

	        atribBuild.append(var.getId() + " " + strOp + " ");
	    }

	    if (null != exprString) {
	        if (Constants.VERDADEIRO.getValue().equalsIgnoreCase(exprString)) {
	            exprString = "true";
	        } else if (Constants.FALSO.getValue().equalsIgnoreCase(exprString)) {
	            exprString = "false";
	        }

	        if (var.getType().equals(Types.TEXT)) {
	            atribBuild.append("strcpy(" + var.getId() + ", " + exprString + ")");
	        } else {
	            atribBuild.append(exprString);
	        }
	    }

	    atribBuild.append(";\n");

		return atribBuild.toString();
	}

	private String generatePythonCode() {
		StringBuilder atribBuild = new StringBuilder();
		if (strOp.equalsIgnoreCase("++") || strOp.equalsIgnoreCase("--")) {
			atribBuild.append(var.getId() + strOp);
		} else {
			if (strOp.equalsIgnoreCase(":=")) {
				strOp = "=";
			}
			atribBuild.append(var.getId() + " " + strOp  + " ");
		}
		if (null != exprString) {
			if (Constants.VERDADEIRO.getValue().equalsIgnoreCase(exprString)) {
				exprString = exprString.replace(exprString, "True");
			} else if (Constants.FALSO.getValue().equalsIgnoreCase(exprString)) {
				exprString = exprString.replace(exprString, "False");
			}
			atribBuild.append(exprString);
		}

		return atribBuild.toString();
	}
}
