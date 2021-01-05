package processor;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class JsonTimeValueProcessor implements JsonValueProcessor {
    private String format = "HH:mm:ss";


    public JsonTimeValueProcessor() {
        super();
    }


    public JsonTimeValueProcessor(String format) {
        super();
        this.format = format;
    }


    @Override
    public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
        return myProcess(paramObject);
    }


    private Object myProcess(Object value) {
        if (value instanceof Time) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }


    @Override
    public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
        return myProcess(paramObject);
    }
}
