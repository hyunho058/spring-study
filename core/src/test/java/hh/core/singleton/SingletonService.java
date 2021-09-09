package hh.core.singleton;

public class SingletonService {

    private SingletonService(){}

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    public void logic(){
        System.out.println("Singleton 객체 로직 호출");
    }

}
