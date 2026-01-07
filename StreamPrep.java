import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StreamPrep {
    public static void main(String[] args) {

        //Predicate <T>
        Predicate<Integer> pred = x-> x%2==0;
        System.out.println(pred.test(2));

        //Function<T,R>
        Function<Integer, Integer> func = x -> x*2;
        Function<Integer, Integer> func2 = x -> x*3;
        System.out.println(func.apply(2));
        System.out.println(func.andThen(func2).apply(2));

        //Consumer<T>
        Consumer<Integer> con = x -> System.out.println(x);
        con.accept(7);

        //Supplier
        Supplier<Integer> supplier = () -> 100;
        System.out.println(supplier.get());

        System.out.println();


    }
}
