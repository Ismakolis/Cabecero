package services;

import modelos.Productos;

import java.util.Arrays;
import java.util.List;

public class ProductoServicesImplement implements Productoservicio {
    @Override
    public List<Productos> listar() {
        return Arrays.asList(new Productos(1,"cafe","computacion",527.25),
                new Productos(2,"Cocina","Tecnologia",200),
                new Productos(3,"cuadernos","utilies escolares",20),
                new Productos(4,"Refrigeradora","Hogar",300));

    }
}
