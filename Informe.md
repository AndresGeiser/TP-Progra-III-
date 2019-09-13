# TP-Progra-III-
Primer TP "Calculadora"



**Responsabilidad de clases:**

- GuiCalculadora se encarga de modelar la interfaz visual del programa.
- Calculadora se encarga de poner en funcionamiento la lógica del programa.
- CalculadoraTest contiene la suite de test correspondientes a la lógica.


**Implementación de Calculadora:** 
La clase representa el accionar de una calculadora de bolsillo la cual funciona de la siguiente manera y se inicializa con los siguientes parámetros:
- ArrayList<Double> _numeros_ contiene la secuencia de los números que se van ingresando.
- ArrayList<String> _signos_ contiene a los signos que se ingresen en la operación actual.
- double _resultado_ que representa el numero actual de la calculadora.

**Métodos de clase:**

- _obtenerNumero(double numero)_ se encarga de agregar el numero a ***numeros***, si no se ingreso un signo despues de un numero este último es reemplazado por uno nuevo. De lo contrario se agrega al final.
- _obtenerOperador(String operador)_ se encarga de agregar el signo ingresado a ***signos*** siempre y cuando ***numeros*** contenga al menos un elemento. Si se ingresan dos signos seguidos el ultimo reemplazara al primero y solo se admiten los signos de suma, resta, multiplicación y división. 




  
