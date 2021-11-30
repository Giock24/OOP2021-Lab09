package experiments.with.lambda;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

@FunctionalInterface // messo per auto-controllo, controlla se è veramente un'interfaccia funzionale
interface I {
    int m(String s); // (String s) => return int 
}

@FunctionalInterface
interface Constructor {
    Object create();
}

@FunctionalInterface
interface Filter<X> {
    boolean filter(X n);
}

public class MyMain {
    
    private MyMain() { }
    
    public static <X> Set<X> filter (final Set<X> set, final Filter<X> filter) {
        final Set<X> newSet = new HashSet<>();
        for (final var numbers : set) {
            if (filter.filter(numbers)) {
                newSet.add(numbers);
            }
        }
        return newSet;
    }
    
    public static <X> Set<X> filter2 (final Set<X> set, final Predicate<X> filter) {
        final Set<X> newSet = new HashSet<>();
        for (final var numbers : set) {
            if (filter.test(numbers)) {
                newSet.add(numbers);
            }
        }
        return newSet;
    }

    public static void main(final String... arg) {
        
        //final I obj = (String string) -> {return string.length();};
        final I obj2 = String::length; // dato che usa il metodo dell'oggetto String
                                       // si può scrivere anche in questo modo
        
        System.out.println(obj2.m("aooooo"));
        
        final Constructor o = () -> new Object();
        final Constructor o2 = Object::new;
        final Constructor time = java.util.Date::new;
        
        System.out.println(time.create());
        
        final var set = Set.of(-10, 20, -50, 70, 0);
        System.out.println(filter2(set, n -> n <= 0)); // Set:[20, 70, 0]
        System.out.println(filter(set, new Filter<Integer>() { // versione pre-lambda

            @Override
            public boolean filter(final Integer n) {
                return n >= 0;
            }
            
        }));
        
        final List<Integer> list = List.of(1,2,3,4,5,6);
        list.forEach(n -> System.out.print("|"+ n));
        System.out.print("\n");
        
        // Costruzione List con Stringhe Opzionali...
        final List<Optional<String>> listOp = List.of(
                    Optional.of("ciao"),
                    Optional.of("1"),
                    Optional.of("2"),
                    Optional.empty(),
                    Optional.of("ciao2")
                );
        
        listOp.forEach(i -> System.out.print("|"+ i.orElse("<null>")));
        System.out.print("\n");
        
        listOp.forEach(i -> System.out.print("|" + i.map(s -> s.length()))); // Optional.map trasforma un tipo di dato in un'altro (T -> S)
        System.out.print("\n");
        
        listOp.forEach(i -> System.out.print("|" + i.filter(s -> s.length() == 1))); // Optional.fiter filtra usando un <<Predicate>>
        System.out.print("\n");

    }

}
