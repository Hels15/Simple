package com.seaofnodes.simple.node.cpus.x86_64_v2;

import com.seaofnodes.simple.*;
import com.seaofnodes.simple.codegen.*;
import com.seaofnodes.simple.node.*;

public class CondAndX86 extends MachConcreteNode implements MachNode {
    CondAndX86( Node and ) { super(and); }
    @Override public String op() { return "CondAnd"; }
    @Override public RegMask regmap(int i) {
        return (i==1) ? x86_64_v2.RAX_MASK : x86_64_v2.RMASK;
    }
    @Override public RegMask outregmap() { return x86_64_v2.RAX_MASK; }

    @Override public void encoding( Encoding enc ) {
        short a = enc.reg(in(1));
        short b = enc.reg(in(2));

        // setne   al
        //    movzx   a, al
        if( a >= 4 ) enc.add1(x86_64_v2.rex(0, a, 0, false));
        enc.add1(0x0F);         // opcode

        enc.add1(0x95);
        enc.add1(x86_64_v2.modrm(x86_64_v2.MOD.DIRECT, 0, a));

        // low 8 bites are set, now zero extend for next instruction
        if( a >= 8 ) enc.add1(x86_64_v2.rex(a, a, 0, false));
        enc.add1(0x0F); // opcode
        enc.add1(0xB6); // opcode
        enc.add1(x86_64_v2.modrm(x86_64_v2.MOD.DIRECT, a, a));


        // setne   cl
        //    movzx   a, al
        if( b >= 4 ) enc.add1(x86_64_v2.rex(0, b, 0, false));
        enc.add1(0x0F);         // opcode

        enc.add1(0x95);
        enc.add1(x86_64_v2.modrm(x86_64_v2.MOD.DIRECT, 0, b));

        // low 8 bites are set, now zero extend for next instruction
        if( b >= 8 ) enc.add1(x86_64_v2.rex(b, b, 0, false));
        enc.add1(0x0F); // opcode
        enc.add1(0xB6); // opcode
        enc.add1(x86_64_v2.modrm(x86_64_v2.MOD.DIRECT, b, b));

        enc.add1(x86_64_v2.rex(a, b, 0));
        enc.add1(0x23); // opcode
        enc.add1(x86_64_v2.modrm(x86_64_v2.MOD.DIRECT, a, b));
    }

    @Override public void asm(CodeGen code, SB sb) {
        sb.p(code.reg(this)).p(" = ").p(code.reg(in(1))).p(" && ").p(code.reg(in(2)));
    }

}
