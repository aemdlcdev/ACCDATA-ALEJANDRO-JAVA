/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.filedomxml.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Clase para gestionar el contenido de un documento XML utilizando DOM.
 * 
 * @version 1.0
 * Creado el 16 oct 2024
 * 
 */
public class GestionContenidoDom {
    
        Document documento;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;

        /**
         * Constructor que inicializa el documento XML con un nodo principal.
         * 
         * @param nombre Nombre del nodo principal
         */
        public GestionContenidoDom(String nombre){
            try {
                factory = DocumentBuilderFactory.newInstance();
                builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                this.documento = (Document) implementation.createDocument(null, nombre, null);
                this.documento.setXmlVersion("1.0");
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Añade un nodo al documento.
         * 
         * @param nombreNodo Nombre del nodo
         * @return nodoPrincipal El nodo creado
         */
        public Element addNodo(String nombreNodo){
            Element nodoPrincipal = this.documento.createElement(nombreNodo);
            this.documento.getDocumentElement().appendChild(nodoPrincipal);
            return nodoPrincipal;
        }

        /**
         * Añade un nodo hijo a un nodo padre existente.
         * 
         * @param datoEmpleado Nombre del nodo hijo
         * @param elementoPadre Nodo padre
         * @return El nodo hijo creado
         */
        public Element addNodo(String datoEmpleado, Element elementoPadre){
            Element dato = this.documento.createElement(datoEmpleado);
            elementoPadre.appendChild(dato);
            return dato;
        }
        /**
        * Añade un nodo "Cargo" con el valor "Por especificar" a todos los elementos "Empleado" en el documento.
        */
        
        public void addCargoToAllEmpleados() {
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element empleadoElement = (Element) nodeList.item(i);
                addNodoYTexto("Cargo","Por especificar",empleadoElement);
            }
        }

        /**
         * Añade un nodo con texto a un nodo raíz.
         * 
         * @param nombreNodo Nombre del nodo
         * @param texto Texto del nodo
         * @param raiz Nodo raíz
         */
        public void addNodoYTexto(String nombreNodo, String texto, Element raiz) {
            Element nodo = this.documento.createElement(nombreNodo);
            Text textoNodo = this.documento.createTextNode(texto);
            nodo.appendChild(textoNodo);
            raiz.appendChild(nodo);
        }

        /**
         * Añade dos nodos con texto a un nodo raíz.
         * 
         * @param datoEmpleadoI Nombre del primer nodo
         * @param datoEmpleadoD Nombre del segundo nodo
         * @param texto Texto del primer nodo
         * @param raiz Nodo raíz
         */
        public void addNodoYTexto(String datoEmpleadoI, String datoEmpleadoD, String texto, Element raiz){
            Element datoI = this.documento.createElement(datoEmpleadoI);
            Element datoD = this.documento.createElement(datoEmpleadoD);
            Text textoDato = this.documento.createTextNode(texto);
            Text textoDatoD = this.documento.createTextNode("Por especificar");
            datoI.appendChild(textoDato);
            datoD.appendChild(textoDatoD);
            raiz.appendChild(datoI);
            raiz.appendChild(datoD);
        }

        /**
         * Añade tres nodos con texto a un nodo raíz.
         * 
         * @param datoEmpleadoI Identificador
         * @param datoEmpleadoC Cargo
         * @param datoEmpleadoS Salario
         * @param texto Texto del primer nodo
         * @param salario Salario del empleado
         * @param raiz Nodo raíz
         */
        public void addNodoYTexto(String datoEmpleadoI, String datoEmpleadoC, String datoEmpleadoS, String texto, double salario, Element raiz){
            Element datoI = this.documento.createElement(datoEmpleadoI);
            Element datoC = this.documento.createElement(datoEmpleadoC);
            Element datoS = this.documento.createElement(datoEmpleadoS);

            Text textoDato = this.documento.createTextNode(texto);
            Text textoDatoC = this.documento.createTextNode("Por especificar");
            Text textoDatoS = this.documento.createTextNode(salario+"");

            datoI.appendChild(textoDato);
            datoC.appendChild(textoDatoC);
            datoS.appendChild(textoDatoS);

            raiz.appendChild(datoI);
            raiz.appendChild(datoC);
            raiz.appendChild(datoS);
        }

        /**
         * Agrega un empleado al documento XML.
         * 
         * @param empleado Objeto Empleado a agregar
         */
        public void agregarEmpleado(Empleado empleado) {
            Element empleadoElement = addNodo("Empleado");
            addNodoYTexto("identificador", String.valueOf(empleado.getIdentificador()), empleadoElement);
            addNodoYTexto("apellido", empleado.getApellido(), empleadoElement);
            addNodoYTexto("departamento", String.valueOf(empleado.getDepartamento()), empleadoElement);
            addNodoYTexto("salario", String.valueOf(empleado.getSalario()), empleadoElement);
            addNodoYTexto("cargo", empleado.getCargo(), empleadoElement);
        }


        /**
         * Crea un transformer y le da formato.
         * 
         * @param indent Indentación para el formato del XML yes/no
         * @return transformer con formato
         */
        private Transformer preProcess(String indent){
            Transformer transformer = null;
            try {
                transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT , indent); // Para que salga el sangrado y no todo en una línea
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                return transformer;
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
            return transformer;
        }

        /**
         * Muestra el contenido del documento XML en la consola.
         * 
         * @param indent Indentación para el formato del XML
         */
        public void mostrarPantalla(String indent){
            try {
                Source source = new DOMSource(this.documento);
                Result salida = new StreamResult(System.out);
                preProcess(indent).transform(source, salida);
            } catch (TransformerException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Genera un archivo XML a partir del documento DOM.
         * 
         * @param nombreArchivo Nombre del archivo a generar
         * @param indent Indentación para el formato del XML
         */
        public void generarArchivoDelDOM(String nombreArchivo, String indent){
            try {
                Source source = new DOMSource(this.documento);
                Result salida = new StreamResult(new File(nombreArchivo));
                preProcess(indent).transform(source, salida);
            } catch (TransformerException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Carga un archivo XML en memoria.
         * 
         * @param nombreArchivo Nombre del archivo a cargar
         */
        public void cargarArchivoEnMemoria(String nombreArchivo){
            try {
                this.documento = this.builder.parse(new File(nombreArchivo));
                this.documento.getDocumentElement().normalize();
            } catch (SAXException | IOException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Obtiene el nombre del elemento principal del documento.
         * 
         * @return Nombre del elemento principal
         */
        public String getElementPrincipal(){
            return this.documento.getDocumentElement().getNodeName();
        }

        /**
         * Obtiene el valor de un tag específico dentro de un elemento.
         * 
         * @param tag Nombre del tag
         * @param elemento Elemento que contiene el tag
         * @return Valor del tag
         */
        public String getTagValue(String tag, Element elemento) {
            NodeList nodeList = elemento.getElementsByTagName(tag);
            if (nodeList != null && nodeList.getLength() > 0) { // Compruebo que la lista no sea null y no esté vacía
                NodeList childNodes = nodeList.item(0).getChildNodes();
                if (childNodes != null && childNodes.getLength() > 0) {
                    Node node = childNodes.item(0);
                    if (node != null) {
                        return node.getNodeValue();
                    }
                }
            }
            return null;
        }
        
        /**
         * Obtiene un objeto Empleado a partir de un nodo.
         * 
         * @param nodo Nodo que contiene la información del empleado
         * @return Objeto Empleado
         */
        private Empleado getEmpleado(Node nodo){
            Empleado emple = new Empleado();
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                emple.setIdentificador(Long.parseLong(getTagValue("identificador", elemento)));
            }
            return emple;
        }

        /**
         * Obtiene una lista de empleados a partir del documento XML.
         * 
         * @return Lista de empleados
         */
        public List<Empleado> getEmpleados(){
            List<Empleado> empleList = new ArrayList<Empleado>();
            NodeList nodeList = this.documento.getElementsByTagName("Empleado");
            for(int i=0; i < nodeList.getLength(); i++){
                empleList.add(getEmpleado(nodeList.item(i)));
            }
            return empleList;
        }

        /**
         * Elimina un elemento del documento XML.
         * 
         * @param nombreElemento Elemento de empleado a eliminar
         */
        public void eliminarElemento( String nombreElemento) {
            NodeList nodeList = this.documento.getElementsByTagName(nombreElemento);
            for (int i = 0; i < nodeList.getLength(); i++) {
                
                Element empleElemento = (Element) nodeList.item(i);
                
                NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
                
                if(elementos.getLength()>0){
                    Node elementoABorrar = elementos.item(0);
                    empleElemento.removeChild(elementoABorrar);
                }
            }
        }

        /**
         * Modifica el salario de un empleado en el documento XML.
         * 
         * @param identificador Identificador del empleado
         * @param nuevoSalario Nuevo salario del empleado
         */
        public void modificarSalarioEmpleado(String identificador, double nuevoSalario) {
            NodeList nodeList = this.documento.getElementsByTagName("Empleado");
            boolean encontrar=true; // Para salirme del bucle cuando encuentre el empleado que quiero
            int i = 0;
            while(encontrar && i < nodeList.getLength()){

                Element empleadoElement = (Element) nodeList.item(i);
                String idStr = getTagValue("identificador", empleadoElement);

                if (identificador.equals(idStr)) {
                    Element salarioElement = (Element) empleadoElement.getElementsByTagName("salario").item(0);
                    salarioElement.setTextContent(Double.toString(nuevoSalario));
                    encontrar=false;
                }
                i++;
            }
        }
        
        public Document crearDocumentoEmpleadosEnMemoria(List<Empleado> empleados) {
            // Crear el elemento raíz del documento XML
            Element raiz = documento.createElement("Empleados");
            documento.getDocumentElement().appendChild(raiz);

            // Para cada empleado en la lista, creamos un nodo en el XML
            for (Empleado empleado : empleados) {
                // Crear un elemento "Empleado"
                Element elementoEmpleado = documento.createElement("Empleado");

                // Crear subelementos para los atributos de cada empleado
                Element id = documento.createElement("Identificador");
                id.setTextContent(String.valueOf(empleado.getIdentificador()));
                elementoEmpleado.appendChild(id);

                Element apellido = documento.createElement("Apellido");
                apellido.setTextContent(empleado.getApellido());
                elementoEmpleado.appendChild(apellido);

                Element departamento = documento.createElement("Departamento");
                departamento.setTextContent(String.valueOf(empleado.getDepartamento()));
                elementoEmpleado.appendChild(departamento);

                Element salario = documento.createElement("Salario");
                salario.setTextContent(String.valueOf(empleado.getSalario()));
                elementoEmpleado.appendChild(salario);

                // Agregar el empleado al elemento raíz
                raiz.appendChild(elementoEmpleado);
            }

            // Retornar el documento en memoria
            return documento;
        }
        
        
        
        
    public static void main (String[] args){
        // Para probar sin tocar el main
    }
        
}
