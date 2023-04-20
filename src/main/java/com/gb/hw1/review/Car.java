package com.gb.hw1.review;

interface Movable {
    void move();
}
interface Stoppable {
    void stop();
}
// вообще странные эти два интерфейса: зачем они нужны, если есть родительский класс Car?
// По смыслу автомобиль должен иметь возможность и двигаться, и останавливаться в любом варианте.
abstract class Car {
    private Engine engine; // лучше поставить private
    private String color;
    private String name;

    // конструктур, мне кажется, не помешал бы
    protected void start() { // возможно, стоит поставить public
        System.out.println("Car is starting");
    }
    abstract void open();
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
class LightWeightCar extends Car implements Movable {
    @Override
    void open() { // public?
        System.out.println("Car is open");
    }
    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}
class Lorry extends Car implements Movable, Stoppable { // extends для классов, implements для интерфейсов
    @Override // рекомендуется поставить аннотацию
    public void move(){
        System.out.println("Car is moving");
    }
    @Override // рекомендуется поставить аннотацию
    public void stop(){
        System.out.println("Car is stopping");
    }
    @Override
    void open() { // необходимо реализовать этот метод из абстрактного класса
        System.out.println("Lorry is open");
    }
}

class Engine {

}
