# TP-Progra-III-
Primer TP "Calculadora"

**Objetivo**
El objetivo que se nos pidio en este trabajo práctico es implementar una aplicacion que simule una calculadora simple de bolsillo, separando el codigo responsable de la logica de la calculadora del codigo encargado de la interfaz (implementada con Swing).

**Responsabilidad de clases:**

- GuiCalculadora se encarga de modelar la interfaz de la aplicacion.
- Calculadora se encarga del funcionamiento de la lógica de la aplicacion.
- CalculadoraTest contiene la suite de test correspondientes a la lógica.

### **Implementación de Calculadora:** 
### **Variables de clase:**

- ArrayList<Double> _numeros_ contendra la secuencia de números que se iran ingresando por un metodo obtenerNumero() que se detallara mas adelante.
- ArrayList<String> _signos_ al igual que el array de numeros esta contendra una secuencia pero de signos(+, -, *, /) que se obtendran a traves del metodo obtenerOperador().
- double _resultado_ contendra el resultado final que se obtendra del metodo calcular().

### **Métodos de clase:**

- _obtenerNumero(double numero)_ se encarga de agregar el double recibido a ***numeros***. Este metodo tiene una condicion, antes de agregar, se fija si por ultima vez la calculadora recibio un signo, de ser verdadero agrega al array de numeros el double recibido, en caso contrario(es decir, que por ultima vez la calculadora recibio un numero) el ultimo numero agregado es reemplazado por el recibido.

- _obtenerOperador(String operador)_ se encarga de agregar el signo recibido a ***signos***. Este metodo tambien tiene condiciones a la hora de agregar, en primer lugar se fija si el string recibido es valido(si es un string distinto de "+", "-", "x", "/" tira una excepcion), en segundo lugar chequea si posteriormente la calculadora recibio algun numero(si el array de numeros tiene por lo menos uno), y en tercer lugar revisa si por ultima vez la calculadora recibio un signo, en caso de que esta condicion sea verdadera el ultimo signo guardado en el array es reemplazado por el recibido y en caso de ser falso lo agrega al array normalmente.

- _calcular()_ se encarga de resolver la operación y almacenar en ***resultado*** el valor obtenido. Antes de operar se fija si al menos el array de numeros tiene uno guardado(tira excepcion sino), tambien borra el ultimo signo ingresado en caso de que por ultima vez se haya ingresado un signo ya que este no tendra relevancia a la hora de resolver la operacion y ademas evitamos futuros resultados mal calculados. Luego de pasar por las condiciones mencionadas este metodo pasa a resolver la operacion, antes de operar con las sumas y restas, llama al metodo resolverOperadoresPrimarios(se detallara mas adelante) que opera primero con las multiplicaciones y divisiones.  

- _resolverOperadoresPrimarios()_ el cual tiene como funcionalidad resolver las operaciones primarias que son la multiplicación y la división. `Terminar de explicar método`

- _reset()_ restaura la calculadora a sus valores predeterminados, asigna el valor 0 al resultado y vacia los arreglos ***numeros*** y ***signos***.

- _borrar()_ método encargado de eliminar el último signo agregado(si por ultima vez se agrego un signo) o bien el último dígito de un número(en caso de que por ultima vez se haya agregado un numero) a través de:
   * _borrarUltimoSigno()_ remueve el último signo agregado (si existe alguno). 
   * _borrarDigitoUltimoNumero()_ remueve el último dígito del ultimo numero ingresado, o lo elimina del array si es un numero de un digito. Se trabaja el número ingresado a través de un String para facilitar el recorrido del mismo.

- `float`_getResultado()_ retorna el resultado final de la operación. Notar que se devuelve al `double` parseado a `float` para que el resultado en caso de ser decimal, sea redondeado y evite números con demasiadas cifras.   

Luego algunos métodos que realizan alguna operación auxilar específica o se utilizan para verificar condiciones: 
 * _cantSignos()_: retorna la cantidad de signos.
 * _ultimoNumero()_: retorna el último número ingresado. 
 * _hayAlgunSigno()_: retorna verdadero si ***signos*** tiene al menos un elemento.
 * _ultimaVezSeIngresoSigno()_: retorna verdero si el tamaño de ***numeros*** y ***signos*** es equivalente.
 * _reemplazarUltimoSigno(String operador)_: reemplaza el último elemento de ***signos*** por el recibido. 
 
 
 

  
