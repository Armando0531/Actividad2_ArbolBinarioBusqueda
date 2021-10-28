class NodoArbol{

	private NodoArbol nodoIzq;
	private int dato;
	private NodoArbol nodoDer;
	
	public NodoArbol(int dato) {
		this.dato = dato;
	}
	
	public NodoArbol getNodoIzq() {
		return nodoIzq;
	}

	public void setNodoIzq(NodoArbol nodoIzq) {
		this.nodoIzq = nodoIzq;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public NodoArbol getNodoDer() {
		return nodoDer;
	}

	public void setNodoDer(NodoArbol nodoDer) {
		this.nodoDer = nodoDer;
	}
	
	public boolean menorQue(int dato) {
		return this.dato<dato;
	}

	public boolean mayorQue(int dato) {
		return this.dato>dato;
	}
	
	@Override
	public String toString() {
		return "NodoArbol [dato=" + dato + "]";
	}

}

class ArbolBinarioBusqueda{
	NodoArbol nodoRaiz;
	
	public ArbolBinarioBusqueda() {
		crearArbol();
	}
	public void crearArbol() {
		nodoRaiz=null;
	}

	public void agregarDato(int dato) {
		NodoArbol nuevoNodo = new NodoArbol(dato);
		if(nodoRaiz==null) {
			nodoRaiz = nuevoNodo;
		}else {
			
			NodoArbol aux = nodoRaiz;
			NodoArbol nodoAnterior;
			
			while(aux!=null) {
				nodoAnterior = aux;
				
				if(dato>aux.getDato()) { 
					aux = aux.getNodoDer();
					if(aux==null)
						nodoAnterior.setNodoDer(nuevoNodo);
				}else { 
					aux = aux.getNodoIzq();
					if(aux==null)
						nodoAnterior.setNodoIzq(nuevoNodo);
				}
			}
		}
	}
	//3) Eliminar
	public boolean eliminarElemento(int dato) {
		
		if(!(nodoRaiz==null)) {
			
			NodoArbol anterior = nodoRaiz;
			NodoArbol aux = nodoRaiz;
			String ladoArbol ="";
			
			//proceso de busqueda----------------------------------------
			while(aux.getDato() != dato) {
				anterior = aux;
				if(dato<=aux.getDato()) { //izquierda
					aux = aux.getNodoIzq();
					ladoArbol = "IZQ";
				}else {//Derecha
					aux = aux.getNodoDer();
					ladoArbol = "DER";
				}
				
				if(aux==null) {
					System.out.println("buscado y no encontrado");
					return false;
				}
			}//while
			
			System.out.println("Encontrado");

			
			//proceso de eliminacion  (se encontro el dato)------------------------
			
			//Escenario 1: El nodo a eliminar es HOJA
			if(aux.getNodoIzq()==null && aux.getNodoDer()==null) { //verficar si es hoja
				if(aux==nodoRaiz)
					nodoRaiz = null;
				else if(ladoArbol.equals("IZQ"))
					anterior.setNodoIzq(null);
				else
					anterior.setNodoDer(null);
			}else if(aux.getNodoIzq()==null) { //verificar si tiene un solo hijo a la IZQ
				if(aux==nodoRaiz)
					nodoRaiz = aux.getNodoIzq();
				else if(ladoArbol.equals("IZQ"))
					anterior.setNodoIzq(aux.getNodoIzq());
				else
					anterior.setNodoDer(aux.getNodoIzq());
			}else if(aux.getNodoDer()==null) { //verificar si tiene un solo hijo a la DER
				if(aux==nodoRaiz)
					nodoRaiz = aux.getNodoDer();
				else if(ladoArbol.equals("IZQ"))
					anterior.setNodoIzq(aux.getNodoDer());
				else
					anterior.setNodoDer(aux.getNodoDer());
			
			
			}else { // de lo contrario TIENE DOS HIJOS -----------------------------------
				
				//----------------------------------------------------------------
				NodoArbol reemplazo = reemplazar(aux);

				if(aux==nodoRaiz)
					nodoRaiz = reemplazo;
				else if(ladoArbol.equals("IZQ"))
					anterior.setNodoIzq(reemplazo);
				else
					anterior.setNodoDer(reemplazo);
				
				reemplazo.setNodoIzq(aux.getNodoIzq());
			}
			return true;
				//----------------------------------------------------------------
			
		}else {
			System.out.println("Arbol vacio");
			return false;
		}
	}
	
	public NodoArbol reemplazar(NodoArbol nodo){
		NodoArbol reemplazarPadre = nodo;
		NodoArbol reemplazo = nodo;
		NodoArbol auxiliar = nodo.getNodoDer();
		
		while(auxiliar != null){
			reemplazarPadre = reemplazo;
			reemplazo = auxiliar;
			auxiliar= auxiliar.getNodoIzq();
		}
		if(reemplazo!=nodo.getNodoDer()){
			reemplazarPadre.setNodoIzq(reemplazo.getNodoDer());
			reemplazo.setNodoDer(nodo.getNodoDer());
		}
		return reemplazo;		

	}//metodo reemplazar
	
	public void recorridoPreorden(NodoArbol nodoRaiz) {
		if(!(nodoRaiz==null)) {
			System.out.println(nodoRaiz);
			recorridoPreorden(nodoRaiz.getNodoIzq());
			recorridoPreorden(nodoRaiz.getNodoDer());
		}
	}
	public void recorridoInorden(NodoArbol nodoRaiz) {
		if(!(nodoRaiz==null)) {
			recorridoInorden(nodoRaiz.getNodoIzq());
			System.out.print(nodoRaiz.getDato() + " => ");
			recorridoInorden(nodoRaiz.getNodoDer());
		}
	}
	public void recorridoPostorden(NodoArbol nodoRaiz) {
		if(!(nodoRaiz==null)) {
			recorridoPostorden(nodoRaiz.getNodoIzq());
			recorridoPostorden(nodoRaiz.getNodoDer());
			System.out.print(nodoRaiz.getDato() + " => ");
		}
	}
	
	public void buscarDatoMayor() {
		if(nodoRaiz==null) {
			System.out.println("No hay elementos");
		}else {
			obtenerMayor(nodoRaiz, nodoRaiz.getNodoDer());
		}
	}
	public void obtenerMayor(NodoArbol anterior, NodoArbol actual) {
		if(actual==null) {
			System.out.println("El mayor es: " + anterior.getDato());
		}else {
			obtenerMayor(anterior.getNodoDer(), actual.getNodoDer());
		}
	}
	
	public void buscarDatoMenor() {
		if(nodoRaiz==null) {
			System.out.println("No hay elementos");
		}else {
			obtenerMenor(nodoRaiz,nodoRaiz.getNodoIzq());
		}
	}
	public void obtenerMenor(NodoArbol anterior, NodoArbol actual) {
		if(actual==null) {
			System.out.println("El menor es: " + anterior.getDato());
		}else {
			obtenerMenor(anterior.getNodoIzq(), actual.getNodoIzq());
		}
	}
	
	public void buscarDato(int dato) {
		if(nodoRaiz == null) {
			System.out.println("Elemento no encontrado");
		}else if(nodoRaiz.menorQue(dato)) {
			buscar(nodoRaiz.getNodoDer(), dato);
		}else if(nodoRaiz.mayorQue(dato)) {
			buscar(nodoRaiz.getNodoIzq(), dato);
		}else {
			System.out.println("encontrado");
		}
	}
	public void buscar(NodoArbol raiz, int dato) {
		if(raiz == null) {
			System.out.println("Elemento no encontrado");
		}else if(raiz.menorQue(dato)) {
			buscar(raiz.getNodoDer(), dato);
		}else if(raiz.mayorQue(dato)) {
			buscar(raiz.getNodoIzq(), dato);
		}else {
			System.out.println("encontrado");
		}
	}
	
}//class
public class PruebaArbolBinario {

	public static void main(String[] args) {
		

	}

}
