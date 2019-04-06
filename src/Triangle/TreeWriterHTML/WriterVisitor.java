package Triangle.TreeWriterHTML;

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
		writeLineHTML("<PackageDeclaration> package");
		ast.P.visit(this, null);
		ast.D.visit(this, null);
		writeLineHTML("</PackageDeclaration>");
		return null;
	}

	@Override
	public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
		writeLineHTML("<SequentialPackageDeclaration>");
		ast.P1.visit(this, null);
		ast.P2.visit(this, null);
		writeLineHTML("</SequentialPackageDeclaration>");
		return null;
	}

	@Override
	public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
		writeLineHTML("<PackageIdentifier>");
		ast.I.visit(this, null);
		writeLineHTML("</PackageIdentifier>");
		return null;
	}

	// <editor-fold defaultstate="collapsed" desc=" Commands ">
	// Commands
	public Object visitAssignCommand(AssignCommand ast, Object obj) {
		writeLineHTML("<AssignCommand>");
		ast.V.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</AssignCommand>");
		return null;
	}

	public Object visitCallCommand(CallCommand ast, Object obj) {
		writeLineHTML("<CallCommand>");
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeLineHTML("</CallCommand>");
		return null;
	}

	public Object visitEmptyCommand(EmptyCommand ast, Object obj) {
		writeLineHTML("<EmptyCommand/>");
		return null;
	}

	public Object visitIfCommand(IfCommand ast, Object obj) {
		writeLineHTML("<IfCommand>");
		ast.E.visit(this, null);
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineHTML("</IfCommand>");
		return null;
	}

	public Object visitLetCommand(LetCommand ast, Object obj) {
		writeLineHTML("<LetCommand>");
		ast.D.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</LetCommand>");
		return null;
	}

	public Object visitSequentialCommand(SequentialCommand ast, Object obj) {
		writeLineHTML("<SequentialCommand>");
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineHTML("</SequentialCommand>");
		return null;
	}

	public Object visitWhileCommand(WhileCommand ast, Object obj) {
		writeLineHTML("<WhileCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</WhileCommand>");
		return null;
	}

	@Override
	public Object visitDoWhileCommand(DoWhileCommand ast, Object o) {
		writeLineHTML("<DoWhileCommand>");
		ast.C.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</DoWhileCommand>");
		return null;
	}

	@Override
	public Object visitUntilCommand(UntilCommand ast, Object o) {
		writeLineHTML("<UntilCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</UntilCommand>");
		return null;
	}

	@Override
	public Object visitDoUntilCommand(DoUntilCommand ast, Object o) {
		writeLineHTML("<DoUntilCommand>");
		ast.C.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</DoUntilCommand>");
		return null;
	}

	@Override
	public Object visitForCommand(ForCommand ast, Object o) {
		writeLineHTML("<ForCommand>");
		ast.F.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</ForCommand>");
		return null;
	}

	@Override
	public Object visitForWhileCommand(ForWhileCommand ast, Object o) {
		writeLineHTML("<ForWhileCommand>");
		ast.F.visit(this, null);
		ast.W.visit(this, null);
		writeLineHTML("</ForWhileCommand>");
		return null;
	}

	@Override
	public Object visitForUntilCommand(ForUntilCommand ast, Object o) {
		writeLineHTML("<ForUntilCommand>");
		ast.F.visit(this, null);
		ast.U.visit(this, null);
		writeLineHTML("</ForCommand>");
		return null;
	}

	@Override
	public Object visitChooseCommand(ChooseCommand ast, Object o) {
		writeLineHTML("<ChooseCommand>");
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</ChooseCommand>");
		return null;
	}

	@Override
	public Object visitCase(Case ast, Object o) {
		writeLineHTML("<Case>");
		ast.CL.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</Case>");
		return null;
	}

	@Override
	public Object visitElseCase(ElseCase ast, Object o) {
		writeLineHTML("<ElseCase>");
		ast.C.visit(this, null);
		writeLineHTML("</ElseCase>");
		return null;
	}

	@Override
	public Object visitSequentialCases(SequentialCases ast, Object o) {
		writeLineHTML("<SequentialCases>");
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeLineHTML("</SequentialCases>");
		return null;
	}

	@Override
	public Object visitCaseLiterals(CaseLiterals ast, Object o) {
		writeLineHTML("<CaseLiterals>");
		ast.R.visit(this, null);
		writeLineHTML("</CaseLiterals>");
		return null;
	}

	@Override
	public Object visitSequentialCaseLiterals(SequentialCaseLiterals ast, Object o) {
		writeLineHTML("<SequentialCaseLiterals>");
		ast.L1.visit(this, null);
		ast.L2.visit(this, null);
		writeLineHTML("</SequentialCaseLiterals>");
		return null;
	}

	@Override
	public Object visitSimpleCaseRange(SimpleCaseRange ast, Object o) {
		writeLineHTML("<SimpleCaseRange>");
		ast.L.visit(this, null);
		writeLineHTML("</SimpleCaseRange>");
		return null;
	}

	@Override
	public Object visitCompleteCaseRange(CompleteCaseRange ast, Object o) {
		writeLineHTML("<CompleteCaseRange>");
		ast.L1.visit(this, null);
		ast.L2.visit(this, null);
		writeLineHTML("</CompleteCaseRange>");
		return null;
	}

	@Override
	public Object visitCaseLiteral(CaseLiteral ast, Object o) {
		writeLineHTML("<CaseLiteral>");
		ast.L.visit(this, null);
		writeLineHTML("</CaseLiteral>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Expressions ">
	// Expressions
	public Object visitArrayExpression(ArrayExpression ast, Object obj) {
		writeLineHTML("<ArrayExpression>");
		ast.AA.visit(this, null);
		writeLineHTML("</ArrayExpression>");
		return null;
	}

	public Object visitBinaryExpression(BinaryExpression ast, Object obj) {
		writeLineHTML("<BinaryExpression>");
		ast.E1.visit(this, null);
		ast.O.visit(this, null);
		ast.E2.visit(this, null);
		writeLineHTML("</BinaryExpression>");
		return null;
	}

	public Object visitCallExpression(CallExpression ast, Object obj) {
		writeLineHTML("<CallExpression>");
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeLineHTML("</CallExpression>");
		return null;
	}

	public Object visitCharacterExpression(CharacterExpression ast, Object obj) {
		writeLineHTML("<CharacterExpression>");
		ast.CL.visit(this, null);
		writeLineHTML("</CharacterExpression>");
		return null;
	}

	public Object visitEmptyExpression(EmptyExpression ast, Object obj) {
		writeLineHTML("<EmptyExpression/>");
		return null;
	}

	public Object visitIfExpression(IfExpression ast, Object obj) {
		writeLineHTML("<IfExpression>");
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.E3.visit(this, null);
		writeLineHTML("</IfExpression>");
		return null;
	}

	public Object visitIntegerExpression(IntegerExpression ast, Object obj) {
		writeLineHTML("<IntegerExpression>");
		ast.IL.visit(this, null);
		writeLineHTML("</IntegerExpression>");
		return null;
	}

	public Object visitLetExpression(LetExpression ast, Object obj) {
		writeLineHTML("<LetExpression>");
		ast.D.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</LetExpression>");
		return null;
	}

	public Object visitRecordExpression(RecordExpression ast, Object obj) {
		writeLineHTML("<RecordExpression>");
		ast.RA.visit(this, null);
		writeLineHTML("</RecordExpression>");
		return null;
	}

	public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
		writeLineHTML("<UnaryExpression>");
		ast.O.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</UnaryExpression>");
		return null;
	}

	public Object visitVnameExpression(VnameExpression ast, Object obj) {
		writeLineHTML("<VnameExpression>");
		ast.V.visit(this, null);
		writeLineHTML("</VnameExpression>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Declarations ">
	// Declarations
	public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
		writeLineHTML("<BinaryOperatorDeclaration>");
		ast.O.visit(this, null);
		ast.ARG1.visit(this, null);
		ast.ARG2.visit(this, null);
		ast.RES.visit(this, null);
		writeLineHTML("</BinaryOperatorDeclaration>");
		return null;
	}

	public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
		writeLineHTML("<ConstDeclaration>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</ConstDeclaration>");
		return null;
	}

	public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
		writeLineHTML("<FuncDeclaration>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.T.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</FuncDeclaration>");
		return null;
	}

	public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
		writeLineHTML("<ProcDeclaration>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</ProcDeclaration>");
		return null;
	}

	public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
		writeLineHTML("<SequentialDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineHTML("</SequentialDeclaration>");
		return null;
	}

	public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
		writeLineHTML("<TypeDeclaration>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</TypeDeclaration>");
		return null;
	}

	public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
		writeLineHTML("<UnaryOperatorDeclaration>");
		ast.O.visit(this, null);
		ast.ARG.visit(this, null);
		ast.RES.visit(this, null);
		writeLineHTML("</UnaryOperatorDeclaration>");
		return null;
	}

	public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
		writeLineHTML("<VarDeclaration>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</VarDeclaration>");
		return null;
	}

	@Override
	public Object visitForDeclaration(ForDeclaration ast, Object o) {
		writeLineHTML("<ForDeclaration>");
		ast.I.visit(this, null);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		writeLineHTML("</ForDeclaration>");
		return null;
	}

	@Override
	public Object visitPrivateDeclaration(PrivateDeclaration ast, Object o) {
		writeLineHTML("<PrivateDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineHTML("</PrivateDeclaration>");
		return null;
	}

	@Override
	public Object visitParDeclaration(ParDeclaration ast, Object o) {
		writeLineHTML("<ParDeclaration>");
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeLineHTML("</ParDeclaration>");
		return null;
	}

	@Override
	public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
		writeLineHTML("<RecursiveDeclaration>");
		ast.P.visit(this, null);
		writeLineHTML("</RecursiveDeclaration>");
		return null;
	}

	@Override
	public Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o) {
		writeLineHTML("<SequentialProcFuncs>");
		ast.R1.visit(this, null);
		ast.R2.visit(this, null);
		writeLineHTML("</SequentialProcFuncs>");
		return null;
	}

	@Override
	public Object visitRecursiveFunc(RecursiveFunc ast, Object o) {
		writeLineHTML("<RecursiveFunc>");
		ast.I.visit(this, null);
		ast.F.visit(this, null);
		ast.T.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</RecursiveFunc>");
		return null;
	}

	@Override
	public Object visitRecursiveProc(RecursiveProc ast, Object o) {
		writeLineHTML("<RecursiveProc>");
		ast.I.visit(this, null);
		ast.F.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</RecursiveProc>");
		return null;
	}

	@Override
	public Object visitInitializedDeclaration(InitializedDeclaration ast, Object o) {
		writeLineHTML("<InitializedDeclaration>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</InitializedDeclaration>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Aggregates ">
	// Array Aggregates
	public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
		writeLineHTML("<MultipleArrayAggregate>");
		ast.E.visit(this, null);
		ast.AA.visit(this, null);
		writeLineHTML("</MultipleArrayAggregate>");
		return null;
	}

	public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
		writeLineHTML("<SingleArrayAggregate>");
		ast.E.visit(this, null);
		writeLineHTML("</SingleArrayAggregate>");
		return null;
	}

	// Record Aggregates
	public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
		writeLineHTML("<MultipleRecordAggregate>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		ast.RA.visit(this, null);
		writeLineHTML("</MultipleRecordAggregate>");
		return null;
	}

	public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
		writeLineHTML("<SingleRecordAggregate>");
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</SingleRecordAggregate>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Parameters ">
	// Formal Parameters
	public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
		writeLineHTML("<ConstFormalParameter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</ConstFormalParameter>");
		return null;
	}

	public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
		writeLineHTML("<FuncFormalParameter>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("<FuncFormalParameter>");
		return null;
	}

	public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
		writeLineHTML("<ProcFormalParameter>");
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		writeLineHTML("</ProcFormalParameter>");
		return null;
	}

	public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
		writeLineHTML("<VarFormalParameter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</VarFormalParameter>");
		return null;
	}

	public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
		writeLineHTML("<EmptyFormalParameterSequence/>");
		return null;
	}

	public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
		writeLineHTML("<MultipleFormalParameterSequence>");
		ast.FP.visit(this, null);
		ast.FPS.visit(this, null);
		writeLineHTML("</MultipleFormalParameterSequence>");
		return null;
	}

	public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
		writeLineHTML("<SingleFormalParameterSequence>");
		ast.FP.visit(this, null);
		writeLineHTML("</SingleFormalParameterSequence>");
		return null;
	}

	// Actual Parameters
	public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
		writeLineHTML("<ConstActualParameter>");
		ast.E.visit(this, null);
		writeLineHTML("</ConstActualParameter>");
		return null;
	}

	public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
		writeLineHTML("<FuncActualParameter>");
		ast.I.visit(this, null);
		writeLineHTML("</FuncActualParameter>");
		return null;
	}

	public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
		writeLineHTML("<ProcActualParameter>");
		ast.I.visit(this, null);
		writeLineHTML("</ProcActualParameter>");
		return null;
	}

	public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
		writeLineHTML("<VarActualParameter>");
		ast.V.visit(this, null);
		writeLineHTML("</VarActualParameter>");
		return null;
	}

	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
		writeLineHTML("<EmptyActualParameterSequence/>");
		return null;
	}

	public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
		writeLineHTML("<MultipleActualParameterSequence>");
		ast.AP.visit(this, null);
		ast.APS.visit(this, null);
		writeLineHTML("</MultipleActualParameterSequence>");
		return null;
	}

	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
		writeLineHTML("<SingleActualParameterSequence>");
		ast.AP.visit(this, null);
		writeLineHTML("</SingleActualParameterSequence>");
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
	// Type Denoters
	public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
		writeLineHTML("<AnyTypeDenoter/>");
		return null;
	}

	public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
		writeLineHTML("<ArrayTypeDenoter>");
		ast.IL.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</ArrayTypeDenoter>");
		return null;
	}

	public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
		writeLineHTML("<BoolTypeDenoter/>");
		return null;
	}

	public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
		writeLineHTML("<CharTypeDenoter/>");
		return null;
	}

	public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
		writeLineHTML("<ErrorTypeDenoter/>");
		return null;
	}

	public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
		writeLineHTML("<SimpleTypeDenoter>");
		ast.I.visit(this, null);
		writeLineHTML("</SimpleTypeDenoter>");
		return null;
	}

	public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
		writeLineHTML("<IntTypeDenoter/>");
		return null;
	}

	public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
		writeLineHTML("<RecordTypeDenoter>");
		ast.FT.visit(this, null);
		writeLineHTML("</RecordTypeDenoter>");
		return null;
	}


	public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
		writeLineHTML("<MultipleFieldTypeDenoter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		ast.FT.visit(this, null);
		writeLineHTML("</MultipleFieldTypeDenoter>");
		return null;
	}

	public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
		writeLineHTML("<SingleFieldTypeDenoter>");
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeLineHTML("</SingleFieldTypeDenoter>");
		return null;
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Literals ">
	// Literals, Identifiers and Operators
	public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
		writeLineHTML("<CharacterLiteral value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitIdentifier(Identifier ast, Object obj) {
		writeLineHTML("<Identifier value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
		writeLineHTML("<IntegerLiteral value=\"" + ast.spelling + "\"/>");
		return null;
	}

	public Object visitOperator(Operator ast, Object obj) {
		writeLineHTML("<Operator value=\"" + transformOperator(ast.spelling) + "\"/>");
		return null;
	}

	@Override
	public Object visitLongIdentifier(LongIdentifier ast, Object o) {
		writeLineHTML("<Long Identifier value=\"" + ast.spelling + "\"/>");
		return null;
	}

	// </editor-fold>


	// <editor-fold defaultstate="collapsed" desc=" Value-or-variable names ">
	// Value-or-variable names
	@Override
	public Object visitLongVName(LongVname ast, Object o) {
		writeLineHTML("<LongVname>");
		ast.P.visit(this, null);
		ast.V.visit(this, null);
		writeLineHTML("</LongVname>");
		return null;
	}

	public Object visitDotVname(DotVname ast, Object obj) {
		writeLineHTML("<DotVname>");
		ast.V.visit(this, null);
		ast.I.visit(this, null);
		writeLineHTML("</DotVname>");
		return null;
	}

	public Object visitSimpleVname(SimpleVname ast, Object obj) {
		writeLineHTML("<SimpleVname>");
		ast.I.visit(this, null);
		writeLineHTML("</SimpleVname>");
		return null;
	}

	public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
		writeLineHTML("<SubscriptVname>");
		ast.V.visit(this, null);
		ast.E.visit(this, null);
		writeLineHTML("</SubscriptVname>");
		return null;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Programs ">
	// Programs
	public Object visitProgram(Program ast, Object obj) {
		writeLineHTML("<Program>");
		ast.C.visit(this, null);
		writeLineHTML("</Program>");
		return null;
	}

	public Object visitProgramTree (Program ast, Object obj) {
		if (ast instanceof PackagedProgram) {
			PackagedProgram ast2 = (PackagedProgram) ast;
			return visitPackagedProgram(ast2, null);
		}
		return (visitProgram(ast, null));
	}

	@Override
	public Object visitPackagedProgram(PackagedProgram ast, Object o) {
		writeLineHTML("<Program>");
		ast.P.visit(this, null);
		ast.C.visit(this, null);
		writeLineHTML("</Program>");
		return null;
	}
	// </editor-fold>

	private void writeLineHTML(String line) {
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