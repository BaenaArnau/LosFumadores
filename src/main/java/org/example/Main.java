package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semProveedor = new Semaphore(1);
        Semaphore semFumador = new Semaphore(0);
        Semaphore semMesa = new Semaphore(1); // Semáforo para controlar el acceso a la mesa.

        Fumador fumadorPapel = new Fumador("Saul", "papel", semProveedor, semFumador, semMesa);
        Fumador fumadorTabaco = new Fumador("Biel", "tabaco", semProveedor, semFumador, semMesa);
        Fumador fumadorFosforo = new Fumador("D.Simos", "fósforo", semProveedor, semFumador, semMesa);
        Proveedor proveedor = new Proveedor(semProveedor, semFumador, semMesa);

        fumadorPapel.start();
        fumadorTabaco.start();
        fumadorFosforo.start();
        proveedor.start();
    }
}
