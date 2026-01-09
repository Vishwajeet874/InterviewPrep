import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;

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

        con.accept(func.apply(supplier.get()));

        //UnaryOperator, BinaryOperator
        UnaryOperator<Integer> unary = x -> x*2;
        BinaryOperator<Integer> binaryOperator = (x, y) -> x*2 + y*2;
        con.accept(binaryOperator.apply(2,5));

        //Method reference --> use method invoking & in place of lambda expression
        List<String> names=Arrays.asList("Ram","Shyam","Jam");
        names.forEach(System.out::println);

        List<Employee> list = names.stream().map(Employee::new).toList();

        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<Integer> list1 = numberList.stream().filter(n -> n % 2 == 0).toList();
        long count = numberList.stream().filter(n -> n % 2 == 0).count();
        System.out.println(count);

        Stream<Integer> generate = Stream.generate(()->1);
        System.out.println(generate);

//        Stream.iterate(1, x -> x+1)
//                .limit(10)
//                .forEach(System.out::println);

        Random r = new Random();

        Stream.generate(() -> r.nextInt(100)) // Supplier
                .limit(5)                            // Essential! Prevents infinite loop
                .forEach(System.out::println);

        List<Integer> collect = Stream.iterate(1,x->x*2).limit(5).collect(Collectors.toList());
        System.out.println(collect);

        // filter
        List<String> fruits = Arrays.asList("Apple","Banana", "Strawberry", "Mango", "Orange","Apple");
        List<String> a = fruits.stream().filter(x -> x.startsWith("A")).collect(Collectors.toList());
        System.out.println(a);


        //map
        List<String> mapped = fruits.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(mapped);

        //sorted
        List<String> sortedByLength = fruits.stream()
                .sorted((str1, str2) -> str1.length() - str2.length())
                .collect(Collectors.toList());

        //distinct
        List<String> a1 = fruits.stream().filter(x -> x.startsWith("A")).distinct().collect(Collectors.toList());
        System.out.println(a1);

        //limit
        List<Integer> collect1 = Stream.iterate(1, x -> x + 1).limit(10).collect(Collectors.toList());
        System.out.println(collect1);

        //skip
        Integer i = Stream.iterate(1, x -> x + 1).limit(10).sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(i);


    }

    public record Employee(String name) {}
}


