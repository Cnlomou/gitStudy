package group.jsjxh;

import java.util.function.Supplier;

public abstract class SuplierUtil {

    public static <T> T invokMethod(Supplier<T> supplier){
        return supplier.get();
    }
}
