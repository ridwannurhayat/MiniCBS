package id.ridwan.minicbs;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    static void main(String... args) {
        Quarkus.run(args);
        System.out.println("Running main method");
    }
}