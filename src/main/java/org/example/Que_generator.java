package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Que_generator {

    ArrayList que = new ArrayList<>();
    Random rnd = new Random();

    public Que_generator(){
//        rnd.setSeed(9223372036854775808);
        rnd.setSeed(922337203);
//        generate_que(lengt_of_que);
    }

    public void generate_que(int duration){
        // generates que of randomly arriving cars
        int i = 0;
        while(i < duration){
            int interval = rnd.nextInt() * 60 * 30 ;  //  max is 1800 = 30 minutes
            Car car = new Car(i);
            this.que.add(car);
            i++;
        }
    }

}
