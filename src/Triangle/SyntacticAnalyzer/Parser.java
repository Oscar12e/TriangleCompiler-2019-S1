/*
 * @(#)Parser.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.SyntacticAnalyzer;

import Triangle.AbstractSyntaxTrees.Package;
import Triangle.AbstractSyntaxTrees.*;
import Triangle.ErrorReporter;

public class Parser {

  private Scanner lexicalAnalyser;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePosition previousTokenPosition;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    lexicalAnalyser = lexer;
    errorReporter = reporter;
    previousTokenPosition = new SourcePosition();
  }

// accept checks whether the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.position;
      currentToken = lexicalAnalyser.scan();
    } else {
      syntacticError("\"%\" expected here but found " + currentToken.spelling + " instead." , Token.spell(tokenExpected));
    }
  }

  void acceptIt() {
    previousTokenPosition = currentToken.position;
    currentToken = lexicalAnalyser.scan();
  }

// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

  // <editor-fold defaultstate="collapsed" desc=" Programs ">
///////////////////////////////////////////////////////////////////////////////
//
// PROGRAMS
//
///////////////////////////////////////////////////////////////////////////////

  public Program parseProgram() {

    Program programAST = null;

    previousTokenPosition.start = 0;
    previousTokenPosition.finish = 0;
    currentToken = lexicalAnalyser.scan();

    try {
      //Packages here
      if (currentToken.kind == Token.PACKAGE){
        Package pAST = null;
        SourcePosition packPos = new SourcePosition();
        start(packPos);
        pAST = parsePackageDeclaration();
        accept(Token.SEMICOLON);
        while (currentToken.kind == Token.PACKAGE){
          Package pAST2 = parsePackageDeclaration();
          accept(Token.SEMICOLON);
          finish(packPos);
          pAST = new SequentialPackageDeclaration(pAST, pAST2, packPos);
        }

        Command cAST = parseCommand();
        programAST = new PackagedProgram(pAST, cAST, previousTokenPosition);
      }
      else {
        Command cAST = parseCommand();
        programAST = new SimpleProgram(cAST, previousTokenPosition);
      }

      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) { return null; }
    return programAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Packages ">
///////////////////////////////////////////////////////////////////////////////
//
// PACKAGES
//
///////////////////////////////////////////////////////////////////////////////


  Package parsePackageDeclaration() throws SyntaxError {
    Package packagesAST = null;

    SourcePosition packagePos = new SourcePosition();
    start(packagePos);

    acceptIt(); //Accept token
    Identifier pAST = parsePackageIdentifier();
    accept(Token.IS);
    Declaration dAST = parseDeclaration();
    accept(Token.END);
    finish(packagePos);

    packagesAST = new PackageDeclaration(pAST,dAST,packagePos);
    return packagesAST;
  }

  Identifier parsePackageIdentifier() throws SyntaxError {
    Identifier packagesAST = null;

    SourcePosition packagePos = new SourcePosition();
    start(packagePos);

    Identifier iAST = parseIdentifier();

    finish(packagePos);

    packagesAST = new PackageIdentifier(iAST.spelling, packagePos);
    return packagesAST;
  }

  Identifier parsePackageIdentifier(Identifier iAST) {
    Identifier packagesAST = null;

    SourcePosition packagePos = iAST.position;

    finish(packagePos);

    packagesAST = new PackageIdentifier(iAST.spelling, packagePos);
    return packagesAST;
  }

  Identifier parseLongIdentifier() throws SyntaxError {
    Identifier lAST = null;

    SourcePosition packagePos = new SourcePosition();
    start(packagePos);

    Identifier iAST = parseIdentifier();

    if (currentToken.kind == Token.DOLLAR) {
      acceptIt();
      Identifier pAST = parsePackageIdentifier(iAST);
      Identifier iAST2 = parseIdentifier();
      finish(packagePos);
      lAST = new LongIdentifier(pAST, iAST2.spelling, packagePos);
    } else {
      finish(packagePos);
      lAST = iAST;
    }

    return lAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Literals ">
///////////////////////////////////////////////////////////////////////////////
//
// LITERALS
//
///////////////////////////////////////////////////////////////////////////////

// parseIntegerLiteral parses an integer-literal, and constructs
// a leaf AST to represent it.

  IntegerLiteral parseIntegerLiteral() throws SyntaxError {
    IntegerLiteral IL = null;

    if (currentToken.kind == Token.INTLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      IL = new IntegerLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      IL = null;
      syntacticError("integer literal expected here", "");
    }
    return IL;
  }

// parseCharacterLiteral parses a character-literal, and constructs a leaf
// AST to represent it.

  CharacterLiteral parseCharacterLiteral() throws SyntaxError {
    CharacterLiteral CL = null;

    if (currentToken.kind == Token.CHARLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      CL = new CharacterLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      CL = null;
      syntacticError("character literal expected here", "");
    }
    return CL;
  }

// parseIdentifier parses an identifier, and constructs a leaf AST to
// represent it.

  Identifier parseIdentifier() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      I = null;
      syntacticError("identifier expected here", "");
    }
    return I;
  }

// parseOperator parses an operator, and constructs a leaf AST to
// represent it.

  Operator parseOperator() throws SyntaxError {
    Operator O = null;

    if (currentToken.kind == Token.OPERATOR) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      O = new Operator(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      O = null;
      syntacticError("operator expected here", "");
    }
    return O;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Commands ">

///////////////////////////////////////////////////////////////////////////////
//
// COMMANDS
//
///////////////////////////////////////////////////////////////////////////////

// parseCommand parses the command, and constructs an AST
// to represent its phrase structure.

  Command parseCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();

    start(commandPos);
    commandAST = parseSingleCommand();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Command c2AST = parseSingleCommand();
      finish(commandPos);
      commandAST = new SequentialCommand(commandAST, c2AST, commandPos);
    }
    return commandAST;
  }

  Command parseSingleCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();
    start(commandPos);

    switch (currentToken.kind) {

      case Token.IDENTIFIER:
        {
          Identifier iAST = parseLongIdentifier();
          if (currentToken.kind == Token.LPAREN) {
            acceptIt();
            ActualParameterSequence apsAST = parseActualParameterSequence();
            accept(Token.RPAREN);
            finish(commandPos);
            commandAST = new CallCommand(iAST, apsAST, commandPos);

          } else {
            Vname vAST = parseRestOfVarName(iAST);
            accept(Token.BECOMES);
            Expression eAST = parseExpression();
            finish(commandPos);
            commandAST = new AssignCommand(vAST, eAST, commandPos);
          }
        }
        break;

      case Token.LET:
        {
          acceptIt();
          Declaration dAST = parseDeclaration();
          accept(Token.IN);
          Command cAST = parseCommand();
          accept(Token.END);
          finish(commandPos);
          commandAST = new LetCommand(dAST, cAST, commandPos);
        }
        break;

      case Token.IF:
        {
          acceptIt();
          Expression eAST = parseExpression();
          accept(Token.THEN);
          Command c1AST = parseCommand();
          accept(Token.ELSE);
          Command c2AST = parseCommand();
          accept(Token.END);
          finish(commandPos);
          commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
        }
        break;

      case Token.PASS:
        {
          acceptIt();
          finish(commandPos);
          commandAST = new EmptyCommand(commandPos);
        }
        break;

      case Token.CHOOSE: {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.FROM);
        Cases cAST = parseCases();
        accept(Token.END);
        finish(commandPos);
        commandAST = new ChooseCommand(eAST, cAST, commandPos);
      }
        break;

      case Token.LOOP: {
        acceptIt();
        switch (currentToken.kind) {

          case Token.WHILE:
          case Token.UNTIL:
          {
            boolean isWhileCommand = Token.WHILE == currentToken.kind;
            acceptIt();
            Expression eAST = parseExpression();
            accept(Token.DO);
            Command cAST = parseCommand();
            accept(Token.END);
            finish(commandPos);
            commandAST = (isWhileCommand ? new WhileCommand(eAST, cAST, commandPos) : new UntilCommand(eAST, cAST, commandPos));
          }
          break;

          case Token.DO: {
            acceptIt();
            Command cAST = parseCommand();

            if (! (currentToken.kind == Token.WHILE || currentToken.kind == Token.UNTIL)){
              syntacticError("Found \"%\" where 'while' or 'Until' statement was expect.",
                      currentToken.spelling);
              break;
            }

            boolean isWhileCommand = Token.WHILE == currentToken.kind;

            acceptIt();
            Expression eAST = parseExpression();
            accept(Token.END);
            finish(commandPos);
            commandAST = (isWhileCommand ? new DoWhileCommand(eAST, cAST, commandPos) : new DoUntilCommand(eAST, cAST, commandPos));
          }
          break;

          case Token.FOR: {
            acceptIt();
            ForDeclaration fAST = parseForDeclaration();
            accept(Token.TO);
            Expression eAST = parseExpression();

            switch (currentToken.kind) {
              case Token.DO:{
                acceptIt();
                Command cAST = parseCommand();
                accept(Token.END);
                finish(commandPos);
                commandAST = new ForCommand(fAST, eAST, cAST, commandPos);
              }
              break;

              case Token.WHILE:
              case Token.UNTIL:
              {
                boolean isWhileCommand = Token.WHILE == currentToken.kind;
                acceptIt();
                Expression eAST2 = parseExpression();
                accept(Token.DO);
                Command cAST = parseCommand();
                accept(Token.END);
                finish(commandPos);
                commandAST = (isWhileCommand ?
                        new ForWhileCommand(fAST, eAST, eAST2, cAST, commandPos) :
                        new ForUntilCommand(fAST, eAST, eAST2, cAST, commandPos));
              }
              break;

              default: {
                syntacticError("Found \"%\" where 'do' or 'while' or 'until' statement was expect.",
                        currentToken.spelling);
              }
              break;
            }
          }
          break;

          default:
            syntacticError("Found \"%\" where 'do' or 'while' or 'until' statement was expect.",
                    currentToken.spelling);
            break;
        }
      }
      break;

      case Token.SEMICOLON:
      case Token.END:
      case Token.ELSE:
      case Token.IN:
      case Token.EOT:
        syntacticError("Found: \"%\". Expected the beginning of a command here.",
                currentToken.spelling);
        break;


      default:
        syntacticError("\"%\" cannot start a command",
          currentToken.spelling);
        break;

      }

    return commandAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Cases ">
///////////////////////////////////////////////////////////////////////////////
//
// CASES
//
///////////////////////////////////////////////////////////////////////////////

  /**
   * Parse all the cases that the user typed in the code.
   * @return
   * @throws SyntaxError
   */
  Cases parseCases() throws SyntaxError {
    Cases seqCasesAST = null;
    SourcePosition casesPos = new SourcePosition();

    start (casesPos);
    accept(Token.WHEN);
    seqCasesAST = parseCase();

    while (currentToken.kind == Token.WHEN){
      acceptIt();
      Cases caseAST = parseCase();
      finish(casesPos);
      seqCasesAST =  new SequentialCases(seqCasesAST, caseAST, casesPos);
    }

    if (currentToken.kind == Token.ELSE){
      acceptIt();
      Cases elseAst = parseElseCase();
      finish(casesPos);
      seqCasesAST = new SequentialCases(seqCasesAST, elseAst, casesPos);
    }


    return seqCasesAST;
  }

  Cases parseCase() throws SyntaxError {
    Cases caseAST = null;
    SourcePosition casePos = new SourcePosition();

    start (casePos);
    Expression literalsAST = parseCaseLiterals();
    accept(Token.THEN);

    Command cAST = parseCommand();
    finish(casePos);

    caseAST = new Case(literalsAST, cAST, casePos);

    return caseAST;
  }

  Cases parseElseCase() throws SyntaxError {
    Cases elseCaseAST = null;
    SourcePosition casePos = new SourcePosition();

    start (casePos);

    Command cAST = parseCommand();
    finish(casePos);
    elseCaseAST = new ElseCase(cAST, casePos);
    return elseCaseAST;
  }

  Expression parseCaseLiterals() throws SyntaxError {
    Expression caseAST = null; // in case there's a syntactic error

    SourcePosition casePos = new SourcePosition();
    start(casePos);

    Expression cAST = parseCaseRange();

    finish(casePos);
    caseAST = new CaseLiterals(cAST, casePos);
    while (currentToken.kind == Token.PIPE){
      acceptIt();
      cAST = parseCaseRange();
      Expression cAST2 = new CaseLiterals(cAST, casePos);
      finish(casePos);
      caseAST = new SequentialCaseLiterals(caseAST, cAST2, casePos);
    }

    return caseAST;
  }

  Expression parseCaseRange() throws SyntaxError {
    Expression caseAST = null; // in case there's a syntactic error

    SourcePosition casePos = new SourcePosition();
    start(casePos);

    caseAST = parseCaseLiteral();
    finish(casePos);

    if (currentToken.kind == Token.DOUBLEDOTS){ //Two range trees
      acceptIt();
      Expression rAst2 = parseCaseLiteral();
      finish(casePos);
      caseAST = new CaseRange(caseAST, rAst2, casePos);
    }

    return caseAST;
  }

  Expression parseCaseLiteral() throws SyntaxError {
    Expression caseAST = null; // in case there's a syntactic error
    SourcePosition casePos = new SourcePosition();
    start(casePos);

    if (!(currentToken.kind == Token.INTLITERAL || currentToken.kind == Token.CHARLITERAL))
      syntacticError("Int Literal or Char Literal expected here.", "");;

    Terminal lAst = (currentToken.kind == Token.INTLITERAL) ? parseIntegerLiteral() : parseCharacterLiteral();
    finish(casePos);
    caseAST = new CaseLiteral(lAst, casePos);
    return caseAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Proc-funcs ">
///////////////////////////////////////////////////////////////////////////////
//
// PROC-FUNC
//
///////////////////////////////////////////////////////////////////////////////
  ProcFuncs parseProcFuncs () throws SyntaxError {
    ProcFuncs procFuncsAST = null; // in case there's a syntactic error

    SourcePosition pfPos = new SourcePosition();
    start(pfPos);

    ProcFuncs pfAST1 = parseProcFunc();

    do {
      accept(Token.PIPE);
      ProcFuncs pfAST2 = parseProcFunc();
      finish(pfPos);
      pfAST1 = new SequentialProcFuncs(pfAST1, pfAST2, pfPos);
    } while (currentToken.kind == Token.PIPE);
    procFuncsAST = pfAST1;
    return procFuncsAST;
  }

  ProcFuncs parseProcFunc () throws SyntaxError {
    ProcFuncs procFuncsAST = null; // in case there's a syntactic error
    if (currentToken.kind != Token.PROC && currentToken.kind != Token.FUNC)
      syntacticError("proc or func expected here, but found instead &.", currentToken.spelling);

    SourcePosition pfPos = new SourcePosition();
    start(pfPos);

    boolean isProc = currentToken.kind == Token.PROC;
    acceptIt();
    Identifier iAST = parseIdentifier();
    accept(Token.LPAREN);
    FormalParameterSequence fpsAST = parseFormalParameterSequence();
    accept(Token.RPAREN);

    if (isProc){
      accept(Token.IS);
      Command cAST = parseCommand();
      accept(Token.END);
      finish(pfPos);
      procFuncsAST = new RecursiveProc(iAST, fpsAST, cAST, pfPos);
    } else {
      accept(Token.COLON);
      TypeDenoter tAST = parseTypeDenoter();
      accept(Token.IS);
      Expression eAST = parseExpression();
      finish(pfPos);
      procFuncsAST = new RecursiveFunc(iAST, fpsAST, tAST, eAST, pfPos);
    }

    return procFuncsAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Expressions ">
///////////////////////////////////////////////////////////////////////////////
//
// EXPRESSIONS
//
///////////////////////////////////////////////////////////////////////////////

  Expression parseExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();

    start (expressionPos);

    switch (currentToken.kind) {

    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Expression eAST = parseExpression();
        finish(expressionPos);
        expressionAST = new LetExpression(dAST, eAST, expressionPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression e1AST = parseExpression();
        accept(Token.THEN);
        Expression e2AST = parseExpression();
        accept(Token.ELSE);
        Expression e3AST = parseExpression();
        finish(expressionPos);
        expressionAST = new IfExpression(e1AST, e2AST, e3AST, expressionPos);
      }
      break;

    default:
      expressionAST = parseSecondaryExpression();
      break;
    }
    return expressionAST;
  }

  Expression parseSecondaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    expressionAST = parsePrimaryExpression();
    while (currentToken.kind == Token.OPERATOR) {
      Operator opAST = parseOperator();
      Expression e2AST = parsePrimaryExpression();
      expressionAST = new BinaryExpression (expressionAST, opAST, e2AST,
        expressionPos);
    }
    return expressionAST;
  }

  Expression parsePrimaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    switch (currentToken.kind) {

    case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(expressionPos);
        expressionAST = new IntegerExpression(ilAST, expressionPos);
      }
      break;

    case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(expressionPos);
        expressionAST = new CharacterExpression(clAST, expressionPos);
      }
      break;

    case Token.LBRACKET:
      {
        acceptIt();
        ArrayAggregate aaAST = parseArrayAggregate();
        accept(Token.RBRACKET);
        finish(expressionPos);
        expressionAST = new ArrayExpression(aaAST, expressionPos);
      }
      break;

    case Token.LCURLY:
      {
        acceptIt();
        RecordAggregate raAST = parseRecordAggregate();
        accept(Token.RCURLY);
        finish(expressionPos);
        expressionAST = new RecordExpression(raAST, expressionPos);
      }
      break;

    case Token.IDENTIFIER:
      {
        Identifier iAST= parseLongIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(iAST, apsAST, expressionPos);

        } else {
          Vname vAST = parseRestOfVarName(iAST);
          finish(expressionPos);
          expressionAST = new VnameExpression(vAST, expressionPos);
        }
      }
      break;

    case Token.OPERATOR:
      {
        Operator opAST = parseOperator();
        Expression eAST = parsePrimaryExpression();
        finish(expressionPos);
        expressionAST = new UnaryExpression(opAST, eAST, expressionPos);
      }
      break;

    case Token.LPAREN:
      acceptIt();
      expressionAST = parseExpression();
      accept(Token.RPAREN);
      break;

    default:
      syntacticError("\"%\" cannot start an expression",
        currentToken.spelling);
      break;

    }
    return expressionAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Aggregate ">
  RecordAggregate parseRecordAggregate() throws SyntaxError {
    RecordAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Identifier iAST = parseIdentifier();
    accept(Token.IS);
    Expression eAST = parseExpression();

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      RecordAggregate aAST = parseRecordAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleRecordAggregate(iAST, eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleRecordAggregate(iAST, eAST, aggregatePos);
    }
    return aggregateAST;
  }

  ArrayAggregate parseArrayAggregate() throws SyntaxError {
    ArrayAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Expression eAST = parseExpression();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ArrayAggregate aAST = parseArrayAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleArrayAggregate(eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleArrayAggregate(eAST, aggregatePos);
    }
    return aggregateAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Vname ">
///////////////////////////////////////////////////////////////////////////////
//
// VALUE-OR-VARIABLE NAMES
//
///////////////////////////////////////////////////////////////////////////////

  /**
   * Parse a variable name, covers the case of simple variables and long variables
   * @return a Vname AST
   * @throws SyntaxError
   */
  Vname parseVname() throws SyntaxError {
    Vname vnameAST = null; // in case there's a syntactic error

    Identifier iAST = parseLongIdentifier(); //Covers package, dollar and identifier
    vnameAST = parseRestOfVarName(iAST);

    return vnameAST;
  }

  /**
   * Parse the case of package$variable
   * @param iAST Covers package, dollar and identifier
   * @return an AST of Vname
   * @throws SyntaxError
   */
  Vname parseRestOfVname(Identifier iAST) throws SyntaxError {
    Vname vnameAST = null; // in case there's a syntactic error
    //vnameAST = parseRestOfVarName(iAST);
    vnameAST = parseRestOfVarName(iAST);
    /*

    if (iAST instanceof LongIdentifier){
      Identifier iAST2 = new Identifier(iAST.spelling, iAST.position) ;
      SourcePosition vnamePos = new SourcePosition();
      vnamePos = iAST2.position;
      Vname vAST = parseRestOfVarName(iAST2);
      finish(vnamePos);

      vnameAST = new LongVname(iAST, vAST, vnamePos);
    } else
      vnameAST = parseRestOfVarName(iAST);*/

    return vnameAST;
  }

  Vname parseVarName () throws SyntaxError {
    Vname vnameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    vnameAST = parseRestOfVarName(iAST);
    return vnameAST;
  }

  /**
   * Parse identifier, for both varNames and vnames. In case of vname (long vname),
   * it receives the identifier hold in a long indetifier. In case of varname (simple vname),
   * it receives a common identifier.
   * @param identifierAST can be a identifier from a simple Vname of LongVanme
   * @return a vname AST for simple vname, dotVname, subscipt vname
   * @throws SyntaxError
   */

  Vname parseRestOfVarName(Identifier identifierAST) throws SyntaxError {
    SourcePosition vnamePos = new SourcePosition();
    vnamePos = identifierAST.position;
    Vname vAST = new SimpleVname(identifierAST, vnamePos);

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        vAST = new DotVname(vAST, iAST, vnamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        finish(vnamePos);
        vAST = new SubscriptVname(vAST, eAST, vnamePos);
      }
    }
    return vAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Declarations ">
///////////////////////////////////////////////////////////////////////////////
//
// DECLARATIONS
//
///////////////////////////////////////////////////////////////////////////////

  ForDeclaration parseForDeclaration() throws SyntaxError {
    ForDeclaration declarationAST = null; // in case there's a syntactic error
    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    Identifier iAST = parseIdentifier();
    accept(Token.FROM);
    Expression eAST = parseExpression();
    declarationAST = new ForDeclaration(iAST, eAST, declarationPos);
    finish(declarationPos);
    return declarationAST;
  }

  Declaration parseDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseCompoundDeclaration();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Declaration d2AST = parseCompoundDeclaration();
      finish(declarationPos);
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  }

  Declaration parseCompoundDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);

    switch (currentToken.kind) {
      case Token.RECURSIVE:{
        acceptIt();
        ProcFuncs pfAST = parseProcFuncs();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new RecursiveDeclaration(pfAST, declarationPos);
      }
        break;

      case Token.PRIVATE:{
        acceptIt();
        Declaration dAst1 = parseDeclaration();
        accept(Token.IN);
        Declaration dAst2 = parseDeclaration();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new PrivateDeclaration(dAst1,dAst2,declarationPos);
      }
        break;

      case Token.PAR:{
        acceptIt();

        declarationAST = parseSingleDeclaration();

        do {
          accept(Token.PIPE);
          Declaration dAst2 = parseSingleDeclaration();
          finish(declarationPos);
          declarationAST = new ParDeclaration(declarationAST, dAst2, declarationPos);
        } while (currentToken.kind == Token.PIPE );
        accept(Token.END);
      }
        break;

      default:
        finish(declarationPos);
        declarationAST = parseSingleDeclaration();
      }

      return declarationAST;
    }

  Declaration parseSingleDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);

    switch (currentToken.kind) {

    case Token.CONST:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new ConstDeclaration(iAST, eAST, declarationPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        if (currentToken.kind == Token.COLON){
          acceptIt();
          TypeDenoter tAST = parseTypeDenoter();
          finish(declarationPos);
          declarationAST = new VarDeclaration(iAST, tAST, declarationPos);
        } else if (currentToken.kind == Token.INITIALIZE){
          acceptIt();
          Expression eAST = parseExpression();
          finish(declarationPos);
          declarationAST = new InitializedDeclaration(iAST, eAST, declarationPos);
        } else {
          syntacticError("Found \"%\" instead of : or ::=",
                  currentToken.spelling);
        }

      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseCommand();
        accept(currentToken.END);
        finish(declarationPos);
        declarationAST = new ProcDeclaration(iAST, fpsAST, cAST, declarationPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST,
          declarationPos);
      }
      break;

    case Token.TYPE:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        TypeDenoter tAST = parseTypeDenoter();
        finish(declarationPos);
        declarationAST = new TypeDeclaration(iAST, tAST, declarationPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return declarationAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Parameters ">
///////////////////////////////////////////////////////////////////////////////
//
// PARAMETERS
//
///////////////////////////////////////////////////////////////////////////////

  FormalParameterSequence parseFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST;

    SourcePosition formalsPos = new SourcePosition();

    start(formalsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(formalsPos);
      formalsAST = new EmptyFormalParameterSequence(formalsPos);

    } else {
      formalsAST = parseProperFormalParameterSequence();
    }
    return formalsAST;
  }

  FormalParameterSequence parseProperFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST = null; // in case there's a syntactic error;

    SourcePosition formalsPos = new SourcePosition();
    start(formalsPos);
    FormalParameter fpAST = parseFormalParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FormalParameterSequence fpsAST = parseProperFormalParameterSequence();
      finish(formalsPos);
      formalsAST = new MultipleFormalParameterSequence(fpAST, fpsAST,
        formalsPos);

    } else {
      finish(formalsPos);
      formalsAST = new SingleFormalParameterSequence(fpAST, formalsPos);
    }
    return formalsAST;
  }

  FormalParameter parseFormalParameter() throws SyntaxError {
    FormalParameter formalAST = null; // in case there's a syntactic error;

    SourcePosition formalPos = new SourcePosition();
    start(formalPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new ConstFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new VarFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        finish(formalPos);
        formalAST = new ProcFormalParameter(iAST, fpsAST, formalPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new FuncFormalParameter(iAST, fpsAST, tAST, formalPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a formal parameter",
        currentToken.spelling);
      break;

    }
    return formalAST;
  }


  ActualParameterSequence parseActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST;

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(actualsPos);
      actualsAST = new EmptyActualParameterSequence(actualsPos);

    } else {
      actualsAST = parseProperActualParameterSequence();
    }
    return actualsAST;
  }

  ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST = null; // in case there's a syntactic error

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    ActualParameter apAST = parseActualParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ActualParameterSequence apsAST = parseProperActualParameterSequence();
      finish(actualsPos);
      actualsAST = new MultipleActualParameterSequence(apAST, apsAST,
        actualsPos);
    } else {
      finish(actualsPos);
      actualsAST = new SingleActualParameterSequence(apAST, actualsPos);
    }
    return actualsAST;
  }

  ActualParameter parseActualParameter() throws SyntaxError {
    ActualParameter actualAST = null; // in case there's a syntactic error

    SourcePosition actualPos = new SourcePosition();

    start(actualPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
    case Token.INTLITERAL:
    case Token.CHARLITERAL:
    case Token.OPERATOR:
    case Token.LET:
    case Token.IF:
    case Token.LPAREN:
    case Token.LBRACKET:
    case Token.LCURLY:
      {
        Expression eAST = parseExpression();
        finish(actualPos);
        actualAST = new ConstActualParameter(eAST, actualPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Vname vAST = parseVname();
        finish(actualPos);
        actualAST = new VarActualParameter(vAST, actualPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new ProcActualParameter(iAST, actualPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new FuncActualParameter(iAST, actualPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start an actual parameter",
        currentToken.spelling);
      break;

    }
    return actualAST;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
///////////////////////////////////////////////////////////////////////////////
//
// TYPE-DENOTERS
//
///////////////////////////////////////////////////////////////////////////////

  TypeDenoter parseTypeDenoter() throws SyntaxError {
    TypeDenoter typeAST = null; // in case there's a syntactic error
    SourcePosition typePos = new SourcePosition();

    start(typePos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseLongIdentifier();
        finish(typePos);
        typeAST = new SimpleTypeDenoter(iAST, typePos);
      }
      break;

    case Token.ARRAY:
      {
        acceptIt();
        IntegerLiteral ilAST = parseIntegerLiteral();
        accept(Token.OF);
        TypeDenoter tAST = parseTypeDenoter();
        finish(typePos);
        typeAST = new ArrayTypeDenoter(ilAST, tAST, typePos);
      }
      break;

    case Token.RECORD:
      {
        acceptIt();
        FieldTypeDenoter fAST = parseFieldTypeDenoter();
        accept(Token.END);
        finish(typePos);
        typeAST = new RecordTypeDenoter(fAST, typePos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a type denoter",
        currentToken.spelling);
      break;

    }
    return typeAST;
  }

  FieldTypeDenoter parseFieldTypeDenoter() throws SyntaxError {
    FieldTypeDenoter fieldAST = null; // in case there's a syntactic error

    SourcePosition fieldPos = new SourcePosition();

    start(fieldPos);
    Identifier iAST = parseIdentifier();
    accept(Token.COLON);
    TypeDenoter tAST = parseTypeDenoter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FieldTypeDenoter fAST = parseFieldTypeDenoter();
      finish(fieldPos);
      fieldAST = new MultipleFieldTypeDenoter(iAST, tAST, fAST, fieldPos);
    } else {
      finish(fieldPos);
      fieldAST = new SingleFieldTypeDenoter(iAST, tAST, fieldPos);
    }
    return fieldAST;
  }

  // </editor-fold>
}
