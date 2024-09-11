// Generated from PtBrLangGrammar.g4 by ANTLR 4.13.2
package com.compiladoresufabc.ptbrlangcompiler.commons.antlr;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import com.compiladoresufabc.ptbrlangcompiler.domains.*;
import com.compiladoresufabc.ptbrlangcompiler.commons.errors.*;
import com.compiladoresufabc.ptbrlangcompiler.commons.generator.*;
import com.compiladoresufabc.ptbrlangcompiler.runtime.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PtBrLangGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		OP_SUM=18, OP_SUB=19, OP_MUL=20, OP_DIV=21, OP_AT=22, OPREL=23, ID=24, 
		NUM=25, VIRG=26, PV=27, AP=28, FP=29, DP=30, AND=31, OR=32, NOT=33, TEXTO=34, 
		WS=35, BOOL=36, CLASS=37;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"OP_SUM", "OP_SUB", "OP_MUL", "OP_DIV", "OP_AT", "OPREL", "ID", "NUM", 
			"VIRG", "PV", "AP", "FP", "DP", "AND", "OR", "NOT", "TEXTO", "WS", "BOOL", 
			"CLASS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim'", "'fimprograma'", "'declare'", 
			"'number'", "'text'", "'bool'", "'se'", "'entao'", "'senao'", "'fimse'", 
			"'enquanto'", "'faca'", "'fimenquanto'", "'leia'", "'escreva'", "'+'", 
			"'-'", "'*'", "'/'", null, null, null, null, "','", "';'", "'('", "')'", 
			"':'", "'AND'", "'OR'", "'NOT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "OP_SUM", "OP_SUB", "OP_MUL", "OP_DIV", 
			"OP_AT", "OPREL", "ID", "NUM", "VIRG", "PV", "AP", "FP", "DP", "AND", 
			"OR", "NOT", "TEXTO", "WS", "BOOL", "CLASS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private ArrayList<String> exprList;
	    private String strOp = "";
	    private IfCommand currentIfCommand;
	    private WhileCommand whileCommand;
	    private AtribCommand atribCommand;
	    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
	    private Stack<AbstractExpression> exprStack = new Stack<AbstractExpression>();
	    private AbstractExpression top = null;

	    public double generateValue(){
	        if (top == null){
	            top = exprStack.pop();
	        }
	        return top.evaluate();
	    }

	    public String generateJSON(){
	        if (top == null){
	            top = exprStack.pop();
	        }
	        return top.toJSON();
	    }

	    public void updateType(){
	     for(Var v: currentDecl){
	        v.setType(currentType);
	        symbolTable.put(v.getId(), v);
	     }
	    }
	    public void exibirVar(){
	        for (String id: symbolTable.keySet()){
	         System.out.println(symbolTable.get(id));
	        }
	    }

	    public Program getProgram(){
	     return this.program;
	     }

	    public boolean isDeclared(String id){
	     return symbolTable.get(id) != null;
	    }
	    
	    public void checkUnusedVariables() {
			String yellowColor = "\u001B[33m";
	    	String resetColor = "\u001B[0m";
	        for (Var var : symbolTable.values()) {
	            if ((var.isInitialized() && !var.isUsed()) || !(var.isInitialized() && var.isUsed())) {
	                System.out.println(yellowColor + "Warning: Variable '" + var.getId() + "' initialized/declared but never used." + resetColor);
	            }
	        }
	    }


	public PtBrLangGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PtBrLangGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000%\u0131\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00d3\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00df\b\u0016\u0001\u0017"+
		"\u0001\u0017\u0005\u0017\u00e3\b\u0017\n\u0017\f\u0017\u00e6\t\u0017\u0001"+
		"\u0018\u0003\u0018\u00e9\b\u0018\u0001\u0018\u0004\u0018\u00ec\b\u0018"+
		"\u000b\u0018\f\u0018\u00ed\u0001\u0018\u0001\u0018\u0004\u0018\u00f2\b"+
		"\u0018\u000b\u0018\f\u0018\u00f3\u0003\u0018\u00f6\b\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 "+
		"\u0001 \u0001!\u0001!\u0005!\u010f\b!\n!\f!\u0112\t!\u0001!\u0001!\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003#\u0129"+
		"\b#\u0001$\u0001$\u0005$\u012d\b$\n$\f$\u0130\t$\u0000\u0000%\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u0018"+
		"1\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%\u0001\u0000"+
		"\u0007\u0002\u0000<<>>\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0005"+
		"\u0000  ,.09AZaz\u0003\u0000\t\n\r\r  \u0001\u0000AZ\u0140\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u0000"+
		"5\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001"+
		"\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000"+
		"\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000"+
		"C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001"+
		"\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0001K\u0001\u0000\u0000"+
		"\u0000\u0003T\u0001\u0000\u0000\u0000\u0005[\u0001\u0000\u0000\u0000\u0007"+
		"_\u0001\u0000\u0000\u0000\tk\u0001\u0000\u0000\u0000\u000bs\u0001\u0000"+
		"\u0000\u0000\rz\u0001\u0000\u0000\u0000\u000f\u007f\u0001\u0000\u0000"+
		"\u0000\u0011\u0084\u0001\u0000\u0000\u0000\u0013\u0087\u0001\u0000\u0000"+
		"\u0000\u0015\u008d\u0001\u0000\u0000\u0000\u0017\u0093\u0001\u0000\u0000"+
		"\u0000\u0019\u0099\u0001\u0000\u0000\u0000\u001b\u00a2\u0001\u0000\u0000"+
		"\u0000\u001d\u00a7\u0001\u0000\u0000\u0000\u001f\u00b3\u0001\u0000\u0000"+
		"\u0000!\u00b8\u0001\u0000\u0000\u0000#\u00c0\u0001\u0000\u0000\u0000%"+
		"\u00c2\u0001\u0000\u0000\u0000\'\u00c4\u0001\u0000\u0000\u0000)\u00c6"+
		"\u0001\u0000\u0000\u0000+\u00d2\u0001\u0000\u0000\u0000-\u00de\u0001\u0000"+
		"\u0000\u0000/\u00e0\u0001\u0000\u0000\u00001\u00e8\u0001\u0000\u0000\u0000"+
		"3\u00f7\u0001\u0000\u0000\u00005\u00f9\u0001\u0000\u0000\u00007\u00fb"+
		"\u0001\u0000\u0000\u00009\u00fd\u0001\u0000\u0000\u0000;\u00ff\u0001\u0000"+
		"\u0000\u0000=\u0101\u0001\u0000\u0000\u0000?\u0105\u0001\u0000\u0000\u0000"+
		"A\u0108\u0001\u0000\u0000\u0000C\u010c\u0001\u0000\u0000\u0000E\u0115"+
		"\u0001\u0000\u0000\u0000G\u0128\u0001\u0000\u0000\u0000I\u012a\u0001\u0000"+
		"\u0000\u0000KL\u0005p\u0000\u0000LM\u0005r\u0000\u0000MN\u0005o\u0000"+
		"\u0000NO\u0005g\u0000\u0000OP\u0005r\u0000\u0000PQ\u0005a\u0000\u0000"+
		"QR\u0005m\u0000\u0000RS\u0005a\u0000\u0000S\u0002\u0001\u0000\u0000\u0000"+
		"TU\u0005i\u0000\u0000UV\u0005n\u0000\u0000VW\u0005i\u0000\u0000WX\u0005"+
		"c\u0000\u0000XY\u0005i\u0000\u0000YZ\u0005o\u0000\u0000Z\u0004\u0001\u0000"+
		"\u0000\u0000[\\\u0005f\u0000\u0000\\]\u0005i\u0000\u0000]^\u0005m\u0000"+
		"\u0000^\u0006\u0001\u0000\u0000\u0000_`\u0005f\u0000\u0000`a\u0005i\u0000"+
		"\u0000ab\u0005m\u0000\u0000bc\u0005p\u0000\u0000cd\u0005r\u0000\u0000"+
		"de\u0005o\u0000\u0000ef\u0005g\u0000\u0000fg\u0005r\u0000\u0000gh\u0005"+
		"a\u0000\u0000hi\u0005m\u0000\u0000ij\u0005a\u0000\u0000j\b\u0001\u0000"+
		"\u0000\u0000kl\u0005d\u0000\u0000lm\u0005e\u0000\u0000mn\u0005c\u0000"+
		"\u0000no\u0005l\u0000\u0000op\u0005a\u0000\u0000pq\u0005r\u0000\u0000"+
		"qr\u0005e\u0000\u0000r\n\u0001\u0000\u0000\u0000st\u0005n\u0000\u0000"+
		"tu\u0005u\u0000\u0000uv\u0005m\u0000\u0000vw\u0005b\u0000\u0000wx\u0005"+
		"e\u0000\u0000xy\u0005r\u0000\u0000y\f\u0001\u0000\u0000\u0000z{\u0005"+
		"t\u0000\u0000{|\u0005e\u0000\u0000|}\u0005x\u0000\u0000}~\u0005t\u0000"+
		"\u0000~\u000e\u0001\u0000\u0000\u0000\u007f\u0080\u0005b\u0000\u0000\u0080"+
		"\u0081\u0005o\u0000\u0000\u0081\u0082\u0005o\u0000\u0000\u0082\u0083\u0005"+
		"l\u0000\u0000\u0083\u0010\u0001\u0000\u0000\u0000\u0084\u0085\u0005s\u0000"+
		"\u0000\u0085\u0086\u0005e\u0000\u0000\u0086\u0012\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0005e\u0000\u0000\u0088\u0089\u0005n\u0000\u0000\u0089\u008a"+
		"\u0005t\u0000\u0000\u008a\u008b\u0005a\u0000\u0000\u008b\u008c\u0005o"+
		"\u0000\u0000\u008c\u0014\u0001\u0000\u0000\u0000\u008d\u008e\u0005s\u0000"+
		"\u0000\u008e\u008f\u0005e\u0000\u0000\u008f\u0090\u0005n\u0000\u0000\u0090"+
		"\u0091\u0005a\u0000\u0000\u0091\u0092\u0005o\u0000\u0000\u0092\u0016\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0005f\u0000\u0000\u0094\u0095\u0005i\u0000"+
		"\u0000\u0095\u0096\u0005m\u0000\u0000\u0096\u0097\u0005s\u0000\u0000\u0097"+
		"\u0098\u0005e\u0000\u0000\u0098\u0018\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0005e\u0000\u0000\u009a\u009b\u0005n\u0000\u0000\u009b\u009c\u0005q"+
		"\u0000\u0000\u009c\u009d\u0005u\u0000\u0000\u009d\u009e\u0005a\u0000\u0000"+
		"\u009e\u009f\u0005n\u0000\u0000\u009f\u00a0\u0005t\u0000\u0000\u00a0\u00a1"+
		"\u0005o\u0000\u0000\u00a1\u001a\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"f\u0000\u0000\u00a3\u00a4\u0005a\u0000\u0000\u00a4\u00a5\u0005c\u0000"+
		"\u0000\u00a5\u00a6\u0005a\u0000\u0000\u00a6\u001c\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0005f\u0000\u0000\u00a8\u00a9\u0005i\u0000\u0000\u00a9\u00aa"+
		"\u0005m\u0000\u0000\u00aa\u00ab\u0005e\u0000\u0000\u00ab\u00ac\u0005n"+
		"\u0000\u0000\u00ac\u00ad\u0005q\u0000\u0000\u00ad\u00ae\u0005u\u0000\u0000"+
		"\u00ae\u00af\u0005a\u0000\u0000\u00af\u00b0\u0005n\u0000\u0000\u00b0\u00b1"+
		"\u0005t\u0000\u0000\u00b1\u00b2\u0005o\u0000\u0000\u00b2\u001e\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b4\u0005l\u0000\u0000\u00b4\u00b5\u0005e\u0000\u0000"+
		"\u00b5\u00b6\u0005i\u0000\u0000\u00b6\u00b7\u0005a\u0000\u0000\u00b7 "+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005e\u0000\u0000\u00b9\u00ba\u0005"+
		"s\u0000\u0000\u00ba\u00bb\u0005c\u0000\u0000\u00bb\u00bc\u0005r\u0000"+
		"\u0000\u00bc\u00bd\u0005e\u0000\u0000\u00bd\u00be\u0005v\u0000\u0000\u00be"+
		"\u00bf\u0005a\u0000\u0000\u00bf\"\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0005+\u0000\u0000\u00c1$\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005-"+
		"\u0000\u0000\u00c3&\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005*\u0000\u0000"+
		"\u00c5(\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005/\u0000\u0000\u00c7*"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005:\u0000\u0000\u00c9\u00d3\u0005"+
		"=\u0000\u0000\u00ca\u00cb\u0005+\u0000\u0000\u00cb\u00d3\u0005=\u0000"+
		"\u0000\u00cc\u00cd\u0005+\u0000\u0000\u00cd\u00d3\u0005+\u0000\u0000\u00ce"+
		"\u00cf\u0005-\u0000\u0000\u00cf\u00d3\u0005-\u0000\u0000\u00d0\u00d1\u0005"+
		"-\u0000\u0000\u00d1\u00d3\u0005=\u0000\u0000\u00d2\u00c8\u0001\u0000\u0000"+
		"\u0000\u00d2\u00ca\u0001\u0000\u0000\u0000\u00d2\u00cc\u0001\u0000\u0000"+
		"\u0000\u00d2\u00ce\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d3,\u0001\u0000\u0000\u0000\u00d4\u00df\u0007\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0005>\u0000\u0000\u00d6\u00df\u0005=\u0000\u0000\u00d7\u00d8"+
		"\u0005<\u0000\u0000\u00d8\u00d9\u0005=\u0000\u0000\u00d9\u00df\u0005 "+
		"\u0000\u0000\u00da\u00db\u0005<\u0000\u0000\u00db\u00df\u0005>\u0000\u0000"+
		"\u00dc\u00dd\u0005=\u0000\u0000\u00dd\u00df\u0005=\u0000\u0000\u00de\u00d4"+
		"\u0001\u0000\u0000\u0000\u00de\u00d5\u0001\u0000\u0000\u0000\u00de\u00d7"+
		"\u0001\u0000\u0000\u0000\u00de\u00da\u0001\u0000\u0000\u0000\u00de\u00dc"+
		"\u0001\u0000\u0000\u0000\u00df.\u0001\u0000\u0000\u0000\u00e0\u00e4\u0007"+
		"\u0001\u0000\u0000\u00e1\u00e3\u0007\u0002\u0000\u0000\u00e2\u00e1\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e50\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e9\u0005-\u0000"+
		"\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000"+
		"\u0000\u00e9\u00eb\u0001\u0000\u0000\u0000\u00ea\u00ec\u0007\u0003\u0000"+
		"\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000"+
		"\u0000\u00ee\u00f5\u0001\u0000\u0000\u0000\u00ef\u00f1\u0005.\u0000\u0000"+
		"\u00f0\u00f2\u0007\u0003\u0000\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f6\u0001\u0000\u0000\u0000"+
		"\u00f5\u00ef\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000"+
		"\u00f62\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005,\u0000\u0000\u00f84"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005;\u0000\u0000\u00fa6\u0001\u0000"+
		"\u0000\u0000\u00fb\u00fc\u0005(\u0000\u0000\u00fc8\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fe\u0005)\u0000\u0000\u00fe:\u0001\u0000\u0000\u0000\u00ff\u0100"+
		"\u0005:\u0000\u0000\u0100<\u0001\u0000\u0000\u0000\u0101\u0102\u0005A"+
		"\u0000\u0000\u0102\u0103\u0005N\u0000\u0000\u0103\u0104\u0005D\u0000\u0000"+
		"\u0104>\u0001\u0000\u0000\u0000\u0105\u0106\u0005O\u0000\u0000\u0106\u0107"+
		"\u0005R\u0000\u0000\u0107@\u0001\u0000\u0000\u0000\u0108\u0109\u0005N"+
		"\u0000\u0000\u0109\u010a\u0005O\u0000\u0000\u010a\u010b\u0005T\u0000\u0000"+
		"\u010bB\u0001\u0000\u0000\u0000\u010c\u0110\u0005\"\u0000\u0000\u010d"+
		"\u010f\u0007\u0004\u0000\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010f"+
		"\u0112\u0001\u0000\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110"+
		"\u0111\u0001\u0000\u0000\u0000\u0111\u0113\u0001\u0000\u0000\u0000\u0112"+
		"\u0110\u0001\u0000\u0000\u0000\u0113\u0114\u0005\"\u0000\u0000\u0114D"+
		"\u0001\u0000\u0000\u0000\u0115\u0116\u0007\u0005\u0000\u0000\u0116\u0117"+
		"\u0001\u0000\u0000\u0000\u0117\u0118\u0006\"\u0000\u0000\u0118F\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0005V\u0000\u0000\u011a\u011b\u0005e\u0000"+
		"\u0000\u011b\u011c\u0005r\u0000\u0000\u011c\u011d\u0005d\u0000\u0000\u011d"+
		"\u011e\u0005a\u0000\u0000\u011e\u011f\u0005d\u0000\u0000\u011f\u0120\u0005"+
		"e\u0000\u0000\u0120\u0121\u0005i\u0000\u0000\u0121\u0122\u0005r\u0000"+
		"\u0000\u0122\u0129\u0005o\u0000\u0000\u0123\u0124\u0005F\u0000\u0000\u0124"+
		"\u0125\u0005a\u0000\u0000\u0125\u0126\u0005l\u0000\u0000\u0126\u0127\u0005"+
		"s\u0000\u0000\u0127\u0129\u0005o\u0000\u0000\u0128\u0119\u0001\u0000\u0000"+
		"\u0000\u0128\u0123\u0001\u0000\u0000\u0000\u0129H\u0001\u0000\u0000\u0000"+
		"\u012a\u012e\u0007\u0006\u0000\u0000\u012b\u012d\u0007\u0002\u0000\u0000"+
		"\u012c\u012b\u0001\u0000\u0000\u0000\u012d\u0130\u0001\u0000\u0000\u0000"+
		"\u012e\u012c\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000"+
		"\u012fJ\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u000e"+
		"\u0000\u00d2\u00de\u00e2\u00e4\u00e8\u00ed\u00f3\u00f5\u010e\u0110\u0128"+
		"\u012c\u012e\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}