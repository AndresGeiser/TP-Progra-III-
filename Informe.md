# TP-Progra-III-
Primer TP "Calculadora"



**Responsabilidad de clases:**

- GuiCalculadora se encarga de modelar la interfaz visual del programa.
- Calculadora se encarga de poner en funcionamiento la lógica del programa.
- CalculadoraTest contiene la suite de test correspondientes a la lógica.


### **Implementación de Calculadora:** 
La clase representa el accionar de una calculadora de bolsillo la cual funciona de la siguiente manera y se inicializa con los siguientes parámetros:
- ArrayList<Double> _numeros_ contiene la secuencia de los números que se ingresan.
- ArrayList<String> _signos_ contiene a los signos que se ingresen en la operación actual.
- double _resultado_ que representa el numero actual de la calculadora.

### **Métodos de clase:**

- _obtenerNumero(double numero)_ se encarga de agregar el numero a ***numeros***, si no se ingreso un signo despues de un numero este último es reemplazado por uno nuevo. De lo contrario se agrega al final.
- _obtenerOperador(String operador)_ se encarga de agregar el signo ingresado a ***signos*** siempre y cuando ***numeros*** contenga al menos un elemento. Si se ingresan dos signos seguidos el ultimo reemplazara al primero y solo se admiten los signos de suma, resta, multiplicación y división. 
- _calcular()_ se encarga de resolver la operación ingresada que contenga sumas o restas, almacenarlas en ***resultado*** y limpiar el último número trabajado de ***numeros***. Las demas operaciones se resuelven mediante el método auxiliar _resolverOperadoresPrimarios()_.
- _resolverOperadoresPrimarios()_ el cual tiene como funcionalidad resolver las operaciones primarias que son la multiplicación y la división. `Terminar de explicar método`
- _reset()_ restaura la calculadora a sus valores predeterminados, asigna el valor 0 al resultado y vacia los arreglos ***numeros*** y ***signos***.
- _borrar()_ es un método auxiliar que es utilizado para eliminar el último signo agregado o bien el último dígito de un número a través de:
   * _borrarUltimoSigno()_ remueve el último signo agregado (si existe alguno). 
   * _borrarDigitoUltimoNumero()_ remueve el último dígito de el número ingresado, o el último número si este contiene un solo dígito. Se trabaja el número ingresado a través de un String para facilitar el recorrido del mismo.
- `float`_getResultado()_ retorna el resultado final de la operación. Notar que se devuelve al `double` parseado a `float` para que el resultado en caso de ser decimal, sea redondeado y evite números con demasiadas cifras.   

Luego algunos métodos que realizan alguna operación auxilar específica o se utilizan para verificar condiciones: 
 * _cantSignos()_: retorna la cantidad de signos.
 * _ultimoNumero()_: retorna el último número ingresado. 
 * _hayAlgunSigno()_: retorna verdadero si ***signos*** tiene al menos un elemento.
 * _getUltimoSigno()_: retorna el último signo ingresado.
 * _ultimaVezSeIngresoSigno()_: retorna verdero si el tamaño de ***numeros*** y ***signos*** es equivalente.
 * _reemplazarUltimoSigno(String operador)_: reemplaza al último elementos en ***signos*** por el recibido. 
 
 
 

  
