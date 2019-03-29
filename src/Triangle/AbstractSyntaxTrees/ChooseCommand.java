package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ChooseCommand extends Command {
	public ChooseCommand (Expression eAST, Cases cAST, SourcePosition pos){
		super(pos);
		E = eAST;
		C = cAST;
	}

	public Object visit(Visitor v, Object o) { return v.visitChooseCommand(this, o); }

	public Expression E;
	public Cases C;
}
