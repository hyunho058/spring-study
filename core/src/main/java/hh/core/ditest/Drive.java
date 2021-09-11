package hh.core.ditest;

public class Drive {
     private Car car;

     public Drive(Car car){
         this.car = car;
     }
     public void drivingCar(){
         this.car.start();;
     }
}
