package org.example;

import java.util.concurrent.Semaphore;

class Fumador extends Thread {
    private String ingredienteFaltante;
    private Semaphore semProveedor;
    private Semaphore semFumador;
    private Semaphore semMesa;

    public Fumador(String nombre, String ingredienteFaltante, Semaphore semProveedor, Semaphore semFumador, Semaphore semMesa) {
        super(nombre);
        this.ingredienteFaltante = ingredienteFaltante;
        this.semProveedor = semProveedor;
        this.semFumador = semFumador;
        this.semMesa = semMesa;
    }

    public void fumar() throws InterruptedException {
        System.out.println(Fumador.super.getName() + " se pone a fumar.");
        Thread.sleep(2000);
        System.out.println(Fumador.super.getName() + " ha terminado de fumar.");
    }

    @Override
    public void run() {
        while (true) {
            try {
                semFumador.acquire();
                semMesa.acquire();
                System.out.println(getName() + " toma los ingredientes de la mesa.");
                semMesa.release();
                semProveedor.release();
                fumar();
                semFumador.release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}