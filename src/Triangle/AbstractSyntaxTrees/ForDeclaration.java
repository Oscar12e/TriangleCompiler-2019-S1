package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ForDeclaration extends Declaration {

    public ForDeclaration(Identifier iAST, Expression eAST1, SourcePosition pos){
        super(pos);
        I = iAST;
        E = eAST1;
    }

    public Object visit(Visitor v, Object o) {
        return v.visitForDeclaration(this, o);
    }

    public Identifier I;
    public Expression E;
}
