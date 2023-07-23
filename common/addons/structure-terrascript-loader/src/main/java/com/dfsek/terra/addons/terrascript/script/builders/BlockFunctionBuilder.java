/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.script.builders;

import java.util.List;

import com.dfsek.terra.addons.terrascript.parser.exceptions.ParseException;
import com.dfsek.terra.addons.terrascript.parser.lang.Expression;
import com.dfsek.terra.addons.terrascript.parser.lang.constants.BooleanConstant;
import com.dfsek.terra.addons.terrascript.parser.lang.constants.StringConstant;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.addons.terrascript.script.functions.BlockFunction;
import com.dfsek.terra.addons.terrascript.tokenizer.SourcePosition;
import com.dfsek.terra.api.Platform;


public class BlockFunctionBuilder implements FunctionBuilder<BlockFunction> {
    private final Platform platform;
    
    public BlockFunctionBuilder(Platform platform) {
        this.platform = platform;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public BlockFunction build(List<Expression<?>> argumentList, SourcePosition position) {
        if(argumentList.size() < 4) throw new ParseException("Expected data", position);
        Expression<Boolean> booleanReturnable = new BooleanConstant(true, position);
        if(argumentList.size() == 5) booleanReturnable = (Expression<Boolean>) argumentList.get(4);
        if(argumentList.get(3) instanceof StringConstant) {
            return new BlockFunction.Constant((Expression<Number>) argumentList.get(0), (Expression<Number>) argumentList.get(1),
                                              (Expression<Number>) argumentList.get(2), (StringConstant) argumentList.get(3),
                                              booleanReturnable, platform, position);
        }
        return new BlockFunction((Expression<Number>) argumentList.get(0), (Expression<Number>) argumentList.get(1),
                                 (Expression<Number>) argumentList.get(2), (Expression<String>) argumentList.get(3), booleanReturnable,
                                 platform, position);
    }
    
    @Override
    public int argNumber() {
        return -1;
    }
    
    @Override
    public Expression.ReturnType getArgument(int position) {
        return switch(position) {
            case 0, 1, 2 -> Expression.ReturnType.NUMBER;
            case 3 -> Expression.ReturnType.STRING;
            case 4 -> Expression.ReturnType.BOOLEAN;
            default -> null;
        };
    }
}
