package Triangle.TreeWriterXML;

import Triangle.AbstractSyntaxTrees.*;

import java.io.FileWriter;
import java.io.IOException;

public class WriterVisitor implements Visitor {

	private FileWriter fileWriter;

	public WriterVisitor(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	@Override
	public Object visitPackageDeclaration(PackageDeclaration ast, Object o) {
		writeLineXML("\t<PackageDeclaration>");
		ast.P.visit(this, null);
		ast.D.visit(this, null);
		writeLineXML("</PackageDeclaration>");
		return null;
	}

	@Override
	public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
		writeLineXML("<SequentialPackageDeclaration>");
		ast.P1.visit(this, null);
		ast.P2.visit(this, null);
		writeLineXML("</SequentialPackageDeclaration>");
		return null;
	}

	@Override
	public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
		writeLineXML("<PackageIdentifier>");
		writeLineXML(ast.spelling);
		writeLineXML("</PackageIdentifier>");
		return null;
	}

	// <editor-fold defaultstate="collapsed" desc=" Commands ">
	// Commands
	public Object visitAssignCommand(AssignCommand ast, Object obj) {
		writeLineXML("<AssignCommand>");
		ast.V.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</AssignCommand>");
		return null;
	}

	public Object visitCallCommand(CallCommand ast, Object obj) {
		writeLineXML("<CallCommand>");
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeLineXML("</CallCommand>");
		return null;
	}

	public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
		writeLineXML("<EmptyCommand/>");
		return null;
	}

	public Object visitIfCommand(IfCommand ast, Object obj) {
		writeLineXML("<IfCommand>");
		ast.E.visit(this, null);
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineXML("</IfCommand>");
		return null;
	}

	public Object visitLetCommand(LetCommand ast, Object obj) {
		writeLineXML("<LetCommand>");
		ast.D.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</LetCommand>");
		return null;
	}

	public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
		writeLineXML("<SequentialCommand>");
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineXML("</SequentialCommand>");
		return null;
	}

	public Object visitWhileCommand(WhileCommand ast, Object obj) {
		writeLineXML("<WhileCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</WhileCommand>");
		return null;
	}

	@Override
	public Object visitDoWhileCommand(DoWhileCommand ast, Object o) {
		writeLineXML("<DoWhileCommand>");
		ast.C.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</DoWhileCommand>");
		return null;
	}

	@Override
	public Object visitUntilCommand(UntilCommand ast, Object o) {
		writeLineXML("<UntilCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</UntilCommand>");
		return null;
	}

	@Override
	public Object visitDoUntilCommand(DoUntilCommand ast, Object o) {
		writeLineXML("<DoUntilCommand>");
		ast.C.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</DoUntilCommand>");
		return null;
	}

	@Override
	public Object visitForCommand(ForCommand ast, Object o) {
		writeLineXML("<ForCommand>");
		ast.F.visit(this, null);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</ForCommand>");
		return null;
	}

	@Override
	public Object visitForWhileCommand(ForWhileCommand ast, Object o) {
		writeLineXML("<ForWhileCommand>");
		ast.F.visit(this, null);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</ForWhileCommand>");
		return null;
	}

	@Override
	public Object visitForUntilCommand(ForUntilCommand ast, Object o) {
		writeLineXML("<ForUntilCommand>");
		ast.F.visit(this, null);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</ForCommand>");
		return null;
	}

	@Override
	public Object visitChooseCommand(ChooseCommand ast, Object o) {
		writeLineXML("<ChooseCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</ChooseCommand>");
		return null;
	}

	@Override
	public Object visitCase(Case ast, Object o) {
		writeLineXML("<Case>");
		ast.CL.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</Case>");
		return null;
	}

	@Override
	public Object visitElseCase(ElseCase ast, Object o) {
		writeLineXML("<ElseCase>");
		ast.C.visit(this, null);
		writeLineXML("</ElseCase>");
		return null;
	}

	@Override
	public Object visitSequentialCases(SequentialCases ast, Object o) {
		writeLineXML("<SequentialCases>");
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineXML("</SequentialCases>");
		return null;
	}

	@Override
	public Object visitCaseLiterals(CaseLiterals ast, Object o) {
		writeLineXML("<CaseLiterals>");
		ast.R.visit(this, null);
		writeLineXML("</CaseLiterals>");
		return null;
	}

	@Override
	public Object visitSequentialCaseLiterals(SequentialCaseLiterals ast, Object o) {
		writeLineXML("<SequentialCaseLiterals>");
		ast.L1.visit(this, null);
		ast.L2.visit(this, null);
		writeLineXML("</SequentialCaseLiterals>");
		return null;
	}

	@Override
	public Object visitCaseRange(CaseRange ast, Object o) {
		writeLineXML("<CompleteCaseRange>");
		ast.L1.visit(this, null);
		ast.L2.visit(this, null);
		writeLineXML("</CompleteCaseRange>");
		return null;
	}

	@Override
	public Object visitCaseLiteral(CaseLiteral ast, Object o) {
		writeLineXML("<CaseLiteral>");
		ast.L.visit(this, null);
		writeLineXML("</CaseLiteral>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Expressions ">
	// Expressions
	public Object visitArrayExpression(ArrayExpression ast, Object obj) {
		writeLineXML("<ArrayExpression>");
		ast.AA.visit(this, null);
		writeLineXML("</ArrayExpression>");
		return null;
	}

	public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
		writeLineXML("<BinaryExpression>");
		ast.E1.visit(this, null);
		ast.O.visit(this, null);
		ast.E2.visit(this, null);
		writeLineXML("</BinaryExpression>");
		return null;
	}

	public Object visitCallExpression(CallExpression ast, Object obj) {
		writeLineXML("<CallExpression>");
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeLineXML("</CallExpression>");
		return null;
	}

	public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
		writeLineXML("<CharacterExpression>");
		ast.CL.visit(this, null);
		writeLineXML("</CharacterExpression>");
		return null;
	}

	public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
		writeLineXML("<EmptyExpression/>");
		return null;
	}

	public Object visitIfExpression(IfExpression ast, Object obj) {
		writeLineXML("<IfExpression>");
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.E3.visit(this, null);
		writeLineXML("</IfExpression>");
		return null;
	}

	public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
		writeLineXML("<IntegerExpression>");
		ast.IL.visit(this, null);
		writeLineXML("</IntegerExpression>");
		return null;
	}

	public Object visitLetExpression(LetExpression ast, Object obj) {
		writeLineXML("<LetExpression>");
		ast.D.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</LetExpression>");
		return null;
	}

	public Object visitRecordExpression(RecordExpression ast, Object obj) {
		writeLineXML("<RecordExpression>");
		ast.RA.visit(this, null);
		writeLineXML("</RecordExpression>");
		return null;
	}

	public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
		writeLineXML("<UnaryExpression>");
		ast.O.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</UnaryExpression>");
		return null;
	}

	public Object visitVnameExpression(VnameExpression ast, Object obj) {
		writeLineXML("<VnameExpression>");
		ast.V.visit(this, null);
		writeLineXML("</VnameExpression>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Declarations ">
	// Declarations
	public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
		writeLineXML("<BinaryOperatorDeclaration>");
		ast.O.visit(this, null);
		ast.ARG1.visit(this, null);
		ast.ARG2.visit(this, null);
		ast.RES.visit(this, null);
		writeLineXML("</BinaryOperatorDeclaration>");
		return null;
	}

	public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
		writeLineXML("<ConstDeclaration>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</ConstDeclaration>");
		return null;
	}

	public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
		writeLineXML("<FuncDeclaration>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.T.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</FuncDeclaration>");
		return null;
	}

	public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
		writeLineXML("<ProcDeclaration>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</ProcDeclaration>");
		return null;
	}

	public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
		writeLineXML("<SequentialDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineXML("</SequentialDeclaration>");
		return null;
	}

	public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
		writeLineXML("<TypeDeclaration>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</TypeDeclaration>");
		return null;
	}

	public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
		writeLineXML("<UnaryOperatorDeclaration>");
		ast.O.visit(this, null);
		ast.ARG.visit(this, null);
		ast.RES.visit(this, null);
		writeLineXML("</UnaryOperatorDeclaration>");
		return null;
	}

	public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
		writeLineXML("<VarDeclaration>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</VarDeclaration>");
		return null;
	}

	@Override
	public Object visitForDeclaration(ForDeclaration ast, Object o) {
		writeLineXML("<ForDeclaration>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</ForDeclaration>");
		return null;
	}

	@Override
	public Object visitPrivateDeclaration(PrivateDeclaration ast, Object o) {
		writeLineXML("<PrivateDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineXML("</PrivateDeclaration>");
		return null;
	}

	@Override
	public Object visitParDeclaration(ParDeclaration ast, Object o) {
		writeLineXML("<ParDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineXML("</ParDeclaration>");
		return null;
	}

	@Override
	public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
		writeLineXML("<RecursiveDeclaration>");
		ast.P.visit(this, null);
		writeLineXML("</RecursiveDeclaration>");
		return null;
	}

	@Override
	public Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o) {
		writeLineXML("<SequentialProcFuncs>");
		ast.R1.visit(this, null);
		ast.R2.visit(this, null);
		writeLineXML("</SequentialProcFuncs>");
		return null;
	}

	@Override
	public Object visitRecursiveFunc(RecursiveFunc ast, Object o) {
		writeLineXML("<RecursiveFunc>");
		ast.I.visit(this, null);
		ast.F.visit(this, null);
		ast.T.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</RecursiveFunc>");
		return null;
	}

	@Override
	public Object visitRecursiveFuncTwo(RecursiveFunc ast, Object o) {
		return null;
	}

	@Override
	public Object visitRecursiveProc(RecursiveProc ast, Object o) {
		writeLineXML("<RecursiveProc>");
		ast.I.visit(this, null);
		ast.F.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</RecursiveProc>");
		return null;
	}

	@Override
	public Object visitRecursiveProcTwo(RecursiveProc ast, Object o) {
		return null;
	}

	@Override
	public Object visitInitializedDeclaration(InitializedDeclaration ast, Object o) {
		writeLineXML("<InitializedDeclaration>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</InitializedDeclaration>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Aggregates ">
	// Array Aggregates
	public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
		writeLineXML("<MultipleArrayAggregate>");
		ast.E.visit(this, null);
		ast.AA.visit(this, null);
		writeLineXML("</MultipleArrayAggregate>");
		return null;
	}

	public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
		writeLineXML("<SingleArrayAggregate>");
		ast.E.visit(this, null);
		writeLineXML("</SingleArrayAggregate>");
		return null;
	}

	// Record Aggregates
	public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
		writeLineXML("<MultipleRecordAggregate>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		ast.RA.visit(this, null);
		writeLineXML("</MultipleRecordAggregate>");
		return null;
	}

	public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
		writeLineXML("<SingleRecordAggregate>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</SingleRecordAggregate>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Parameters ">
	// Formal Parameters
	public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
		writeLineXML("<ConstFormalParameter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</ConstFormalParameter>");
		return null;
	}

	public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
		writeLineXML("<FuncFormalParameter>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("<FuncFormalParameter>");
		return null;
	}

	public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
		writeLineXML("<ProcFormalParameter>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		writeLineXML("</ProcFormalParameter>");
		return null;
	}

	public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
		writeLineXML("<VarFormalParameter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</VarFormalParameter>");
		return null;
	}

	public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
		writeLineXML("<EmptyFormalParameterSequence/>");
		return null;
	}

	public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
		writeLineXML("<MultipleFormalParameterSequence>");
		ast.FP.visit(this, null);
		ast.FPS.visit(this, null);
		writeLineXML("</MultipleFormalParameterSequence>");
		return null;
	}

	public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
		writeLineXML("<SingleFormalParameterSequence>");
		ast.FP.visit(this, null);
		writeLineXML("</SingleFormalParameterSequence>");
		return null;
	}

	// Actual Parameters
	public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
		writeLineXML("<ConstActualParameter>");
		ast.E.visit(this, null);
		writeLineXML("</ConstActualParameter>");
		return null;
	}

	public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
		writeLineXML("<FuncActualParameter>");
		ast.I.visit(this, null);
		writeLineXML("</FuncActualParameter>");
		return null;
	}

	public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
		writeLineXML("<ProcActualParameter>");
		ast.I.visit(this, null);
		writeLineXML("</ProcActualParameter>");
		return null;
	}

	public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
		writeLineXML("<VarActualParameter>");
		ast.V.visit(this, null);
		writeLineXML("</VarActualParameter>");
		return null;
	}

	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
		writeLineXML("<EmptyActualParameterSequence/>");
		return null;
	}

	public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
		writeLineXML("<MultipleActualParameterSequence>");
		ast.AP.visit(this, null);
		ast.APS.visit(this, null);
		writeLineXML("</MultipleActualParameterSequence>");
		return null;
	}

	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
		writeLineXML("<SingleActualParameterSequence>");
		ast.AP.visit(this, null);
		writeLineXML("</SingleActualParameterSequence>");
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
	// Type Denoters
	public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
		writeLineXML("<AnyTypeDenoter/>");
		return null;
	}

	public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
		writeLineXML("<ArrayTypeDenoter>");
		ast.IL.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</ArrayTypeDenoter>");
		return null;
	}

	public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
		writeLineXML("<BoolTypeDenoter/>");
		return null;
	}

	public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
		writeLineXML("<CharTypeDenoter/>");
		return null;
	}

	public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
		writeLineXML("<ErrorTypeDenoter/>");
		return null;
	}

	public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
		writeLineXML("<SimpleTypeDenoter>");
		ast.I.visit(this, null);
		writeLineXML("</SimpleTypeDenoter>");
		return null;
	}

	public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
		writeLineXML("<IntTypeDenoter/>");
		return null;
	}

	public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
		writeLineXML("<RecordTypeDenoter>");
		ast.FT.visit(this, null);
		writeLineXML("</RecordTypeDenoter>");
		return null;
	}


	public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
		writeLineXML("<MultipleFieldTypeDenoter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		ast.FT.visit(this, null);
		writeLineXML("</MultipleFieldTypeDenoter>");
		return null;
	}

	public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
		writeLineXML("<SingleFieldTypeDenoter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineXML("</SingleFieldTypeDenoter>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Literals ">
	// Literals, Identifiers and Operators
	public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
		writeLineXML("<CharacterLiteral value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitIdentifier(Identifier ast, Object obj) {
		writeLineXML("<Identifier value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
		writeLineXML("<IntegerLiteral value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitOperator(Operator ast, Object obj) {
		writeLineXML("<Operator value=\"" + transformOperator(ast.spelling) + "\"/>");
		return null;
	}

	@Override
	public Object visitLongIdentifier(LongIdentifier ast, Object o) {
		writeLineXML("<Long Identifier value=\"" + ast.spelling + "\"/>");
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Value-or-variable names ">
	// Value-or-variable names

	public Object visitDotVname(DotVname ast, Object obj) {
		writeLineXML("<DotVname>");
		ast.V.visit(this, null);
		ast.I.visit(this, null);
		writeLineXML("</DotVname>");
		return null;
	}

	public Object visitSimpleVname(SimpleVname ast, Object obj) {
		writeLineXML("<SimpleVname>");
		ast.I.visit(this, null);
		writeLineXML("</SimpleVname>");
		return null;
	}

	public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
		writeLineXML("<SubscriptVname>");
		ast.V.visit(this, null);
		ast.E.visit(this, null);
		writeLineXML("</SubscriptVname>");
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Programs ">
	// Programs
	public Object visitProgram(Program ast, Object obj) {
		if (ast instanceof SimpleProgram){
			SimpleProgram pAST = (SimpleProgram) ast;
			return visitSimpleProgram(pAST, obj);
		} else {
			PackagedProgram pAST = (PackagedProgram) ast;
			return visitPackagedProgram(pAST, obj);
		}
	}

	@Override
	public Object visitSimpleProgram(SimpleProgram ast, Object o) {
		writeLineXML("<Program>");
		ast.C.visit(this, null);
		writeLineXML("</Program>");
		return null;
	}

	@Override
	public Object visitPackagedProgram(PackagedProgram ast, Object o) {
		writeLineXML("<Program>");
		ast.P.visit(this, null);
		ast.C.visit(this, null);
		writeLineXML("</Program>");
		return null;
	}

	@Override
	public Object visitSequentialProcFuncsTwo(SequentialProcFuncs ast, Object o) {
		return null;
	}
	// </editor-fold>

	private void writeLineXML(String line) {
		try {
			fileWriter.write(line);
			fileWriter.write('\n');
		} catch (IOException e) {
			System.err.println("Error while writing file for print the AST");
			e.printStackTrace();
		}
	}

	/*
	 * Convert the characters "<" & "<=" to their equivalents in html
	 */

	private String transformOperator(String operator) {
		if (operator.compareTo("<") == 0)
			return "&lt;";
		else if (operator.compareTo("<=") == 0)
			return "&lt;=";
		else
			return operator;
	}

}