package experiments.with.lambda;

import java.util.HashSet;
import java.util.Set;

interface I {
    int m(String s); // (String s) => return int 
}

interface Constructor {
    Object create();
}

public class MyMain {
    
    public static Set<Integer> filter (final Set<Integer> set) {
        final Set<Integer> newSet = new HashSet<>();
        for (final Integer numbers : set) {
            if (numbers >= 0) {
                newSet.add(numbers);
            }
        }
        return newSet;
    }

    public static void main(final String... arg) {
        
        final I obj = (String string) -> {return string.length();};
        final I obj2 = String::length; // dato che usa il metodo dell'oggetto String
                                       // si può scrivere anche in questo modo
        
        System.out.println(obj2.m("aooooo"));
        
        final Constructor o = () -> new Object();
        final Constructor o2 = Object::new;
        final Constructor time = java.util.Date::new;
        
        System.out.println(time.create());
        
        var set = Set.of(-10, 20, -50, 70, 0);
        System.out.println(filter(set)); // Set:[20, 70, 0]

    }

}
