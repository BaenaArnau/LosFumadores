package org.example;

import java.util.concurrent.Semaphore;

class Proveedor extends Thread {
    private Semaphore semProveedor;
    private Semaphore semFumador;
    private Semaphore semMesa;

    public Proveedor(Semaphore semProveedor, Semaphore semFumador, Semaphore semMesa) {
        this.semProveedor = semProveedor;
        this.semFumador = semFumador;
        this.semMesa = semMesa;
    }

    public void colocarIngredientes() throws InterruptedException {
        System.out.println("El proveedor coloca ingredientes en la mesa.");
        Thread.sleep(1000); // Simula el tiempo que lleva colocar ingredientes.
    }

    @Override
    public void run() {
        while (true) {
            try {
                semProveedor.acquire();
                semMesa.acquire();
                colocarIngredientes();
                semMesa.release();
                semFumador.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}