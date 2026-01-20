import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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


        //Terminal ops
        List<Integer> ll = Arrays.asList(1, 2, 3, 4);

        //collect
        List<Integer> collect2 = ll.stream().skip(1).collect(Collectors.toList());
        System.out.println(collect2);

        //forEach
        ll.forEach(System.out::println);

        //reduce - combine lements to produce a single result

        Optional<Integer> reduce = ll.stream().reduce((x, y) -> x + y);
        System.out.println(reduce.get());

        //anyMatch,allMatch,noMatch

        //findFirst,findAny

        //Squaring and sorting numbers
        List<Integer> list2 = Arrays.asList(5, -2, 3, -4, 6);
        List<Integer> sqauredList = list2.stream().map(x -> x * x).sorted().toList();
        System.out.println(sqauredList);

        //summing values
        sqauredList.stream().reduce(Integer::sum).ifPresent(System.out::println);

        //counting occurence of character
        String sentence = "Hello World";
        long count1 = sentence.chars().filter(x -> x == 'l').count();
        System.out.println(count1);

        long startTime = System.currentTimeMillis();
        List<Integer> list3 = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        list3.stream().map(x-> f(x)).toList();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");

        startTime = System.currentTimeMillis();
        List<Integer> list4 = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        list4.parallelStream().map(x-> f(x)).toList();
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");

        //Parallel streams are most effective for CPU intensive or large datasets where tasks r independent
        //They may add overhead for simple tasks or small datasets

        //cumulative sum
        //[1,2,3,4,5] -> [1,3,6,10,15]

        Arrays.asList(1,2,3,4,5,6);

        int m=10,n=2;
        Optional<Integer> reduce1 = Stream.iterate(1, x -> x + 1).limit(m).filter(x -> x % n == 0).reduce(Integer::sum);
        System.out.println(reduce1.get());

        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        AtomicInteger sum=new AtomicInteger(0);
        List<Integer> list6 = list5.stream().map(x -> sum.addAndGet(x)).toList();
        System.out.println(list6);

        System.out.println(Stream.of(1,5,10).max(Comparator.naturalOrder()));


        List<String> sentences = Arrays.asList(
                "hello world",
                "Java is good language",
                "I am Vishwajeet Singh"
        );

        list5.parallelStream().forEachOrdered(System.out::println);

        System.out.println(sentences.stream().flatMap(sent-> Arrays.stream(sent.split(" "))).map(String::toUpperCase).toList());

        //using joining
        String list7 =sentences.stream()
                        .flatMap(sent-> Arrays.stream(sent.split(" ")))
                        .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        System.out.println(list7);

        List<String> words = List.of("hello","world","java","is","good language");

        //grouping elements & counting
        //Map<Integer, List<String>> collect3 = words.stream().collect(Collectors.groupingBy(String::length));
        //System.out.println(collect3);
        Map<Integer, Long> collect3 = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(collect3);

        //count word occurence
        String temp= "hello hello java java world back back back";
        System.out.println(Arrays.stream(temp.split("\\s")).collect(Collectors.groupingBy(x->x,Collectors.counting())));

        System.out.println(numberList.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)));

        //summing values of a map

        Map<String,Integer> items = Map.of(
                "Apple",5,
                "Banana",6,
                "Guava",8
        );

        System.out.println(items.values().stream().reduce(Integer::sum));

        List<Integer> list8 = Arrays.asList(1,2,2,2,3,3,4,4,5,6,7,8,9,10);

        System.out.println(list8.stream().distinct().filter(x-> Collections.frequency(list8, x)>1).toList());

        System.out.println(list8.stream().collect(Collectors.summingInt(Integer::intValue)));

        String cleanStr= "ababab";

        boolean isPalindrome = IntStream.range(0, cleanStr.length() / 2)
                .allMatch(x-> cleanStr.charAt(x) == cleanStr.charAt(cleanStr.length() - 1 - x));
        System.out.println(isPalindrome);

        List<String> list9 = Arrays.asList("abc","b","c","d","e","f");
        System.out.println(list9.stream().collect(Collectors.joining(",")));

        System.out.println(list9.stream().max(Comparator.comparing(String::length)).get());

        System.out.println(list9.stream().map(String::toUpperCase).toList());

        System.out.println(list8.stream().reduce(0,(z,b)-> z+b));

//        list8.stream().distinct()
//                .forEach(System.out::println);

         List<Employee> employees = Arrays.asList(
                new Employee(1, "Abraham", 29, "IT", "Mumbai", 20000, "Male"),
                new Employee(2, "Mary", 27, "Sales", "Chennai", 25000, "Female"),
                new Employee(3, "Joe", 28, "IT", "Chennai", 22000, "Male"),
                new Employee(4, "John", 29, "Sales", "Gurgaon", 29000, "Male"),
                new Employee(5, "Liza", 25, "Sales", "Bangalore", 32000, "Female"),
                new Employee(6, "Peter", 27, "Admin", "Mumbai", 31500, "Male"),
                new Employee(7, "Harry", 30, "Research", "Kochi", 21000, "Male")
        );

//        System.out.println(employees.stream().filter(e-> e.getName().startsWith("A")).toList());
//
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames)));
//        System.out.println(employees.stream().count());
//        System.out.println(employees.stream().max(Comparator.comparing(Employee::getAge)));
//        System.out.println(employees.stream().map(e->e.getDepartNames()).distinct().toList());
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames,Collectors.counting())));
//        System.out.println(employees.stream().filter(e-> e.getAge()<30).toList());
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge))));
//
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get());
//        System.out.println(employees.stream().filter(e->e.getAddress()=="Chennai").sorted(Comparator.comparing(Employee::getName)).toList());
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames,Collectors.averagingDouble(Employee::getSalary))));
//        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartNames,Collectors.maxBy(Comparator.comparing(Employee::getSalary)))));

        System.out.println(employees.stream().sorted(Comparator.comparing(Employee::getSalary)).toList());
        System.out.println(employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).skip(1).findFirst().get());
    }

    private static long f(int n){
        long result = 1;

        for(int i=2;i<=n;i++){
            result *= i;
        }
        return result;
    }




}


