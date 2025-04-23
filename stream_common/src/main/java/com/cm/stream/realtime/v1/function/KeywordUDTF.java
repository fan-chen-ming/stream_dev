package com.cm.stream.realtime.v1.function;

import com.cm.stream.realtime.v1.utils.KeywordUtil;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

/**
 * @Package com.cm.stream.realtime.v1.function.KeywordUDTF
 * @Author chen.ming
 * @Date 2025/4/21 15:27
 * @description: KeywordUDTF
 */
@FunctionHint(output = @DataTypeHint("ROW<word STRING>"))
public class KeywordUDTF extends TableFunction<Row> {
    public void eval(String text) {
        for (String keyword : KeywordUtil.analyze(text)) {
            collect(Row.of(keyword));
        }
    }
}
