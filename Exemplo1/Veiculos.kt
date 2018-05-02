fun main(args: Array<String>) {

    // Conhecendo os mapas e testando seus usos
    // O codigo abaixo refere-se à um exemplo mostrado pelo prof. Thiago para demonstrar o uso dos mapas em Java
    /* Exemplo de Mapas em Java:
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

	public static void main(String[] args) {
		Map vehicles = new HashMap();

		// Add some vehicles.
		vehicles.put("BMW", 5);
		vehicles.put("Mercedes", 3);
		vehicles.put("Audi", 4);
		vehicles.put("Ford", 10);

		System.out.println("Total vehicles: " + vehicles.size());

		// Iterate over all vehicles, using the keySet method.
		for(String key: vehicles.keySet())
			System.out.println(key + " - " + vehicles.get(key));
		System.out.println();

		String searchKey = "Audi";
		if(vehicles.containsKey(searchKey))
			System.out.println("Found total " + vehicles.get(searchKey) + " "
					+ searchKey + " cars!\n");

		// Clear all values.
		vehicles.clear();

		// Equals to zero.
		System.out.println("After clear operation, size: " + vehicles.size());
	}
}
*/

    // Exemplo citado acima transcrito em Kotlin
    val veiculos = mapOf("BMW" to 5, "Mercedes" to 3, "Audi" to 4, "Ford" to 10)

    println("Total de veículos: ${veiculos.size}")

    for(key: String in veiculos.keys){
        println("$key - ${veiculos[key]}")
    }
    var searchKey = "Audi"
    if(veiculos.containsKey(searchKey)){
        println("Total encontrados: ${veiculos[searchKey]} carros")
    }
}