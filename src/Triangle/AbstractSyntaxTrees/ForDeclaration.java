package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForDeclaration extends Declaration {

    public ForDeclaration(Identifier iAst, Expression eAst, SourcePosition pos){
        super(pos);
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return null;
    }

    Identifier I;
    Expression E;
}
