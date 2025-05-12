package com.seaofnodes.simple.node;

import com.seaofnodes.simple.Parser;
import com.seaofnodes.simple.type.Type;
import com.seaofnodes.simple.type.TypeFloat;
import com.seaofnodes.simple.type.TypeInteger;

import java.util.BitSet;

// Corresponds to &&
public class CondOrNode extends Node{
    public CondOrNode(Node lhs, Node rhs) {super(null, lhs, rhs);}

    @Override public String label() { return "CondOr"; }
    @Override public String glabel() { return "||"; }
    public String op() { return "||"; }

    @Override
    public final TypeInteger compute() {
        Type t1 = in(1)._type;
        Type t2 = in(2)._type;

        if( t1 instanceof TypeInteger i1 &&
                t2 instanceof TypeInteger i2 &&
                i1.isConstant() && i2.isConstant()) {
            return i1.value() != 0 || i2.value() != 0
                    ? TypeInteger.TRUE
                    : TypeInteger.FALSE;
        }

        if( t1 instanceof TypeFloat i1 &&
                t2 instanceof TypeFloat i2 &&
                i1.isConstant() && i2.isConstant()) {
            return i1.value() != 0 || i2.value() != 0
                    ? TypeInteger.TRUE
                    : TypeInteger.FALSE;
        }

        return TypeInteger.BOT;
    }

    @Override
    public Node idealize() {
        return null;
    }

    @Override
    public StringBuilder _print1(StringBuilder sb, BitSet visited) {
        in(1)._print0(sb.append("("), visited);
        in(2)._print0(sb.append(op()), visited);
        return sb.append(")");
    }

}
