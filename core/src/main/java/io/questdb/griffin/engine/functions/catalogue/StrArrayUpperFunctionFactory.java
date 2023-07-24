package io.questdb.griffin.engine.functions.catalogue;

import io.questdb.cairo.CairoConfiguration;
import io.questdb.cairo.sql.Function;
import io.questdb.cairo.sql.Record;
import io.questdb.griffin.FunctionFactory;
import io.questdb.griffin.SqlException;
import io.questdb.griffin.SqlExecutionContext;
import io.questdb.griffin.engine.functions.IntFunction;
import io.questdb.griffin.engine.functions.StrArrayFunction;
import io.questdb.griffin.engine.functions.UnaryFunction;
import io.questdb.std.IntList;
import io.questdb.std.ObjList;


public class StrArrayUpperFunctionFactory implements FunctionFactory {
    @Override
    public String getSignature() {
        return "array_upper(wI)";
    }

    @Override
    public Function newInstance(
            int position,
            ObjList<Function> args,
            IntList argPositions,
            CairoConfiguration configuration,
            SqlExecutionContext sqlExecutionContext
    ) throws SqlException {
        return new StrArrayUpperFunction((StrArrayFunction) args.getQuick(0));
    }

    private static final class StrArrayUpperFunction extends IntFunction implements UnaryFunction {
        private final StrArrayFunction arrayFunction;

        public StrArrayUpperFunction(StrArrayFunction arrayFunction) {
            this.arrayFunction = arrayFunction;
        }

        @Override
        public Function getArg() {
            return arrayFunction;
        }

        @Override
        public int getInt(Record rec) {
            return arrayFunction.getArrayLength();
        }
    }
}
