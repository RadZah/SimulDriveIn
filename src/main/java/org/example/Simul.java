package org.example;

public class Simul {

    private static Que_generator que_a = new Que_generator();
    private static Que_generator que_b = new Que_generator();

    public void Simul(){
        // this.init_ques();
    }

    public void init_ques(){
        this.que_a.generate_que(20);
        this.que_b.generate_que(20);
    }

    public void serve_ques(){
        // here we will process the queues


    }

}
