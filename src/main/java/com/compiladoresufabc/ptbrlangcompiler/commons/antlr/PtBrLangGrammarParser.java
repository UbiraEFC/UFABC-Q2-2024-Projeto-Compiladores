// Generated from PtBrLangGrammar.g4 by ANTLR 4.13.2
package com.compiladoresufabc.ptbrlangcompiler.commons.antlr;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import com.compiladoresufabc.ptbrlangcompiler.domains.*;
import com.compiladoresufabc.ptbrlangcompiler.commons.errors.*;
import com.compiladoresufabc.ptbrlangcompiler.commons.generator.*;
import com.compiladoresufabc.ptbrlangcompiler.runtime.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PtBrLangGrammarParser extends Parser {
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
	public static final int
		RULE_programa = 0, RULE_declaravar = 1, RULE_comando = 2, RULE_cmdIF = 3, 
		RULE_cmdWhile = 4, RULE_cmdWhileReverse = 5, RULE_cmdAttrib = 6, RULE_cmdLeitura = 7, 
		RULE_cmdEscrita = 8, RULE_expr = 9, RULE_exprl = 10, RULE_termo = 11, 
		RULE_termol = 12, RULE_fator = 13, RULE_exprList = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaravar", "comando", "cmdIF", "cmdWhile", "cmdWhileReverse", 
			"cmdAttrib", "cmdLeitura", "cmdEscrita", "expr", "exprl", "termo", "termol", 
			"fator", "exprList"
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

	@Override
	public String getGrammarFileName() { return "PtBrLangGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public PtBrLangGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(PtBrLangGrammarParser.CLASS, 0); }
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
			match(CLASS);
			 program.setName(_input.LT(-1).getText());
			                                stack.push(new ArrayList<Command>());
			                              
			setState(34); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(33);
				declaravar();
				}
				}
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(38);
			match(T__1);
			setState(40); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(39);
				comando();
				}
				}
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16998912L) != 0) );
			setState(44);
			match(T__2);
			setState(45);
			match(T__3);

			                  program.setSymbolTable(symbolTable);
			                  program.setCommandList(stack.pop());
			                  checkUnusedVariables();
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(PtBrLangGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PtBrLangGrammarParser.ID, i);
		}
		public TerminalNode DP() { return getToken(PtBrLangGrammarParser.DP, 0); }
		public TerminalNode PV() { return getToken(PtBrLangGrammarParser.PV, 0); }
		public List<TerminalNode> VIRG() { return getTokens(PtBrLangGrammarParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(PtBrLangGrammarParser.VIRG, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(T__4);
			 currentDecl.clear(); 
			setState(50);
			match(ID);

			          if (isDeclared(_input.LT(-1).getText())) {
			              throw new SemanticException("Variable '" + _input.LT(-1).getText() + "' already declared at line " 
			              + _input.LT(-1).getLine() + ", column " + _input.LT(-1).getCharPositionInLine() + ".");
			          }
			          currentDecl.add(new Var(_input.LT(-1).getText()));
			      
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(52);
				match(VIRG);
				setState(53);
				match(ID);

				          if (isDeclared(_input.LT(-1).getText())) {
				              throw new SemanticException("Variable '" + _input.LT(-1).getText() + "' already declared at line " 
				              + _input.LT(-1).getLine() + ", column " + _input.LT(-1).getCharPositionInLine() + ".");
				          }
				          currentDecl.add(new Var(_input.LT(-1).getText()));
				        
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(DP);
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(61);
				match(T__5);
				currentType = Types.NUMBER;
				}
				break;
			case T__6:
				{
				setState(63);
				match(T__6);
				currentType = Types.TEXT;
				}
				break;
			case T__7:
				{
				setState(65);
				match(T__7);
				currentType = Types.BOOL;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(70);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdIFContext cmdIF() {
			return getRuleContext(CmdIFContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdWhileReverseContext cmdWhileReverse() {
			return getRuleContext(CmdWhileReverseContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comando);
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				cmdAttrib();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				cmdLeitura();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				cmdIF();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				cmdWhile();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(77);
				cmdWhileReverse();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIFContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(PtBrLangGrammarParser.AP, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode FP() { return getToken(PtBrLangGrammarParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdIFContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIF; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdIF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdIF(this);
		}
	}

	public final CmdIFContext cmdIF() throws RecognitionException {
		CmdIFContext _localctx = new CmdIFContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cmdIF);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                      exprList = new ArrayList<>();
			                      currentIfCommand = new IfCommand();
			                    
			setState(82);
			match(AP);
			setState(83);
			exprList();

							          for (String var : exprList) {
							              if (symbolTable.containsKey(var)) {
							                  symbolTable.get(var).setUsed(true);
							              }
							          }
							          
							        if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
										throw new SemanticException("Missing or invalid condition in 'if' statement at line " + _input.LT(1).getLine() + ".");
									}
							      
			setState(85);
			match(FP);

			                     if (exprList.size() == 1 && leftType != Types.BOOL) {
			                        throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line "
			                        + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			                     }
			                     currentIfCommand.setExpressions(exprList);
			                   
			setState(87);
			match(T__9);
			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(88);
				comando();
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16998912L) != 0) );

			                  currentIfCommand.setTrueList(stack.pop());
			               
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(94);
				match(T__10);
				 stack.push(new ArrayList<Command>()); 
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(96);
					comando();
					}
					}
					setState(99); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16998912L) != 0) );

				                   currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(105);
			match(T__11);

			                   stack.peek().add(currentIfCommand);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(PtBrLangGrammarParser.AP, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode FP() { return getToken(PtBrLangGrammarParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__12);
			 stack.push(new ArrayList<Command>());
			                      exprList = new ArrayList<>();
			                      whileCommand = new WhileCommand(false);
			                    
			setState(110);
			match(AP);
			setState(111);
			exprList();

							          for (String var : exprList) {
							              if (symbolTable.containsKey(var)) {
							                  symbolTable.get(var).setUsed(true);
							              }
							          }
							          
							        if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
							          throw new SemanticException("Missing or invalid condition in 'while' statement at line " + _input.LT(1).getLine() + ".");
							        }
							      
			setState(113);
			match(FP);

			                                    if (exprList.size() == 1 && leftType != Types.BOOL) {
			                                       throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			                                    }
			                                    whileCommand.setExpressions(exprList);
			                                
			setState(115);
			match(T__13);
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				comando();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16998912L) != 0) );
			whileCommand.setTrueList(stack.pop());
			setState(122);
			match(T__14);

			                               stack.peek().add(whileCommand);
			                               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileReverseContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(PtBrLangGrammarParser.AP, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode FP() { return getToken(PtBrLangGrammarParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdWhileReverseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhileReverse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdWhileReverse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdWhileReverse(this);
		}
	}

	public final CmdWhileReverseContext cmdWhileReverse() throws RecognitionException {
		CmdWhileReverseContext _localctx = new CmdWhileReverseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdWhileReverse);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__13);
			 stack.push(new ArrayList<Command>());
			          exprList = new ArrayList<>();
			          whileCommand = new WhileCommand(true);
			      
			setState(128); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(127);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(130); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			whileCommand.setTrueList(stack.pop());
			setState(133);
			match(T__12);
			 strExpr = "";
			setState(135);
			match(AP);
			setState(136);
			exprList();

				          for (String var : exprList) {
				              if (symbolTable.containsKey(var)) {
				                  symbolTable.get(var).setUsed(true);
				              }
				          }
				          
				        if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
							throw new SemanticException("Missing or invalid condition in 'do-while' statement at line " + _input.LT(1).getLine() + ".");
						}
				      
			setState(138);
			match(FP);

			                        if (exprList.size() == 1 && leftType != Types.BOOL) {
			                           throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			                        }
			                        whileCommand.setExpressions(exprList);
			                    
			setState(140);
			match(T__14);

			                   stack.peek().add(whileCommand);
			                 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PtBrLangGrammarParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(PtBrLangGrammarParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(PtBrLangGrammarParser.PV, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(ID);

			          strExpr = "";
			          if (!isDeclared(_input.LT(-1).getText())) {
			              throw new SemanticException("Undeclared Variable During assignment: " + _input.LT(-1).getText());
			          }
			          symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			          symbolTable.get(_input.LT(-1).getText()).setUsed(true);
			          leftType = symbolTable.get(_input.LT(-1).getText()).getType();
			          atribCommand = new AtribCommand(symbolTable.get(_input.LT(-1).getText()));
			      
			setState(145);
			match(OP_AT);

			          strOp = _input.LT(-1).getText();
			          atribCommand.setStrOp(strOp);
			      
			setState(147);
			expr();

			          atribCommand.setExprString(strExpr);

			          if (strOp.equalsIgnoreCase("++") || strOp.equalsIgnoreCase("--")) {
			              System.out.println("Left  Side Expression Type = " + leftType);
			              if (leftType != Types.NUMBER) {
			                  throw new SemanticException("Operator " + strOp + " is only allowed for numeric variables at line "
			                      + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			              }
			              atribCommand.setExprString(null);
			          }

			          else if (strOp.equalsIgnoreCase("+=") || strOp.equalsIgnoreCase("-=")) {
			              System.out.println("Left Side Expression Type = " + leftType);
			              System.out.println("Right Side Expression Type = " + rightType);
			              if (leftType != Types.NUMBER || rightType != Types.NUMBER) {
			                  throw new SemanticException("Operator " + strOp + " requires both sides to be numeric at line "
			                      + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			              }
			              stack.peek().add(atribCommand);
			          }

			          else {
			              System.out.println("Left Side Expression Type = " + leftType);
			              System.out.println("Right Side Expression Type = " + rightType);

			              if (!((leftType == Types.NUMBER && rightType == Types.NUMBER) ||
			                    (leftType == Types.TEXT && rightType == Types.TEXT) ||
			                    (leftType == Types.BOOL && rightType == Types.BOOL))) {
			                  throw new SemanticException("Type mismatch: cannot assign '" + rightType + "' to variable of type '" + leftType + "' at line "
			                      + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
			              }
			              stack.peek().add(atribCommand);
			          }
			      
			setState(149);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(PtBrLangGrammarParser.AP, 0); }
		public TerminalNode ID() { return getToken(PtBrLangGrammarParser.ID, 0); }
		public TerminalNode FP() { return getToken(PtBrLangGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(PtBrLangGrammarParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__15);
			setState(152);
			match(AP);
			setState(153);
			match(ID);

			            if (!isDeclared(_input.LT(-1).getText())) {
			                throw new SemanticException("Undeclared Variable During reading: " + _input.LT(-1).getText());
			            }
			            symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			            symbolTable.get(_input.LT(-1).getText()).setUsed(true);
			            Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
			            stack.peek().add(cmdRead);
			        
			setState(155);
			match(FP);
			setState(156);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(PtBrLangGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(PtBrLangGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(PtBrLangGrammarParser.PV, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__16);
			setState(159);
			match(AP);
			{
			setState(160);
			termo();
			 
								        String content = _input.LT(-1).getText();
								            
								        if (!content.startsWith("\"")) {
								            Types varType = symbolTable.get(content).getType();
								            Command cmdWrite = new WriteCommand(content, varType);
								            stack.peek().add(cmdWrite);
								        } else {
								            Command cmdWrite = new WriteCommand(content, Types.TEXT);
								            stack.peek().add(cmdWrite);
								        }
								    
			}
			setState(163);
			match(FP);
			setState(164);
			match(PV);
			 rightType = null;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Types type;
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public List<ExprlContext> exprl() {
			return getRuleContexts(ExprlContext.class);
		}
		public ExprlContext exprl(int i) {
			return getRuleContext(ExprlContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			termo();

			        ((ExprContext)_localctx).type =  rightType;
			      
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(169);
				exprl();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public Types type;
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode OP_SUM() { return getToken(PtBrLangGrammarParser.OP_SUM, 0); }
		public TerminalNode OP_SUB() { return getToken(PtBrLangGrammarParser.OP_SUB, 0); }
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !(_la==OP_SUM || _la==OP_SUB) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			          strExpr += _input.LT(-1).getText();
			          BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
			          bin.setLeft(exprStack.pop());
			          exprStack.push(bin);
			      
			setState(177);
			termo();

			          AbstractExpression top = exprStack.pop();
			          BinaryExpression root = (BinaryExpression)exprStack.pop();
			          root.setRight(top);
			          exprStack.push(root);

			          if (leftType != rightType) {
			            if (!(leftType == Types.BOOL && rightType == null))
			              throw new SemanticException("Type mismatch: incompatible types '" + leftType + "' and '" + rightType + "' in operation at line "
			              + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ". Expression: " + _input.LT(1).getText());
			          }
			          leftType = rightType;
			          System.out.println("Expression value " + root.evaluate());
			          System.out.println(root.toJSON());
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public Types type;
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public List<TermolContext> termol() {
			return getRuleContexts(TermolContext.class);
		}
		public TermolContext termol(int i) {
			return getRuleContext(TermolContext.class,i);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termo);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			fator();

					strExpr += _input.LT(-1).getText();
			        ((TermoContext)_localctx).type =  rightType;
			      
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(182);
					termol(_localctx.type);
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermolContext extends ParserRuleContext {
		public Types inheritedType;
		public Types type;
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode OP_MUL() { return getToken(PtBrLangGrammarParser.OP_MUL, 0); }
		public TerminalNode OP_DIV() { return getToken(PtBrLangGrammarParser.OP_DIV, 0); }
		public TermolContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TermolContext(ParserRuleContext parent, int invokingState, Types inheritedType) {
			super(parent, invokingState);
			this.inheritedType = inheritedType;
		}
		@Override public int getRuleIndex() { return RULE_termol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterTermol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitTermol(this);
		}
	}

	public final TermolContext termol(Types inheritedType) throws RecognitionException {
		TermolContext _localctx = new TermolContext(_ctx, getState(), inheritedType);
		enterRule(_localctx, 24, RULE_termol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if ( !(_la==OP_MUL || _la==OP_DIV) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			          strExpr += _input.LT(-1).getText();
			          BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
			          bin.setLeft(exprStack.pop());
			          exprStack.push(bin);
			      
			setState(190);
			termo();

			          AbstractExpression rightExpr = exprStack.pop();
			          BinaryExpression root = (BinaryExpression) exprStack.pop();
			          root.setRight(rightExpr);
			          exprStack.push(root);

			          if (leftType != rightType) {
			            if (!(leftType == Types.BOOL && rightType == null))
			             throw new SemanticException("Type mismatch: incompatible types '" + leftType + "' and '" + rightType + "' in operation at line "
			             + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ". Expression: " + _input.LT(1).getText());
			          }
			          ((TermolContext)_localctx).type =  rightType;
			          System.out.println("Expression value " + root.evaluate());
			          System.out.println(root.toJSON());
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public Types type;
		public Token NUM;
		public Token TEXTO;
		public Token BOOL;
		public TerminalNode ID() { return getToken(PtBrLangGrammarParser.ID, 0); }
		public TerminalNode NUM() { return getToken(PtBrLangGrammarParser.NUM, 0); }
		public TerminalNode TEXTO() { return getToken(PtBrLangGrammarParser.TEXTO, 0); }
		public TerminalNode BOOL() { return getToken(PtBrLangGrammarParser.BOOL, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fator);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(ID);

				          if (!isDeclared(_input.LT(-1).getText())) {
				              throw new SemanticException("Undeclared Variable During term: " + _input.LT(-1).getText());
				          }
				          if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()) {
				              throw new SemanticException("Variable " + _input.LT(-1).getText() + " has no value assigned");
				          }
				          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				      
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				((FatorContext)_localctx).NUM = match(NUM);

				          exprStack.push(new UnaryExpression(Double.parseDouble((((FatorContext)_localctx).NUM!=null?((FatorContext)_localctx).NUM.getText():null))));
				          rightType = Types.NUMBER;
				      
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				((FatorContext)_localctx).TEXTO = match(TEXTO);

				          exprStack.push(new UnaryExpression((((FatorContext)_localctx).TEXTO!=null?((FatorContext)_localctx).TEXTO.getText():null)));  // Texto como string literal
				          rightType = Types.TEXT;
				      
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				((FatorContext)_localctx).BOOL = match(BOOL);

				          exprStack.push(new UnaryExpression(Boolean.parseBoolean((((FatorContext)_localctx).BOOL!=null?((FatorContext)_localctx).BOOL.getText():null))));
				          rightType = Types.BOOL;
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public ExprContext e;
		public ExprContext e2;
		public Token op;
		public ExprContext e3;
		public ExprContext e4;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(PtBrLangGrammarParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(PtBrLangGrammarParser.OPREL, i);
		}
		public List<TerminalNode> AND() { return getTokens(PtBrLangGrammarParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(PtBrLangGrammarParser.AND, i);
		}
		public List<TerminalNode> OR() { return getTokens(PtBrLangGrammarParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(PtBrLangGrammarParser.OR, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PtBrLangGrammarListener ) ((PtBrLangGrammarListener)listener).exitExprList(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			        leftType = null;
			        rightType = null;
			        ArrayList<String> auxList = new ArrayList<>();
			      
			setState(204);
			((ExprListContext)_localctx).e = expr();

			        exprList.add((((ExprListContext)_localctx).e!=null?_input.getText(((ExprListContext)_localctx).e.start,((ExprListContext)_localctx).e.stop):null));
			        auxList.add((((ExprListContext)_localctx).e!=null?_input.getText(((ExprListContext)_localctx).e.start,((ExprListContext)_localctx).e.stop):null));
			        leftType = ((ExprListContext)_localctx).e.type;
			      
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6450839552L) != 0)) {
				{
				setState(229);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPREL:
					{
					{
					setState(206);
					match(OPREL);

					            exprList.add(_input.LT(-1).getText());
					            
					            if (_input.LT(-1).getText().matches("[<>]=?") && leftType != Types.NUMBER) {
					                throw new SemanticException("Operator '" + _input.LT(-1).getText() + "' can only be applied to numeric types at line " + _input.LT(1).getLine());
					            }
					          
					setState(208);
					((ExprListContext)_localctx).e2 = expr();

					            exprList.add((((ExprListContext)_localctx).e2!=null?_input.getText(((ExprListContext)_localctx).e2.start,((ExprListContext)_localctx).e2.stop):null));
					            auxList.add((((ExprListContext)_localctx).e2!=null?_input.getText(((ExprListContext)_localctx).e2.start,((ExprListContext)_localctx).e2.stop):null));
					            rightType = ((ExprListContext)_localctx).e2.type;

					            if (leftType != rightType) {
					                if (!(leftType == Types.BOOL && rightType == null))
					                    throw new SemanticException("Type mismatch: cannot compare '" + leftType + "' with '" + rightType + "' in expression '" +  (((ExprListContext)_localctx).e!=null?_input.getText(((ExprListContext)_localctx).e.start,((ExprListContext)_localctx).e.stop):null) + "' and '" +  (((ExprListContext)_localctx).e2!=null?_input.getText(((ExprListContext)_localctx).e2.start,((ExprListContext)_localctx).e2.stop):null) + "' at line "
					                    + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
					            }

					          
					}

					        if (auxList.size() == 1 && leftType != Types.BOOL) {
					            throw new SemanticException("Single expression '" + auxList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
					        }
					      
					}
					break;
				case AND:
				case OR:
					{
					setState(215);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case AND:
						{
						setState(213);
						((ExprListContext)_localctx).op = match(AND);
						}
						break;
					case OR:
						{
						setState(214);
						((ExprListContext)_localctx).op = match(OR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}

					            exprList.add((((ExprListContext)_localctx).op!=null?((ExprListContext)_localctx).op.getText():null));
					            auxList.clear();
					        
					setState(218);
					((ExprListContext)_localctx).e3 = expr();

					            exprList.add((((ExprListContext)_localctx).e3!=null?_input.getText(((ExprListContext)_localctx).e3.start,((ExprListContext)_localctx).e3.stop):null));
					            auxList.add((((ExprListContext)_localctx).e3!=null?_input.getText(((ExprListContext)_localctx).e3.start,((ExprListContext)_localctx).e3.stop):null));
					            leftType = ((ExprListContext)_localctx).e3.type;
					        
					setState(225);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						setState(220);
						match(OPREL);

						            exprList.add(_input.LT(-1).getText());
						            
						            if (_input.LT(-1).getText().matches("[<>]=?") && leftType != Types.NUMBER) {
						                throw new SemanticException("Operator '" + _input.LT(-1).getText() + "' can only be applied to numeric types at line " + _input.LT(1).getLine());
						            }
						          
						setState(222);
						((ExprListContext)_localctx).e4 = expr();

						            exprList.add((((ExprListContext)_localctx).e4!=null?_input.getText(((ExprListContext)_localctx).e4.start,((ExprListContext)_localctx).e4.stop):null));
						            auxList.add((((ExprListContext)_localctx).e4!=null?_input.getText(((ExprListContext)_localctx).e4.start,((ExprListContext)_localctx).e4.stop):null));
						            rightType = ((ExprListContext)_localctx).e4.type;

						            if (leftType != rightType) {
						                if (!(leftType == Types.BOOL && rightType == null))
						                    throw new SemanticException("Type mismatch: cannot compare '" + leftType + "' with '" + rightType + "' in expression '" +  (((ExprListContext)_localctx).e3!=null?_input.getText(((ExprListContext)_localctx).e3.start,((ExprListContext)_localctx).e3.stop):null) + "' and '" +  (((ExprListContext)_localctx).e4!=null?_input.getText(((ExprListContext)_localctx).e4.start,((ExprListContext)_localctx).e4.stop):null) + "' at line "
						                    + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
						            }

						          
						}
						break;
					}

					        if (auxList.size() == 1 && leftType != Types.BOOL) {
					            throw new SemanticException("Single expression '" + auxList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
					        }
					      
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u00eb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0004\u0000#\b\u0000\u000b\u0000\f\u0000$\u0001"+
		"\u0000\u0001\u0000\u0004\u0000)\b\u0000\u000b\u0000\f\u0000*\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00018\b\u0001"+
		"\n\u0001\f\u0001;\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001D\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002O\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0004\u0003Z\b\u0003\u000b\u0003\f\u0003[\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0004\u0003b\b\u0003\u000b\u0003\f\u0003"+
		"c\u0001\u0003\u0001\u0003\u0003\u0003h\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004v\b\u0004"+
		"\u000b\u0004\f\u0004w\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005\u0081\b\u0005\u000b\u0005"+
		"\f\u0005\u0082\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0005\t\u00ab"+
		"\b\t\n\t\f\t\u00ae\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00b8\b\u000b\n\u000b\f\u000b\u00bb"+
		"\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00ca\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00d8\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00e2\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u00e6\b\u000e\n\u000e\f\u000e\u00e9\t\u000e\u0001"+
		"\u000e\u0000\u0000\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u0000\u0002\u0001\u0000\u0012\u0013\u0001"+
		"\u0000\u0014\u0015\u00f3\u0000\u001e\u0001\u0000\u0000\u0000\u00020\u0001"+
		"\u0000\u0000\u0000\u0004N\u0001\u0000\u0000\u0000\u0006P\u0001\u0000\u0000"+
		"\u0000\bl\u0001\u0000\u0000\u0000\n}\u0001\u0000\u0000\u0000\f\u008f\u0001"+
		"\u0000\u0000\u0000\u000e\u0097\u0001\u0000\u0000\u0000\u0010\u009e\u0001"+
		"\u0000\u0000\u0000\u0012\u00a7\u0001\u0000\u0000\u0000\u0014\u00af\u0001"+
		"\u0000\u0000\u0000\u0016\u00b4\u0001\u0000\u0000\u0000\u0018\u00bc\u0001"+
		"\u0000\u0000\u0000\u001a\u00c9\u0001\u0000\u0000\u0000\u001c\u00cb\u0001"+
		"\u0000\u0000\u0000\u001e\u001f\u0005\u0001\u0000\u0000\u001f \u0005%\u0000"+
		"\u0000 \"\u0006\u0000\uffff\uffff\u0000!#\u0003\u0002\u0001\u0000\"!\u0001"+
		"\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000"+
		"$%\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&(\u0005\u0002\u0000"+
		"\u0000\')\u0003\u0004\u0002\u0000(\'\u0001\u0000\u0000\u0000)*\u0001\u0000"+
		"\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,-\u0005\u0003\u0000\u0000-.\u0005\u0004\u0000\u0000"+
		"./\u0006\u0000\uffff\uffff\u0000/\u0001\u0001\u0000\u0000\u000001\u0005"+
		"\u0005\u0000\u000012\u0006\u0001\uffff\uffff\u000023\u0005\u0018\u0000"+
		"\u000039\u0006\u0001\uffff\uffff\u000045\u0005\u001a\u0000\u000056\u0005"+
		"\u0018\u0000\u000068\u0006\u0001\uffff\uffff\u000074\u0001\u0000\u0000"+
		"\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000"+
		"\u0000\u0000:<\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<C\u0005"+
		"\u001e\u0000\u0000=>\u0005\u0006\u0000\u0000>D\u0006\u0001\uffff\uffff"+
		"\u0000?@\u0005\u0007\u0000\u0000@D\u0006\u0001\uffff\uffff\u0000AB\u0005"+
		"\b\u0000\u0000BD\u0006\u0001\uffff\uffff\u0000C=\u0001\u0000\u0000\u0000"+
		"C?\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000"+
		"\u0000EF\u0006\u0001\uffff\uffff\u0000FG\u0005\u001b\u0000\u0000G\u0003"+
		"\u0001\u0000\u0000\u0000HO\u0003\f\u0006\u0000IO\u0003\u000e\u0007\u0000"+
		"JO\u0003\u0010\b\u0000KO\u0003\u0006\u0003\u0000LO\u0003\b\u0004\u0000"+
		"MO\u0003\n\u0005\u0000NH\u0001\u0000\u0000\u0000NI\u0001\u0000\u0000\u0000"+
		"NJ\u0001\u0000\u0000\u0000NK\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000"+
		"\u0000NM\u0001\u0000\u0000\u0000O\u0005\u0001\u0000\u0000\u0000PQ\u0005"+
		"\t\u0000\u0000QR\u0006\u0003\uffff\uffff\u0000RS\u0005\u001c\u0000\u0000"+
		"ST\u0003\u001c\u000e\u0000TU\u0006\u0003\uffff\uffff\u0000UV\u0005\u001d"+
		"\u0000\u0000VW\u0006\u0003\uffff\uffff\u0000WY\u0005\n\u0000\u0000XZ\u0003"+
		"\u0004\u0002\u0000YX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000"+
		"\u0000]g\u0006\u0003\uffff\uffff\u0000^_\u0005\u000b\u0000\u0000_a\u0006"+
		"\u0003\uffff\uffff\u0000`b\u0003\u0004\u0002\u0000a`\u0001\u0000\u0000"+
		"\u0000bc\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000"+
		"\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0006\u0003\uffff\uffff\u0000"+
		"fh\u0001\u0000\u0000\u0000g^\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0001\u0000\u0000\u0000ij\u0005\f\u0000\u0000jk\u0006\u0003\uffff"+
		"\uffff\u0000k\u0007\u0001\u0000\u0000\u0000lm\u0005\r\u0000\u0000mn\u0006"+
		"\u0004\uffff\uffff\u0000no\u0005\u001c\u0000\u0000op\u0003\u001c\u000e"+
		"\u0000pq\u0006\u0004\uffff\uffff\u0000qr\u0005\u001d\u0000\u0000rs\u0006"+
		"\u0004\uffff\uffff\u0000su\u0005\u000e\u0000\u0000tv\u0003\u0004\u0002"+
		"\u0000ut\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wu\u0001\u0000"+
		"\u0000\u0000wx\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0006"+
		"\u0004\uffff\uffff\u0000z{\u0005\u000f\u0000\u0000{|\u0006\u0004\uffff"+
		"\uffff\u0000|\t\u0001\u0000\u0000\u0000}~\u0005\u000e\u0000\u0000~\u0080"+
		"\u0006\u0005\uffff\uffff\u0000\u007f\u0081\u0003\u0004\u0002\u0000\u0080"+
		"\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0006\u0005\uffff\uffff\u0000"+
		"\u0085\u0086\u0005\r\u0000\u0000\u0086\u0087\u0006\u0005\uffff\uffff\u0000"+
		"\u0087\u0088\u0005\u001c\u0000\u0000\u0088\u0089\u0003\u001c\u000e\u0000"+
		"\u0089\u008a\u0006\u0005\uffff\uffff\u0000\u008a\u008b\u0005\u001d\u0000"+
		"\u0000\u008b\u008c\u0006\u0005\uffff\uffff\u0000\u008c\u008d\u0005\u000f"+
		"\u0000\u0000\u008d\u008e\u0006\u0005\uffff\uffff\u0000\u008e\u000b\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0005\u0018\u0000\u0000\u0090\u0091\u0006"+
		"\u0006\uffff\uffff\u0000\u0091\u0092\u0005\u0016\u0000\u0000\u0092\u0093"+
		"\u0006\u0006\uffff\uffff\u0000\u0093\u0094\u0003\u0012\t\u0000\u0094\u0095"+
		"\u0006\u0006\uffff\uffff\u0000\u0095\u0096\u0005\u001b\u0000\u0000\u0096"+
		"\r\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u0010\u0000\u0000\u0098\u0099"+
		"\u0005\u001c\u0000\u0000\u0099\u009a\u0005\u0018\u0000\u0000\u009a\u009b"+
		"\u0006\u0007\uffff\uffff\u0000\u009b\u009c\u0005\u001d\u0000\u0000\u009c"+
		"\u009d\u0005\u001b\u0000\u0000\u009d\u000f\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\u0011\u0000\u0000\u009f\u00a0\u0005\u001c\u0000\u0000\u00a0"+
		"\u00a1\u0003\u0016\u000b\u0000\u00a1\u00a2\u0006\b\uffff\uffff\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u001d\u0000\u0000\u00a4"+
		"\u00a5\u0005\u001b\u0000\u0000\u00a5\u00a6\u0006\b\uffff\uffff\u0000\u00a6"+
		"\u0011\u0001\u0000\u0000\u0000\u00a7\u00a8\u0003\u0016\u000b\u0000\u00a8"+
		"\u00ac\u0006\t\uffff\uffff\u0000\u00a9\u00ab\u0003\u0014\n\u0000\u00aa"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ae\u0001\u0000\u0000\u0000\u00ac"+
		"\u00aa\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad"+
		"\u0013\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0007\u0000\u0000\u0000\u00b0\u00b1\u0006\n\uffff\uffff\u0000\u00b1"+
		"\u00b2\u0003\u0016\u000b\u0000\u00b2\u00b3\u0006\n\uffff\uffff\u0000\u00b3"+
		"\u0015\u0001\u0000\u0000\u0000\u00b4\u00b5\u0003\u001a\r\u0000\u00b5\u00b9"+
		"\u0006\u000b\uffff\uffff\u0000\u00b6\u00b8\u0003\u0018\f\u0000\u00b7\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u0017"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0007\u0001\u0000\u0000\u00bd\u00be\u0006\f\uffff\uffff\u0000\u00be\u00bf"+
		"\u0003\u0016\u000b\u0000\u00bf\u00c0\u0006\f\uffff\uffff\u0000\u00c0\u0019"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u0018\u0000\u0000\u00c2\u00ca"+
		"\u0006\r\uffff\uffff\u0000\u00c3\u00c4\u0005\u0019\u0000\u0000\u00c4\u00ca"+
		"\u0006\r\uffff\uffff\u0000\u00c5\u00c6\u0005\"\u0000\u0000\u00c6\u00ca"+
		"\u0006\r\uffff\uffff\u0000\u00c7\u00c8\u0005$\u0000\u0000\u00c8\u00ca"+
		"\u0006\r\uffff\uffff\u0000\u00c9\u00c1\u0001\u0000\u0000\u0000\u00c9\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c5\u0001\u0000\u0000\u0000\u00c9\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ca\u001b\u0001\u0000\u0000\u0000\u00cb\u00cc"+
		"\u0006\u000e\uffff\uffff\u0000\u00cc\u00cd\u0003\u0012\t\u0000\u00cd\u00e7"+
		"\u0006\u000e\uffff\uffff\u0000\u00ce\u00cf\u0005\u0017\u0000\u0000\u00cf"+
		"\u00d0\u0006\u000e\uffff\uffff\u0000\u00d0\u00d1\u0003\u0012\t\u0000\u00d1"+
		"\u00d2\u0006\u000e\uffff\uffff\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0006\u000e\uffff\uffff\u0000\u00d4\u00e6\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d8\u0005\u001f\u0000\u0000\u00d6\u00d8\u0005 \u0000\u0000"+
		"\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da\u0006\u000e\uffff\uffff"+
		"\u0000\u00da\u00db\u0003\u0012\t\u0000\u00db\u00e1\u0006\u000e\uffff\uffff"+
		"\u0000\u00dc\u00dd\u0005\u0017\u0000\u0000\u00dd\u00de\u0006\u000e\uffff"+
		"\uffff\u0000\u00de\u00df\u0003\u0012\t\u0000\u00df\u00e0\u0006\u000e\uffff"+
		"\uffff\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00dc\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0006\u000e\uffff\uffff\u0000\u00e4\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e5\u00ce\u0001\u0000\u0000\u0000\u00e5\u00d7\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u001d\u0001"+
		"\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u0011$*9CN[cgw"+
		"\u0082\u00ac\u00b9\u00c9\u00d7\u00e1\u00e5\u00e7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}