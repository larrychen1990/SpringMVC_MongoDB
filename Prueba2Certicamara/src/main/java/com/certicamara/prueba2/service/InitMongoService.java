package com.certicamara.prueba2.service;

//import com.certicamara.prueba2.domain.Bodega;
//import com.certicamara.prueba2.domain.Producto;
//import com.certicamara.prueba2.domain.Proveedor;
import com.certicamara.prueba2.domain.Bodega;
import com.certicamara.prueba2.domain.Producto;
import com.certicamara.prueba2.domain.Proveedor;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public void init() {
		// Drop existing collections
		mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("user");

		// Create new records
		

                Bodega b1 = new Bodega();
                b1.setId(UUID.randomUUID().toString());
                b1.setCodigo("123");
                b1.setDireccion("Calle");

                Bodega b2 = new Bodega();
                b2.setId(UUID.randomUUID().toString());
                b2.setCodigo("456");
                b2.setDireccion("Carrera");

                Proveedor pr1 = new Proveedor();
                pr1.setId(UUID.randomUUID().toString());
                pr1.setDireccion("Cl");
                pr1.setMail("@1");
                pr1.setNombre("Prov1");
                pr1.setTelefono("362");

                Proveedor pr2 = new Proveedor();
                pr2.setId(UUID.randomUUID().toString());
                pr2.setDireccion("Kl");
                pr2.setMail("@2");
                pr2.setNombre("Prov2");
                pr2.setTelefono("789");

                Producto p1 = new Producto();
                p1.setId(UUID.randomUUID().toString());
                p1.setNombre("Menta");
                p1.setPrecio((float)100);
                p1.setCantidad(1500);
                p1.setBodega(b1);
                p1.setProveedor(pr1);

                Producto p2 = new Producto();
                p2.setId(UUID.randomUUID().toString());
                p2.setNombre("Galleta");
                p2.setPrecio((float)200);
                p2.setCantidad(2500);
                p2.setBodega(b2);
                p2.setProveedor(pr2);
		
//		 Insert to db
                mongoTemplate.insert(p1);
                mongoTemplate.insert(p2);
                mongoTemplate.insert(b1);
                mongoTemplate.insert(b2);
                mongoTemplate.insert(pr1);
                mongoTemplate.insert(pr2);

	}
}
